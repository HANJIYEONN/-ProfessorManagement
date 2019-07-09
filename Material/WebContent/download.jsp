
<%@page import="java.io.BufferedOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="com.dao.MaterialEntity"%>
<%@page import="com.dao.MaterialDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<font color="red">파일을 다운로드 합니다.</font>
<%
String savePath = "C:/Material/upload";
String mno = request.getParameter("mno");

MaterialDAO dao = new MaterialDAO();
MaterialEntity material = dao.select(mno);


//무조건 다운로드 받기 위한 content type
response.setContentType("application/octer-stream");

//다운로드 될 파일의 이름 결정
response.setHeader("Content-Disposition", "attachment;filename=" + material.getRealName());

//서버에 있는 파일
File file = new File(savePath, material.getMaskName());

//파일의 내용을 읽어드리기 위한 객체 마련
FileInputStream fi = new FileInputStream(file);
BufferedInputStream bis = new BufferedInputStream(fi);

//파일을 클라이언트에 전송하기 위한 객체 마련
out.clear();
out = pageContext.pushBody();
BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());

//한번에 읽어들일 데이터의 양
byte [] buffer = new byte[1024];

//파일에서 데이터 읽기
int data = bis.read(buffer);

while(data != -1){
	//클라이언트에 데이터 전송하기
	bos.write(buffer);
	
	data = bis.read(buffer);
}

bis.close();
bos.close();
%>

<script>window.close();</script>
</body>
</html>