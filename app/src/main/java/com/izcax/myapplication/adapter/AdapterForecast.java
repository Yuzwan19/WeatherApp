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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Izcax on 1/21/18.
 */

public class AdapterForecast extends RecyclerView.Adapter<AdapterForecast.CategoryViewHolder> {

    private Context context;
    private List<com.izcax.myapplication.model.forecast.List> listForecast;

    public List<com.izcax.myapplication.model.forecast.List> getListForecast() {
        return listForecast;
    }

    public void setListForecast(List<com.izcax.myapplication.model.forecast.List> listForecast) {
        this.listForecast = listForecast;
    }

    public AdapterForecast(Context context) {
        this.context = context;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather,parent,false);
        return new CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {

        String tanggal = Constant.getDate(getListForecast().get(position).getDt());
        holder.tvDay.setText(tanggal);
        holder.tvDesc.setText(getListForecast().get(position)
                .getWeather().get(0).getDescription());
        holder.tvMax.setText(""+getListForecast().get(position).getMain().getTempMax());
        holder.tvMin.setText(""+getListForecast().get(position).getMain().getTempMin());

        String img_url = "http://openweathermap.org/img/w/" +
                getListForecast().get(position).getWeather().get(0).getIcon()
                + ".png";

        Picasso.with(context).load(img_url)
                .placeholder(android.R.color.darker_gray)
                .error(R.mipmap.ic_launcher)
                .into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return getListForecast().size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvDay, tvDesc, tvMin, tvMax;
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
