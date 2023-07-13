<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>kakao-api</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.2.0/kakao.min.js" integrity="sha384-x+WG2i7pOR+oWb6O5GV5f1KN2Ko6N7PTGPS7UlasYWNxZMKQA63Cj/B2lbUmUfuC" crossorigin="anonymous">
	</script>
	
	<script>
		Kakao.init('e61c69473af4d6797abb6a1103cc30f4');
		console.log(Kakao.isInitialized());
		
		function kakaoLogin(){
			console.log("---------kakaoLogin in----------");
			
			Kakao.Auth.authorize({
				  redirectUri: 'http://localhost:8080/kakaoApi/callback'
				});
			console.log("out");
			/*var urlSearch = new URLSearchParams(location.search);
            var code = urlSearch.get('code');
			console.log("code : " + code);*/
			
			/*$.ajax({
			    type : "POST"
			    , url : 'https://kauth.kakao.com/oauth/token'
			    , data : {
			        grant_type : 'authorization_code',
			        client_id : e61c69473af4d6797abb6a1103cc30f4, //'본인이 할당받은 key입력'
			        redirect_uri : 'http://localhost:8080/login',
			        code : kakaoCode
			    }
			    , contentType:'application/x-www-form-urlencoded;charset=utf-8'
			    , dataType: null
			    , success : function(response) {
			        Kakao.Auth.setAccessToken(response.access_token);
			        document.querySelector('button.api-btn').style.visibility = 'visible';
			    }
			    ,error : function(jqXHR, error) {

			    }
			}); */
			
			//console.log("---------kakaoLogin out----------");
		}
		
	</script>
  
  </script>
</head>
<body>

	<button onclick="kakaoLogin()"> kakao login </button><br>
	
	<!-- <form action="aaa" method="post">
	이름 : <input type="text" name="userName"><br>
	계정 : <input type="text" name="userId">
	<input type="submit" value="전송버튼">
	</form> -->
	<br>
	<div>javaMail</div>
	<form action = "mailSendCon" method="post">
	수신인 : <input type="text" name="addressee"><br>
	제목 : <input type="text" name="title"><br>
	내용 : <input type="text" name="content"><br>
	<input type="submit" value="메일 전송">
	</form>

	<a href="fileList"><button>업로드 파일 목록</button></a>
	
	

</body>
</html>
