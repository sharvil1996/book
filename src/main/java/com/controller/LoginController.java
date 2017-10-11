package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CustomerBean;
import com.dao.LoginDAO;
import com.util.ValidationUtils;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("txtEmail");
		String password = request.getParameter("txtPassword");
		boolean isError = false;
		if (ValidationUtils.isEmpty(userName)) {
			isError = true;
			request.setAttribute("email", "please enter username");
		} else {
			request.setAttribute("txtEmail", userName);
		}
		if (ValidationUtils.isEmpty(password)) {
			isError = true;
			request.setAttribute("password", "please enter password");
		}
		if (isError)
		{
			request.setAttribute("msgUser",
					"please enter valid username or password");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}else {

			String temp = password;
			Object obj = new LoginDAO().checkAuthentication(temp, userName);

			CustomerBean customerBean;
			HttpSession session = request.getSession();

			if (obj == null) {
				request.setAttribute("msgUser",
						"please enter valid username or password");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
			} else if (obj.equals("customer")) {
				
				
				String refer = request.getParameter("refer");
				customerBean = new LoginDAO().setCustomerBean(temp, userName);
				session.setAttribute("customerBean", customerBean);
				if(refer!=null){
					response.sendRedirect(refer);
				}else{
				request.getRequestDispatcher("books.jsp").forward(
						request, response);
				}
			
			} else if (obj.equals("admin")) {
				

				
				
				customerBean = new LoginDAO().setCustomerBean(temp, userName);

				if(customerBean == null)
				{
				
				}
				else
					session.setAttribute("customerBean", customerBean);
				
				
				request.getRequestDispatcher("dashBoardAdmin.jsp").forward(
						request, response);
			} else{
				
			}
		}
	}
}