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
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/banner.jsp"></jsp:include>
		
	</div>

		
		
		<div id="sideBar"></div>
		<div id="contents-1">
		<br><br>
		<h3 align="center">공지사항</h3>
	 	<br><br>
			<table align="center" class="table table-hover col-10">
			<tr>
				<th class="col-1" scope="col">번호</th>
				<th class="col-4" scope="col">제목</th>
				<th class="col-2" scope="col">작성자</th>
				<th class="col-1" scope="col">날짜</th>
				<th class="col-1" scope="col">조회수</th>
			</tr>
			
			<tbody class="table-group-divider"">
				<c:forEach items="${nList }" var="notice" varStatus="i">
					<tr>
						<td scope="row">${i.count }</td>
						<td><a href="#" onclick="noticeView(${notice.noticeNo });">${notice.noticeTitle }</a></td>
						<td>${notice.noticeWriter }</td>
						<td>${notice.nUpdateDate }</td>
						<td>${notice.noticeCount }</td>
					</tr>	
				</c:forEach>
			</table>
		
		
		</div>
		<div id="contents-2"></div>
	<div id="footer"></div>
</body>

<script type="text/javascript">
function noticeView(noticeNo) {
	window.open('/home/notice/detail.kh?noticeNo='+noticeNo+'', 'window', 'width=800, height=700, menubar=no, status=no, toolbar=no');
}
</script>
</html>