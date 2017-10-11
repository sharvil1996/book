<%@page import="com.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books | BookBerries</title>
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
	<div
		style="position: fixed; width: 320px; top: 15px; background-color: white; box-shadow: 0 10px 30px rgba(0, 0, 0, 0.7); padding: 15px; z-index: 3; border-radius: 5px; left: 50%; margin-left: -160px; box-sizing: border-box;display:none;"
		class="filter">
		<p style="font-size: 24px;color:#e74c3c; display: inline-block; margin: 0;">Filter</p>
		<i class="material-icons filtercloser tooltip" toolmsg="Close"
			style="float: right; position: relative; top: 7px; cursor: pointer;color:#e74c3c;">close</i>
		<div style="height: 1px; background-color: #e74c3c; margin: 10px 0;"></div>
		<span style="font-size:14px;font-weight: 700;">Category</span>
		<div class="select catselect">
			<span class="selected-option"></span>
			<select class="catlist">
				<option value="All">All</option>
			</select>
			<div class="options" style="bottom:auto;top:100%;z-index:2;"></div>
			<i class="material-icons">arrow_drop_down</i>
		</div>
		<span style="font-size:14px;font-weight: 700;">Price</span>
		<input type="text" class="myinput" ng-model="pricemin" placeholder="From"/>
		<input type="text" class="myinput" ng-model="pricemax" placeholder="To"/>
		<input type="text" ng-model="category" style="display:none;" class="cathandle"/>
	</div>
	<div class="container" ng-controller="mainCtrl"
		style="text-align: center;">
		<div style="text-align: left;"><a href="#" class="filterbutton tooltip" toolmsg='Filter' style="background-color: #e74c3c; color: white; padding: 16px 15px 7px 15px;position:relative;left:20px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.5); border-radius:5px; z-index: 1;"><img src="res/imgs/filterred.svg" style="height: 20px;width: 20px;"/> <span style="position: relative;top:-4px;">&nbsp;Filter</span></a></div><br>
		<div class="wishbook" ng-repeat="book in books | bookfilter:pricemin:pricemax:category"
			style="padding: 5px 5px; overflow: hidden; width: 220px; display: inline-block; color: rgba(0, 0, 0, 0.87); cursor: pointer;">
			<div
				style="background-color: white; box-sizing: border-box; border: 10px solid white; height: 353px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.5); text-align: left; overflow: hidden; position: relative;">
				<div style="height: 260px; width: 200px; text-align: center;">
					<img ng-src="{{book.image}}"
						style="max-height: 260px; max-width: 200px; border-radius: 5px;" />
				</div>
				<span class="prodid" style="display: none;">{{book.id}}</span>
				<div style="position: relative;">
					<span
						style="white-space: nowrap; margin: 10px 0 0 0; display: block; font-weight: 700;">{{book.name}}</span>
					<span style="white-space: nowrap; display: block; color: grey;">by
						{{book.author}}, {{book.publisher}}</span> <span
						style="white-space: nowrap; display: block; color: grey;">Rs.
						{{book.price}}</span>
					<div class="paragraph-end"></div>
				</div>
			</div>
		</div>
	</div>
	<span id="idProvider" style="display: none;"><%=new ProductDAO().listOfProductId()%></span>
	<span id="nameProvider" style="display: none;"><%=new ProductDAO().listOfProductName()%></span>
	<span id="authorProvider" style="display: none;"><%=new ProductDAO().listOfProductAuthor()%></span>
	<span id="publisherProvider" style="display: none;"><%=new ProductDAO().listOfProductPublisher()%></span>
	<span id="priceProvider" style="display: none;"><%=new ProductDAO().listOfProductPrice()%></span>
	<span id="discountProvider" style="display: none;"><%=new ProductDAO().listOfProductDiscount()%></span>
	<span id="purchasesProvider" style="display: none;"><%=new ProductDAO().listOfProductPurchases()%></span>
	<span id="imageProvider" style="display: none;"><%=new ProductDAO().listOfProductImage()%></span>
	<span id="categoryProvider" style="display: none;"><%=new ProductDAO().listOfProductCategoryName()%></span>
	<span id="subCategoryProvider" style="display: none;"><%=new ProductDAO().listOfProductSubCategoryName()%></span>
	<span id="linkProvider" style="display: none;"><%=new ProductDAO().listOfLinks()%></span>
	<%@include file="footer.jsp"%>
	${msgFeedback}
	<%
		CustomerBean checkStatus = (CustomerBean) session
				.getAttribute("customerBean");
		String loginStatus = "0";
		if (checkStatus != null) {
	%><span id="logState" style="display: none;"><%=loginStatus = "1"%></span>
	<span id="custId" style="display: none;"><%=checkStatus.getCustomerId()%></span>
	<script src="res/js/books.js"></script>
	<%
		} else {
	%><span id="logState" style="display: none;"><%=loginStatus = "0"%></span>
	<script src="res/js/books.js"></script>
	<%
		}
	%>
</body>
</html>