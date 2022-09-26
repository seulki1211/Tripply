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
			<div class="my-side-bar">북마크</div>
			<div class="my-side-bar" onclick="location.href='/point/chargeView.kh';">포인트관리</div>
		</div>
		<div class="profile-form">
	<h1 align="center">작성글</h1>
			<div class="wrapper">
				<div class="profile-box">
					<img class="profile" alt="본문이미지" src="/resources/buploadFiles/20220922193622.JPG" >
				</div>
			</div>
			<div class="">
				<form action="/member/modify.kh" method="post" name="modify_form">
					<table align="center">
						<tr>
							<td colspan="2" align="center">
								<input type="file" name="reloadFile">
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
		</tr>
		<c:if test="${!empty fList }">
			<c:forEach items="${fList }" var="free" varStatus="i">
				<tr>
					<td>${fn:length(fList) - i.index}<!--${i.count } 얘는 게시글 순서대로 출력--></td>
					<td colspan="2"><a href="/free/detail.kh?boardNo=${free.boardNo }&page=${currentPage }">${free.freeTitle }</a></td>
					<td>${free.freeWriter }</td>
					<td>${free.fCreateDate }</td>
					<td>${free.freeCount }</td>
				</tr>
			</c:forEach>
			<tr align="center" height="20">
			<td colspan="6">
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
				<td colspan="6" align="center"><b>데이터가 존재하지 않습니다.</b></td>
			</tr>
		</c:if>
		<%-- <tr>
			<td colspan="5" align="center">
				<form action="/free/search.kh" method="get">
					<select name="searchCondition">
						<option value="all" <c:if test="${searchCondition eq 'all' }">selected</c:if>>전체</option>
						<option value="writer" <c:if test="${searchCondition eq 'writer' }">selected</c:if>>작성자</option>
						<option value="title" <c:if test="${searchCondition eq 'title' }">selected</c:if>>제목</option>
						<option value="contents" <c:if test="${searchCondition eq 'contents' }">selected</c:if>>내용</option>
					</select>
					<input type="text" name="searchValue" value="${searchValue }">
					<input type="submit" value="검색">
				</form>
			</td>
			<td>
				<button onclick="loginCheck('${loginUser.memberId}','/free/writeView.kh')";>글쓰기</button>
			</td>
		</tr> --%>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>