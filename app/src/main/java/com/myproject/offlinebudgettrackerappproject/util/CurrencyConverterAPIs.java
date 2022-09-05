package com.myproject.offlinebudgettrackerappproject.util;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CurrencyConverterAPIs {
    private static String convertedResult;
//    String convertedResult;
    public CurrencyConverterAPIs(FragmentActivity activity) {
    }

    public static String currencyConverter(String currentCurrencyString, String targetCurrencyString, Double bankBalanceNum) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        //YouTube tutorial
        String url  = "https://api.apilayer.com/exchangerates_data/convert?to=" + targetCurrencyString + "&from=" + currentCurrencyString + "&amount=" + bankBalanceNum;
        String apiKey = "GHSOg6VcH44yR9oKUQBm19JPhoGbq0mD";


        Request request = new Request.Builder()
                .url(url)
                .addHeader("apiKey", apiKey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myResponse = response.body().string();
                Log.d("TAG0905", "bankBalance: " + myResponse);

                JSONObject obj = null;
                try {
                    obj = new JSONObject(myResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    convertedResult = obj.getString("result");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return convertedResult;
    }
}
