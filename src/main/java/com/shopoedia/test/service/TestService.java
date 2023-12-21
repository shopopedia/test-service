package com.shopoedia.test.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

	public String getMsg() {
		String msg = "Server is up!!!";
		return msg;
	}
}
