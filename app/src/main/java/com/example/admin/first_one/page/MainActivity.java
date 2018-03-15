package com.example.admin.first_one.page;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.first_one.LoginActivity;
import com.example.admin.first_one.R;
import com.example.admin.first_one.adapter.MyArrayAdapter;
import com.example.admin.first_one.model.MyModel;
import com.example.admin.first_one.parser.JSONParser;
import com.example.admin.first_one.utilimageloader.GlideImageLoader;
import com.example.admin.first_one.utils.InternetConnection;
import com.example.admin.first_one.utils.keys;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.admin.first_one.utils.Static_activities.*;


public class MainActivity extends AppCompatActivity implements OnBannerListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        banner = (Banner) findViewById(R.id.banner);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new GlideImageLoader());
        loadimg();
        banner.setImages(list).setImageLoader(new GlideImageLoader()).setOnBannerListener(this).start();
        banner.isAutoPlay(true);
        banner.setDelayTime(1500);
        banner.start();

        list_model = new ArrayList<>();
        myArrayAdapter = new MyArrayAdapter(this, list_model);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(myArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, list_model.get(position).getName() + " => " +
                        list_model.get(position).getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
        if (InternetConnection.checkConnection(getApplicationContext())) {
            new GetDataTask().execute();
        } else {
            Toast.makeText(this, "Internet Connection Not Available", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getApplicationContext(), "點擊：" + position, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                Toast.makeText(this, "點0", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, case0Activity.class);
//                intent.putExtra()
                startActivity(intent);
                break;
            case 1:
                Toast.makeText(this, "點1", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, case1Activity.class);
//                intent.putExtra()
                startActivity(intent);
                break;
            case 2:
                Toast.makeText(this, "點2", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, case2Activity.class);
//                intent.putExtra()
                startActivity(intent);
                break;

        }
    }

    public void loadimg() {
        list.add("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg");
        list.add("http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg");
        list.add("http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg");
    }

    private class GetDataTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /*ProgressDialog版本*/
//            dialog = new ProgressDialog(MainActivity.this);
//            dialog.setTitle("Hey Wait Please...");
//            dialog.setMessage("I am getting your JSON");
//            dialog.setProgress(100);
//            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            new Thread(new Runnable() {
//                int i = 0;
//                @Override
//                public void run() {
//                    try{
//                        while (i <= 100) {
//                            // 由线程来控制进度。
//                            dialog.setProgress(i ++);
//                            Thread.sleep(1000);
//                        }
//                        dialog.cancel();
//                    }
//                    catch (InterruptedException e){
//                        dialog.cancel();
//                    }
//                }
//            }).start();
//            dialog.show();
            /*ProgressDialog版本*/
            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
            listView.setVisibility(View.GONE);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            jsonObject = JSONParser.getDataFromWeb();
            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        JSONArray jsonArray = jsonObject.getJSONArray(keys.KEY_CONTACTS);
                        int lenArray = jsonArray.length();
                        if (lenArray > 0) {
                            for (int array = 0; array < lenArray; array++) {
                                MyModel myModel = new MyModel();
                                JSONObject innerObject = jsonArray.getJSONObject(array);
                                String name = innerObject.getString(keys.KEY_NAME);
                                String email = innerObject.getString(keys.KEY_EMAIL);
                                String image = innerObject.getString(keys.KEY_PROFILE_PIC);
                             /*phone*/
                                JSONObject phoneObj = innerObject.getJSONObject(keys.KEY_PHONE);
                                String phone = phoneObj.getString(keys.KEY_MOBILE);
                                myModel.setName(name);
                                myModel.setEmail(email);
                                myModel.setPhone(phone);
                                myModel.setImage(image);
                            /*新增List*/
                                list_model.add(myModel);
                            }
                        }
                    } else {

                    }
                }
            } catch (JSONException e) {
                Log.i(JSONParser.TAG, "" + e.getLocalizedMessage());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            dialog.dismiss();
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            if (list_model.size() > 0) {
                myArrayAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(MainActivity.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
        }
    }

}