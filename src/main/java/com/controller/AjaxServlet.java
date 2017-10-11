package com.controller;

import java.io.File;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CartDAO;
import com.dao.CategoryDAO;
import com.dao.CustomerDAO;
import com.dao.OrdersDAO;
import com.dao.PincodeDAO;
import com.dao.ProductDAO;
import com.dao.SubCategoryDAO;
import com.dao.SuggestionDAO;
import com.dao.WishlistDAO;
import com.util.ValidationUtils;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean result = false;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String mName = request.getParameter("method");
		String output = "";

		File file;

		if (mName.equals("getSubCategory")) {
			output = new SubCategoryDAO().getSubCategory(request
					.getParameter("categoryId"));
		} else if (mName.equals("subCategoryInsert")) {
			output = new SubCategoryDAO().ajaxSubCategoryInsert(
					request.getParameter("subCategoryName"),
					request.getParameter("categoryId"));
		} else if (mName.equals("subCategoryDelete")) {
			output = new SubCategoryDAO().ajaxSubCategoryDelete(request
					.getParameter("subCategoryId"));
		} else if (mName.equals("subCategoryUpdate")) {
			output = new SubCategoryDAO().ajaxSubCategoryUpdate(
					request.getParameter("subCategoryName"),
					request.getParameter("subCategoryId"));
		} else if (mName.equals("categoryInsert")) {
			output = new CategoryDAO().ajaxCategoryInsert(request
					.getParameter("categoryName"));
		} else if (mName.equals("categoryDelete")) {
			output = new CategoryDAO().ajaxCategoryDelete(request
					.getParameter("categoryId"));
		} else if (mName.equals("customerDelete")) {
			output = new CustomerDAO().ajaxCustomerDelete(request
					.getParameter("customerId"));
		} else if (mName.equals("productDelete")) {
			file = new File("C:\\Inetpub\\vhosts\\bookberries.co.in\\httpdocs\\Images\\"+ File.separator
					+ request.getParameter("productId") + ".png");
			output = new ProductDAO().ajaxProductDelete(
					request.getParameter("productId"), file);
		} else if (mName.equals("categoryUpdate")) {
			output = new CategoryDAO().ajaxCategoryUpdate(
					request.getParameter("categoryId"),
					request.getParameter("categoryName"));
		} else if (mName.equals("customerUpdate")) {
			output = new CustomerDAO().ajaxCustomerUpdate(
					request.getParameter("customerId"),
					request.getParameter("isAdmin"));
		} else if (mName.equals("cartInsert")) {
			output = new CartDAO().ajaxCartInsert(
					request.getParameter("customerId"),
					request.getParameter("productId"),
					request.getParameter("quantity"));
		} else if (mName.equals("cartDelete")) {
			output = new CartDAO().ajaxCartDelete(request
					.getParameter("cartId"));
		} else if (mName.equals("suggetionDelete")) {
			output = new SuggestionDAO().ajaxSuggetionDelete(request
					.getParameter("suggetionId"));
		} else if (mName.equals("cancelOrder")) {
			output = new OrdersDAO().ajaxCancelOrders(request
					.getParameter("ordersId"));
		} else if (mName.equals("cartUpdate")) {
			output = new CartDAO().ajaxCartUpdate(
					request.getParameter("productId"),
					request.getParameter("customerId"),
					request.getParameter("quantity"));
		} else if (mName.equals("wishlistInsert")) {
			output = new WishlistDAO().ajaxWishlistInsert(
					request.getParameter("productId"),
					request.getParameter("customerId"));
		} else if (mName.equals("wishlistDelete")) {
			output = new WishlistDAO().ajaxWishlistDelete(
					request.getParameter("productId"),
					request.getParameter("customerId"));
		}else if (mName.equals("wishlistCheck")) {
			output = new WishlistDAO().ajaxWishlistCheck(
					request.getParameter("productId"),
					request.getParameter("customerId"));
		}else if (mName.equals("pincodeInsert")) {
			output = new PincodeDAO().ajaxPincodeInsert(request
					.getParameter("pincode"));
		} else if (mName.equals("pincodeDelete")) {
			output = new PincodeDAO().ajaxPincodeDelete(request
					.getParameter("pincode"));
		} else if (mName.equals("pincodeUpdate")) {
			output = new PincodeDAO().ajaxPincodeUpdate(
					request.getParameter("oldPincode"),
					request.getParameter("newPincode"));
		} else if (mName.equals("ordersUpdate")) {
			output = new OrdersDAO().ajaxOrdersUpdate(
					request.getParameter("ordersId"),
					request.getParameter("ordersIsDispatched"),
					request.getParameter("ordersIsDelivered"));
		} else if (mName.equals("ordersDelete")) {
			output = new OrdersDAO().ajaxOrdersDelete(request
					.getParameter("ordersId"));
		} else if (mName.equals("productDetail")) {
			output = new ProductDAO().ajaxProductDetail(request
					.getParameter("productId"));
		} else if (mName.equals("cartList")) {
			output = new CartDAO().cartList(request.getParameter("customerId"));
		} else if (mName.equals("validateEmail")) {
			String email = request.getParameter("email");
			if (ValidationUtils.isValidEmailAddress(email)) {
				if (new CustomerDAO().checkUserExists(email)) {
					output = "2";
				} else {
					output = "0";
				}
			} else {
				output = "1";
			}
		} else if (mName.equals("validateText")) {
			String text = request.getParameter("text");
			if (ValidationUtils.validateText(text)) {
				output = "1";
			} else {
				output = "0";
			}
		} else if (mName.equals("validateNumber")) {
			String num = request.getParameter("number");
			if (ValidationUtils.validateNumber(num)) {
				output = "0";
			} else {
				output = "1";
			}
		}
		response.setContentType("text");
		response.getWriter().write(output);
	}

}
