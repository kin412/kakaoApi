package com.kim.mail.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kim.mail.mailSend;

@Controller
public class mailController {
	
	@Autowired
	mailSend ms;

	@RequestMapping(value = "mailSendCon", method = RequestMethod.POST)
	public String mailSendCon(HttpServletRequest request) throws UnsupportedEncodingException{
		System.out.println("mailSendCon in");
		request.setCharacterEncoding("utf-8");
		String addressee = request.getParameter("addressee");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println("수신인 : " + addressee);
		System.out.println("제목 : " + title);
		System.out.println("content : " + content);
		
		ms.mailSendProp(addressee, title, content);
		
		return "redirect:/";
	}
	
}
