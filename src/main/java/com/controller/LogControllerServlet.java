package com.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.LogDAO;

public class LogControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		call();
		
	}
	public void call()
	{
		Date dateToday = new Date();
		String str = dateToday+"";
		String newCompare =  str.substring(4, 11)+str.substring(str.length()-4,	str.length())+"";
	
		long temp =  new LogDAO().isAlreadyExists();
		Date newDate = new Date(temp);
		String strold = newDate+"";
		String oldCompare =  strold.substring(4, 11)+strold.substring(strold.length()-4,strold.length())+"";
		

		if(oldCompare.equals(newCompare))
		{
			new LogDAO().insertLogAfterFirstTime(temp);
		}else
		{
			new LogDAO().insertLogFirstTime(dateToday.getTime());
		}
	}

}
