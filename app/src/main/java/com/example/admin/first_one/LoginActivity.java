package com.example.admin.first_one;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.first_one.page.MainActivity;

public class LoginActivity extends AppCompatActivity {
    private ImageView logo;
    private EditText user,pas;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logo = (ImageView) findViewById(R.id.logo);
        user =(EditText)findViewById(R.id.username);
        pas = (EditText)findViewById(R.id.password);
        Glide.with(this).load(R.drawable.logo).into(logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "沒東西", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(View view) {
        username = user.getText().toString();
        password = pas.getText().toString();
        Log.d("test",password);
        Log.d("test",username);
        if (username.equals("root") && password.equals("root")) {
            SharedPreferences setting =
                    getSharedPreferences("Index", MODE_PRIVATE);
            setting.edit()
                    .putString("PREF_USERID", username)
                    .putString("PREF_PASSWORD", password)
                    .commit();
            Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Sampele_1")
                    .setMessage("登入失敗")
                    .setPositiveButton("OK", null)
                    .show();
        }
    }


}
