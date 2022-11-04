package com.example.kondawms;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView itemsView, locationView, timeView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemsView = itemView.findViewById(R.id.item);
        locationView =itemView.findViewById(R.id.location);
        timeView = itemView.findViewById(R.id.time);
    }
}

