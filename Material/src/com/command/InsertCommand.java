package com.command;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.ActionForward;
import com.common.Command;
import com.dao.MaterialDAO;
import com.dao.MemberEntity;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class InsertCommand implements Command {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		HttpSession session = request.getSession();
		MemberEntity member = (MemberEntity)session.getAttribute("member");
		
		String savePath = "C:/Material/upload";
		
		ActionForward action = new ActionForward();
		
		if(member == null) {
			action.setPath("loginForm.do");
			action.setSend(true);
		}else {
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					savePath, //파일의 저장 위치
					1024 * 1024 *10, //파일의 최대 크기
					"UTF-8", // 한글 처리
					new DefaultFileRenamePolicy() // 같은 이름에 대한 정책
					);
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			String realName = multi.getFilesystemName("realName");
			
			String maskName = null;
			
			if(realName != null) {
				maskName = System.currentTimeMillis() + realName.substring(realName.lastIndexOf("."));
				
				//저장된 파일 이름을 변경
				File file = new File(savePath, realName);
				file.renameTo(new File(savePath, maskName));
			}
			
			MaterialDAO dao = new MaterialDAO();
			boolean result = dao.insert(title, content, member.getId(), maskName, realName);
			
			if(result) {
				action.setPath("materialList.do");
				action.setSend(true);
			}else {
				action.setPath("WEB-INF/error.jsp");
				action.setSend(false);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return action;
	}

}
