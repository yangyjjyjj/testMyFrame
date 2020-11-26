package com.wtwd.ldl.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @Author ldaoliang
 * @Date 2019/9/30 0030 上午 10:24
 * @Description TODO
 **/
@RestController
public class TestController {

	@GetMapping("/myProject")
	public String Test(){
		return "this is my first springboot project in server";
	}



}
