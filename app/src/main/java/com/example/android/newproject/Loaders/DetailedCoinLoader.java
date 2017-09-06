package com.example.android.newproject.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.example.android.newproject.Models.DetailedCryptoCoin;
import com.example.android.newproject.Utils.DetailedCryptoCoinUtils;

import java.util.List;

/**
 * Created by Martin on 31.8.2017 г..
 */

public class DetailedCoinLoader extends AsyncTaskLoader<List<DetailedCryptoCoin>> {
    private String mUrl;

    public DetailedCoinLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<DetailedCryptoCoin> loadInBackground() {
        if (mUrl == null){
            return  null;
        }

        List<DetailedCryptoCoin> cryptoCoin = DetailedCryptoCoinUtils.fetchDetailedData(mUrl);
        return cryptoCoin;
    }
}