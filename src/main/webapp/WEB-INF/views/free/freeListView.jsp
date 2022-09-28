<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 게시글 역순 출력위한 라이브러리 태그 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<link rel="stylesheet" href="/resources/css/free/freeListView.css">
</head>
<body>
	<div>
		<jsp:include page="../common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	<h1 align="center">게시글 목록</h1>
	<br><br>
	<div class="main_table">
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
						<td colspan="2"><a href="#" onclick="loginCheck('${loginUser.memberNickname}', '/free/detail.kh?boardNo=${free.boardNo }&page=${currentPage }')" >${free.freeTitle  }</a></td>
						<!--  -->
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
			<tr>
				<td colspan="6" align="center">
					<form action="/free/search.kh" method="get">
						<select name="searchCondition">
							<option value="all" <c:if test="${searchCondition eq 'all' }">selected</c:if>>전체</option>
							<option value="writer" <c:if test="${searchCondition eq 'writer' }">selected</c:if>>작성자</option>
							<option value="title" <c:if test="${searchCondition eq 'title' }">selected</c:if>>제목</option>
							<option value="contents" <c:if test="${searchCondition eq 'contents' }">selected</c:if>>내용</option>
						</select>
						<input type="text" name="searchValue" value="${searchValue }">
						<input type="submit" value="검색">
						<button onclick="loginCheck('${loginUser.memberId}','/free/writeView.kh')";>글쓰기</button>
					</form>
				</td>
				<%-- <td>
					<button onclick="loginCheck('${loginUser.memberId}','/free/writeView.kh')";>글쓰기</button>
				</td> --%>
			</tr>
		</table>
	</div>
	<script>
		function loginCheck(loginId, url){
			if(loginId != ""){
				location.href=url;
			}else{
				alert("로그인을 해주세요.")
			}
		}
	</script>
</body>
</html>