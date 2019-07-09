package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.ActionForward;
import com.common.Command;
import com.dao.MaterialDAO;
import com.dao.MaterialEntity;
import com.dao.MemberEntity;

public class UpdateFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		MemberEntity member = (MemberEntity)session.getAttribute("member");

		ActionForward action = new ActionForward();

		if (member == null) {
			action.setPath("loginForm.do");
			action.setSend(true);
		} else {
		
			String mno = request.getParameter("mno");
			
			MaterialDAO dao = new MaterialDAO();
			MaterialEntity material = dao.select(mno); 
			
			request.setAttribute("material", material);
			
			action.setPath("WEB-INF/updateForm.jsp");
			action.setSend(false);
			
			
		}
		
		return action;
	}

}
