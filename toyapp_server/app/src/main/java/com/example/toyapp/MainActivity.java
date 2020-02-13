package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Server MainActivity", "start onCreate" );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //code for start RemoteService when boot the app

        Intent intent = new Intent(getApplicationContext(), RemoteService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e("MainActivity", "startForegroundService");
            startForegroundService(intent);

        }
        else { startService(intent); }
        */
    }
}
