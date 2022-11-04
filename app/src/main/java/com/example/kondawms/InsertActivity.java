package com.example.kondawms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends MainActivity {
    EditText item_code_input, bin_location_input;
    Button btn;
    private static final String url="http://192.168.0.79/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        item_code_input=(EditText)findViewById(R.id.item_code_input);
        bin_location_input=(EditText)findViewById(R.id.bin_location_input);
        btn=(Button)findViewById(R.id.button_id3);

        final Button button = findViewById(R.id.insert_back_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert_item(item_code_input.getText().toString(), bin_location_input.getText().toString());
            }
        });
    }



    public void insert_item(final String item, final String location) {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                item_code_input.setText("");
                bin_location_input.setText("");
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                item_code_input.setText("");
                bin_location_input.setText("");
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>map = new HashMap<String, String>();
                map.put("item", item);
                map.put("location", location);
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(request);
    }


}
