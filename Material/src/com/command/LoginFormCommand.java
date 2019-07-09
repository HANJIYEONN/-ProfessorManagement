package com.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.common.ActionForward;
import com.common.Command;

public class LoginFormCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward action = new ActionForward();
		action.setPath("WEB-INF/loginForm.jsp");
		action.setSend(false);
		
		return action;
	}

}
