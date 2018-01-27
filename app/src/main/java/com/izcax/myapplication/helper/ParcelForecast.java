package com.izcax.myapplication.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Izcax on 1/21/18.
 */

public class ParcelForecast implements Parcelable {
    private String date, tempMax, tempMin, desc, icon, wind, humidity, degree;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeString(this.tempMax);
        dest.writeString(this.tempMin);
        dest.writeString(this.desc);
        dest.writeString(this.icon);
        dest.writeString(this.wind);
        dest.writeString(this.humidity);
        dest.writeString(this.degree);
    }

    public ParcelForecast() {
    }

    protected ParcelForecast(Parcel in) {
        this.date = in.readString();
        this.tempMax = in.readString();
        this.tempMin = in.readString();
        this.desc = in.readString();
        this.icon = in.readString();
        this.wind = in.readString();
        this.humidity = in.readString();
        this.degree = in.readString();
    }

    public static final Parcelable.Creator<ParcelForecast> CREATOR = new Parcelable.Creator<ParcelForecast>() {
        @Override
        public ParcelForecast createFromParcel(Parcel source) {
            return new ParcelForecast(source);
        }

        @Override
        public ParcelForecast[] newArray(int size) {
            return new ParcelForecast[size];
        }
    };
}
