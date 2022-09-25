<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<!-- <link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css"> -->
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
		<div id="sideBar"></div>
		<div id="contents-1">
		
			<table>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>게시판</th>
				</tr>
				<c:forEach items="${mainList }" var="recent">
					<tr>
						<td>${recent.boardNo }</td>
						<td>
							<a href="/${recent.sort }/detail.kh?page=1&boardNo=${recent.boardNo}">
								${recent.title }
							</a>
						</td>
						<td>${recent.cDate }</td>
						<td>
							<c:if test="${recent.sort eq 'planner' }">일정</c:if>
							<c:if test="${recent.sort eq 'review' }">후기</c:if>
							<c:if test="${recent.sort eq 'trade' }">거래</c:if>
							<c:if test="${recent.sort eq 'party' }">동행</c:if>
							<c:if test="${recent.sort eq 'free' }">자유</c:if>
						
						</td>
					</tr>
				</c:forEach>
				
			</table>
		
		
		
		</div>
		<div id="contents-2"></div>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
</body>
</html>