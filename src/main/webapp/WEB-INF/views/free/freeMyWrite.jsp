<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 게시물</title>
<link rel="stylesheet" href="/resources/css/common-style.css">
<link rel="stylesheet" href="/resources/css/free/freeMyWrite.css">
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	<div class="wrapper-form">
			<jsp:include page="/WEB-INF/views/common/myside.jsp"></jsp:include>
		<div class="profile-form">
	<h1 align="center" class="title_text">작성글</h1>
			<div class="wrapper">
				<div class="profile-box">
					<img class="profile" alt="본문이미지" src="/resources/buploadFiles/${member.memberFileRename }" >
				</div>
			</div>
			<div class="">
				<form action="/member/modify.kh" method="post" name="modify_form">
					<table align="center">
						<tr>
							
							<th>${loginUser.memberNickname }님의 작성글</th>
								<!-- <input type="file" name="reloadFile"> -->
								<!-- <button type="button" id="memberProfile" value="">프로필 사진 변경</button> -->
							
						</tr>
					<table align="center" border="1">
		<tr>
			<th>번호</th>
			<th colspan="2">제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>게시판</th>
		</tr>
		<c:if test="${!empty fList }">
			<c:forEach items="${fList }" var="free" varStatus="i">
				<tr>
					<td>${fn:length(fList) - i.index}<!--${i.count } 얘는 게시글 순서대로 출력--></td>
					<td colspan="2"><a href="/${free.category }/detail.kh?boardNo=${free.boardNo }&page=${currentPage }">${free.freeTitle }</a></td>
					<!-- 게시판 별로 domain주소가 다르므로 el을 주소에 집어넣어서 카테고리별로 게시글을 부를 수 있게 코드작성 -->
					<td>${free.freeWriter }</td>
					<td>${free.fCreateDate }</td>
					<td>${free.freeCount }</td>
					<td>
						<c:if test="${free.category eq 'free' }">자유롭게</c:if>
						<c:if test="${free.category eq 'party' }">우리함께</c:if>
						<c:if test="${free.category eq 'review' }">여행후기</c:if>
						<c:if test="${free.category eq 'planner' }">여행일정</c:if>
						<c:if test="${free.category eq 'trade' }">중고거래</c:if>
						<!-- 카테고리를 domain과 동일값을 줬기 때문에 그대로 출력하면 게시판에도 'free', 'party'
						이런 식으로 출력이 됨. 그래서 c:if문을 돌려서 카테고리 값을 한글로 바꿔서 받고 출력하게끔 만듬 -->
					</td>
				</tr>
			</c:forEach>
			<tr align="center" height="20">
			<td colspan="7">
				<c:if test="${currentPage != 1 }">
					<a href="/free/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[이전]</a>
				</c:if>
				<c:forEach var="p" begin="${startNavi }" end="${endNavi }">
					<c:if test="${currentPage eq p }">
						<b>${p }</b>
					</c:if>
					<c:if test="${currentPage ne p }">
						<a href="/free/${urlVal }.kh?page=${p }&searchCondition=${searchCondition}&searchValue=${searchValue}">${p }</a>
					</c:if>
				</c:forEach>
				<c:if test="${maxPage > currentPage }">
					<a href="/free/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[다음]</a>
				</c:if>
			</td>
		</tr>
		</c:if>
		<c:if test="${empty fList }">
			<tr>
				<td colspan="7" align="center"><b>데이터가 존재하지 않습니다.</b></td>
			</tr>
		</c:if>
		
					</table>
				</form>
			</div>
		</div>
	</div>
	<script>
		function seeyoulater() {
			alert("추후 업데이트 예정입니다.");
		}
	</script>
</body>
</html>