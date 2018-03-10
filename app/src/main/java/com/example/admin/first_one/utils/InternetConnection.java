package com.example.admin.first_one.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by admin on 2018/3/10.
 */

public class InternetConnection {
    public static boolean checkConnection(Context context) {
        return  ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo() != null;
    }
}
