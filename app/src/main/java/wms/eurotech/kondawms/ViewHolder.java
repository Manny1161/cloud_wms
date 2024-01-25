package wms.eurotech.kondawms;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kondawms.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView itemsView, locationView, timeView;
    ImageButton Delete;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        itemsView = itemView.findViewById(R.id.item);
        locationView =itemView.findViewById(R.id.location);
        timeView = itemView.findViewById(R.id.time);
        Delete = itemView.findViewById(R.id.rcy_delete);
    }
}



