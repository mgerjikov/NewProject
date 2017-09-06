package com.example.android.newproject.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.newproject.Models.CryptoCoin;
import com.example.android.newproject.Utils.CryptoCoinUtils;

import java.util.List;

/**
 * Created by Martin on 31.8.2017 г..
 */

public class CoinLoader extends AsyncTaskLoader<List<CryptoCoin>> {

    private String mUrl;

    public CoinLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<CryptoCoin> loadInBackground() {
        if (mUrl == null){
            return null;
        }

        List<CryptoCoin> coinList = CryptoCoinUtils.fetchCoinData(mUrl);
        return coinList;
    }
}