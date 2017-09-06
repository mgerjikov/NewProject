package com.example.android.newproject.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.android.newproject.Adapters.DetailedCoinAdapter;
import com.example.android.newproject.Loaders.DetailedCoinLoader;
import com.example.android.newproject.Models.DetailedCryptoCoin;
import com.example.android.newproject.R;

import java.util.ArrayList;
import java.util.List;

public class DetailCoinFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<DetailedCryptoCoin>> {

    private static final String ARGUMENT_ID = "id";

    private static final int COIN_DETAIL_LOADER_ID = 1;
    private static final String INITIAL_QUERY = "https://api.coinmarketcap.com/v1/ticker/";
    private DetailedCoinAdapter detailedCoinAdapter;
    String searchQuery;

    public static DetailCoinFragment newInstance() {

//        Bundle args = new Bundle();
//        args.putString(ARGUMENT_ID,id);

        DetailCoinFragment fragment = new DetailCoinFragment();
//        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_coin_list,container,false);

        LoaderManager loaderManager = getActivity().getSupportLoaderManager();
        loaderManager.initLoader(COIN_DETAIL_LOADER_ID,null,this);

        Activity activity = getActivity();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        detailedCoinAdapter = new DetailedCoinAdapter(activity, new ArrayList<DetailedCryptoCoin>());
        recyclerView.setAdapter(detailedCoinAdapter);
        return view;
    }

    @Override
    public Loader onCreateLoader(int id, Bundle args) {

        searchQuery = args.getString(ARGUMENT_ID);

        Uri baseUri = Uri.parse(INITIAL_QUERY);

        Uri.Builder builder = baseUri.buildUpon();

        builder.appendQueryParameter("id",searchQuery);
        Log.v("DetailCoinFragment" , "Uri: " + builder);
        Log.v("DetailCoinFragment" , "Uri: " + builder.toString());

        return new DetailedCoinLoader(getContext(), builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<DetailedCryptoCoin>> loader, List<DetailedCryptoCoin> data) {
        if (data != null && !data.isEmpty()){
            detailedCoinAdapter.addAll(data);
        } else {
            Toast.makeText(getContext(),"DetailCoinFragment.java , onLoadeFinished - here is the problem", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        detailedCoinAdapter.clearAll();
    }
}
