package com.example.toyapp;

interface IRemoteService{
	int connect();
	int disconnect();
	boolean isConnect();
	String hello();
}