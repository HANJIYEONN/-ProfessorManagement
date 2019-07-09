<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<b><font color="red">수정 </font></b>
	<hr />

	<form action="update.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${material.mno }<input type="hidden" name="mno" value="${material.mno }"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"
					value="${material.title }"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="5" cols="30">${material.content }</textarea></td>
			</tr>
			<tr>
			<th>파일</th>
			<td>${material.realName }<br/><input type="file" name="realName"/></td>
			</tr>
			<tr>
			<th colspan="2">
			<input type="submit" value="수정"/>
			</th>
			</tr>
		</table>
	</form>


</body>
</html>