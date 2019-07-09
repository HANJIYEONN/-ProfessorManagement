package com.common;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mapper.CommandFactory;

@WebServlet("*.do")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getRequestURI();
		System.out.println("URL path : " + url);
		
		CommandFactory factory = CommandFactory.getInstance();
		Command command = factory.getCommand(url);
		ActionForward action = command.execute(request, response);
		
		if(action.isSend()) {
			response.sendRedirect(action.getPath());
		
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(action.getPath());
			rd.forward(request, response);
		}
	}

}
