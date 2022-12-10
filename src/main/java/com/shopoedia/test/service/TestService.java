package com.shopoedia.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopoedia.test.repository.TestRepository;

@Service
public class TestService {

	public String getMsg() {
		String msg = "Server is up!!!";
		return msg;
	}
}
