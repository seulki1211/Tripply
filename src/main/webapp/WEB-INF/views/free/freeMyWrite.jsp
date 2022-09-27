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
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
	<div class="wrapper-form">
		<div class="my-side">
			<div class="my-side-bar" onclick="location.href='/member/myPage.kh';">회원정보수정</div>
			<div class="my-side-bar" onclick="location.href='#';">작성글</div>
			<div class="my-side-bar" onclick="seeyoulater()">북마크</div>
			<div class="my-side-bar" onclick="location.href='/point/chargeView.kh';">포인트관리</div>
		</div>
		<div class="profile-form">
	<h1 align="center">작성글</h1>
			<div class="wrapper">
				<div class="profile-box">
					<img class="profile" alt="본문이미지" src="/resources/buploadFiles/${member.memberFileRename }" >
				</div>
			</div>
			<div class="">
				<form action="/member/modify.kh" method="post" name="modify_form">
					<table align="center">
						<tr>
							<td colspan="2" align="center">
							<th>${member.memberFilePath }님</th>
								<!-- <input type="file" name="reloadFile"> -->
								<!-- <button type="button" id="memberProfile" value="">프로필 사진 변경</button> -->
							</td>
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
					<td colspan="2"><a href="/free/detail.kh?boardNo=${free.boardNo }&page=${currentPage }">${free.freeTitle }</a></td>
					<td>${free.freeWriter }</td>
					<td>${free.fCreateDate }</td>
					<td>${free.freeCount }</td>
					<td>${free.category }</td>
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