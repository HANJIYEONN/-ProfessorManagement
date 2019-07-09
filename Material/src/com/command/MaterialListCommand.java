package com.command;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.ActionForward;
import com.common.Command;
import com.dao.MaterialDAO;
import com.dao.MaterialEntity;
import com.dao.MemberEntity;

public class MaterialListCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberEntity member = (MemberEntity) session.getAttribute("member");

		ActionForward action = new ActionForward();

		if (member == null) {
			action.setPath("loginForm.do");
			action.setSend(true);
		} else {

			try {
				request.setCharacterEncoding("UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			String column = request.getParameter("column");
			String keyword = request.getParameter("keyword");
			String page = request.getParameter("page");

			if (column == null) {
				column = "title";
			}

			if (keyword == null) {
				keyword = "";
			}
			
			int pageCount = 3;
			int pageGroupCount = 3;
			
			int curPage = 1;
			
			if(page != null) {
				curPage = Integer.parseInt(page);
			}
			
			int groupStartNum = (curPage - 1) / pageGroupCount * pageGroupCount + 1;
			
			int startNum = (curPage - 1) * pageCount + 1;
			
			MaterialDAO dao = new MaterialDAO();
			
			int totalRow = dao.getTotalRow(column, keyword);
			int totalPage = (totalRow -1 ) / pageCount + 1;
			
			ArrayList<MaterialEntity> list = dao.selectAll(column, keyword, startNum, pageCount);

			request.setAttribute("list", list);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("groupStartNum", groupStartNum);
			request.setAttribute("pageGroupCount", pageGroupCount);
			
			action.setPath("WEB-INF/materialList.jsp");
			action.setSend(false);
		}
		return action;

	}
}
