package com.izcax.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.izcax.myapplication.R;
import com.izcax.myapplication.helper.Constant;
import com.izcax.myapplication.model.ForecastParcel;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ForecastParcel parcel;
    TextView tempMax, date, date2, humidity, pressure, wind;
    ImageView imgIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupView();

        parcel = getIntent().getParcelableExtra(Constant.EXTRA_FORECAST);

//        tempMax.setText(parcel.getTempMax());
//        date.setText(parcel.getDate());
//        humidity.setText(parcel.getHumidity());
//        pressure.setText(parcel.getPressure());
//        wind.setText(parcel.getWind());
//        Picasso.with(this).load(parcel.getIcon()).into(imgIcon);

//        tempMax.setText(getResources().getString(R.string.format_temperature,
//                Float.parseFloat(parcel.getTempMax())));
//        humidity.setText(getResources().getString(R.string.format_humidity,
//                Float.parseFloat(parcel.getHumidity())));
//        pressure.setText(getResources().getString(R.string.format_pressure,
//                Float.parseFloat(parcel.getPressure())));
//        String direction = Constant.getFormattedWind(this,
//                Float.parseFloat(parcel.getWind()), Float.parseFloat(parcel.getDegree()));
//        wind.setText(direction);
//        Picasso.with(this).load(parcel.getIcon()).error(R.mipmap.ic_launcher)
//                .placeholder(android.R.color.darker_gray).into(imgIcon);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setupView() {
        tempMax = findViewById(R.id.item_tempMax_main);
        date = findViewById(R.id.txt_date);
        date2 = findViewById(R.id.txt_date2);
        humidity = findViewById(R.id.txt_humid);
        pressure = findViewById(R.id.txt_press);
        wind = findViewById(R.id.txt_wind);

        imgIcon = findViewById(R.id.item_icon_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_setting) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
