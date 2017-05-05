package com.example.naveen.weatherapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Naveen on 5/5/2017.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    Context context;
    List<WeatherHelper> weatherHelpers;

    public WeatherAdapter(Context context, List<WeatherHelper> weatherHelpers) {
        this.context = context;
        this.weatherHelpers = weatherHelpers;
    }

    @Override
    public WeatherAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        WeatherHelper helper =weatherHelpers.get(position);

        holder.day.setText(helper.getDay());
        holder.min.setText(helper.getMin());
        holder.max.setText(helper.getMax());
        holder.night.setText(helper.getNight());
        holder.eve.setText(helper.getEve());
        holder.morn.setText(helper.getMorn());
        holder.description.setText(helper.getDescription());
        holder.date.setText(helper.getDt());

    }

    @Override
    public int getItemCount() {
        return weatherHelpers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView day, min,max,night,eve,morn,description,date;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            min = (TextView) itemView.findViewById(R.id.min);
            max = (TextView) itemView.findViewById(R.id.max);
            night = (TextView) itemView.findViewById(R.id.night);
            eve = (TextView) itemView.findViewById(R.id.eve);
            morn = (TextView) itemView.findViewById(R.id.morn);
            description = (TextView) itemView.findViewById(R.id.description);
            date = (TextView) itemView.findViewById(R.id.date);





        }
    }
}
