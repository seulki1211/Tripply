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

<!-- 부트스트랩,jQuery -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

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
			
			
			
<!-- 최신글 5개 조회	mbk	 -->
				<h3 align="center">최신글</h3>
	 			<br><br>
				<table align="center" class="table table-hover col-10">
				<tr>
					<th class="col-1" scope="col" >번호</th>
					<th class="col-4" scope="col" >제목</th>
					<th class="col-2" scope="col" >작성자</th>
					<th class="col-2" scope="col" >날짜</th>
					<th class="col-1" scope="col">조회수</th>
					<th class="col-2" scope="col" >게시판</th>
				</tr>
				<c:if test="${!empty mainList }">
				<c:forEach items="${mainList }" var="recent">
					<tr>
						<td>${recent.boardNo }</td>
						<td >
							<a href="#" onclick="loginCheck('${loginUser.memberId }','/${recent.sort }/detail.kh?page=1&boardNo=${recent.boardNo}')")>
								${recent.title }
							</a>
						</td>
						<td >${recent.writer }</td>
						<td >${recent.cDate }</td>
						<td >${recent.count }</td>
						<td >
							<c:if test="${recent.sort eq 'planner' }">일정</c:if>
							<c:if test="${recent.sort eq 'review' }">후기</c:if>
							<c:if test="${recent.sort eq 'trade' }">거래</c:if>
							<c:if test="${recent.sort eq 'party' }">동행</c:if>
							<c:if test="${recent.sort eq 'free' }">자유</c:if>
						
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty mainList }">
					<tr>
						<td>
							<span>결과가 존재하지 않습니다.</span>
						</td>
					</tr>
				</c:if>
			</table>
		
		
		</div>
		<div id="contents-2"></div>
	<div id="footer"></div>
</body>

<script type="text/javascript">
function noticeView(noticeNo) {
	window.open('/home/notice/detail.kh?noticeNo='+noticeNo+'', 'window', 'width=800, height=700, menubar=no, status=no, toolbar=no');
}


//로그인 체크 기능
function loginCheck(loginId,url){
	if(loginId != ""){
		location.href=url;
	}else{
		alert("로그인을 해주세요.")
	}
}


</script>
</html>