
<%@page import="com.bean.CustomerBean"%>
<%@page import="com.bean.PincodeBean"%>
<%@page import="com.dao.PincodeDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="linker.jsp"%>
<title>Register | BookBerries</title>
</head>
<body
	style="background-image: url(res/imgs/registerback.png); background-size: cover;">

	<%@include file="headerGuest.jsp"%>
	<div class="container">
		<div class="row">
			<div class="cmd5" style="overflow: hidden;">
				<div class="rightpanel">
					<p style="font-size: 32px;color:#e74c3c;">Register</p>
					<div style="height: 2px;background-color: #e74c3c;margin: 10px 0;"></div>
					<form action="CustomerInsertServlet" method="post"
						name="CustomerForm" id="mainform">
						<input type="text" value="${txtFirstName}"
							placeholder="First Name" name="txtFirstName" class="myinput"
							id="fnbox" maxlength="15"> <span class="ermsg"
							id="firstNameError"></span> <input type="text"
							value="${txtLastName}" placeholder="Last Name" name="txtLastName"
							class="myinput" id="lnbox" maxlength="15"> <span
							class="ermsg" id="lastNameError"></span><input type="text"
							value="${txtEmail}" placeholder="Email" name="txtEmail"
							class="myinput" id="emailbox" maxlength="50"> <span
							class="ermsg" id="emailError"></span>
						<div style="width: 85%; display: inline-block;position:relative;">
							<input type="password" placeholder="Password" id="temppass"
								class="myinput" maxlength="80" style="width:100%;">
								<i class="material-icons showpass" style="position:absolute;top:15px;right:-30px;opacity:0.5;cursor:pointer;">remove_red_eye</i>
						</div>
						<span
							class="ermsg" id="str">Very Weak</span>
						<input type="text" name="txtPassword" value="${pwd}" id="psbox"
							style="display: none;" maxlength="128"> <span
							class="ermsg" id="passwordError"></span>
						<textarea rows="3" name="txtAddress" maxlength="255"
							placeholder="Address" class="myinput" id="addressbox">${txtAddress}</textarea>
						<span class="ermsg" id="addressError"></span> <input type="text"
							value="${txtmobileNo}" placeholder="Mobile 1" name="txtMobileNo"
							class="myinput" id="mobbox" maxlength="10"><span
							class="ermsg" id="mobile1Error"></span> <input type="text"
							value="${txtmobileNo1}" placeholder="Mobile 2"
							name="txtMobileNo1" class="myinput" id="mob2box" maxlength="10"><span
							class="ermsg" id="mobile2Error"></span>
						<div class="select">
							<span class="selected-option"></span>
							<ul class="options"></ul>
							<i class="material-icons">arrow_drop_down</i> <select
								name="selPincode" id="selPincode">
								<option value="def" selected="selected">Select Zip Code</option>
								<%
									PincodeDAO pincodeDAO = new PincodeDAO();

															List<PincodeBean> pincodeList = pincodeDAO.list();
										
																			
															for(int i=0;i<pincodeList.size();i++)
															{
																					
														String tmp="unselected";
														String type=request.getParameter("selPincode");
														if(type==null)
														tmp="unselected";
														else if(pincodeList.get(i).getPincodeId().equals(type))
														tmp="selected";
								%>

								<option value=<%=pincodeList.get(i).getPincodeId()%> <%=tmp%>>
									<%=pincodeList.get(i).getPincodeNumber()%></option>
								<%
									}
								%>
							</select>
						</div>
						<span class="ermsg" id="pinCodeError"></span><br>
						<button type="submit" name="submit" class="mybutton">
							<i class="material-icons tooltip" toolmsg="Go">arrow_forward</i>
						</button>
						<br>Already have an account, <a href="login.jsp"
							class="linkbutton">Login Now</a>
					</form>
				</div>
			</div>
			<div class="cmd7" style="padding: 0; overflow: hidden;"></div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	${firstName}${lastName}${email}${password}${address}${address}
	${mobileNo}${mobileNo2} ${pincode}
	<script src="res/js/register.js"></script>

</body>
</html>