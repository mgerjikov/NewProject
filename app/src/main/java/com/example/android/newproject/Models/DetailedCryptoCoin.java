package com.example.android.newproject.Models;

/**
 * Created by Martin on 31.8.2017 г..
 */

public class DetailedCryptoCoin
{
    public String rank;
    public String name;
    public String symbol;
    public String price_usd ;
    public String price_btc ;
    public String market_cap_usd ;
    public String available_supply ;
    public String total_supply ;
    public String percent_change_1h ;
    public String percent_change_24h ;
    public String percent_change_7d ;

    public DetailedCryptoCoin(String rank, String name, String symbol, String price_usd, String price_btc, String market_cap_usd, String available_supply, String total_supply, String percent_change_1h, String percent_change_24h, String percent_change_7d) {
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.price_usd = price_usd;
        this.price_btc = price_btc;
        this.market_cap_usd = market_cap_usd;
        this.available_supply = available_supply;
        this.total_supply = total_supply;
        this.percent_change_1h = percent_change_1h;
        this.percent_change_24h = percent_change_24h;
        this.percent_change_7d = percent_change_7d;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice_usd() {
        return price_usd;
    }

    public void setPrice_usd(String price_usd) {
        this.price_usd = price_usd;
    }

    public String getPrice_btc() {
        return price_btc;
    }

    public void setPrice_btc(String price_btc) {
        this.price_btc = price_btc;
    }

    public String getMarket_cap_usd() {
        return market_cap_usd;
    }

    public void setMarket_cap_usd(String market_cap_usd) {
        this.market_cap_usd = market_cap_usd;
    }

    public String getAvailable_supply() {
        return available_supply;
    }

    public void setAvailable_supply(String available_supply) {
        this.available_supply = available_supply;
    }

    public String getTotal_supply() {
        return total_supply;
    }

    public void setTotal_supply(String total_supply) {
        this.total_supply = total_supply;
    }

    public String getPercent_change_1h() {
        return percent_change_1h;
    }

    public void setPercent_change_1h(String percent_change_1h) {
        this.percent_change_1h = percent_change_1h;
    }

    public String getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(String percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public String getPercent_change_7d() {
        return percent_change_7d;
    }

    public void setPercent_change_7d(String percent_change_7d) {
        this.percent_change_7d = percent_change_7d;
    }
}