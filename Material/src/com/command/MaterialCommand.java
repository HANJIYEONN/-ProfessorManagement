package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.common.ActionForward;
import com.common.Command;
import com.dao.MaterialDAO;
import com.dao.MaterialEntity;

public class MaterialCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mno = request.getParameter("mno");
		System.out.println(mno);
		
		MaterialDAO dao = new MaterialDAO();
		MaterialEntity material = dao.select(mno);
		
		request.setAttribute("material", material);
		
		ActionForward action = new ActionForward();
		action.setPath("WEB-INF/material.jsp");
		action.setSend(false);
		
		return action;
	}

}
