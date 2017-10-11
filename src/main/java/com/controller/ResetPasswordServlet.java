package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CustomerDAO;
import com.dao.ForgotPasswordDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String forgotPasswordId = request.getParameter("forgotPasswordId");
		String customerId = request.getParameter("customerId");

		String password = request.getParameter("txtPassword");
		boolean isError = false;


		if (ValidationUtils.isEmpty(password)) {
			isError = true;
			request.setAttribute("password", "Please Enter Password");
		} else {
			request.setAttribute("txtPassword", password);
		}


		if (isError) {
			request.getRequestDispatcher(
					"changePassword.jsp?forgotPasswordId=" + forgotPasswordId
							+ "&customerId=" + customerId).forward(request,
					response);
		} else {

//			password = new GenrateMathodsUtils().makeSHA512(password);
			if (new CustomerDAO().changePassword(password, customerId)) {


				if (new ForgotPasswordDAO().delete(forgotPasswordId)) {
					request.setAttribute("msgUser",
							"Password Change Successfully");
					response.sendRedirect("login.jsp");
				}

			} else {

				request.setAttribute("msgUser",
						"Something went Wrong...<br><br>Please Try Again");
				response.sendRedirect("login.jsp");
			}
		}

	}

}
