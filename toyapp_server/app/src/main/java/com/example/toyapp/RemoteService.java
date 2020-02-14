package com.example.toyapp;


import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


public class RemoteService extends Service {

    private final static String TAG = "RemoteService";


    @Override
    public void onCreate() {
        Log.e("RemoteService", "onCreate");
        super.onCreate();

        /*
        //code for start this service when boot the app
        int NOTIFICATION_ID = (int) (System.currentTimeMillis()%10000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(NOTIFICATION_ID, new Notification());
        }*/
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("StartService","onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.e("RemoteService", "onBind");
        return mRemoteBinder;
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("RemoteService", "onUnbind");
        return super.onUnbind(intent);
    }

    private final IRemoteService.Stub mRemoteBinder = new IRemoteService.Stub() {

        @Override
        public String hello() throws RemoteException {
            Log.d("RemoteServer", "hello() called!");
            return "CONNECTION ESTABLISHED";
        }

        @Override
        public int addService(int arg1, int arg2) throws RemoteException {

            Log.e("RemoteService", "addService() called");
            return arg1 + arg2;

        }

        @Override
        public boolean isConnect() throws RemoteException {
            Log.e("RemoteService", "isConnect");
            return true;
        }

        @Override
        public int disconnect() throws RemoteException {
            Log.e("RemoteService", "disconnect");
            return -1;
        }

        @Override
        public int connect() throws RemoteException {
            Log.e("RemoteService", "connect");
            return 1;
        }
    };





}



















