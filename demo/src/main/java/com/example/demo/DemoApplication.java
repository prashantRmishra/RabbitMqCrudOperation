package com.example.demo;

import com.blade.Blade;

public class DemoApplication {

	public static void main(String[] args) {
		Blade.of().get("/home", handler->handler.text("home pge")).start(DemoApplication.class,args);
	}

}
