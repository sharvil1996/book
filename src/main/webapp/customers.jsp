<%@page import="com.dao.CustomerDAO"%>
<%@page import="com.dao.CategoryDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customers | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body>
	<%@include file="headerAdmin.jsp"%>
	<div class="container" ng-controller="mainCtrl">
		<div style="text-align:center;">
		<input type="text" id="searchTrigger" class="myinput" placeholder="Search Customer" style="width:40%;"/>
		<input type="text" ng-model="search.name" id="nameSearch" style="display:none;"/>
		<input type="text" ng-model="search.email" id="emailSearch" style="display:none;"/>
		<input type="text" ng-model="search.address" id="addressSearch" style="display:none;"/>
		<input type="text" ng-model="search.mobile" id="mobileSearch" style="display:none;"/>
		<input type="text" ng-model="search.mobile2" id="mobile2Search" style="display:none;"/>
		<input type="text" ng-model="search.zipcode" id="zipcodeSearch" style="display:none;"/>
		<div class="select" style="width:110px;text-align:left;">
			<span class="selected-option"></span>
			<select class="searchCriteria">
			<option>by name</option>
			<option>by email</option>
			<option>by address</option>
			<option>by mobile</option>
			<option>by mobile 2</option>
			<option>by zip code</option>
			</select>
			<div class="options" style="bottom:auto;top:100%;"></div>
			<i class="material-icons">arrow_drop_down</i>
		</div>
		<span class="ermsg" id="custError"></span></div>
		<div class="responsive">
			<table class="table stripped">
				<thead>
					<th>Name</th>
					<th>Email</th>
					<th>Address</th>
					<th>Mobile</th>
					<th>Mobile 2</th>
					<th>Zip Code</th>
					<th>Is Admin?</th>
					<th>Delete?</th>
				</thead>
				<tbody>
					<tr ng-repeat="cust in customers | filter:search">
						<td><span class="custid" style="display:none;">{{cust.id}}</span>{{cust.name}}</td>
						<td>{{cust.email}}</td>
						<td>{{cust.address}}</td>
						<td>{{cust.mobile}}</td>
						<td>{{cust.mobile2}}</td>
						<td>{{cust.zipcode}}</td>
						<td><i class="material-icons modelbutton adminmodelbutton tooltip" toolmsg="Is Admin?" style="cursor:pointer;" target="adminmodel">{{cust.admin}}</i></td>
						<td><i class="material-icons deletebutton tooltip" style="cursor:pointer;" toolmsg="Remove">delete</i></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="model" id="adminmodel">
		<p>Are you sure, you want to update?</p>
		<i class="material-icons adminbutton tooltip" toolmsg="Yes">done</i>&nbsp;&nbsp;<i class="material-icons modelcloser tooltip" toolmsg="No">close</i>
	</div>
	<span id="idProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerId()%></span>
	<span id="nameProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerName()%></span>
	<span id="emailProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerEmail()%></span>
	<span id="mobileProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerMobileNo1()%></span>
	<span id="mobile2Provider" style="display:none;"><%=new CustomerDAO().listOfCustomerMobileNo2()%></span>
	<span id="addressProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerAddress()%></span>
	<span id="pincodeProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerPinCode()%></span>
	<span id="adminProvider" style="display:none;"><%=new CustomerDAO().listOfCustomerIsAdmin()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/customers.js"></script>
</body>
</html>