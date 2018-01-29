package com.izcax.myapplication.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.izcax.myapplication.BuildConfig;
import com.izcax.myapplication.R;
import com.izcax.myapplication.adapter.ForecastAdapter;
import com.izcax.myapplication.database.ForecastHelper;
import com.izcax.myapplication.helper.Constant;
import com.izcax.myapplication.helper.ItemClickView;
import com.izcax.myapplication.model.ForecastParcel;
import com.izcax.myapplication.model.db.ForecastDb;
import com.izcax.myapplication.model.forecast.Forecast;
import com.izcax.myapplication.model.forecast.List;
import com.izcax.myapplication.rest.ApiClient;
import com.izcax.myapplication.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {

    ForecastHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getForecastData(BuildConfig.OPEN_WEATHER_MAP_API_KEY);
        db = new ForecastHelper(SplashScreen.this);

    }

    private void getForcastDATA(String ApiKey) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Forecast> call = apiInterface.getForecast("jakarta", "metric", ApiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {
                    final java.util.List<List> list = response.body().getList();
                    for (int i = 0; i < list.size(); i++) {
                        db.addForecast(new ForecastDb(
                                Constant.getDate(list.get(i).getDt()),
                                String.valueOf(list.get(i).getMain().getTempMax()),
                                String.valueOf(list.get(i).getMain().getTempMin()),
                                list.get(i).getWeather().get(0).getDescription(),
                                String.format("http://openweathermap.org/img/w/%s.png",
                                        list.get(i).getWeather().get(0).getIcon())
                        ));
                    }

                    startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    finish();

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("DB Forecast", "onFailure: ", t);
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();

            }
        });
    }


    private void getForecastData(String ApiKey) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Forecast> call = apiInterface.getForecast("jakarta", "metric", ApiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {
                    Log.d("onResponse: ", new Gson().toJson(response));
                    final java.util.List<List> list =
                            response.body().getList();

                    for (int i = 0; i < list.size(); i++) {
                        db.addForecast(new ForecastDb(
                                Constant.getDate(list.get(i).getDt()),
                                String.valueOf(list.get(i).getMain().getTempMax()),
                                String.valueOf(list.get(i).getMain().getTempMin()),
                                list.get(i).getWeather().get(0).getDescription(),
                                String.format("http://openweathermap.org/img/w/%s.png",
                                        list.get(i).getWeather().get(0).getIcon())
                        ));
                    }

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();

                } catch (NullPointerException e) {
                    Log.e("onResponse: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }
}
