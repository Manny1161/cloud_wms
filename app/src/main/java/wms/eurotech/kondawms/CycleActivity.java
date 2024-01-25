package wms.eurotech.kondawms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kondawms.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CycleActivity extends MainActivity {
    EditText item_input;
    Button btn;
    ProgressDialog loading;
    private static final String CYCLE_COUNT = "https://eurotechwms.com.au/cycle.php?id=";
    private static final String KEY_QTY = "quantity";
    private static final String KEY_ITEM = "item";
    private static final String KEY_LOCATION = "location";
    private static final String JSON_ARRAY = "result";
    List<Items> items = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle);
        recyclerView = findViewById(R.id.recyclerList);
        item_input=(findViewById(R.id.item_input));
        btn=(findViewById(R.id.button_id3));
        final Button button = findViewById(R.id.cycle_back_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener((View.OnClickListener) this);
    }

    private void getData() {
        String[] arr = item_input.getText().toString().split("/");
        if(arr[0].equals("")) {
            Toast.makeText(this, "Scan Item Barcode", Toast.LENGTH_LONG).show();
            return;
        }
        loading = ProgressDialog.show(this, "Please wait...", "Fetching...",false,false);
        String url = CYCLE_COUNT+item_input.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CycleActivity.this, error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {
        String item;
        String qty;
        String location;
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(JSON_ARRAY);
            for(int i=0;i<2;i++) {
                JSONObject itemData = result.getJSONObject(i);
                item = itemData.getString(KEY_ITEM);
                qty = itemData.getString(KEY_QTY);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new ItemAdapter(CycleActivity.this, items));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
