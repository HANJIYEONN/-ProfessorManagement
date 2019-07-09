<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b><font color="red">답글쓰기</font></b>
	<hr />

	<form action="reply.do" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="[RE] ${material.title }" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="5" cols="30"></textarea></td>
			</tr>
			<tr>
				<th>파일</th>
				<td><input type="file" name="realName" /></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="저장" /></th>
			</tr>
		</table>
		<input type="hidden" name="mno" value="${material.mno }"/>
	</form>

</body>
</html>