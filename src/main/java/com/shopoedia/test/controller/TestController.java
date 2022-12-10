package com.shopoedia.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopoedia.test.service.TestService;

@RestController
public class TestController {

	@Autowired
	TestService service;
	
	@GetMapping("/servertest")
	public ResponseEntity<String> testApi(){
		//C-S-R
		String  msg = service.getMsg();
		 return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}