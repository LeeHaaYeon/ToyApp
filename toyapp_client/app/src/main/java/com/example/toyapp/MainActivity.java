package com.example.toyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private IRemoteService mRemoteBinder, myAidlInterface ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Client MainActivity", "start onCreate" );

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        // start RemoteActivity by Button
        Button button = (Button) findViewById(R.id.client_button) ;
        Log.d("MainActivity", "register client_button on MainActivity" );

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("MainActivity", "Button onClickListener" );
                Intent intent = new Intent(MainActivity.this, RemoteActivity.class) ;
                startActivity(intent) ;
            }
        });



        //getApplicationContext().bindService(intent, serviceConnection, BIND_AUTO_CREATE);


        /*
        Intent intent = new Intent();
        intent.setClassName("com.example.toyapp", "com.example.toyapp.RemoteService");

        getApplicationContext().bindService(intent, mServiceConn, BIND_AUTO_CREATE);
        *
        * */
    }



}

