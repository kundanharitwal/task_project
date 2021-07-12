package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class AdapterCoun extends RecyclerView.Adapter<AdapterCoun.ViewHolder> {


    Context context;
    ArrayList<ModelC> countriesList;

    public AdapterCoun(Context context, ArrayList<ModelC> countriesList) {
        this.context = context;
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public AdapterCoun.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_countries, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCoun.ViewHolder holder, int position) {

        ModelC mc = countriesList.get(position);

        //Glide.with(context).load(mc.getFlag()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.flag);
        Utils.fetchSvg(context, mc.getFlag(), holder.flag);
        holder.name.setText(mc.getName());
        holder.region.setText(mc.getRegion());
        holder.languages.setText(mc.getLanguages());
        holder.population.setText(mc.getPopulation());
        holder.border.setText(mc.getBorder().replace("[", "").replace("]", ""));
        holder.capital.setText(mc.getCapital());
        holder.subRegion.setText(mc.getSubregion());


    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView name, capital, region, subRegion, population, border, languages;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flag = itemView.findViewById(R.id.flagImg);
            name = itemView.findViewById(R.id.name);
            capital = itemView.findViewById(R.id.capital);
            region = itemView.findViewById(R.id.region);
            subRegion = itemView.findViewById(R.id.subregion);
            population = itemView.findViewById(R.id.population);
            border = itemView.findViewById(R.id.border);
            languages = itemView.findViewById(R.id.languages);

        }
    }
}
