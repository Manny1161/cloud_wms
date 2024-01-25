package wms.eurotech.kondawms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.kondawms.R;

import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class InsertActivity extends MainActivity {
    Connection connection;
    EditText item_code_input, bin_location_input;
    Button btn;
    Context context;

    private static final String url="https://eurotechwms.com.au/insert.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        btn=findViewById(R.id.button_id3);
        item_code_input=findViewById(R.id.item_code_input);
        bin_location_input=findViewById(R.id.bin_location_input);

        final Button button = findViewById(R.id.insert_back_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(InsertActivity.this);
                builder.setTitle("PICKING LOCATION");
                builder.setMessage("IS THIS A PICKING LOCATION?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ConSQL c = new ConSQL();
                        connection = c.conclass();
                        String[] arr1 = item_code_input.getText().toString().split("/");
                        String[] arr2 = bin_location_input.getText().toString().split("/");
                        if(c != null) {
                            try {
                                String sqlstatement = "update STOCK_LOC_INFO set BINCODE='" + arr2[0] + "' where STOCKCODE='" + arr1[0] + "'";
                                Statement smt = connection.createStatement();
                                smt.executeUpdate(sqlstatement);
                                connection.close();
                                bin_location_input.getText().clear();
                                item_code_input.getText().clear();
                            }

                            catch (Exception e) {
                                Log.e("Error: ", e.getMessage());
                            }
                        }
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        insert_item(item_code_input.getText().toString(), bin_location_input.getText().toString());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
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
