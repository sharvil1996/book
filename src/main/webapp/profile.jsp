

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
<title>Profile | BookBerries</title>
<%@include file="linker.jsp"%>
</head>
<body
	style="background-image: url(res/imgs/defback.png); background-size: cover;">

	<%
		CustomerBean checkHeader = (CustomerBean) session
			.getAttribute("customerBean");
		
		if(checkHeader == null)
			response.sendRedirect("login.jsp");

		
			if (checkHeader != null
			&& checkHeader.getCustomerIsAdmin().equals("y")) {
	%>
	<%@include file="headerAdmin.jsp"%>
	<%
		} else if (checkHeader != null
			&& checkHeader.getCustomerIsAdmin().equals("n")) {
	%>
	<%@include file="headerUser.jsp"%>
	<%
		}
	%>


	<%
		CustomerBean customerBean = (CustomerBean) session
			.getAttribute("customerBean");

		
			if (customerBean != null) {
	%>
	<div class="container">
		<p style="color: #e74c3c; font-size: 48px;">
			Profile&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i
				class="material-icons editbutton tooltip" toolmsg="Edit Profile"
				style="font-size: 32px; cursor: pointer;">edit</i>
		</p>
		<div class="row">
			<div class="detailsbox">
				<div style="display: inline-block;">
					<span style="color: #e74c3c;">Name</span><br> <span
						style="font-size: 32px;"><%=customerBean.getCustomerFname()%>
						<%=customerBean.getCustomerLname()%></span>
				</div>
				<br> <br>
				<div style="display: inline-block;">
					<span style="color: #e74c3c;">Email</span><br> <span><%=customerBean.getCustomeEmail()%></span>
				</div>
				<br> <br>
				<div style="display: inline-block;">
					<span style="color: #e74c3c;">Address</span><br> <span><%=customerBean.getCustomerAddress()%>
					</span>
				</div>
				<br> <br>
				<div style="display: inline-block;">
					<span style="color: #e74c3c;">Zip Code</span><br> <span><%=customerBean.getCustomerPinnumber()%>
					</span>
				</div>
				<br> <br>
				<div style="display: inline-block;">
					<span style="color: #e74c3c;">Contact Details</span><br> <span><%=customerBean.getCustomerMobileNo()%>
						<%=customerBean.getCustomerMobileNo1()%></span>
				</div>
				<bR> <br>
				<button type="button" class="mybutton cpbutton">Change
					Password</button>
			</div>
			<div class="cmd4 cpform" style="display: none;">
				<form action="UserChangePassword" method="post">
					<input type="password" placeholder="Old Password" id="temppass"
						class="myinput" maxlength="80"> <input type="text"
						name="txtOldPassword" value="" id="oldpsbox"
						style="display: none;">
					<div style="width: 85%; display: inline-block; position: relative;">
						<input type="password" placeholder="New Password" id="temppass1"
							class="myinput" maxlength="80" style="width: 100%;"> <i
							class="material-icons showpass"
							style="position: absolute; top: 15px; right: -30px; opacity: 0.5; cursor: pointer;">remove_red_eye</i>
					</div>
					<input type="text" name="txtNewPassword" value="" id="newpsbox"
						style="display: none;">
					<button class="mybutton" type="submit">
						<i class="material-icons tooltip" toolmsg="Go">arrow_forward</i>
					</button>
					<button type="button" class="mybutton cancelcp">
						<i class="material-icons tooltip" toolmsg="Cancel">close</i>
					</button><br><br><br><br><br><br><br><br><br><br><br>
				</form>
			</div>
			<div class="cmd4 editform" style="display: none;">
				<form action="UserUpdateServlet" method="post" name="CustomerForm"
					id="mainform">
					<input type="hidden" name="customerId" id="customerId"
						value="<%=customerBean.getCustomerId()%>"> <input
						type="text" value="<%=customerBean.getCustomerFname()%>"
						placeholder="First Name" name="txtFirstName" class="myinput"
						id="fnbox" maxlength="15"><span class="ermsg"
						id="firstNameError"></span> <input type="text"
						value="<%=customerBean.getCustomerLname()%>"
						placeholder="Last Name" name="txtLastName" class="myinput"
						id="lnbox" maxlength="15"><span class="ermsg"
						id="lastNameError"></span> <input type="text"
						value="<%=customerBean.getCustomeEmail()%>" placeholder="Email"
						name="txtEmail" class="myinput" id="emailbox" maxlength="50"><span
						class="ermsg" id="emailError"></span>
					<textarea rows="3" name="txtAddress" placeholder="Address"
						class="myinput" id="addressbox" maxlength="255"><%=customerBean.getCustomerAddress()%></textarea>
					<span class="ermsg" id="addressError"></span> <input type="text"
						value="<%=customerBean.getCustomerMobileNo()%>"
						placeholder="Mobile 1" name="txtMobileNo" class="myinput"
						id="mobbox" maxlength="10"><span class="ermsg"
						id="mobile1Error"></span> <input type="text"
						value="<%=customerBean.getCustomerMobileNo1()%>"
						placeholder="Mobile 2" id="mob2box" name="txtMobileNo1"
						class="myinput" maxlength="10"><span class="ermsg"
						id="mobile2Error"></span>
					<div class="select">
						<span class="selected-option"></span> <select name="selPincode"
							id="selPincode">
							<%
								List<PincodeBean> pincodeBeansList = new PincodeDAO().list();
																								String type = customerBean.getCustomerPincode();
																								for (int i = 0; i < pincodeBeansList.size(); i++) {

																									String tmp = "unselected";
																									if (pincodeBeansList.get(i).getPincodeId().equals(type))
																										tmp = "selected";
							%>


							<option value=<%=pincodeBeansList.get(i).getPincodeId()%>
								<%=tmp%>><%=pincodeBeansList.get(i).getPincodeNumber()%></option>
							<%
								}
							%>
						</select>
						<div class="options"></div>
						<i class="material-icons">arrow_drop_down</i>
					</div>
					<span class="ermsg" id="pinCodeError"></span><br>
					<button type="submit" class="mybutton">
						<i class="material-icons tooltip" toolmsg="Save">save</i>
					</button>
					<button type="button" class="mybutton canceledit">
						<i class="material-icons tooltip" toolmsg="Cancel">close</i>
					</button>
					<%
						}
					%>
				</form>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
	${firstName}${lastName}${email}${mobileNo}${address}${pincode}${msgProfile}${oldpserror}${newpserror}
	<script src="res/js/profile.js"></script>
</body>
</html>