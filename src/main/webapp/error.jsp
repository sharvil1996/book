<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<%
		CustomerBean checkHeader = (CustomerBean) session
				.getAttribute("customerBean");
		if (checkHeader != null
				&& checkHeader.getCustomerIsAdmin().equals("y")) {
	%>
	<%@include file="headerAdmin.jsp"%>
	<%
		} else if (checkHeader != null
				&& checkHeader.getCustomerIsAdmin().equals("n")) {
	%>
	<%@include file="headerUser.jsp"%>
	<%
		} else {
	%>
	<%@include file="headerGuest.jsp"%>
	<%
		}
	%>

	<%
		String temp = (String) request.getAttribute("errorMsg");
		temp = "<script>showToast('" + temp + "');</script>";
	%>
	<div class="container" style="text-align: center;">
		<br>
		<br> <img src="res/imgs/confused.svg"
			style="width: 200px; height: 200px;" />
		<p style="font-size: 48px; color: #e74c3c;">Oops !!!</p>
		<p style="font-size: 24px; color: #e74c3c;">
			Sorry, there are some technical issues<br>and we are working on
			them
		</p>
		<br>
		<br>
	</div>
	<%@include file="footer.jsp"%>
	<%=temp%>
</body>
</html>