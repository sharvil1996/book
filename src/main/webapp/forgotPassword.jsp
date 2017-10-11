<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password } BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<%@include file="headerGuest.jsp"%>
	<div class="container">
		<div
			style="background-color: white; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); padding: 20px;">
			<div style="text-align: center;" class="row">
				<div class="cmd4" style="padding: 0;"></div>
				<div class="cmd4">
					<p style="font-size: 24px; color: #e74c3c;">Forgot Password</p>
					<input type="text" placeholder="Your Email" class="myinput embox"
						style="width: 100%; box-sizing: border-box;" ng-model="mainemail" />
						<span class="ermsg" id="forgotError"></span>
						<button type="button" class="mybutton" id="subbutton"><i class="material-icons tooltip" toolmsg="Go">arrow_forward</i></button>
				</div>
				<div class="cmd4" style="padding: 0;"></div>
			</div>
			<br>
			<p style="font-size: 32px; color: #e74c3c;text-align: center;">Steps to Follow</p>
			<div class="row" style="text-align: center;">
				<div class="cmd3"><p style="font-size: 24px; color: #e74c3c;">1.</p><p style="font-size: 24px;">Enter Your<br>Email Address</p></div>
				<div class="cmd3"><p style="font-size: 24px; color: #e74c3c;">2.</p><p style="font-size: 24px;">Receive And<br>Check Our Mail</p></div>
				<div class="cmd3"><p style="font-size: 24px; color: #e74c3c;">3.</p><p style="font-size: 24px;">Follow<br>Given Instructions</p></div>
				<div class="cmd3"><p style="font-size: 24px; color: #e74c3c;">4.</p><p style="font-size: 24px;">Update Your<br>Password</p></div>
			</div>
			<br>
			<form action="UserForgotPasswordServlet" style="display:none;" method="post">
				<input type="text" name="txtEmailId" ng-model="mainemail"> <input
					type="submit" value="submit" id="mainbutton">
			</form>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script src="res/js/forgotpassword.js"></script>
</body>
</html>