<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
		<div id="sideBar"></div>
<!-- 게시물 출력 -->
		<div id="contents-1">
			<table align="center" border="1px">
				<tr>
					<td>${review.boardNo }</td>
					<td>제목</td>
					<td>${review.reviewTitle }</td>
				</tr>
			   	<tr>
					<td colspan='3'>
						${review.reviewWriter }
						<span class="detail viewcount-wrap">
						<img alt="눈모양 아이콘" src="/resources/image/viewcount.jpg" width="25px" height="25px">
						조회수 ${review.reviewCount } &nbsp;
						</span>
						<span  class="detail date">${review.rCreateDate }</span>
					</td>
				</tr>
				<tr>
					<td colspan="2">${review.reviewContents }</td>
				</tr>
			</table>
		</div>
<!-- 작성자인 경우에만 수정, 삭제버튼이 노출되도록 		 -->
		<div id="contents-2">
			<a href="/review/modifyView.kh?boardNo=${review.boardNo }">수정</a> 
			<a href="/review/remove.kh?boardNo=${review.boardNo }">삭제</a>
		</div>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
</body>
</html>