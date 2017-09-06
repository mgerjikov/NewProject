package com.example.android.newproject.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.newproject.Adapters.ListCoinsAdapter;
import com.example.android.newproject.Interface.Communication;
import com.example.android.newproject.Loaders.CoinLoader;
import com.example.android.newproject.Models.CryptoCoin;
import com.example.android.newproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 30.8.2017 г..
 */

public class ListCoinsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<CryptoCoin>>
         {

    private static final int COIN_LOADER_ID = 1;
    private static final String INITIAL_QUERY = "https://api.coinmarketcap.com/v1/ticker/";
    private static LoaderManager loaderManager;
    private Communication coinSelectedInterface;
    private ListCoinsAdapter listCoinsAdapter;


    public static ListCoinsFragment newInstance() {
        return new ListCoinsFragment();
    }

    @Override
    public void onAttach(Context context) {
        // The Context here obviously is the MainActivity !!! Focus, dude !
        super.onAttach(context);

        if (context instanceof Communication) {
            coinSelectedInterface = (Communication) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement Interface.");
        }


        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
//        try {
//            coinSelectedInterface = (CoinSelectedInterface) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " must implement CoinSelectedInterface");
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coin_list, container, false);

        loaderManager = getActivity().getSupportLoaderManager();
        loaderManager.initLoader(COIN_LOADER_ID, null, this);

        Activity activity = getActivity();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        listCoinsAdapter = new ListCoinsAdapter(activity, new ArrayList<CryptoCoin>());
        listCoinsAdapter.setSelectedInterface(this.coinSelectedInterface);
        recyclerView.setAdapter(listCoinsAdapter);

        return view;
    }

    @Override
    public Loader<List<CryptoCoin>> onCreateLoader(int id, Bundle args) {
        return new CoinLoader(getContext(), INITIAL_QUERY.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<CryptoCoin>> loader, List<CryptoCoin> coinList) {
        if (coinList != null && !coinList.isEmpty()) {
            listCoinsAdapter.addAll(coinList);
        } else {
            // do nothing for now
        }
    }

    @Override
    public void onLoaderReset(Loader<List<CryptoCoin>> loader) {
        listCoinsAdapter.clearAll();
    }






}

