package com.timbau3285.myinventory.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timbau3285.myinventory.R;
import com.timbau3285.myinventory.database.SharedPreference;

public class MainActivity extends AppCompatActivity {

    SharedPreference userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInfo = new SharedPreference(this);

        if (!userInfo.getState()){
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override public void onBackPressed(){
        finish();
    }
}
