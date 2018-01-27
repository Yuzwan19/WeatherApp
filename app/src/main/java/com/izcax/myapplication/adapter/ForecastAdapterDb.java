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
import com.izcax.myapplication.model.db.ForecastDb;
import com.izcax.myapplication.model.forecast.List;
import com.squareup.picasso.Picasso;

/**
 * Created by Izcax on 1/26/18.
 */

public class ForecastAdapterDb extends RecyclerView.Adapter<ForecastAdapterDb.CategoryViewHolder> {
    private Context context;
    private java.util.List<ForecastDb> listForecast;

    public java.util.List<ForecastDb> getListForecast() {
        return listForecast;
    }

    public void setListForecast(java.util.List<ForecastDb> listForecast) {
        this.listForecast = listForecast;
    }

    public ForecastAdapterDb(Context context) {
        this.context = context;
    }

    @Override
    public ForecastAdapterDb.CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new ForecastAdapterDb.CategoryViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(ForecastAdapterDb.CategoryViewHolder holder, int position) {
//        notifyDataSetChanged();
        String date = getListForecast().get(position).getDate();

        holder.tvDay.setText(date);
        holder.tvDesc.setText(getListForecast().get(position).getDesc());
        holder.tvMin.setText(context.getResources().getString(R.string.format_temperature,
                Float.parseFloat(getListForecast().get(position).getTempMin())));
        holder.tvMax.setText(context.getResources().getString(R.string.format_temperature,
                Float.parseFloat(getListForecast().get(position).getTempMax())));

        Picasso.with(context)
                .load(getListForecast().get(position).getImgUrl())
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.background_light)
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
