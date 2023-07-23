package com.myproject.offlinebudgettrackerappproject.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MysqlInsertDao  {
//    private final String userId;
//    private final String userPassword;
    private Context mContext;

//    public MysqlInsertDao(String userId, String userPassword) {
//        this.userId = userId;
//        this.userPassword = userPassword;
//    }

    public MysqlInsertDao(Context context) {
        mContext = context.getApplicationContext();
    }


    public MysqlInsertDao() {

    }

    public void mySqlCsvInsert(String csvData) {
        try {
            Properties properties = new Properties();
            mContext = mContext.getApplicationContext();
            InputStream inputStream = mContext.getAssets().open("server_config.properties");
            properties.load(inputStream);
            String serverUrl = properties.getProperty("server_url");
            String phpInsertFile = properties.getProperty("insert_php_file");
            String insert_url = serverUrl + phpInsertFile;
            // do something with the url
            StringRequest stringRequest = new StringRequest(Request.Method.POST, insert_url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");

                                if (success.equals("1")) {
                                    Toast.makeText(mContext, "CSV data has been inserted", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(mContext, "Unable to insert data" + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }){
                //We need to set Parameters that will also match with the API
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("csvData", csvData);  // Add the csvData to the parameters

                    return params;
                }
            };
            //Let a requestQue which enables us to use Volley.
            RequestQueue requestQueue = Volley.newRequestQueue(mContext);
            requestQueue.add(stringRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
