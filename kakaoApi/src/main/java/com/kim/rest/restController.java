package com.kim.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class restController {
	
	//RequestParam은 get방식 같이 주소에 파라미터로 보내는걸 받는 방법이기때문에 json은 받을수 없다.
	@ResponseBody
	@RequestMapping(value = "restParam", method = RequestMethod.POST)
	public String aaa(@RequestParam Map<String,Object> show) {
		System.out.println("-------- requestParam :  a : " + show.get("a"));
		
		return "qweqwsf";
	}
	
	//json은 RequestBody로 받는다.
	@ResponseBody
	@RequestMapping(value = "restBody", method = RequestMethod.POST)
	public HashMap<String, Object> aaa(@RequestBody HashMap<String, Object> map) {
		System.out.println("--------requestBody a : " + map);
		
		
		//Map<String, String> map = new HashMap<String, String>();
		//map.put("first", "number");
		
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "restCon")
	public ttt restCon(@RequestBody ttt tecl /*@RequestBody String bbb*/) {
		System.out.println("----restCon in");
		//System.out.println("----requset.getparameter : " + request.getParameter("testText1"));
		//System.out.println("----RequestBody bbb : " + bbb);
		System.out.println("tecl.toString() : " + tecl.toString());
		
		
		
		//return "redirect:/";
		return tecl;
	}

}
