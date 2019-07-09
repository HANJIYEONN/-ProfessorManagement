<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<b><font color="red">로그인</font></b>
	<hr />
	
	<c:if test="${param.code == 1 }">
	<font color="red">ID 또는 PW가 틀립니다.<br/>
	다시 시도하세요.
	</font>
	</c:if>
	
	
	<form action="login.do" method="post">
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" /></td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" name="pw" /></td>
			</tr>
			<tr>
				<th colspan="2"><input type="submit" value="로그인" /></th>
			</tr>
		</table>
	</form>
</body>
</html>