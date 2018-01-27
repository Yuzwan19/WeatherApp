package com.izcax.myapplication.rest;

import com.izcax.myapplication.model.Weather;
import com.izcax.myapplication.model.forecast.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Izcax on 12/3/17.
 */

public interface ApiInterface {
    @GET("weather")
    Call<Weather> getWeather(@Query("q") String kota,
                              @Query("units") String satuan,
                              @Query("appid") String api_key
                              );


    @GET("forecast")
    Call<Forecast> getForecast(@Query("q") String kota,
                               @Query("units") String satuan,
                               @Query("appid") String api_key
    );

}
