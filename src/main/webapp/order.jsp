<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body style="background-image: url(res/imgs/defback.png);background-size:cover;">
	<%@include file="headerUser.jsp"%>
	<div class="container">
		<div class="row">
			<div class="cmd8" style="padding: 5px 3px 10px 1px;">
				<div
					style="background-color: white; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); padding: 15px;">
					<p style="margin: 0; color: #e74c3c; font-size: 24px;">Order
						Details</p>
					<br>
					<div class="orders">
					</div>
				</div>
			</div>
			<div class="cmd4" style="padding: 5px 1px 10px 3px;">
				<div
					style="background-color: white; border-radius: 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); padding: 15px;">
					<p style="margin: 0; color: #e74c3c; font-size: 24px;">Payment
						Details</p>
					<br> <span>Book(s) Price : </span> <span style="color: grey;" class="bookprice">Rs.
						0</span><br> <span style="font-size: 14px;">[+]Delivery
						Charges : </span> <span style="color: grey; font-size: 14px;" class="delcharge">Rs.
						0</span><br><span style="font-size: 14px;">[-]Discount : </span> <span style="color: grey; font-size: 14px;" class="discountcharge">Rs.
						0</span><br>
					<div style="height: 1px; background-color: #e74c3c; margin: 10px 0;"></div>
					<span style="font-size: 18px;">Amount Payable : </span> <span
						style="color: grey; font-size: 18px;" class="totalprice">Rs. 0</span><br> <br>
					<span>Payment Mode : </span> <span style="color: grey;">Cash
						On Delivery</span><br><span>Expected Delivery : </span> <span style="color: grey;">3 - 4 Business Days</span><br> <br> <span>Delivery Address </span><br>
					<span style="color: grey;"><%=customerBeanHeader.getCustomerAddress()%></span><br>
					<br> <span>Contact Information </span><br> <span
						style="color: grey;"><%=customerBeanHeader.getCustomerMobileNo()%></span><br>
					<br>
					<button type="button" class="mybutton placeorderbutton"
						style="text-align: center; width: 100%;">Place Order</button>
					<span id="custId" style="display: none;"><%=customerBeanHeader.getCustomerId()%></span>
				</div>
			</div>
		</div>
	</div>
	<form action="OrderInsertServlet" style="display:none;">
		<input type="text" name="txtProductId" id="prodids"> <input
			type="text" name="txtProductQty" id="prodqtys"> <input
			type="submit" id="submitbutton" name="Submit" value="Submit">
	</form>
	<%@include file="footer.jsp"%>
	<script src="res/js/order.js"></script>
</body>
</html>