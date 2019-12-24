package com.djinggoo.bigdataanalytic.bluetweets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Common {
	
	@GetMapping(value = "/") @ResponseBody
	public String home() {
		return ":p";
	}

}
