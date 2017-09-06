package com.example.android.newproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newproject.Interface.Communication;
import com.example.android.newproject.Models.CryptoCoin;
import com.example.android.newproject.R;

import java.util.List;

/**
 * Created by Martin on 30.8.2017 г..
 */

public class ListCoinsAdapter extends RecyclerView.Adapter<ListCoinsAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private List<CryptoCoin> coinList;
    private Communication communication;

    private String id;

    public ListCoinsAdapter(Context context, List<CryptoCoin> list) {
        mLayoutInflater = LayoutInflater.from(context);
        coinList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.fragment_coin_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CryptoCoin cryptoCoin = coinList.get(position);

        TextView coinRank = holder.mRank;
        TextView coinName = holder.mName;
        TextView coinPrice = holder.mPrice;
        TextView percentChange = holder.mPercentChange;
        ImageView coinIcon = holder.mIcon;


        id = cryptoCoin.getcId();

        coinRank.setText(cryptoCoin.getcRank());
        coinName.setText(cryptoCoin.getcName());
        coinPrice.setText(cryptoCoin.getcPrice());
        percentChange.setText(cryptoCoin.getcPercentChange());
        coinIcon.setImageDrawable(cryptoCoin.getcIcon());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communication.Communication(id);
                Log.v("ListCoinAdapt Listener", id);
            }
        });

    }

    public void setSelectedInterface(Communication interfaceCommunication){
        communication = interfaceCommunication;
    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    public void addAll(List<CryptoCoin> coins) {
        coinList.clear();
        coinList.addAll(coins);
        notifyDataSetChanged();
    }

    public void clearAll() {
        coinList.clear();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        // Views
        private TextView mRank;
        private ImageView mIcon;
        private TextView mName;
        private TextView mPrice;
        private TextView mPercentChange;

        public ViewHolder(View inflate) {
            super(inflate);

            mRank = (TextView) itemView.findViewById(R.id.rankTextView);
            mIcon = (ImageView) itemView.findViewById(R.id.iconImageView);
            mName = (TextView) itemView.findViewById(R.id.nameTextView);
            mPrice = (TextView) itemView.findViewById(R.id.priceTextView);
            mPercentChange = (TextView) itemView.findViewById(R.id.percentChangeTextView);
        }
    }



}


