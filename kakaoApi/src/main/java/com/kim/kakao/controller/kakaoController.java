package com.kim.kakao.controller;

import java.io.UnsupportedEncodingException;
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
	public String memberRegi(Locale locale, Model model) {
	  System.out.println("Test111111111111111111");
	  
	  
	  return "home2";
	}
	
	@RequestMapping(value = "/callback/tokenAfter", method = RequestMethod.POST)
	public void tokenAfter(Locale locale, Model model) {
	  System.out.println("----tokenAfter in----");
	  
	  
	  //return "home2";
	}
	
	@RequestMapping(value = "/ck/textWrite", method = RequestMethod.POST)
	public String textWrite(Locale locale, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
	  System.out.println("----textWrite in----");
	  request.setCharacterEncoding("utf-8");
	  String textContent = request.getParameter("content1");
	  String testInput = request.getParameter("testInput"); 
	  System.out.println("textContent : " + textContent);
	  System.out.println("testInput : " + testInput);
	  
	  
	  return "redirect:/callback";
	}
	
}
