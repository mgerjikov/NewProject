package com.example.android.newproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.newproject.Models.DetailedCryptoCoin;
import com.example.android.newproject.R;

import java.util.List;

/**
 * Created by Martin on 31.8.2017 г..
 */

public class DetailedCoinAdapter extends RecyclerView.Adapter<DetailedCoinAdapter.ViewHolder> {



    private LayoutInflater mLayoutInflater;
    private List<DetailedCryptoCoin> detailedCryptoCoinList;
    private Context mContext;

    public DetailedCoinAdapter(Context context, List<DetailedCryptoCoin> detailedCryptoCoins) {
        mContext = context;
        detailedCryptoCoinList = detailedCryptoCoins;
    }

    @Override
    public DetailedCoinAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(mLayoutInflater.inflate(R.layout.fragment_coin_detail,parent,false));

        Context context = parent.getContext();

        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.fragment_coin_detail, parent, false);

        ViewHolder viewholder = new ViewHolder(mContext, view);

        return viewholder;


    }

    @Override
    public void onBindViewHolder(DetailedCoinAdapter.ViewHolder holder, int position) {

        DetailedCryptoCoin detailedCryptoCoin = detailedCryptoCoinList.get(position);


        TextView detailRank = holder.dRank;
        TextView detailName = holder.dName;
        TextView detailSymbol = holder.dSymbol;
        TextView detailPriceUSD = holder.dPriceUSD;
        TextView detailPriceBTC = holder.dPriceBTC;
        TextView detailMarketCap = holder.dMarketCap;
        TextView detailAvailableSupply = holder.dAvailableSupply;
        TextView detailTotalSupply = holder.dTotalSupply;
        TextView detailChange1h = holder.dChange1h;
        TextView detailChange24h = holder.dChange24h;
        TextView detailChange7d = holder.dChange7d;
        ImageView detailPinIcon = holder.dPinIcon;
        ImageView detailCoinIcon = holder.dCoinIcon;

        detailRank.setText(detailedCryptoCoin.getRank());
        detailName.setText(detailedCryptoCoin.getName());
        detailSymbol.setText(detailedCryptoCoin.getSymbol());
        detailPriceUSD.setText(detailedCryptoCoin.getPrice_usd());
        detailPriceBTC.setText(detailedCryptoCoin.getPrice_btc());
        detailMarketCap.setText(detailedCryptoCoin.getMarket_cap_usd());
        detailAvailableSupply.setText(detailedCryptoCoin.getAvailable_supply());
        detailTotalSupply.setText(detailedCryptoCoin.getTotal_supply());
        detailChange1h.setText(detailedCryptoCoin.getPercent_change_1h());
        detailChange24h.setText(detailedCryptoCoin.getPercent_change_24h());
        detailChange7d.setText(detailedCryptoCoin.getPercent_change_7d());
    }

    @Override
    public int getItemCount() {
        return detailedCryptoCoinList.size();
    }

    public void addAll(List<DetailedCryptoCoin> detailedCryptoCoins){
        detailedCryptoCoinList.clear();
        detailedCryptoCoinList.addAll(detailedCryptoCoins);
        notifyDataSetChanged();
    }

    public void clearAll(){
        detailedCryptoCoinList.clear();
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        // Views
        private TextView dRank;
        private TextView dName;
        private TextView dSymbol;
        private TextView dPriceUSD;
        private TextView dPriceBTC;
        private TextView dMarketCap;
        private TextView dAvailableSupply;
        private TextView dTotalSupply;
        private TextView dChange1h;
        private TextView dChange24h;
        private TextView dChange7d;
        private ImageView dPinIcon;
        private ImageView dCoinIcon;


        public ViewHolder(Context context ,View inflate) {
            super(inflate);
            mContext = context;

            dRank = (TextView) inflate.findViewById(R.id.d_rank);
            dName = (TextView) inflate.findViewById(R.id.d_name);
            dSymbol = (TextView) inflate.findViewById(R.id.d_symbol);
            dPriceUSD = (TextView) inflate.findViewById(R.id.d_price_usd);
            dPriceBTC = (TextView) inflate.findViewById(R.id.d_price_btc);
            dMarketCap = (TextView) inflate.findViewById(R.id.d_market_cap);
            dAvailableSupply = (TextView) inflate.findViewById(R.id.d_available_supply);
            dTotalSupply = (TextView) inflate.findViewById(R.id.d_total_supply);
            dChange1h = (TextView) inflate.findViewById(R.id.d_percent_change_1h);
            dChange24h = (TextView) inflate.findViewById(R.id.d_percent_change_24h);
            dChange7d = (TextView) inflate.findViewById(R.id.d_percent_change_7d);

            dPinIcon = (ImageView) inflate.findViewById(R.id.d_pin_icon);
            dCoinIcon = (ImageView) inflate.findViewById(R.id.d_coin_icon);
        }
    }
}
