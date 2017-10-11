<%@page import="com.dao.CategoryDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category & Subcategory</title>
<%@include file="linker.jsp"%>
</head>
<body style="background-color: rgba(0,0,0,0.03);">
	<%@include file="headerAdmin.jsp"%>
	<div class="container" ng-controller="mainCtrl">
		<div class="row">
			<div class="cmd5" style="text-align: center;">
				<input type="text" class="myinput"
					placeholder="Enter Category to Add" style="width: 55%;"
					maxlength="50" id="catbox" />
				<button type="button" class="mybutton" id="addcatbutton"
					style="position: relative; top: 5px; padding: 7px;">
					<i class="material-icons tooltip" toolmsg="Add">add</i>
				</button>
				<span class="ermsg" id="cataddError"></span>
				<div class="list">
					<div class="header">Categories</div>
					<ul class="content catlist">
						<li ng-repeat="cat in category"><span class="catname">{{cat.name}}</span>
							<span class="catid" style="display: none;">{{cat.id}}</span> <input
							type="text" placeholder="Category Name" class="myinput small"
							style="padding: 1px 10px; margin: 0; text-align: center; width: 150px; display: none;"
							maxlength="50" />
							<div style="float: right;">
								<i class="material-icons cateditbutton tooltip" toolmsg="Edit">edit</i> <i
									class="material-icons catdeletebutton tooltip" toolmsg="Remove">delete</i> <i
									class="material-icons catdonebutton tooltip" toolmsg="Update" style="display: none;">done</i>
								<i class="material-icons catclosebutton tooltip" toolmsg="Cancel" style="display: none;">close</i>
							</div></li>
					</ul>
				</div>
			</div>
			<div class="cmd2" style="padding: 0;"></div>
			<div class="cmd5" style="text-align: center;">
				<input type="text" class="myinput"
					placeholder="Enter Subcategory to Add" style="width: 55%;"
					maxlength="50" id="subcatbox" />
				<button type="button" class="mybutton" id="addsubcatbutton"
					style="position: relative; top: 5px; padding: 7px;">
					<i class="material-icons tooltip" toolmsg="Add">add</i>
				</button>
				<span class="ermsg" id="subcataddError"></span>
				<div class="list notactive">
					<div class="header">Subcategories</div>
					<ul class="content subcatlist">
					</ul>
				</div>
			</div>
		</div>
	</div><br><br><br><br><br><br>
	<span id="catIdProvider" style="display: none;"><%=new CategoryDAO().listOfCategoryId()%></span>
	<span id="catNameProvider" style="display: none;"><%=new CategoryDAO().listOfCategoryName()%></span>
	<%@include file="footer.jsp"%>
	<script src="res/js/category.js"></script>
</body>
</html>