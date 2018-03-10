package com.example.admin.first_one.utils;

import android.content.Intent;
import android.widget.ListView;

import com.example.admin.first_one.adapter.MyArrayAdapter;
import com.example.admin.first_one.model.MyModel;
import com.youth.banner.Banner;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/3/10.
 */

public class Static_activities {

    private static final String TAG = "test";
    public static Banner banner;
    public static List<String> list = new ArrayList<String>();
    public static Intent intent;
    public static JSONObject jsonObject;
    public static ListView listView;
    public static ArrayList<MyModel> list_model;
    public static MyArrayAdapter myArrayAdapter;
}
