<%@page import="com.dao.CustomerDAO"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CategoryDAO"%>
<%@page import="com.bean.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Feedback | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body>
	<%@include file="headerUser.jsp"%>
	<div class="container">
		<p style="font-size: 36px; color: #e74c3c;">Tell us, How can we
			make our service better ?</p>
		<br>
		<div class="row">
			<div class="cmd6">
				<form action="SuggestionInsertServlet" method="post"
					name="subCategoryForm" id="mainform">
					<div style="position: relative">
						<textarea rows="10" name="txtSuggestionDesc" maxlength="255"
							class="myinput" id="sugbox">${txtSuggestionDesc}</textarea>
						<span
							style="color: grey; position: absolute; bottom: 15px; right: 55px; padding: 3px 5px; box-shadow: 0 2px 3px rgba(0, 0, 0, 0.3); background-color: white; border-radius: 5px;"><span
							class="remainchars" style="font-size: 20px; font-weight: 700;">0</span>
							/ 255</span>
					</div>
					<span class="ermsg" id="feedbackError"></span>
					<button type="submit" class="mybutton">
						<i class="material-icons tooltip" toolmsg="Send">send</i>
					</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
	<%@include file="footer.jsp"%>
	<script src="res/js/feedback.js"></script>
	${suggestionDesc}
</body>
</html>