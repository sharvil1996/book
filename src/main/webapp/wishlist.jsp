<%@page import="com.dao.WishlistDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wishlist | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body style="background-image: url(res/imgs/defback.png);background-size:cover;")>
	<%
		CustomerBean customerBean = (CustomerBean) session
				.getAttribute("customerBean");

		if (customerBean != null) {
	%>
	<%@include file="headerUser.jsp"%>
	<div class="container" ng-controller="mainCtrl"
		style="text-align: center;">
		<span class="ermsg" id="wishlistError"></span>
		<div class="messageholder" style="display: none; padding: 130px 0;">
			<i class="material-icons" style="font-size: 112px; color: #BBB;">do_not_disturb</i><br>
			<span style="font-size: 36px; color: #BBB;">Nothing Appear<br>Right
				Now
			</span>
		</div>
		<div class="bodyholder" style="display: none;">
			<div class="wishbook" ng-repeat="wish in wishlist"
				style="padding: 3px 5px; overflow: hidden; width: 220px; display: inline-block; cursor: pointer; color: rgba(0, 0, 0, 0.87); cursor: pointer;position: relative;">
				<div
					style="background-color: white; box-sizing: border-box; border: 10px solid white; height: 320px; border-radius: 5px; text-align: left; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.5); overflow: hidden; position: relative;">
					<div style="height: 260px; width: 200px; text-align: center;">
						<img ng-src="http://www.bookberries.co.in/Images/{{wish.image}}"
							style="max-height: 260px; max-width: 200px; border-radius: 5px;" />
					</div>
					<span class="wishid" style="display: none;">{{wish.id}}</span> <span
						class="prodid" style="display: none;">{{wish.productid}}</span>
					<div class="deletebutton"
						style="display: inline-block; padding: 10px; position: absolute; top: 5px; right: 5px; background-color: white; border-radius: 100px; box-shadow: 0px 2px 3px rgba(0, 0, 0, 0.3);">
						<i class="material-icons tooltip" toolmsg="Remove">delete</i>
					</div>
					<div style="position: relative;">
						<span
							style="white-space: nowrap; margin: 10px 0 0 0; display: block; font-weight: 700;">{{wish.productname}}</span>
						<div class="paragraph-end"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<span id="idProvider" style="display: none;"><%=new WishlistDAO().listOfWishlistId(customerBean
						.getCustomerId())%></span>
	<span id="imageProvider" style="display: none;"><%=new WishlistDAO()
						.listOfWishlistIsProductImage(customerBean
								.getCustomerId())%></span>
	<span id="productIdProvider" style="display: none;"><%=new WishlistDAO()
						.listOfWishlistProductId(customerBean.getCustomerId())%></span>
	<span id="productNameProvider" style="display: none;"><%=new WishlistDAO()
						.listOfWishlistProductName(customerBean.getCustomerId())%></span>
	<span id="custId" style="display: none;"><%=customerBean.getCustomerId()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/wishlist.js"></script>
	<%
		}
	%>
</body>
</html>