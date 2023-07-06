package com.kim.kakao.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class kakaoController {
	
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		System.out.println("----login in----");
		String code = request.getParameter("code");
		System.out.println("code = " + code);
		
		model.addAttribute("code", code);
		
		return "home2";
	}
	
	@RequestMapping(value = "aaa", method = RequestMethod.POST)
	public void memberRegi(Locale locale, Model model) {
	  System.out.println("Test111111111111111111");
	  
	  
	  //return "home2";
	}
	
	@RequestMapping(value = "/callback/tokenAfter", method = RequestMethod.POST)
	public void tokenAfter(Locale locale, Model model) {
	  System.out.println("----tokenAfter in----");
	  
	  
	  //return "home2";
	}
	
}
