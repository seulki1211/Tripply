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
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
	<div id="contents" width="90%">
		<div id="sideBar"></div>
		<div id="search-area">
			<form action="/review/search.kh" method="get">
				<jsp:include page="/WEB-INF/views/common/search.jsp"></jsp:include>
			</form>
		</div>
		<div id="list-area">
			<table align="center" width="100%">
				<tr>
					<td class="list-td" width="30%">
						<div>
							<img class="thumbnail" src="/resources/image/flower1.png" height="100" width="100">
						</div>
						<div>
							<div>제목</div>
							<div>작성자</div>
							<div>조회수</div>
							<div>날짜</div>
						</div>
					</td>
					<td class="list-td" width="30%">
						<div>
							<img class="thumbnail" src="/resources/image/flower1.png" height="100" width="100">
						</div>
						<div>
							<div>제목</div>
							<div>작성자</div>
							<div>조회수</div>
							<div>날짜</div>
						</div>
					</td>
					<td class="list-td" width="30%">
						<div>
							<img class="thumbnail" src="/resources/image/flower1.png" height="100" width="100">
						</div>
						<div>
							<div>제목</div>
							<div>작성자</div>
							<div>조회수</div>
							<div>날짜</div>
						</div>
					</td>
				</tr>
			</table>
			<div id="pageNavi">
			
			</div>
			<div id="button">
				<button onclick="location.href='/review/writeView.kh';">글 작성</button>
			</div>
			
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>