package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CustomerBean;
import com.dao.CustomerDAO;
import com.util.ValidationUtils;

public class UserChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String oldPassword = request.getParameter("txtOldPassword");
		String newPassword = request.getParameter("txtNewPassword");
		boolean isError = false;
		boolean isCorrectPassword = false;
		HttpSession session = request.getSession();

		CustomerBean customerBean = (CustomerBean) session
				.getAttribute("customerBean");

		String password = new CustomerDAO().getPassword(customerBean
				.getCustomerId());

		if (ValidationUtils.isEmpty(oldPassword)) {
			isError = true;
			request.setAttribute("oldpserror",
					"<script>showToast('Please Enter Old Password');</script>");
		} else if (!password.equals(oldPassword)) {
			isCorrectPassword = true;
		} else {
			request.setAttribute("txtOldPassword", oldPassword);
		}

		if (ValidationUtils.isEmpty(newPassword)) {
			isError = true;
			request.setAttribute("newpserror",
					"<script>showToast('Please Enter New Password')</script>");
		} else if (password.equals(newPassword)) {
			isCorrectPassword = true;

		} else {
			request.setAttribute("txtNewPassword", oldPassword);
			customerBean.setCustomerPwd(newPassword);
		}

		if (isError) {
			request.getRequestDispatcher("profile.jsp").forward(
					request, response);
		} else if (isCorrectPassword) {
			request.setAttribute("newpserror",
					"New Password &amp; Old Password are Same");
			request.getRequestDispatcher("profile.jsp").forward(
					request, response);
		} else {
			String customerId = customerBean.getCustomerId();

			if (new CustomerDAO().updatePassword(newPassword, customerId)) {
				request.setAttribute("newpserror", "<script>showToast('Password Changed Successfully');</script>");
				request.getRequestDispatcher("profile.jsp").forward(
						request, response);
			} else {
				request.setAttribute("newpserror", "<script>showToast('Old Password Mismatch');</script>");
				request.getRequestDispatcher("profile.jsp").forward(
						request, response);
			}
		}
	}
}