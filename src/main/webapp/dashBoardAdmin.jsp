<%@page import="com.dao.SuggestionDAO"%>
<%@page import="com.dao.SubCategoryDAO"%>
<%@page import="com.dao.ProductDAO"%>
<%@page import="com.dao.PincodeDAO"%>
<%@page import="com.dao.OrdersDAO"%>
<%@page import="com.dao.LogDAO"%>
<%@page import="com.dao.CustomerDAO"%>
<%@page import="com.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body style="background-color: rgba(0, 0, 0, 0.05)">
	<style type="text/css">
.dashlink {
	color: rgba(0, 0, 0, 0.87);
	transition: all 0.3s ease;
}

.dashlink:hover,.dashlink:focus {
	color: #e74c3c;
	transition: all 0.3s ease;
}

.dashlink span:first-child {
	font-size: 44px;
	font-weight: 700;
}

.dashlink span:nth-child(2) {
	font-size: 20px;
	color: grey;
}

.dashlink:hover span:nth-child(2),.dashlink:focus span:nth-child(2) {
	color: rgba(255, 51, 51, 0.87);
}
</style>
	<%@include file="headerAdmin.jsp"%>
	<br>
	<div class="container" style="padding: 30px;text-align: center;">
		<p style="font-size: 48px; color: #e74c3c; display: inline-block;background-color: white;box-shadow:0px 2px 3px rgba(0,0,0,0.3);border-radius:5px;padding:7px 15px;">Dashboard</p>
    <br> <a href="customers.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new CustomerDAO().noOfCustomer()%></span> <span>Customers</span>
		</a> <a href="products.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new ProductDAO().noOfProduct()%></span> <span>Products</span>
		</a> <a href="category.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span style="font-size: 44px; font-weight: 700;"><%=new CategoryDAO().noOfCategory()%></span>
			<span>Categories</span>
		</a> <a href="orders.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new OrdersDAO().noOfOrders()%></span> <span>Orders</span>
		</a> <a href="orders.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new OrdersDAO().noOfOrdersNotDispatched()%></span> <span>Undispatched
				Orders</span>
		</a> <a href="orders.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new OrdersDAO().noOfOrdersNotDelivered()%></span> <span>Undelivered
				Orders</span>
		</a> <a href="zipcode.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new PincodeDAO().noOfPincode()%></span> <span>Allowed
				Zip Codes</span>
		</a> <a href="feedbacks.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new SuggestionDAO().noOfSuggestion()%></span> <span>Feedbacks</span>
		</a> <a href="feedbacks.jsp" class="dashlink"
			style="text-align: left; background-color: white; margin: 7px 4px; white-space: nowrap; width: 250px; display: inline-block; padding: 4px 20px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3);">
			<span><%=new SuggestionDAO().noOfSuggestionNotAnswered()%></span> <span>Unanswered
				Feedbacks</span>
		</a>
	</div>
	<br><br>
	<%@include file="footer.jsp"%>
	<script src="res/js/dashboard.js"></script>
</body>
</html>