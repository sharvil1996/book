<%@page import="com.dao.OrdersDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Orders | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<%@include file="headerAdmin.jsp"%>
	<div class="messageholder container"
		style="display: none; padding: 130px 0; text-align: center;">
		<i class="material-icons" style="font-size: 112px; color: #BBB;">do_not_disturb</i><br>
		<span style="font-size: 36px; color: #BBB;">Nothing Appear<br>Right
			Now
		</span>
	</div>
	<div class="container bodyholder" ng-controller="mainCtrl"
		style="display: none;">
		<div style="text-align: center;">
			<input type="text" class="myinput" style="width:250px;" placeholder="Search by Ticket" maxlength="15" ng-model="searchticket"/>
			<div class="select" style="text-align: left; width: 120px;">
				<span class="selected-option"></span> <select
					ng-model="dispatchlist">
					<option value="">All</option>
					<option value="done">Dispatched</option>
					<option value="close">Undispatched</option>
				</select>
				<div class="options" style="bottom: auto; top: 100%;"></div>
				<i class="material-icons">arrow_drop_down</i>
			</div>
			<div class="select" style="text-align: left; width: 120px;">
				<span class="selected-option"></span> <select ng-model="deliverlist">
					<option value="">All</option>
					<option value="done">Delivered</option>
					<option value="close">Undelivered</option>
				</select>
				<div class="options" style="bottom: auto; top: 100%;"></div>
				<i class="material-icons">arrow_drop_down</i>
			</div>
			<div class="select" style="text-align: left; width: 120px;">
				<span class="selected-option"></span> <select ng-model="cancellist">
					<option value="">All</option>
					<option value="done">Cancelled</option>
					<option value="close">Non-cancelled</option>
				</select>
				<div class="options" style="bottom: auto; top: 100%;"></div>
				<i class="material-icons">arrow_drop_down</i>
			</div>
		</div>
		<div style="text-align: center;">
			<span class="ermsg" id="orderError"></span>
		</div>
		<div class="responsive">
			<table class="table">
				<thead>
					<th>Ticket</th>
					<th>Date</th>
					<th>Name</th>
					<th>Product(s)</th>
					<th>Quantity</th>
					<th>Price</th>
					<th>Email</th>
					<th>Address</th>
					<th>Mobile</th>
					<th>Is Dispatched?</th>
					<th>Is Delivered</th>
					<th>Is Canceled?</th>
				</thead>
				<tbody>
					<tr
						ng-repeat="order in orders | myfilter:cancellist:dispatchlist:deliverlist | filter:searchticket">
						<td class="orderid">{{order.id}}</td>
						<td>{{order.date}}</td>
						<td>{{order.name}}</td>
						<td><span ng-repeat="pro in order.products">{{pro}}<br></span></td>
						<td><span ng-repeat="q in order.qty">{{q}}<br></span></td>
						<td>{{order.price}}</td>
						<td>{{order.email}}</td>
						<td>{{order.address}}</td>
						<td>{{order.mobile}}</td>
						<td class="disid"><i class="material-icons dispatchbutton tooltip" toolmsg="Is Dispatched?"
							style="cursor: pointer;">{{order.dispatch}}</i></td>
						<td class="delid"><i class="material-icons deliverbutton tooltip" toolmsg="Is Delivered?"
							style="cursor: pointer;">{{order.deliver}}</i></td>
						<td><i class="material-icons">{{order.cancel}}</i></td>
					</tr>
				</tbody>
			</table>
		</div>
		<br> <br> <br> <br> <br> <br> <br>
		<br> <br> <br> <br> <br> <br> <br>
	</div>
	<span id="idProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersId()%></span>
	<span id="nameProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersCustomerName()%></span>
	<span id="productNameProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersProductName()%></span>
	<span id="qtyProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersQty()%></span>
	<span id="priceProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersPrice()%></span>
	<span id="dateProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersDate()%></span>
	<span id="isCancelledProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersIsCancelled()%></span>
	<span id="isDispatchedProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersIsDispatched()%></span>
	<span id="isDeliveredProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersIsDelivered()%></span>
	<span id="emailProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersCustomerEmail()%></span>
	<span id="addressProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersCustomerAddress()%></span>
	<span id="mobileProvider" style="display: none;"><%=new OrdersDAO().listOfOrderscustomerMobileNo()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/orders.js"></script>
</body>
</html>