package com.controller;

import java.io.IOException;
import java.util.Date;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;

import com.bean.CustomerBean;
import com.bean.OrdersBean;
import com.dao.OrdersDAO;
import com.dao.ProductDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class OrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String productIds = request.getParameter("txtProductId");
		String productQty = request.getParameter("txtProductQty");

		String ids[] = productIds.split(",");
		String qty[] = productQty.split(",");

		HttpSession session = request.getSession();

		OrdersBean ordersBean = new OrdersBean();

		String customerId;
		int temp, price = 0, charge = 0;
		
		boolean isError = false;

		if (ValidationUtils.isEmpty(productIds)) {
			isError = true;
			request.setAttribute("productId", "Please Enter Product Id");
		} else {
			request.setAttribute("txtProductId", productIds);
		}

		if (ValidationUtils.isEmpty(productQty)) {
			isError = true;
			request.setAttribute("productQty", "Please Enter Product Qty");
		} else {
			request.setAttribute("txtProductQty", productQty);
		}

		for (int i = 0; i < ids.length; i++) {
			if (ids[i].length() != 15) {
				isError = true;
				break;
			}
		}

		for (int i = 0; i < qty.length; i++) {
			if (qty[i].length() != 1 && qty[i].length() != 2
					&& qty[i].length() != 3) {
				isError = true;
				break;

			}
		}
		if (ids.length != qty.length)
			isError = true;

		for (int i = 0; i < ids.length; i++) {
			if (new ProductDAO().isExistsProductId(ids[i])) {
			} else {
				ids = (String[]) ArrayUtils.removeElement(ids, ids[i]);
				qty = (String[]) ArrayUtils.removeElement(qty, qty[i]);
			}

		}

		

		if (isError) {


			
			
		} else {
			
			
			CustomerBean customerBean = (CustomerBean) session
					.getAttribute("customerBean");
			customerId = customerBean.getCustomerId();

			Date date = new Date();
			Long tempDate = date.getTime();
			int dis=0;
			for (int i = 0; i < ids.length; i++) {
				temp = new ProductDAO().totalPrice(ids[i]);
				dis = Integer.parseInt(new ProductDAO().listOfProductDiscount(ids[i]));
				System.out.println(dis+"<- Inside");
				temp *= Integer.parseInt(qty[i]);
				int temp1=(temp*dis)/100;
				temp-=temp1;
				charge += Integer.parseInt(qty[i]);
				price += temp;
			}
			System.out.println("original price->"+price);
			charge *= 10;
			price += charge;
			System.out.println("price+delivery charge->"+price);
			String ordersId = GenrateMathodsUtils.getRandomString(15);

			ordersBean.setOrdersId(ordersId);
			ordersBean.setOrdersIsCancelled("n");
			ordersBean.setOrdersIsDelivered("n");
			ordersBean.setOrdersIsDispatched("n");
			ordersBean.setOrdersQty(productQty);
			ordersBean.setOrdersDate(tempDate);
			ordersBean.setCustomerId(customerId);
			ordersBean.setProductId(productIds);
			ordersBean.setOrdersPrice(price + "");

			if(new OrdersDAO().insert(ordersBean))
			{

				response.sendRedirect("myorders.jsp");
			}
			else
			{
				request.setAttribute("errorMsg", "Sorry ! Insufficient Stock...!!");
				
				request.getRequestDispatcher("error.jsp").forward(request, response);
				
			}
			
		}

	}

}
