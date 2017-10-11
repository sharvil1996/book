package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.CustomerBean;
import com.dao.CustomerDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class CustomerInsertServlet extends HttpServlet {
	static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String customerFname = request.getParameter("txtFirstName");
		String customerLname = request.getParameter("txtLastName");
		String customerEmail = request.getParameter("txtEmail");
		String customerPassword = request.getParameter("txtPassword");
		String customerMobileNo = request.getParameter("txtMobileNo");
		String customerMobileNo1 = request.getParameter("txtMobileNo1");
		String customerAddress = request.getParameter("txtAddress");
		String customerPincode = request.getParameter("selPincode");
		String customerId;

		CustomerBean customerBean = new CustomerBean();
		boolean isError = false;

		if (ValidationUtils.isEmpty(customerFname)) {
			isError = true;
			request.setAttribute("firstName",
					"<script>showError('firstNameError','Please Enter First Name')</script>");
		}else {
			request.setAttribute("txtFirstName", customerFname);
			customerBean.setCustomerFname(customerFname);
		}
		
		if (ValidationUtils.validateText(customerFname)) {
			isError = true;
			request.setAttribute("firstName",
					"<script>showError('firstNameError','Please Enter Valid Name')</script>");
		}

		if (ValidationUtils.isEmpty(customerLname)) {
			isError = true;
			request.setAttribute("lastName",
					"<script>showError('lastNameError','Please Enter Last Name')</script>");
		}else {
			request.setAttribute("txtLastName", customerLname);
			customerBean.setCustomerLname(customerLname);
		}
		
		if (ValidationUtils.validateText(customerLname)) {
			isError = true;
			request.setAttribute("lastName",
					"<script>showError('lastNameError','Please Enter Valid Name')</script>");
		}

		if (ValidationUtils.isEmpty(customerEmail)) {
			isError = true;
			request.setAttribute("email",
					"<script>showError('emailError','Please Enter Email')</script>");
		} else {
			request.setAttribute("txtEmail", customerEmail);
			customerBean.setCustomeEmail(customerEmail);
		}
		
		if (!ValidationUtils.isValidEmailAddress(customerEmail)) {
			isError = true;
			request.setAttribute("email",
					"<script>showError('emailError','Please Enter Email')</script>");
		}

		if (ValidationUtils.isEmpty(customerPassword)) {
			isError = true;
			request.setAttribute("password",
					"<script>showError('passwordError','Please Enter Password')</script>");
		} else {
			request.setAttribute("txtPassword", customerPassword);
			if (customerPassword.length() == 128) {
				customerBean.setCustomerPwd(customerPassword);
			}
		}

		if (ValidationUtils.isEmpty(customerMobileNo)) {
			isError = true;
			request.setAttribute("mobileNo",
					"<script>showError('mobile1Error','Please Enter Mobile No 1')</script>");
		}else {
			request.setAttribute("txtmobileNo", customerMobileNo);
			customerBean.setCustomerMobileNo(customerMobileNo);
		}
		
		if (!ValidationUtils.validateNumber(customerMobileNo)) {
			isError = true;
			request.setAttribute("mobileNo",
					"<script>showError('mobile1Error','Please Enter Mobile No 1')</script>");
		}
		
		
		if (!ValidationUtils.validateNumber(customerMobileNo1) && !ValidationUtils.isEmpty(customerMobileNo1)) {
			isError = true;
			request.setAttribute("mobileNo1",
					"<script>showError('mobile2Error','Please Enter Mobile No 2')</script>");
		}

		if (ValidationUtils.isEmpty(customerAddress)) {
			isError = true;
			request.setAttribute("address",
					"<script>showError('addressError','Please Enter Address')</script>");
		}else {
			request.setAttribute("txtAddress", customerAddress);
			customerBean.setCustomerAddress(customerAddress);
		}

		if (customerPincode.equals("0")) {
			isError = true;
			request.setAttribute("pincode",
					"<script>showError('lastNameError','Please Enter First Name')</script>");
		} else {
			request.setAttribute("", customerPincode);
			customerBean.setCustomerPincode(customerPincode);
		}

		if (isError) {
			request.getRequestDispatcher("register.jsp").forward(request,
					response);
		} else {

			customerBean.setCustomerPincode(customerPincode);
			customerId = GenrateMathodsUtils.getRandomString(15);

			customerBean.setCustomerId(customerId);
			customerBean.setCustomerMobileNo1(customerMobileNo1);

			if (new CustomerDAO().insert(customerBean)) {

				SendEmail mail = new SendEmail();
				String s = mail.SendEmail("REGISTRATION", customerEmail,
						"Reg successfully!!!");

				request.setAttribute("msgReg","<script>showToast('Registration Successful','default',10000);</script>");				
				request.getRequestDispatcher("login.jsp").forward(request,
						response);

			} else {
				request.getRequestDispatcher("register.jsp").forward(request,
						response);

			}

		}
	}

}
