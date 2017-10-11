package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ForgotPasswordBean;
import com.dao.CustomerDAO;
import com.dao.ForgotPasswordDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class UserForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String emailId = request.getParameter("txtEmailId");
		boolean isError = false;
		String customerId = null;
		String forgotPasswordId;
		ForgotPasswordBean forgotPasswordBean = new ForgotPasswordBean();

		if (ValidationUtils.isEmpty(emailId)) {
			isError = true;
			request.setAttribute("email",
					"<script>showError('emailError','Please Enter Email')</script>");
		} else {
			request.setAttribute("txtEmailId", emailId);

			customerId = new CustomerDAO().getCustomerId(emailId);
			forgotPasswordBean.setCustomerId(customerId);
		}

		if (!ValidationUtils.isValidEmailAddress(emailId)) {
			isError = true;
			request.setAttribute("email",
					"<script>showError('emailError','Please Enter Email')</script>");
		}

		if (isError) {
			request.getRequestDispatcher("forgotPassword.jsp").forward(request,
					response);
		} else {

			forgotPasswordId = GenrateMathodsUtils.getRandomString(30);
			forgotPasswordBean.setForgotPasswordId(forgotPasswordId);

			if (new ForgotPasswordDAO().checkUser(customerId)) {
				if (new ForgotPasswordDAO().updateForgotPasswordId(
						forgotPasswordId, customerId)) {
					String tmp = "<br><a href=\"http://www.bookberries.co.in/bookshop/changePassword.jsp?forgotPasswordId="
							+ forgotPasswordId
							+ "&customerId="
							+ customerId
							+ "\" style=\"display:inline-block;font-size: 16px;background-color: #FF3333;padding: 12px 45px;outline: 0;margin: 5px 0;border-radius: 5px;transition: all .3s ease;cursor: pointer;color: #fff;white-space: nowrap;text-align: center;box-shadow: 0 2px 3px rgba(0, 0, 0, .3);text-decoration:none;\">Reset Password</a>";
					String subject = "Forgot Password";
					String message = "Please follow given link to reset your password"
							+ tmp;

					SendEmail S = new SendEmail();

					if (S.SendEmail(subject, emailId, message)
							.equals("success")) {

						request.setAttribute("msgUser", "succesfully send to "
								+ "your email<br>please check your email<br>");
						request.getRequestDispatcher("login.jsp").forward(
								request, response);
					} else {// For Fail
						request.setAttribute("msgUser",
								"failed!please try again");
						request.getRequestDispatcher("forgotPassword.jsp")
								.forward(request, response);

					}

				}
			}

			else if (new ForgotPasswordDAO().insert(forgotPasswordBean)) {

				String tmp = "http://www.bookberries.co.in/bookshop/changePassword.jsp?forgotPasswordId="
						+ forgotPasswordId + "&customerId=" + customerId;
				String subject = "Forgot Password";
				String message = "Please click below link to change your password...! "
						+ tmp;

				SendEmail S = new SendEmail();

				if (S.SendEmail(subject, emailId, message).equals("success")) {

					request.setAttribute("msgUser", "succesfully send to "
							+ "your email<br>please check your email<br>");
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
				} else {// For Fail
					request.setAttribute("msgUser", "failed!please try again");
					request.getRequestDispatcher("forgotPassword.jsp").forward(
							request, response);

				}

			} else {
				request.setAttribute("msgUser", "failed!please try again");
				request.getRequestDispatcher("forgotPassword.jsp").forward(
						request, response);

			}
		}
	}
}
