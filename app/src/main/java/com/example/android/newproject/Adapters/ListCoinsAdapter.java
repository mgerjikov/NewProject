package com.example.android.newproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.newproject.Fragments.ListCoinsFragment.Communication;
import com.example.android.newproject.Models.CryptoCoin;
import com.example.android.newproject.R;

import java.util.List;

/**
 * Created by Martin on 30.8.2017 г..
 */

public class ListCoinsAdapter extends RecyclerView.Adapter<ListCoinsAdapter.ViewHolder> {


    private LayoutInflater mLayoutInflater;
    private List<CryptoCoin> coinList;
    private Context mContext;
    private String id;

    private Communication comFromFragment;

    public ListCoinsAdapter(Context context, List<CryptoCoin> list) {
        mContext = context;
        coinList = list;
    }

    @Override
    public ListCoinsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(mLayoutInflater.inflate(R.layout.fragment_coin_list_item, parent, false));
        Context context = parent.getContext();
        mLayoutInflater = LayoutInflater.from(context);

        View view = mLayoutInflater.inflate(R.layout.fragment_coin_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(mContext, view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder( ListCoinsAdapter.ViewHolder holder, int position) {
        CryptoCoin cryptoCoin = coinList.get(position);

        TextView coinRank = holder.mRank;
        TextView coinName = holder.mName;
        TextView coinPrice = holder.mPrice;
        TextView percentChange = holder.mPercentChange;
        ImageView coinIcon = holder.mIcon;


//        id = cryptoCoin.getcId();

        coinRank.setText(cryptoCoin.getcRank());
        coinName.setText(cryptoCoin.getcName());
        coinPrice.setText(cryptoCoin.getcPrice());
        percentChange.setText(cryptoCoin.getcPercentChange());
        coinIcon.setImageDrawable(cryptoCoin.getcIcon());

    }

    public void setCommunicationListener(Communication communicationListener){
        comFromFragment = communicationListener;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Context theContext;

        // Views
        public TextView mRank;
        public ImageView mIcon;
        public TextView mName;
        public TextView mPrice;
        public TextView mPercentChange;

        public ViewHolder(Context context, View view) {
            super(view);
            this.theContext = context;

            view.setOnClickListener(this);

            mRank = (TextView) view.findViewById(R.id.rankTextView);
            mIcon = (ImageView) view.findViewById(R.id.iconImageView);
            mName = (TextView) view.findViewById(R.id.nameTextView);
            mPrice = (TextView) view.findViewById(R.id.priceTextView);
            mPercentChange = (TextView) view.findViewById(R.id.percentChangeTextView);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            CryptoCoin coin = coinList.get(position);

            id = coin.getcId();

            comFromFragment.onCommunicationClick(id);


//            DetailCoinFragment fragment = DetailCoinFragment.newInstance(id);



//            Bundle bundle = new Bundle();
//            bundle.putString("id", id);
//            fragment.setArguments(bundle);

//            Uri uri = Uri.parse(id);
//            Intent webIntent = new Intent(Intent.ACTION_VIEW, uri);
//
//            theContext.startActivity(webIntent);


            Log.v("Adapter",id);
            Toast.makeText(theContext,id,Toast.LENGTH_SHORT).show();

        }
    }



}


