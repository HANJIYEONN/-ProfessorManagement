package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.ActionForward;
import com.common.Command;
import com.dao.MemberDAO;
import com.dao.MemberEntity;

public class LoginCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String id =request.getParameter("id");
		String pw =request.getParameter("pw");
		
		MemberDAO dao = new MemberDAO();
		MemberEntity member = dao.login(id, pw);
		
		ActionForward action = new ActionForward();
		
		if(member == null) {
			action.setPath("loginForm.do?code=1");
			action.setSend(true);
			
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("member", member);
			
			action.setPath("materialList.do");
			action.setSend(true);
		}
		return action;
	}

}
