package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.SuggestionDAO;

/**
 * Servlet implementation class AdminSendFeedBack
 */
public class AdminSendFeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String suggestionId = request.getParameter("suggestionId");
		
		String suggestionSubject = request.getParameter("suggestionSubject");
		
		
		String emailId = new SuggestionDAO().getCustomerEmailId(suggestionId);
		
		SendEmail mail =new SendEmail();
	    String s = mail.SendEmail("Feedback", emailId, suggestionSubject);
		
	    if(s.equals("success"))
	    {
	    	if(new SuggestionDAO().updateIsAnswered(suggestionId))
	    		response.sendRedirect("feedbacks.jsp");    
	    }
	    else
	    {
	    	request.setAttribute("failFeedback", "Sorry...Something Went Wrrong...!");
	    	response.sendRedirect("feedbacks.jsp");    
	    }
		
		
		
		
		// TODO Auto-generated method stub
	}

}
