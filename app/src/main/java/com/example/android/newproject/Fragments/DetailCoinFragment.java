package com.example.android.newproject.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.newproject.Adapters.DetailedCoinAdapter;
import com.example.android.newproject.Loaders.DetailedCoinLoader;
import com.example.android.newproject.Models.DetailedCryptoCoin;
import com.example.android.newproject.R;

import java.util.ArrayList;
import java.util.List;

public class DetailCoinFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<DetailedCryptoCoin>> {

    public final static String ARGUMENT_ID = "id";

    private static final int COIN_DETAIL_LOADER_ID = 101;
    private static final String INITIAL_QUERY = "https://api.coinmarketcap.com/v1/ticker/";

    private static LoaderManager loaderManager;

    private DetailedCoinAdapter detailedCoinAdapter;
    String searchQuery;


    /**
     * TextView displaying messages to the user
     */
    TextView emptyState;
    /**
     * Progress bar displayed to user
     */
    ProgressBar progressBar;
    /**
     * Refresh Layout
     */
    SwipeRefreshLayout swipeRefreshLayout;
    /**
     * Network info to check for internet connection
     */
    NetworkInfo networkInfo;

    View view;

//    public DetailCoinFragment newInstance(String id) {
//
//        Bundle args = new Bundle();
//        args.putString(ARGUMENT_ID,id);
//
//        DetailCoinFragment fragment = new DetailCoinFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    public DetailCoinFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        if (bundle != null){
//            searchQuery = bundle.getString(ARGUMENT_ID);
//        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_coin_list,container,false);

        emptyState = (TextView) view.findViewById(R.id.empty_state_text);
        progressBar = (ProgressBar) view.findViewById(R.id.progres_bar);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewRefresh(view);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){

            emptyState.setText("Loading...");

            initLoaderAndAdapter(view);
        } else {

            progressBar.setVisibility(View.GONE);

            emptyState.setText("No Inet Connection");
        }

        return view;
    }

    private void recyclerViewRefresh(View v) {

        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()){

            emptyState.setText("Refreshing...");

            progressBar.setVisibility(View.GONE);

            if (detailedCoinAdapter != null){
                detailedCoinAdapter.clearAll();
            }
            if (loaderManager != null){

                loaderManager.restartLoader(COIN_DETAIL_LOADER_ID, null,this);

                swipeRefreshLayout.setRefreshing(false);

            } else {

                initLoaderAndAdapter(v);

                swipeRefreshLayout.setRefreshing(false);

            }

        } else {

            progressBar.setVisibility(View.GONE);

            if (detailedCoinAdapter != null){

                detailedCoinAdapter.clearAll();
            }

            emptyState.setText("No Connection");
            swipeRefreshLayout.setRefreshing(false);

        }

    }

    private void initLoaderAndAdapter(View v) {


        Activity activity = getActivity();

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        detailedCoinAdapter = new DetailedCoinAdapter(activity, new ArrayList<DetailedCryptoCoin>());

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(detailedCoinAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        loaderManager = getActivity().getSupportLoaderManager();

        Bundle bundle = this.getArguments();

//        if (bundle != null){
//            Log.v("DCFragment", "bundle ne e null");
//            loaderManager.initLoader(COIN_DETAIL_LOADER_ID,bundle,this);
//        } else {
//            Log.v("DCFragment" , "bundle e null");
//            loaderManager.initLoader(COIN_DETAIL_LOADER_ID,null,this);
//        }


        loaderManager.initLoader(COIN_DETAIL_LOADER_ID,null,this);

    }


    @Override
    public Loader<List<DetailedCryptoCoin>> onCreateLoader(int id, Bundle args) {

        Bundle bundle = this.getArguments();

        searchQuery = bundle.getString(ARGUMENT_ID);

        Uri baseUri = Uri.parse(INITIAL_QUERY);

        Uri.Builder builder = baseUri.buildUpon();

        builder.appendPath(searchQuery);
        Log.v("DetailCoinFragment" , "Uri: " + builder);
        Log.v("DetailCoinFragment" , "Uri: " + builder.toString());

        return new DetailedCoinLoader(getContext(), builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<DetailedCryptoCoin>> loader, List<DetailedCryptoCoin> data) {
        if (data != null && !data.isEmpty()){
            detailedCoinAdapter.addAll(data);

            progressBar.setVisibility(View.GONE);

            emptyState.setText(null);
        } else {
//            Toast.makeText(getContext(),"DetailCoinFragment.java , onLoaderFinished - here is the problem", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);

            emptyState.setText("Nothing found");

        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        detailedCoinAdapter.clearAll();
    }
}
