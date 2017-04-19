<%@ page language="java"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!DOCTYPE html>
<% 
	Gson gson = new Gson();
	String error = "";
	JsonObject myJSON = (JsonObject)request.getAttribute("userInfo");
	if(myJSON  != null){
		JsonObject errorJson = (JsonObject) myJSON.get("error");
		System.out.println(gson.toJson(errorJson));
		if(errorJson != null){
			error = "Invalid Login. Please try again";
		}
		System.out.println("errorr   " + error);
	}
%> 
<html >
<%@include file="header.jsp"%>
<head>
  <meta charset="UTF-8">
  <title>Game Zone Login</title>
  <link rel="stylesheet" href="/gamezone/resources/css/common.css">
  <link rel="stylesheet" href="/gamezone/resources/css/login.css">
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  <script
			src='/gamezone/resources/js/header.js'></script>	
  <script src="/gamezone/resources/js/login.js"></script>
  <script language="javascript">
  		function showRegister(){
  			$(".form").hide();
  			$(".register-form").show();
  		}
  		
  		function showLogin(){
  			$(".form").show();
  			$(".register-form").hide();  			
  		}
  		$('.message a').click(function(){
  		   $('container').animate({height: "toggle", opacity: "toggle"}, "slow");
  		});
  		
  </script>
  		<div id="fb-root"></div>
  		<script>(function(d, s, id) {
  		  var js, fjs = d.getElementsByTagName(s)[0];
  		  if (d.getElementById(id)) return;
  		  js = d.createElement(s); js.id = id;
  		  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.8";
  		  fjs.parentNode.insertBefore(js, fjs);
  		}(document, 'script', 'facebook-jssdk'));</script>
  
</head>

<body>
  <div class="wrapper">
		<h1>Welcome</h1>
	<div class="container">
		<form class="form" action="/gamezone/loginUser" method="POST">
			<input type="text"  name="name" placeholder="Username">
			<input type="password" name="password" placeholder="Password">
			<button type="submit" id="login-button">Login</button>
			<p class="message">
			Not registered? <a href="javascript:showRegister()">Create an account</a>
			</p>
			<div class="error"><%=error%></div>
		</form>
		
		<form class="register-form" action="/gamezone/registerUser" method="POST">
			<input type="text" name=name placeholder="Username">
			<input type="password" name="password" placeholder="Password">
			<input type="email" name="email" placeholder="Email Address">
			<button type="submit" id="login-button">Register</button>
			<p class="message">
			Already registered? <a href="javascript:showLogin()">Sign In</a>
			</p>
		</form>
	</div>
	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
</div>
</body>
</html>
