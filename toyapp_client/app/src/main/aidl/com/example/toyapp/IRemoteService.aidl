// IRemoteService.aidl
package com.example.toyapp;

// Declare any non-default types here with import statements

interface IRemoteService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    //void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);

    int connect();
    int disconnect();
    boolean isConnect();
    String hello();
    int addService(int arg1, int arg2);

}
