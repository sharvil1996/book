<%@page import="com.dao.LogDAO"%>
<%@page import="com.dao.CategoryDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Logs | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body>
	<%@include file="headerAdmin.jsp"%>
	<div class="container" ng-controller="mainCtrl">
		<div style="text-align: center;">
			<div style="display: inline-block; white-space: nowrap;">
				<span style="font-weight: 600;">From</span>&nbsp;&nbsp; <input
					type="text" placeholder="dd/mm/yyyy" class="myinput fromdate"
					style="width: 110px; text-align: center;" maxlength="10" />
			</div>
			&nbsp;&nbsp;
			<div style="display: inline-block; white-space: nowrap;">
				<span style="font-weight: 600;">To</span>&nbsp;&nbsp; <input
					type="text" placeholder="dd/mm/yyyy" class="myinput todate"
					style="width: 110px; text-align: center;" maxlength="10" />
			</div>
			&nbsp;&nbsp;
			<button type="button" class="mybutton"
				style="position: relative; top: -2px;">Go</button>
			<span class="ermsg" id="dateError"></span>
		</div>
		<input id="fromdate" type="text" style="display: none;"
			ng-model="fromdate" /><input type="text" style="display: none;"
			ng-model="todate" id="todate" />
		<div class="row">
			<div class="cmd3">
				<div class="responsive log0pres">
					<table class="table">
						<thead>
							<th style="width: 60%;">Date</th>
							<th style="width: 40%;">Visitors</th>
						</thead>
						<tbody>
							<tr ng-repeat="log in logs[0] | myfilter:fromdate:todate">
								<td>{{log.date}}</td>
								<td>{{log.cnt}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="cmd3">
				<div class="responsive log1pres">
					<table class="table">
						<thead>
							<th style="width: 60%;">Date</th>
							<th style="width: 40%;">Visitors</th>
						</thead>
						<tbody>
							<tr ng-repeat="log in logs[1] | myfilter:fromdate:todate">
								<td>{{log.date}}</td>
								<td>{{log.cnt}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="cmd3">
				<div class="responsive log2pres">
					<table class="table">
						<thead>
							<th style="width: 60%;">Date</th>
							<th style="width: 40%;">Visitors</th>
						</thead>
						<tbody>
							<tr ng-repeat="log in logs[2] | myfilter:fromdate:todate">
								<td>{{log.date}}</td>
								<td>{{log.cnt}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="cmd3">
				<div class="responsive log3pres">
					<table class="table">
						<thead>
							<th style="width: 60%;">Date</th>
							<th style="width: 40%;">Visitors</th>
						</thead>
						<tbody>
							<tr ng-repeat="log in logs[3] | myfilter:fromdate:todate">
								<td>{{log.date}}</td>
								<td>{{log.cnt}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<span id="dateProvider" style="display: none;"><%=new LogDAO().listOfLogDates()%></span>
	<span id="cntProvider" style="display: none;"><%=new LogDAO().listOfUserCnt()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/logs.js"></script>
</body>
</html>