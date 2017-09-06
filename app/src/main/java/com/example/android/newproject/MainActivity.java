package com.example.android.newproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.newproject.Fragments.DetailCoinFragment;
import com.example.android.newproject.Fragments.ListCoinsFragment;
import com.example.android.newproject.Interface.Communication;

public class MainActivity extends AppCompatActivity implements Communication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.root_layout, ListCoinsFragment.newInstance(), "allCoinsList")
                    .commit();
        }


    }


    @Override
    public void Communication(String id) {

        DetailCoinFragment detailCoinFragment =
                DetailCoinFragment.newInstance();


        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        detailCoinFragment.setArguments(bundle);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.root_layout, detailCoinFragment, "detailedCoin")
                .addToBackStack(null)
                .commit();
    }

}

