<%@page import="com.dao.ForgotPasswordDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<%@include file="headerGuest.jsp"%>
	<%
		String forgotPasswordId = request.getParameter("forgotPasswordId");

		String customerId = request.getParameter("customerId");

		if (forgotPasswordId.length() == 30
				&& customerId.length() == 15
				&& new ForgotPasswordDAO().checkAuthentication(
						forgotPasswordId, customerId)) {
	%>
	<div class="container" style="text-align: center;">
		<div
			style="padding: 20px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); border-radius: 5px; background-color: white;">
			<br>
			<Br>
			<br>
			<Br>
			<br>
			<p style="color: #e74c3c; font-size: 24px;">Change your password
				here</p>
			<div class="row">
				<div class="cmd4" style="padding: 0;"></div>
				<div class="cmd4" style="text-align: center;">
					<form action="ResetPasswordServlet" id="mainform">
						<input type="hidden" name="forgotPasswordId"
							value="<%=forgotPasswordId%>"> <input type="hidden"
							name="customerId" value="<%=customerId%>">
						<div
							style="width: 100%; display: inline-block; position: relative;">
							<input type="password" placeholder="New Password" id="temppass"
								class="myinput" maxlength="80"
								style="width: 100%; box-sizing: border-box;"> <i
								class="material-icons showpass"
								style="position: absolute; top: 15px; right: 15px; opacity: 0.5; cursor: pointer;">remove_red_eye</i>
						</div>
						<span
							class="ermsg" id="str">Very Weak</span>
						<br> <input type="text" name="txtPassword" id="psbox"
							style="display: none;" maxlength="128" value="${txtPassword}">
						<span class="ermsg" id="passError"></span>
						<button type="submit" name="submit" value="submit"
							class="mybutton">
							<i class="material-icons tooltip" toolmsg="Go">arrow_forward</i>
						</button>
					</form>
				</div>
				<div class="cmd4" style="padding: 0;"></div>
			</div>
			<br>
			<Br>
			<br>
			<Br>
			<br>
			<Br>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	${password}
	<script src="res/js/changepassword.js"></script>
	<%
		}
	%>

</body>
</html>