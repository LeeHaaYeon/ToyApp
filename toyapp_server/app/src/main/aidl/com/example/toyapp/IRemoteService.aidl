package com.example.toyapp;

interface IRemoteService{
	int connect();
	int disconnect();
	boolean isConnect();
	String hello();
    int addService(int arg1, int arg2);
}