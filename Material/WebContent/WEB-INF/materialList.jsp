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

	<b><font color="red">자료실</font></b>
	<hr />
<form action="materialList.do" method="post">
<select name="column">
<option value="title">제목</option>
<option value="content">내용</option>
<option value="id">작성자</option>
</select>
<input type="text" name="keyword"/>
<input type="submit" name="검색"/>
</form>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>파일</th>
			<th>조회수</th>
		</tr>

		<c:choose>
			<c:when test="${list.size() == 0 }">
				<tr>
					<th colspan="6"><font color="blue">등록된 자료가 없습니다.</font></th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="material">
				<c:set var="space"/>
				
				<c:forEach begin="1" end="${material.step }">
				<c:set var="space">${space }&nbsp;&nbsp;&nbsp;</c:set></c:forEach>
					<tr>
						<td>${material.mno }</td>
						<td>${space }<a href="material.do?mno=${ material.mno }">${material.title }</a></td>
						<td>${material.id }</td>
						<td>${material.writeDate }</td>
						<td>${material.realName }</td>
						<td>${material.hit }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		
		<tr>
		<th colspan="6">
 			<c:if test="${ groupStartNum - 1 > 0 }"><a href="materialList.do?page=${ groupStartNum - 1 }">[prev]</a></c:if>
			<c:forEach begin="${ groupStartNum }" end="${ groupStartNum + pageGroupCount - 1 }" var="pageNum">
				<c:if test="${ pageNum <= totalPage }">
					[<a href="materialList.do?page=${ pageNum }">${ pageNum }</a>]
				</c:if>
			</c:forEach>
			<c:if test="${ groupStartNum + pageGroupCount <= totalPage }"><a href="materialList.do?page=${ groupStartNum + pageGroupCount }">[next]</a></c:if>
		</th>
		</tr>
	</table>
	<p/>
	<a href="insertForm.do">새글쓰기</a>
	<a href="loginForm.do"><font color = "red">로그인창으로</font></a>

</body>
</html>