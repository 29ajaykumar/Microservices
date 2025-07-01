package com.example.docker.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World Java <h1>V1</h1>";
	}
}
