package com.example.android.newproject.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.android.newproject.Adapters.ListCoinsAdapter;
import com.example.android.newproject.Loaders.CoinLoader;
import com.example.android.newproject.Models.CryptoCoin;
import com.example.android.newproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 30.8.2017 г..
 */

public class ListCoinsFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<CryptoCoin>> {

    private static final int COIN_LOADER_ID = 1;
    private static final String INITIAL_QUERY = "https://api.coinmarketcap.com/v1/ticker/";
    private static LoaderManager loaderManager;
    private ListCoinsAdapter listCoinsAdapter;

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

    Communication communication;


    public static ListCoinsFragment newInstance() {
        return new ListCoinsFragment();
    }

    @Override
    public void onAttach(Context activity) {
        // The Context here obviously is the MainActivity !!! Focus, dude !
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            communication = (Communication) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement Communication Interface");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_coin_list, container, false);

        emptyState = (TextView) view.findViewById(R.id.empty_state_text);
        progressBar = (ProgressBar) view.findViewById(R.id.progres_bar);

        // Initialize the refresh layout and assign refresh listener to it
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerViewRefresh(view);
            }
        });

        // Connectivity manager to check state of network connectivity
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        networkInfo = connectivityManager.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Notify the user that fetching data is in progress
            emptyState.setText("Loading...");
            // Helper method that initialize Loader and News Adapter
            initLoaderAndAdapter(view);
        } else {
            // If there is no internet connection
            // Hide the progress bar
            progressBar.setVisibility(View.GONE);
            // and inform the user
            emptyState.setText("No Internet Connection");
        }

        Log.v("List","onCreateView?");

        return view;
    }

    private void recyclerViewRefresh(View v) {
        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            // Notify the user that the Layout is being refreshed
            emptyState.setText("Refreshing...");
            // and show the progress bar
            progressBar.setVisibility(View.VISIBLE);

            // Check if newsAdapter is not null (which will happen if on launch there was no
            // connection)
            if (listCoinsAdapter != null) {
                // Clear the adapter
                listCoinsAdapter.clearAll();
            }
            if (loaderManager != null) {
                // Restart Loader
                loaderManager.restartLoader(COIN_LOADER_ID, null, this);
                // Inform SwipeRefreshLayout that loading is complete so it can hide its progress bar
                swipeRefreshLayout.setRefreshing(false);
            } else {
                initLoaderAndAdapter(v);
                // Inform SwipeRefreshLayout that loading is complete so it can hide its progress bar
                swipeRefreshLayout.setRefreshing(false);
            }
        } else {
            // Hide progress bar
            progressBar.setVisibility(View.GONE);

            // Check if newsAdapter is not null (which will happen if on launch there was no
            // connection)
            if (listCoinsAdapter != null) {
                // Clear the adapter
                listCoinsAdapter.clearAll();
            }
            // Display error
            emptyState.setText("No Internet Connection");
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    private void initLoaderAndAdapter(View v) {



        Activity activity = getActivity();
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        listCoinsAdapter = new ListCoinsAdapter(activity, new ArrayList<CryptoCoin>());
        listCoinsAdapter.setCommunicationListener(communication);

        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(listCoinsAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

        loaderManager = getActivity().getSupportLoaderManager();
        loaderManager.initLoader(COIN_LOADER_ID, null, this);
    }


    @Override
    public Loader<List<CryptoCoin>> onCreateLoader(int id, Bundle args) {
        return new CoinLoader(getContext(), INITIAL_QUERY);
    }

    @Override
    public void onLoadFinished(Loader<List<CryptoCoin>> loader, List<CryptoCoin> coinList) {
        if (coinList != null && !coinList.isEmpty()) {
            listCoinsAdapter.addAll(coinList);
            // Hide loading indicator because the data has been loaded
            progressBar.setVisibility(View.GONE);
            // Hide message text
            emptyState.setText(null);
        } else {
            // Hide loading indicator because the data has been loaded
            progressBar.setVisibility(View.GONE);
            // Set message text to display "No articles found!"
            emptyState.setText("No cryptocurrency found");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<CryptoCoin>> loader) {
        listCoinsAdapter.clearAll();
    }

    public interface Communication {
        void onCommunicationClick(String s);
    }


}

