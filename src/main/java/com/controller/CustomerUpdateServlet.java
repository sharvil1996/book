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


public class CustomerUpdateServlet extends HttpServlet {
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
		String customerMobileNo = request.getParameter("txtMobileNo");
		String customerMobileNo1 = request.getParameter("txtMobileNo1");
		String customerAddress = request.getParameter("txtAddress");
		String customerPincode = request.getParameter("selPincode");
		String customerId = request.getParameter("customerId");
		
		
		
		CustomerBean customerBean = new CustomerBean();
		boolean isError = false;
		
		
		
		if (ValidationUtils.isEmpty(customerFname)) {
			isError = true;
			request.setAttribute("firstName",
					"<font color=red>* First Name is Required</font>");
		}

		else {
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
					"<font color=red>* Last Name is Required</font>");
		}
		else {
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
					"<font color=red>* Email is Required</font>");
		}

		else {
			request.setAttribute("txtEmail", customerEmail);
			customerBean.setCustomeEmail(customerEmail);
		}
		
		if (!ValidationUtils.isValidEmailAddress(customerEmail)) {
			isError = true;
			request.setAttribute("email",
					"<script>showError('emailError','Please Enter Email')</script>");
		}

		

		if (ValidationUtils.isEmpty(customerMobileNo)) {
			isError = true;
			request.setAttribute("mobileNo",
					"<font color=red>* MobileNo is Required</font>");
		}

		else {
			request.setAttribute("txtmobileNo", customerMobileNo);
			customerBean.setCustomerMobileNo(customerMobileNo);
		}
		
		if (!ValidationUtils.validateNumber(customerMobileNo)) {
			isError = true;
			request.setAttribute("mobileNo",
					"<script>showError('mobile1Error','Please Enter Mobile No 1')</script>");
		}
		if (!ValidationUtils.validateNumber(customerMobileNo1)) {
			isError = true;
			request.setAttribute("mobileNo1",
					"<script>showError('mobile2Error','Please Enter Mobile No 2')</script>");
		}

		if (ValidationUtils.isEmpty(customerAddress)) {
			isError = true;
			request.setAttribute("address",
					"<font color=red>* ADDRESS is Required</font>");
		}

		else {
			request.setAttribute("txtAddress", customerAddress);
			customerBean.setCustomerAddress(customerAddress);
		}

		if (customerPincode.equals("0")) {
			isError = true;
			request.setAttribute("pincode",
					"<font color=red>* PINCODE is Required</font>");
		}
		else
		{
			request.setAttribute("", customerPincode);
			customerBean.setCustomerPincode(customerPincode);
		}

		if (isError) {
			customerBean.setCustomerMobileNo(customerMobileNo);
			customerBean.setCustomerMobileNo1(customerMobileNo1);
			customerBean.setCustomerPincode(customerPincode);
			customerBean.setCustomerAddress(customerAddress);
			customerBean.setCustomeEmail(customerEmail);
			customerBean.setCustomerFname(customerFname);
			customerBean.setCustomerLname(customerLname);
			customerBean.setCustomerId(customerId);
			request.setAttribute("customerBean",customerBean);
			request.getRequestDispatcher("customerEdit.jsp").forward(request,
					response);
		} else {

			customerBean.setCustomerPincode(customerPincode);
			
			customerBean.setCustomerMobileNo1(customerMobileNo1);
			customerBean.setCustomerId(customerId);
			if (new CustomerDAO().update(customerBean)) {

				response.sendRedirect("CustomerListServlet");

			} else {
				response.sendRedirect("CustomerListServlet");
			}

		}
	}

}
