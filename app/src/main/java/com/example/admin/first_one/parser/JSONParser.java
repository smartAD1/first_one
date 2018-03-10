package com.example.admin.first_one.parser;

import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2018/3/10.
 */

public class JSONParser {
    /*URL部分*/
    private static final String URL = "http://pratikbutani.x10.mx/json_data.json";
    /*TAG*/
    public static final String TAG = "TAG";
    /*Key to Send*/
    private static final String KEY_USER_ID = "user_id";
    /*Response */
    private static Response response;

    /*jsonobj*/
    public static JSONObject getDataFromWeb() {
        try {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(URL).build();
            response = okHttpClient.newCall(request).execute();
            return new JSONObject(response.body().string());
        } catch (IOException |JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
}
