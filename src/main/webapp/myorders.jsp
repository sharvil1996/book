<%@page import="com.dao.OrdersDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Orders | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">
	<%@include file="headerUser.jsp"%>
	<%
		CustomerBean customerBean = (CustomerBean) session
				.getAttribute("customerBean");
	%>
	<div class="model" id="errormodel">
		<p>Order can not be deleted right now !</p>
		<i class="material-icons modelcloser tooltip" toolmsg="Ok">done</i>
	</div>
	<div class="model" id="cancelmodel">
		<p>Are you sure, you want to cancel order?</p>
		<i class="material-icons cancelfinalbutton tooltip" toolmsg="Yes">done</i>&nbsp;&nbsp;<i
			class="material-icons modelcloser tooltip" toolmsg="No">close</i>
	</div>
	<div class="model" id="deletemodel">
		<p>Are you sure, you want to delete order?</p>
		<i class="material-icons deletefinalbutton tooltip" toolmsg="Yes">done</i>&nbsp;&nbsp;<i
			class="material-icons modelcloser tooltip" toolmsg="No">close</i>
	</div>
	<div class="container" ng-controller="mainCtrl">
		<div class="messageholder"
			style="display: none; padding: 130px 0; text-align: center; width: 100%;">
			<i class="material-icons" style="font-size: 112px; color: #BBB;">do_not_disturb</i><br>
			<span style="font-size: 36px; color: #BBB;">Nothing Ordered<br>Right
				Now
			</span>
		</div>
		<div class="bodyholder" style="display: none;">
			<div
				style="padding: 15px 25px; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); background-color: white;">
				<p style="font-size: 36px; color: #e74c3c;">My Orders</p>
			</div>
			<div ng-repeat="order in orders"
				style="padding: 15px 25px; margin-top: 15px; position: relative; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); background-color: white; overflow: hidden;">
				<div class="row">
					<div class="cmd4">
						<span style="font-weight: 700;">Order Ticket :</span><span
							style="color: grey;"> {{order.id}}</span>
					</div>
					<div class="cmd4">
						<span style="font-weight: 700;">Ordered On :</span><span
							style="color: grey;"> {{order.date}}</span>
					</div>
					<div class="cmd4">
						<span style="font-weight: 700;">Order Status :</span><span
							style="color: grey;"> {{order.status}}</span>
					</div>
				</div>
				<br>
				<p style="font-size: 22px; font-weight: 700;">Book Details</p>
				<div class="reponsive">
					<table class="table stripped">
						<thead>
							<th>Book Name</th>
							<th>Quantity</th>
							<th>Price(Rs.)</th>
							<th>Discount(Rs.)</th>
							<th>Gross Price(Rs.)</th>
						</thead>
						<tbody>
							<tr>
								<td><span ng-repeat="pro in order.products">{{pro}}<br></span></td>
								<td><span ng-repeat="q in order.qty">{{q}}<br></span></td>
								<td><span ng-repeat="ind in order.indpricenormal">{{ind}}<br></span></td>
								<td><span ng-repeat="ind in order.inddiscountnormal">{{ind}}<br></span></td>
								<td><span ng-repeat="ind in order.indprice">{{ind}}<br></span></td>
							</tr>
							<tr style="background-color: white;">
								<td></td>
								<td></td>
								<td style="text-align: right;"><span
									style="font-weight: 700;">Delivery Charges</span></td>
								<td><span style="color: grey;">{{order.delcharge}}</span></td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td style="text-align: right;"><span
									style="font-weight: 700;">Discount</span></td>
								<td><span style="color: grey;">{{order.discount}}</span></td>
							</tr>
							<tr  style="background-color: white;">
								<td></td>
								<td></td>
								<td style="text-align: right;"><span
									style="font-weight: 700;">Total Payable</span></td>
								<td><span style="color: grey;">{{order.price}}</span></td>
							</tr>
						</tbody>
					</table>
				</div>
				<span class="orderid" style="display: none;">{{order.id}}</span>
				<button type="button" class="mybutton {{order.buttonclass}}"
					style="{{order.buttonstyle}}">{{order.button}}</button>
				<div
					style="height: 5px; background-color: #e74c3c; position: absolute; bottom: 0px; left: 0; width: {{order.statusWidth}}"></div>
			</div>
		</div>
	</div>
	<span id="idProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersId(customerBean
					.getCustomerId())%></span>
	<span id="productNameProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersProductName(customerBean
					.getCustomerId())%></span>
	<span id="qtyProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersQty(customerBean
					.getCustomerId())%></span>
	<span id="individualPriceProvider" style="display: none;"><%=new OrdersDAO().listOfIndividualPrice(customerBean
					.getCustomerId())%></span>
	<span id="individualPriceNormalProvider" style="display: none;"><%=new OrdersDAO().listOfIndividualPriceNormal(customerBean
					.getCustomerId())%></span>
	<span id="individualDiscountProvider" style="display: none;"><%=new OrdersDAO().listOfIndividualDiscount(customerBean
					.getCustomerId())%></span>
	<span id="priceProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersPrice(customerBean
					.getCustomerId())%></span>
	<span id="dateProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersDate(customerBean
					.getCustomerId())%></span>
	<span id="isCancelledProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersIsCancelled(customerBean
					.getCustomerId())%></span>
	<span id="isDispatchedProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersIsDispatched(customerBean
					.getCustomerId())%></span>
	<span id="isDeliveredProvider" style="display: none;"><%=new OrdersDAO().listOfOrdersIsDelivered(customerBean
					.getCustomerId())%></span>
	<span id="custId" style="display: none;"><%=customerBean.getCustomerId()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/myorders.js"></script>
</body>
</html>