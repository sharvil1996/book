<%@page import="com.bean.CustomerBean"%>
<%@page import="com.bean.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%
	ProductBean productBean = (ProductBean) request
			.getAttribute("productBean");
	if (productBean != null) {
%>
<title><%=productBean.getProductName()%> | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<style type="text/css">
.actionbuttons .mybutton {
	font-size: 22px;
}
</style>
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
	<div class="model" id="errorModel">
		<p>Book can't be added to cart right now !</p>
		<i class="material-icons modelcloser tooltip" toolmsg="Close">done</i>
	</div>
	<div class="container" ng-controller="mainCtrl">
		<div class="messageholder"
			style="width: 100%; text-align: center; padding: 130px 0; display: none;">
			<i class="material-icons" style="font-size: 112px; color: #BBB;">do_not_disturb</i><br>
			<span style="font-size: 36px; color: #BBB;">Book Not Available
			</span>
		</div>
		<div class="bodyholder"
			style="background-color: white; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); padding: 25px 15px; display: none;">
			<div class="row">
				<div class="cl4"
					style="text-align: center; overflow: auto; padding: 25px 0;">
					<!-- <img ng-src="http://www.bookberries.co.in/Images/{{image}}" -->
					<img ng-src="<%=productBean.getLink() %>"
						style="max-height: 350px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);" />
				</div>
				<div class="cl8">
					<p style="font-size: 32px; font-weight: 700;"><%=productBean.getProductName()%>&nbsp;&nbsp;<i
							class="material-icons wishlistbutton"
							style="cursor: pointer; color: #e74c3c;">bookmark_border</i><span class="wishlabel" style="color:#e74c3c;font-size: 16px;font-weight: 400;position: relative;top:-6px;">Add to Wishlist</span>
					</p>
					<p style="color: grey">
						by
						<%=productBean.getProductAuthor()%>,
						<%=productBean.getProductPublisher()%></p>
					<div
						style="background-color: #e74c3c; height: 1px; margin: 15px 0;"></div>
					<p style=" display: inline-block;">
						<span style="font-size: 24px;" class="orgprice"></span>
						<span style="font-size: 18px;color:grey;"><strike>Rs. <%=productBean.getProductPrice()%></strike></span></p>
					<p style="float: right; color: grey; font-size: 22px;"><%=productBean.getProductStock()%>
						books available
					</p><br>
					<p style="font-size: 24px; display: inline-block;color:grey;">Save <%=productBean.getProductDiscount()%>%</p>
					<p style="font-size: 20px; font-weight: 700;">Description</p>
					<p style="color: grey;"><%=productBean.getProductDesc()%></p>
					<br>
					<p style="color: grey;">
						<span style="font-weight: 700;"><%=productBean.getProductPurchases()%></span>
						purchases
					</p>
					<p style="color: grey;">Cash On Delivery</p>
					<br>
					<div class="actionbuttons"></div>
					<br> <span style="display: none;" class="disc"><%=productBean.getProductDiscount()%></span>
					<span style="display: none;" class="price"><%=productBean.getProductPrice()%></span>
				</div>
			</div>
		</div>
	</div>
	<span id="proid" style="display: none;"><%=productBean.getProductId()%></span>
	<span id="proimg" style="display: none;"><%=productBean.getProductImage()%></span>
	<%@include file="footer.jsp"%>
	<%
		CustomerBean checkStatus = (CustomerBean) session
					.getAttribute("customerBean");
			String loginStatus = "0";
			if (checkStatus != null) {
	%><span id="logState" style="display: none;"><%=loginStatus = "1"%></span>
	<span id="custid" style="display: none;"><%=checkStatus.getCustomerId()%></span>
	<script src="res/js/bookdetails.js"></script>
	<%
		} else {
	%><span id="logState" style="display: none;"><%=loginStatus = "0"%></span>
	<script src="res/js/bookdetails.js"></script>
	<%
		}
		} else {
	%>
	<%
		}
	%>
</body>
</html>