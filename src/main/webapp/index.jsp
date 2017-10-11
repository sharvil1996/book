<%@page import="com.controller.LogControllerServlet"%>
<%@page import="com.dao.CustomerDAO"%>
<%@page import="com.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<% LogControllerServlet log = new LogControllerServlet();
log.call();%>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover; padding: 0;">
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
	<div style="width: 100%; background-color: #e74c3c; height: 100px;"></div>
	<div class="container"
		style="width: 100%; background-color: #e74c3c; box-shadow: 0 7px 20px rgba(0, 0, 0, 0.5);">
		<div class="gallery fade"
			style="width: 90%; margin: 20px auto; box-shadow: 0 7px 20px rgba(0, 0, 0, 0.5); max-width: 1300px;">
			<div class="images">
				<div class="active">
					<img src="res/imgs/gal1.png">
				</div>
				<div>
					<img src="res/imgs/gal2.png">
				</div>
				<div>
					<img src="res/imgs/gal3.png">
				</div>
				<div>
					<img src="res/imgs/gal4.png">
				</div>
			</div>
		</div>
	</div>
	<div class="cotainer" style="text-align: center; padding: 20px 0;">
		<div
			style="display: inline-block; height: 300px; width: 300px; margin: 20px; background-color: white; border-radius: 10px; box-shadow: 0 7px 10px rgba(0, 0, 0, 0.3); padding: 50px 0 20px 0;">
			<img style="height: 175px; width: 175px;" src="res/imgs/category.svg" /><br>
			<br> <span style="font-size: 28px; color: #e74c3c;">Explore
				Books by Category</span>
		</div>
		<div
			style="display: inline-block; height: 300px; width: 300px; margin: 20px; background-color: white; border-radius: 10px; box-shadow: 0 7px 10px rgba(0, 0, 0, 0.3); padding: 50px 0 20px 0;">
			<img style="height: 175px; width: 175px;" src="res/imgs/cashondelivery.svg" /><br>
			<br> <span style="font-size: 28px; color: #e74c3c;">Pay Cash <br>on Delivery</span>
		</div>
		<div
			style="display: inline-block; height: 300px; width: 300px; margin: 20px; background-color: white; border-radius: 10px; box-shadow: 0 7px 10px rgba(0, 0, 0, 0.3); padding: 50px 0 20px 0;">
			<img style="height: 175px; width: 175px;" src="res/imgs/filter.svg" /><br>
			<br> <span style="font-size: 28px; color: #e74c3c;">Filter by Certain Aspects</span>
		</div>
		<div
			style="display: inline-block; height: 300px; width: 300px; margin: 20px; background-color: white; border-radius: 10px; box-shadow: 0 7px 10px rgba(0, 0, 0, 0.3); padding: 50px 0 20px 0;">
			<img style="height: 175px; width: 175px;" src="res/imgs/track.svg" /><br>
			<br> <span style="font-size: 28px; color: #e74c3c;">Track Your Order Any Time</span>
		</div>
		<div
			style="display: inline-block; height: 300px; width: 300px; margin: 20px; background-color: white; border-radius: 10px; box-shadow: 0 7px 10px rgba(0, 0, 0, 0.3); padding: 50px 0 20px 0;">
			<img style="height: 175px; width: 175px;" src="res/imgs/books.svg" /><br>
			<br> <span style="font-size: 28px; color: #e74c3c;">Explore
				All Kind of Study Books</span>
		</div>
		<div
			style="display: inline-block; height: 300px; width: 300px; margin: 20px; background-color: white; border-radius: 10px; box-shadow: 0 7px 10px rgba(0, 0, 0, 0.3); padding: 50px 0 20px 0;">
			<img style="height: 175px; width: 175px;" src="res/imgs/support.svg" /><br>
			<br> <span style="font-size: 28px; color: #e74c3c;">All Time Support Guarantee</span>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	<script src="res/js/index.js"></script>
</body>
</html>