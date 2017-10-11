<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/loginback.png); background-size: cover;">
	<%@include file="headerGuest.jsp"%>
	<div class="container">
		<div class="row">
			<div class="cmd7" style="paKdding: 0; overflow: hidden;"></div>
			<div class="cmd5" style="overflow: hidden;">
				<div class="rightpanel">
					<form action="LoginController" method="post" class="mainform">
						<%
							String refer = request.getParameter("refer");
							if (refer != null) {
						%>
						<input type="hidden" name="refer" id="refer"
							value=<%=refer%>>
						<%
							}
						%>
						<div style="font-size: 32px; color: #e74c3c;">Login</div>
						<div style="height:2px; background-color: #e74c3c;margin:10px 0;"></div>
						<br> <br> <input type="text" name="txtEmail"
							placeholder="Email" class="myinput" id="embox" maxlength="50"
							value="${txtEmail}" /><br> <input type="password"
							placeholder="Password" id="temppass" class="myinput"
							maxlength="80" /><input type="text" name="txtPassword"
							id="psbox" style="display: none;" /> <span class="ermsg"
							id="loginError"></span>
						</label><br> <br>
						<button type="submit" class="mybutton">
							<i class="material-icons tooltip" toolmsg="Go">arrow_forward</i>
						</button>
						<br> <br>
						<a href="forgotPassword.jsp" class="linkbutton">Forgot Password
							?</a>
						<br> Not have an account, <a href="register.jsp"
							class="linkbutton">Register Now</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	${msgUser}${email}${password}${msgReg}
	<script src="res/js/login.js"></script>
</body>
</html>