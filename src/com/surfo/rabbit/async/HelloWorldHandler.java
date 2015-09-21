package com.surfo.rabbit.async;

public class HelloWorldHandler {

	public void handleMessage(String text) {
		System.out.println("Received: " + text);
	}

}
