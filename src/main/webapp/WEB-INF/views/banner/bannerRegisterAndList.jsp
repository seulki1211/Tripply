<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  
<meta charset="UTF-8">
<title>게시글작성</title>
</head>
<body>
<div>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>
</div>

	<h1 align="center">배너 등록 및 리스트 페이지</h1>
	<br><br>
	
		<form action="/banner/register.kh" method="post" enctype="multipart/form-data">
		<table border='1'  align="center">
		<tr>
				<th>배너 순서</th>
				<th>배너 이미지</th>
				<th>작성자</th>
				<th>등록</th>
				<th>삭제</th>
			</tr>
			
			<c:forEach items="${bList }" var="banner" varStatus="i">
			
			<tr>
				<td><input type="hidden" name="bannerNo" > ${i.count }</td>
				<td><c:if test="${not empty banner.bannerFileName}">
						<img alt="본문이미지" src="/resources/buploadFiles/${banner.bannerFileRename }" width = '50%'>
					</c:if><td>
				<td><input type="text" name="bannerWriter" value="${banner.bannerWriter }"></td>
				<td><input type="file" name="uploadFile"></td>
				<td><input type="submit" value="저장"></td>
				<td> <button type="button" onclick ="location.href='/banner/remove.kh'" >삭제하기</button>  </td>
			</tr>
			
			</c:forEach>
		</table>

		</form>
	
</body>
</html>