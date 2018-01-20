package com.izcax.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.izcax.myapplication.R;
import com.izcax.myapplication.helper.Constant;
import com.izcax.myapplication.model.forecast.List;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Izcax on 12/4/17.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.CategoryViewHolder>{
    private Context context;

    public java.util.List<List> getListForecast() {
        return listForecast;
    }
    public void setListForecast(java.util.List<List> listForecast) {
        this.listForecast = listForecast;
    }

    private java.util.List<List> listForecast;

    public ForecastAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
//        notifyDataSetChanged();
        String date= Constant.getDate(getListForecast().get(position).getDt());
        holder.tvDay.setText(date);
        holder.tvDesc.setText(getListForecast().get(position).getWeather().get(0).getDescription());
        holder.tvMin.setText(context.getResources().getString(R.string.format_temperature,
                getListForecast().get(position).getMain().getTempMin()));
        holder.tvMax.setText(context.getResources().getString(R.string.format_temperature,
                getListForecast().get(position).getMain().getTempMax()));

        String img_url = "http://openweathermap.org/img/w/"+
                getListForecast().get(position).getWeather().get(0).getIcon()
                +".png";
        Picasso.with(context)
                .load(img_url)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.background_light)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return getListForecast().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView tvDay,tvDesc, tvMin, tvMax;
        ImageView icon;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvDay = itemView.findViewById(R.id.item_day);
            tvDesc = itemView.findViewById(R.id.item_desc);
            tvMax = itemView.findViewById(R.id.item_max);
            tvMin = itemView.findViewById(R.id.item_min);
            icon = itemView.findViewById(R.id.item_icon);
        }
    }
}
