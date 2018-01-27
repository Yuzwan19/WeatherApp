package com.izcax.myapplication.model.db;

/**
 * Created by Izcax on 1/26/18.
 */

public class ForecastDb {
    private String date, tempMax, tempMin, desc, imgUrl;
    private int id;

    public ForecastDb() {
    }

    public ForecastDb(String date, String tempMax, String tempMin, String desc, String imgUrl) {
        this.date = date;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.desc = desc;
        this.imgUrl = imgUrl;
    }


    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
