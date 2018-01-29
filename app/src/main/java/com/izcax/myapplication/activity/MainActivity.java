package com.izcax.myapplication.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.izcax.myapplication.BuildConfig;
import com.izcax.myapplication.R;
import com.izcax.myapplication.adapter.ForecastAdapter;
import com.izcax.myapplication.adapter.ForecastAdapterDb;
import com.izcax.myapplication.database.ForecastHelper;
import com.izcax.myapplication.helper.Constant;
import com.izcax.myapplication.helper.ItemClickView;
import com.izcax.myapplication.model.ForecastParcel;
import com.izcax.myapplication.model.Weather;
import com.izcax.myapplication.model.db.ForecastDb;
import com.izcax.myapplication.model.forecast.Forecast;
import com.izcax.myapplication.rest.ApiClient;
import com.izcax.myapplication.rest.ApiInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvForecast;
    TextView tvTempMin, tvTempMax, tvDesc, tvDay;
    ImageView imgIcon;
    String API_KEY = "sajfsjkfbjewbfjka291023sas";
    ForecastHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();

        db = new ForecastHelper(MainActivity.this);
        
//        getForecastData(BuildConfig.OPEN_WEATHER_MAP_API_KEY);
        getForecastDataDb();
        getCurrentWeatherData(BuildConfig.OPEN_WEATHER_MAP_API_KEY);

    }
    
    private void setupView() {
        tvTempMin = findViewById(R.id.item_tempMin_main);
        tvTempMax = findViewById(R.id.item_tempMax_main);
        tvDesc = findViewById(R.id.item_desc_main);
        tvDay = findViewById(R.id.item_day_main);
        imgIcon = findViewById(R.id.item_icon_main);
        rvForecast = findViewById(R.id.rv_forecast);

    }

    private void getCurrentWeatherData(String ApiKey) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Weather> call = apiInterface.getWeather("jakarta", "metric", ApiKey);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                String temp = String.valueOf(response.body().getMain().getTemp());
//                Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
                String day = Constant.getDate(response.body().getDt());

                String tanggal = Constant.getDate(response.body().getDt());
                tvDay.setText(tanggal);

                tvDay.setText(response.body().getName() + "\n" + day);
                tvDesc.setText(response.body().getWeather().get(0).getMain());
//                tvTempMax.setText(getResources().getString(R.string.format_temperature1,
//                        response.body().getMain().getTempMax()));
//                tvTempMin.setText(getResources().getString(R.string.format_temperature1,
//                        Float.parseFloat(String.valueOf(response.body().getMain().getTempMin()))));

                tvTempMin.setText(String.valueOf(response.body().getMain().getTempMin()));
                tvTempMax.setText(String.valueOf(response.body().getMain().getTempMax()));

                String img_url = "http://openweathermap.org/img/w/" +
                        response.body().getWeather().get(0).getIcon()
                        + ".png";
                Picasso.with(MainActivity.this)
                        .load(img_url)
                        .placeholder(android.R.color.holo_blue_light)
                        .error(R.mipmap.ic_launcher)
                        .into(imgIcon);

            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                Toast.makeText(MainActivity.this, R.string.no_connection, Toast.LENGTH_SHORT).show();
                tvDay.setText(R.string.no_connection);

            }
        });
    }

    private void getDataForecast(String kota, String satuan, String ApiKey){

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Forecast> call = apiInterface.getForecast(kota, satuan, ApiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {
                    final List<com.izcax.myapplication.model.forecast.List> lists=
                            response.body().getList();
                    ForecastAdapter adapter = new ForecastAdapter(MainActivity.this);
                    adapter.setListForecast(lists);
                    rvForecast.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvForecast.setAdapter(adapter);

                }catch (NullPointerException e){
                    Log.e("Forecast", "onResponse: ", e);
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("ErrorForecast", "onFailure: ", t);

            }
        });

    }

    private void getForecastDataDb(){
        List<ForecastDb> list = db.getAllForecast();
        ForecastAdapterDb adapter = new ForecastAdapterDb(
                MainActivity.this);
        adapter.setListForecast(list);
        rvForecast.setLayoutManager(new LinearLayoutManager(
                MainActivity.this));
        rvForecast.setAdapter(adapter);
    }

    private void getForecastData(String ApiKey) {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setMessage("Get Current Weather");
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Forecast> call = apiInterface.getForecast("jakarta", "metric", ApiKey);
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                try {
                    Log.d("onResponse: ", new Gson().toJson(response));
                    final List<com.izcax.myapplication.model.forecast.List> list =
                            response.body().getList();
                    ForecastAdapter adapter = new ForecastAdapter(MainActivity.this);
                    adapter.setListForecast(list);
                    rvForecast.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvForecast.setAdapter(adapter);
                    ItemClickView.addTo(rvForecast).setOnItemClickListener(new ItemClickView.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            ForecastParcel parcel = new ForecastParcel();
                            parcel.setDate(Constant.getDate(list.get(position).getDt()));
                            parcel.setDesc(list.get(position).getWeather().get(0).getDescription());
                            parcel.setTempMin(String.valueOf(list.get(position).getMain().getTempMin()));
                            parcel.setTempMax(String.valueOf(list.get(position).getMain().getTempMax()));
                            parcel.setWind(String.valueOf(list.get(position).getWind().getSpeed()));
                            parcel.setHumidity(String.valueOf(list.get(position).getMain().getHumidity()));
                            parcel.setPressure(String.valueOf(list.get(position).getMain().getPressure()));
                            parcel.setDegree(String.valueOf(list.get(position).getWind().getDeg()));
                            String img_url = String.format("http://openweathermap.org/img/w/%s.png",
                                    list.get(position).getWeather().get(0).getIcon());
                            parcel.setIcon(img_url);
                            intent.putExtra("key", parcel);
                            startActivity(intent);
                        }
                    });

                    for (int i = 0; i<list.size(); i++){
                        db.addForecast(new ForecastDb(
                                Constant.getDate(list.get(i).getDt()),
                                String.valueOf(list.get(i).getMain().getTempMax()),
                                String.valueOf(list.get(i).getMain().getTempMin()),
                                list.get(i).getWeather().get(0).getDescription(),
                                String.format("http://openweathermap.org/img/w/%s.png",
                                        list.get(i).getWeather().get(0).getIcon())
                        ));
                    }

                    progressDialog.dismiss();

                } catch (NullPointerException e) {
                    Log.e("onResponse: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Log.e("onFailure: ", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }
}
