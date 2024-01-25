package wms.eurotech.kondawms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kondawms.R;

import java.util.List;

public class LookupAdapter extends RecyclerView.Adapter<ViewHolder>{
    private static final String LOOKUP_ITEM = "https://eurotechwms.com.au/lookup.php?id=";
    Context context;
    List<Items> items;

    public LookupAdapter(Context context, List<Items> items) {
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

