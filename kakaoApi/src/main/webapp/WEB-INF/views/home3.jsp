<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>ajax and layer popup</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	
<script type="text/javascript">
	$(document).ready(function() {
		
		//$(".submitIdBtn").click(function(){ .은 class name은 input[name='123'] 
		$("#submitIdBtn").click(function(){
			
			var form = document.submitForm;
			//console.log("document.submitForm.testText1 : " + document.submitForm.testText1.value);
			//form.action = "/restCon";
			//form.submit();
			
			var aaa = {no : "no", name : "name", nick : "nick"};
			
			console.log(aaa);
			console.log("JSON.stringify(aaa) : " + JSON.stringify(aaa));
			console.log("aaa.no : " + aaa.no);
			//console.log("aaa[no] : " + aaa[no]);
			
			var serialize = $(submitForm1).serialize();
			var serializeArr = $(submitForm1).serializeArray();
			var srlForm = JSON.stringify(serializeArr);
			srlForm = srlForm.substring(0, srlForm.length - 1);
			srlForm = srlForm.substring(1, srlForm.length);
			console.log("serialize : " + serialize);
			console.log("JSON.stringify(serialize) : " + JSON.stringify(serialize));
			console.log("serializeArr : " + serializeArr);
			console.log("JSON.stringify(serializeArr) srlForm subString : " + srlForm);
			console.log("------------------------");
			var serializeObject = $('#submitForm1').serializeObject();
			console.dir("serializeObject : " + JSON.stringify(serializeObject));
			
			$.ajax({
				type : 'post',           // 타입 (get, post, put 등등)
			    url : '/restCon',           // 요청할 서버url
			    //async : true,            // 비동기화 여부 (default : true)
			    /*headers : {              // Http header
			      "Content-Type" : "application/json",
			      "X-HTTP-Method-Override" : "POST"
			    },*/
			    
			    //보내는 데이터 타입 -  이게 제이슨을 서버단에서 자동으로 디코딩 해주는듯
			    contentType: "application/json; charset=utf-8", 
			    
			    //받는 데이터 타입
			    dataType : 'json',       // 데이터 타입 (html, xml, json, text 등등) 
			    
			    // 보낼 데이터 (Object , String, Array)
			    data : JSON.stringify(serializeObject),
			    success : function(result) { // 결과 성공 콜백함수
			        console.log("success!!");
			    	console.log("result.testText2 : " + result.testText2);
			    },
			    error : function(request, status, error) { // 결과 에러 콜백함수
			        console.log(error);
			    }	
			});
			
		});
		
		function submitFunc(){
			console.log("submitFunc!!");
		}
		
		$.fn.serializeObject = function() {
			  var result = {}
			  var extend = function(i, element) {
				  console.log("----$.fn.serializeObject start--------"); 
				console.log("i : " + i); 
				console.log("element : " + element)
				  
				var node = result[element.name]
				console.log("node(result[element.name]) : " + node);
				console.log("element.name : " + element.name);
				console.log("element.value : " + element.value);
			    if ("undefined" !== typeof node && node !== null) {
			      if ($.isArray(node)) {
			          console.log("$.isArray");
			    	  node.push(element.value);
			    	  console.log("node : " + node);
			      } else {
			    	  console.log("$.isArray else");
			    	  result[element.name] = [node, element.value]
			    	  console.log("$.isArray else");
			      }
			    } else {
			      	console.log("type else");
			    	result[element.name] = element.value
			    	console.log("else result[element.name] : " + result[element.name]);
			    }
				console.log("----$.fn.serializeObject end--------"); 
			  }

			  $.each(this.serializeArray(), extend)
			  return result;
		}

			
		
	});
</script>
	
</head>
	
<body>

	ajax and layer popup<br>
	
	<form action="/restCon" name="submitForm" id="submitForm1">
		<input type="text" name="testText1"/>
		<input type="text" name="testText2"/>
	</form>
	
	<button name="submitNameBtn" id="submitIdBtn">제출</button>

</body>
</html>
