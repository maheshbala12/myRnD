package com.mydemo;

import javax.xml.ws.Endpoint;

public class ServiceLauncher {
	public static void main(String[] args) {
		String url = "http://localhost:1212/hello";
		Endpoint.publish(url, new HelloService());
		System.out.println("Service started @ " + url);
	}
}
