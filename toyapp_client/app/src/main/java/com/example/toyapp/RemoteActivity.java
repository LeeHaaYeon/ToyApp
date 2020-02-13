package com.example.toyapp;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class RemoteActivity extends AppCompatActivity {

    private IRemoteService mRemoteBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("RemoteClient", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

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
        public void onServiceDisconnected(ComponentName name) {
            Log.e("RemoteClient", "onServiceDisconnected");
            mRemoteBinder = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("RemoteClient", "onServiceConnected!");
            mRemoteBinder = IRemoteService.Stub.asInterface(service);

            try {

                String msg = mRemoteBinder.hello();
                Log.e("RemoteClient", msg);

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
}
