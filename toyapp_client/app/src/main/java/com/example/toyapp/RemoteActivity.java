package com.example.toyapp;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RemoteActivity extends AppCompatActivity {

    private IRemoteService mRemoteBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("RemoteClient", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);




        // start RemoteActivity by Button
        Button button = (Button) findViewById(R.id.buttonStartSum) ;
        Log.d("MainActivity", "register client_button on MainActivity" );

        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                int arg1, arg2, result;
                Intent intent = new Intent();
                intent.setClassName("com.example.toyapp", "com.example.toyapp.RemoteService");

                EditText editTextNo1 = (EditText) findViewById(R.id.Number1) ;
                String strNo1 = editTextNo1.getText().toString() ;
                arg1 =  Integer.parseInt(strNo1);


                EditText editTextNo2 = (EditText) findViewById(R.id.Number2) ;
                String strNo2 = editTextNo2.getText().toString() ;
                arg2 =  Integer.parseInt(strNo2);

                try {
                    result =  mRemoteBinder.addService(arg1, arg2);
                    Log.e("RemoteClient", "addService succeed! -> " + result);

                } catch (RemoteException e) {
                    result = 0;
                    e.printStackTrace();
                }

                Context context = getApplicationContext();
                Toast.makeText(RemoteActivity.this , "button clicked! " + result , Toast.LENGTH_SHORT).show();

            }

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.e("RemoteClient", "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.remote, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.e("RemoteClient", "onOptionsItemSelected");
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        Log.e("RemoteClient", "onResume");
        super.onResume();
        registerService();
    }

    @Override
    protected void onPause() {
        Log.e("RemoteClient", "onPause");
        super.onPause();
    }

    protected void onDestroy(){
        Log.e("RemoteClient", "onDestroy");
        unregisterService();
        super.onDestroy();

    }


    private void registerService() {
        Log.e("RemoteClient", "registerService");

        Intent intent = new Intent();
        intent.setClassName("com.example.toyapp", "com.example.toyapp.RemoteService");

        getApplicationContext().bindService(intent, mServiceConn, BIND_AUTO_CREATE);

    }

    private void unregisterService() {
        Log.e("RemoteClient", "unregisterService");
        unbindService(mServiceConn);
    }

    private final ServiceConnection mServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("RemoteClient", "onServiceConnected!");
            mRemoteBinder = IRemoteService.Stub.asInterface(service);

            try {

                String msg = mRemoteBinder.hello();

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("RemoteClient", "onServiceDisconnected");
            mRemoteBinder = null;
        }
    };


}
