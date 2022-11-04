package com.example.kondawms;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ViewHolder>{

    Context context;
    List<Items> items;

    public ItemAdapter(Context context, List<Items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemsView.setText(items.get(position).getItem());
        holder.locationView.setText(items.get(position).getLocation());
        holder.timeView.setText(items.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
