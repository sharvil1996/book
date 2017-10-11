

package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.CustomerBean;
import com.bean.SubCategoryBean;
import com.bean.SuggestionBean;
import com.dao.SubCategoryDAO;
import com.dao.SuggestionDAO;
import com.util.GenrateMathodsUtils;
import com.util.ValidationUtils;

public class SuggestionInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String suggestionId = null;
		String suggestionDesc = request.getParameter("txtSuggestionDesc");
		
		HttpSession session = request.getSession();
		
		CustomerBean customerBean = (CustomerBean)session.getAttribute("customerBean");
		
		String customerId = customerBean.getCustomerId();
		
		
		
		
		boolean isError = false;
		SuggestionBean suggestionBean = new SuggestionBean();
		
		if (ValidationUtils.isEmpty(suggestionDesc)) {
			request.setAttribute("suggestionDesc",
					"<font color=red>* desc is Required</font>");
			isError = true;
		} else {
			request.setAttribute("txtSuggestionDesc", suggestionDesc);
			if (suggestionDesc == null)
				suggestionDesc = "";
			suggestionBean.setSuggestionDesc(suggestionDesc);
			
		}
	
		if (isError) {
			request.getRequestDispatcher("feedback.jsp").forward(request,
					response);
		} else {
			
			suggestionId = GenrateMathodsUtils.getRandomString(15);
			suggestionBean.setSuggestionId(suggestionId);
			suggestionBean.setCustomerId(customerId);
			
			if (new SuggestionDAO().insert(suggestionBean)) {

				request.setAttribute("msgFeedback","<script>showToast('Your feedback is saved!','default',10000);</script>");
			request.getRequestDispatcher("books.jsp").forward(request, response);
			
			} else {
				response.sendRedirect("feedback.jsp");
			}
		}
		
		
		
		
	}

}
