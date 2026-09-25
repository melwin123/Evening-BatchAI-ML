package com.amc.bfsi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amc.bfsi.dto.LoginRequestDTO;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequestDTO req) {
		System.out.println("Name "+req.getName());
		System.out.println("Password "+req.getPassword());
		return "SUCCESS";
	}

}
