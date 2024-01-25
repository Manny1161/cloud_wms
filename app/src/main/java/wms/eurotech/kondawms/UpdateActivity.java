package wms.eurotech.kondawms;

import android.app.AlertDialog;
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

public class UpdateActivity extends MainActivity {
    Connection connection;
    EditText bin_location_input, item_code_input;
    Button btn;
    private static final String url="https://eurotechwms.com.au/update.php";
    private static final String del="https://eurotechwms.com.au/delete.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        item_code_input=findViewById(R.id.item_code_input);
        bin_location_input=findViewById(R.id.bin_location_input);
        btn=findViewById(R.id.button_id3);

        final Button button = findViewById(R.id.update_back_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                builder.setTitle("PICKING LOCATION");
                builder.setMessage("IS THIS A PICKING LOCATION?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        del_item(item_code_input.getText().toString());
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
                        update_item(item_code_input.getText().toString(), bin_location_input.getText().toString());
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }



    public void update_item(final String item, final String location) {
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

    public void del_item(final String item)
    {
        StringRequest delRequest = new StringRequest(Request.Method.POST, del, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> deleteParams = new HashMap<>();
                deleteParams.put("item", item);
                return deleteParams;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(delRequest);
    }

}
