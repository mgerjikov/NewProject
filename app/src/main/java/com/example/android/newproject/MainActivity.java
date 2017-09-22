package com.example.android.newproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.newproject.Fragments.DetailCoinFragment;
import com.example.android.newproject.Fragments.ListCoinsFragment;

public class MainActivity extends AppCompatActivity implements ListCoinsFragment.Communication {

    private ListCoinsFragment listCoinsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v("Main","onCreate in MainActivity");

        listCoinsFragment = ListCoinsFragment.newInstance();

        if (savedInstanceState == null) {
            Log.v("Main","onCreate if statement");
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_layout, listCoinsFragment)
                    .addToBackStack(null)
                    .commit();
            Log.v("Main","onCreate after commit");
        }


    }

    @Override
    public void onCommunicationClick(String id) {


        DetailCoinFragment detailCoinFragment =
                new DetailCoinFragment();

        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        detailCoinFragment.setArguments(bundle);

        Log.v("Main", "ID ---- > " + id);
        Log.v("Main", bundle.toString());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, detailCoinFragment)
                .addToBackStack(null)
                .commit();
    }
}

