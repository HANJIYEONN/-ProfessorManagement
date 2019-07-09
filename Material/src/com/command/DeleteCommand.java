package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.ActionForward;
import com.common.Command;
import com.dao.MaterialDAO;

public class DeleteCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Object member = session.getAttribute("member");
		
		ActionForward action = new ActionForward();
		
		if(member == null) {
			action.setPath("loginForm.do");
			action.setSend(true);
		}else {
		String mno = request.getParameter("mno");
		
		MaterialDAO dao = new MaterialDAO();
		boolean result = dao.delete(mno);
		
		if(result) {
			action.setPath("materialList.do");
			action.setSend(true);
		}else {
			action.setPath("WEB-INF/error.jsp");
			action.setSend(false);
		}
		}
	
		
		return action;
	}

}
