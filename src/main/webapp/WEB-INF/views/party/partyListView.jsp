<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>동행자 게시판</title>
</head>
<body>

	<jsp:include page="../common/menuBar.jsp"></jsp:include>


	<h1 align="center"> 게시글 목록</h1>

	<table align="center" class="table table-hover">
	<tr>
		<th scope="col">번호</th>
		<th scope="col">제목</th>
		<th scope="col">작성자</th>
		<th scope="col">날짜</th>
		<th scope="col">조회수</th>
	</tr>
	
	<tbody class="table-group-divider">
		<c:forEach items="${pList }" var="party" varStatus="i">
			<tr>
				<td scope="row">${i.count }</td>
				
				<c:if test="${!empty loginUser and party.partyFirstDate le today }">
				<td>
				<a href='/party/detail.kh?partyNo=${party.partyNo }&page=${currentPage }'>${party.partyTitle }</a>
				</td>
				</c:if>
				<c:if test="${(!empty loginUser) and (party.partyFirstDate gt today) }">
				<td>여행 기간이 만료되었습니다ㅜㅜㅜ</td>
				</c:if>
				
				<c:if test="${empty loginUser }">
				<td>${party.partyTitle }</td>
				</c:if>
				<td scope="row">${party.partyWriter }</td>
				<td scope="row">${party.pCreateDate }</td>
				<td scope="row">${party.partyCount }</td>
			</tr>	
		</c:forEach>
		
		<c:if test="${empty pList }">
		<tr>			
			<td colspan='6' align = 'center' scope="row"> <b>검색 결과가 없습니다.</b>
			</td>
		</tr>
	</c:if>
	
	<form action="/party/search.kh" method="get">
		<jsp:include page="../common/search_party.jsp"></jsp:include>
	</form>
			
	<tr>
			<td>
			<c:if test="${!empty loginUser }">
			<button type="button" onclick="location.href='/party/writeView.kh'">글 작성</button> 
			</c:if>
			</td>
	</tr>
	
				<!--  하단 페이지 목록 -->
		<tr align='center' height="20">
		<td colspan='6' scope="row" align='center'>
	
			<c:if test="${currentPage != 1 }">
				<a href='/party/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>[이전]</a>
			</c:if>
			
			<c:forEach var='p' begin="${startNavi }" end="${endNavi }">
				<c:if test="${currentPage eq p}">
					 <b>${p }</b>
				</c:if>
				<c:if test="${currentPage ne p}">
					<a href="/party/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p }</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${currentPage < maxPage }">
				<a href='/party/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>[다음]</a>
			</c:if>
	
		</td>
		</tr>
		
		</tbody>
	</table>
	<br><br><br>
</body>
</html>