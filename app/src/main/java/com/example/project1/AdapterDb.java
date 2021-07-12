package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class AdapterDb extends RecyclerView.Adapter<AdapterDb.TasksViewHolder> {

    private Context mCtx;
    private List<ModelDB> list;

    public AdapterDb(Context mCtx, List<ModelDB> list) {
        this.mCtx = mCtx;
        this.list = list;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.item_countries, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        ModelDB mc = list.get(position);
        Utils.fetchSvg(mCtx, mc.getFlag(), holder.flag);
        holder.name.setText(mc.getName());
        holder.region.setText(mc.getRegion());
        holder.languages.setText(mc.getLanguage());
        holder.population.setText(mc.getPopulation());
        holder.border.setText(mc.getBorder().replace("[", "").replace("]", ""));
        holder.capital.setText(mc.getCapital());
        holder.subRegion.setText(mc.getSubregion());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder{

        ImageView flag;
        TextView name, capital, region, subRegion, population, border, languages;

        public TasksViewHolder(View itemView) {
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