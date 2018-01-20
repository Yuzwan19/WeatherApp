package com.izcax.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Izcax on 1/9/18.
 */

public class ForecastParcel implements Parcelable {
    private String date;
    private String tempMax;
    private String tempMin;
    private String desc;
    private String icon;
    private String wind;
    private String humidity;
    private String degree;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
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

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    private String pressure;

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


    public ForecastParcel() {
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
        dest.writeString(this.pressure);
    }

    protected ForecastParcel(Parcel in) {
        this.date = in.readString();
        this.tempMax = in.readString();
        this.tempMin = in.readString();
        this.desc = in.readString();
        this.icon = in.readString();
        this.wind = in.readString();
        this.humidity = in.readString();
        this.degree = in.readString();
        this.pressure = in.readString();
    }

    public static final Creator<ForecastParcel> CREATOR = new Creator<ForecastParcel>() {
        @Override
        public ForecastParcel createFromParcel(Parcel source) {
            return new ForecastParcel(source);
        }

        @Override
        public ForecastParcel[] newArray(int size) {
            return new ForecastParcel[size];
        }
    };
}
