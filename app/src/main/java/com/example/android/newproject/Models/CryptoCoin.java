package com.example.android.newproject.Models;

import android.graphics.drawable.Drawable;

/**
 * Created by Martin on 31.8.2017 г..
 */

public class CryptoCoin {

    private String cRank;
    private Drawable cIcon;
    private String cName;
    private String cPrice;
    private String cPercentChange;
    private String cId;



    public CryptoCoin(String rank, Drawable icon, String name, String price, String percentChange, String id) {
        cRank = rank;
        cIcon = icon;
        cName = name;
        cPrice = price;
        cPercentChange = percentChange;
        cId = id;

    }


    public String getcRank() {
        return cRank;
    }

    public void setcRank(String cRank) {
        this.cRank = cRank;
    }

    public Drawable getcIcon() {
        return cIcon;
    }

    public void setcIcon(Drawable cIcon) {
        this.cIcon = cIcon;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcPrice() {
        return cPrice;
    }

    public void setcPrice(String cPrice) {
        this.cPrice = cPrice;
    }

    public String getcPercentChange() {
        return cPercentChange;
    }

    public void setcPercentChange(String cPercentChange) {
        this.cPercentChange = cPercentChange;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }
}