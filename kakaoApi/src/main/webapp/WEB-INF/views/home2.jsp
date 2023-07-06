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
		
		var access_token;
		
		function kakaoToken(){
			console.log("---------kakaoToken in----------");
			
			/* Kakao.Auth.authorize({
				  redirectUri: 'http://localhost:8080/kakaoApi/callback'
				});
			console.log("out"); */
			/*var urlSearch = new URLSearchParams(location.search);
            var code = urlSearch.get('code');
			console.log("code : " + code);*/
			
			$.ajax({
			    type : "POST"
			    , url : 'https://kauth.kakao.com/oauth/token'
			    , data : {
			        grant_type : 'authorization_code',
			        client_id : 'e61c69473af4d6797abb6a1103cc30f4', //'본인이 할당받은 key입력'
			        redirect_uri : 'http://localhost:8080/kakaoApi/callback',
			        code : $('#code').val()
			    }
			    , contentType:'application/x-www-form-urlencoded;charset=utf-8'
			    , dataType: null
			    , success : function(response) {
			    	console.log('tokenAfter success');
			    	
			        Kakao.Auth.setAccessToken(response.access_token);
			        access_token = response.access_token;
			        console.log('access_token : ' + access_token);
			        //document.querySelector('button.api-btn').style.visibility = 'visible';
			    }
			    ,error : function(jqXHR, error) {

			    }
			});
			
			//console.log("---------kakaoLogin out----------");
		}
		
		function disconnect(){
			Kakao.API.request({
				  url: '/v1/user/unlink',
				})
				  .then(function(response) {
				    console.log(response);
				  })
				  .catch(function(error) {
				    console.log(error);
				  });
			//화면이동. 뒤로가기 불가
			location.replace('/kakaoApi/');
		}
		
			
		function userInfo() {
			console.log("userInfo in");
		    Kakao.API.request({
		      url: '/v2/user/me',
		    })
		      .then(function(res) {
		    	var resVar = JSON.stringify(res)
		        console.log(JSON.stringify(res));
		        console.log("--------------");
		        console.log("res.id : " + res.id);
		        console.log("--------------");
		        console.log("--------------");
		        console.log("resVar : " + resVar); //json을 String으로 변환했으니 보임.
		        console.log("--------------");
		        console.log("resVar.id : " + resVar.id); // resVar는 json을 String으로 변환한 것이기 때문에 .id가 안됨
		        console.log("--------------");
		        console.log("--------------");
		        console.log("id : " + res.id);
		        console.log("nickname : " + res.properties.nickname);
		        console.log("email : " + res.kakao_account.email);
		        $("label[for='id']").text(res.id);
		        $("label[for='nickname']").text(res.properties.nickname);
		        $("label[for='email']").text(res.kakao_account.email);
		        
		      })
		      .catch(function(err) {
		        console.log(
		          'failed to request user information: ' + JSON.stringify(err)
		        );
		      });
		    console.log("userInfo out");
		  }
		
		function logout(){
			Kakao.Auth.logout()
			  .then(function(response) {
			    console.log(Kakao.Auth.getAccessToken()); // null
			  })
			  .catch(function(error) {
			    console.log('Not logged in.');
			  });
			//화면이동. 뒤로가기 불가
			location.replace('/kakaoApi/');
			
		}
		
		function mySelfMessageSend(){
			Kakao.API.request({
				  url: '/v2/api/talk/memo/default/send',
				  data: {
				    template_object: {
				      object_type: 'feed',
				      content: {
				        title: '딸기 치즈 케익',
				        description: '#케익 #딸기 #삼평동 #카페 #분위기 #소개팅',
				        image_url:
				          'https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
				        link: {
				          web_url: 'https://developers.kakao.com',
				          mobile_web_url: 'https://developers.kakao.com',
				        },
				      },
				      item_content: {
				        profile_text: 'Kakao',
				        profile_image_url: 'https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
				        title_image_url: 'https://mud-kage.kakao.com/dn/Q2iNx/btqgeRgV54P/VLdBs9cvyn8BJXB3o7N8UK/kakaolink40_original.png',
				        title_image_text: 'Cheese cake',
				        title_image_category: 'Cake',
				        items: [
				          {
				            item: 'Cake1',
				            item_op: '1000원',
				          },
				          {
				            item: 'Cake2',
				            item_op: '2000원',
				          },
				          {
				            item: 'Cake3',
				            item_op: '3000원',
				          },
				          {
				            item: 'Cake4',
				            item_op: '4000원',
				          },
				          {
				            item: 'Cake5',
				            item_op: '5000원',
				          },
				        ],
				        sum: 'Total',
				        sum_op: '15000원',
				      },
				      social: {
				        like_count: 100,
				        comment_count: 200,
				      },
				      buttons: [
				        {
				          title: '웹으로 보기',
				          link: {
				            mobile_web_url: 'https://developers.kakao.com',
				            web_url: 'https://developers.kakao.com',
				          },
				        },
				        {
				          title: '앱으로 보기',
				          link: {
				            mobile_web_url: 'https://developers.kakao.com',
				            web_url: 'https://developers.kakao.com',
				          },
				        },
				      ],
				    },
				  },
				})
				  .then(function(response) {
				    console.log(response);
				  })
				  .catch(function(error) {
				    console.log(error);
				  });
		}
		
		function codeTest(){
			console.log("code : " + $('#code').val());
		}
		
	</script>
  
  </script>
</head>
<body>
	
	<div>code : ${code}</div>
	<input id ='code' value = ${code}>
	<br>
	<button onclick="codeTest()"> codeTest </button>
	<br>
	<br>
	<button onclick="kakaoToken()"> kakao token setting </button>
	<br>
	<br>
	<button onclick="userInfo()"> userInfo </button>
	<br>
	<br>
	<button onclick="logout()"> logout </button>
	<br>
	<br>
	<br>
	<button onclick="disconnect()"> app disconnect </button>
	<br>
	<br>
	<div>login userInfo</div>
	<div>
		id : <label for="id"></label><br>
		nickname : <label for="nickname"></label><br>
		email : <label for="email"></label><br>
	</div>
	<br>
	<button onclick="mySelfMessageSend()"> mySelfMessageSend </button>
	
	

</body>
</html>
