<%@page import="com.dao.SuggestionDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedbacks | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body style="background-image: url(res/imgs/defback.png);background-size:cover;">
	<%@include file="headerAdmin.jsp"%>
	${failFeedback}
	<div class="messageholder container"
		style="display: none; padding: 130px 0;text-align: center;margin:0 auto;">
		<i class="material-icons" style="font-size: 112px; color: #BBB;">do_not_disturb</i><br>
		<span style="font-size: 36px; color: #BBB;">Nothing Found<br>Right
			Now
		</span>
	</div>
	<div class="container bodyholder" ng-controller="mainCtrl" style="display: none;margin:0 auto;">
		<h4 style="display: inline-block; margin: 0;">Show Feedbacks</h4>
		<div class="select" style="text-align: left; width: 115px;">
			<span class="selected-option"></span> <select class="filterOption">
				<option>All</option>
				<option>Answered</option>
				<option>Unanswered</option>
			</select>
			<div class="options" style="top: 100%; bottom: auto;"></div>
			<i class="material-icons">arrow_drop_down</i>
		</div>
		<input type="text" ng-model="search.answer" class="filterBox"
			style="display: none;" />
		<div class="row">
			<div class="cmd6">
				<div class="list" style="margin-top: 0;">
					<div class="header">Feedbacks</div>
					<ul class="content suglist"
						style="max-height: 450px; overflow: auto;">
						<li ng-repeat="sug in suggestions | filter:search"><span
							class="sugid" style="display: none;">{{sug.id}}</span>
							<h4 style="margin: 10px 0;">
								<i class="deletebutton material-icons tooltip" toolmsg="Remove"
									style="float: right;{{(sug.answer=='1')?'':'display:none'}}">{{(sug.answer=="1")?"delete":""}}</i>
								<b class="custname">{{sug.custname}}</b><b class="answerType"
									style="float: right;">{{(sug.answer=="1")?"Answered":"Unanswered"}}&nbsp;&nbsp;</b>
							</h4> <i>&quot;{{sug.desc}}&quot;</i></li>
					</ul>
				</div>
			</div>
			<form action="AdminSendFeedBack">
				<div class="cmd6" style="text-align: center;">
					<input name="suggestionId" type="text" class="replysugid"
						style="display: none;" />
					<textarea name="suggestionSubject" class="myinput replybox"
						style="margin-top: 0; width: 80%;" placeholder="Reply here"
						rows="15"></textarea>
					<button type="submit" class="mybutton">
						<i class="material-icons tooltip" toolmsg="Send">arrow_forward</i>
					</button>
				</div>
			</form>
		</div>
	</div>
	<span id="sugIdProvider" style="display: none;"><%=new SuggestionDAO().listOfSuggestionId()%></span>
	<span id="custNameProvider" style="display: none;"><%=new SuggestionDAO().listOfCustomerName()%></span>
	<span id="descProvider" style="display: none;"><%=new SuggestionDAO().listOfSuggestionDesc()%></span>
	<span id="isAnsweredProvider" style="display: none;"><%=new SuggestionDAO().listOfsuggestionIsAnswered()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/feedbacks.js"></script>
</body>
</html>