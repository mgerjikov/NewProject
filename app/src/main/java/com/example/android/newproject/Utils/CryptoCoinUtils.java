package com.example.android.newproject.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.newproject.Models.CryptoCoin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 31.8.2017 г..
 */


public class CryptoCoinUtils {

    private static final String LOG_TAG = CryptoCoinUtils.class.getSimpleName();

    private static final String RANK = "rank";
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String SYMBOL = "symbol";
    private static final String PRICE_USD = "price_usd";
    private static final String PRICE_EUR = "price_eur";
    private static final String PRICE_GBP = "price_gbp";
    private static final String PRICE_BTC = "price_btc";
    private static final String PERCENT_CHANGE_24h = "percent_change_24h";
    private static final String TOTAL_MARKET_CAP_USD = "total_market_cap_usd";
    private static final String TOTAL_MARKET_CAP_EUR = "total_market_cap_eur";
    private static final String TOTAL_MARKET_CAP_GBP = "total_market_cap_gbp";
    private static final String TOTAL_MARKET_CAP_BTC = "total_market_cap_btc";


    private CryptoCoinUtils() {}

    public static List<CryptoCoin> fetchCoinData (String requestUrl){

        URL url = createURL (requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHTTPRequest(url);
        } catch (IOException e){
            Log.e(LOG_TAG,"Error closing input stream." , e);
        }

        List<CryptoCoin> cryptoCoinList = extractCoinData(jsonResponse);

        return  cryptoCoinList;

    }

    private static List<CryptoCoin> extractCoinData(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)){
            return null;
        }

        List<CryptoCoin> coinList = new ArrayList<>();


        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);

            String rank;
            String name;
            String id;
            String symbol;
            String price;
            String percentChange;


            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jObj = new JSONObject();
                jObj = jsonArray.getJSONObject(i);

                rank = jObj.getString(RANK);
                name = jObj.getString(NAME);
                symbol = jObj.getString(SYMBOL);
                price = jObj.getString(PRICE_USD);
                percentChange = jObj.getString(PERCENT_CHANGE_24h);
                id = jObj.getString(ID);

                CryptoCoin coinItem = new CryptoCoin(rank,null,name,price,percentChange, id);

                coinList.add(coinItem);
            }
        } catch (JSONException | NullPointerException e) {
            e.printStackTrace();
        }
        return coinList;
    }

    private static String makeHTTPRequest(URL url) throws IOException {
        String jsonResponse = null;
        if (url == null){
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3500);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromInputStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code " + urlConnection.getResponseCode());
            }
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the JSON results", e);
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (inputStream != null){
                inputStream.close();
            }
        }
        return  jsonResponse;


    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder streamOutput = new StringBuilder();
        if (inputStream != null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null){
                streamOutput.append(line);
                line = bufferedReader.readLine();
            }
        }
        return streamOutput.toString();


    }

    private static URL createURL(String requestUrl) {
        URL url = null;
        try {
            url = new URL(requestUrl);
        } catch (MalformedURLException e){
            Log.e(LOG_TAG, "Error creating URL", e);
        }
        return url;
    }


}
