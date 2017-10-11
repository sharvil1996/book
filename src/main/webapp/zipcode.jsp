<%@page import="com.dao.PincodeDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Zip Code | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body>
	<%@include file="headerAdmin.jsp"%>
	<div class="container">
		<div class="row">
			<div class="cmd4" style="padding: 0;"></div>
			<div class="cmd4" style="text-align: center;">
				<input type="text" class="myinput"
					placeholder="Enter Zip Code to Add" style="width: 55%;"
					maxlength="6" id="pinbox" />
				<button type="button" class="mybutton" id="addbutton"
					style="position: relative; top: 5px; padding: 7px;">
					<i class="material-icons tooltip" toolmsg="Add">add</i>
				</button>
				<span class="ermsg" id="pinaddError"></span>
				<div class="responsive">
					<table class="table stripped">
						<th>Zip Code(s)</th>
						<tbody ng-controller="mainCtrl">
							<tr ng-repeat="zip in zipcodes">
								<td><span class="mainzip">{{zip.code}}</span><span
									style="display: none;">{{zip.id}}</span> <input type="text"
									placeholder="Zip Code" class="myinput small"
									style="padding: 1px 10px; margin: 0; text-align: center; width: 70px; display: none;"
									maxlength="6" />
									<div style="display: inline-block; float: right;">
										<i class="material-icons editbutton tooltip" toolmsg="Edit"
											style="position: relative; top: 3px; cursor: pointer;">edit</i>
										<i class="material-icons deletebutton tooltip"
											toolmsg="Remove"
											style="position: relative; top: 3px; cursor: pointer;">delete</i>
										<i class="material-icons donebutton tooltip" toolmsg="Update"
											style="position: relative; top: 3px; cursor: pointer; display: none;">done</i>
										<i class="material-icons closebutton tooltip" toolmsg="Cancel"
											style="position: relative; top: 3px; cursor: pointer; display: none;">close</i>
									</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="cmd4" style="padding: 0;"></div>
		</div>
		<span id="numberProvider" style="display: none;"><%=new PincodeDAO().listOfpincodeNumber()%></span>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="footer.jsp"%>
	<script src="res/js/zipcode.js"></script>
</body>
</html>