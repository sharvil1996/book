package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ProductBean;
import com.dao.ProductDAO;



public class ProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		String productId = request.getParameter("productId");
		ProductBean productBean = new ProductDAO().getByPK(productId);
		if(productBean!=null){
				request.setAttribute("productBean", productBean);
				request.getRequestDispatcher("productEdit.jsp").forward(request, response);
		}else{
				response.sendRedirect("ProductListServlet");
		}

		
	}

}
