<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>

	<jsp:include page="../common/menuBar.jsp"></jsp:include>


	<h1 align="center"> 게시글 목록</h1>

	<table   align="center" class="table table-hover">
	<tr>
		<th scope="col">번호</th>
		<th scope="col">제목</th>
		<th scope="col">작성자</th>
		<th scope="col">날짜</th>
		<th scope="col">조회수</th>
		<th scope="col">첨부파일</th>
	</tr>
	
	<tbody class="table-group-divider">
		<c:if test="${!empty bList }">
		<c:forEach items="${bList }" var="board" varStatus="i">
			<tr>
				<td scope="row">${i.count }</td>
				
					<c:if test="${!empty loginUser }">
						<td><a href='/board/detail.kh?boardNo=${board.boardNo }&page=${currentPage }'>${board.boardTitle }</a></td>
					</c:if>
					<c:if test="${empty loginUser }">
						<td>${board.boardTitle }</td>
					</c:if>
				<td scope="row">${board.boardWriter }</td>
				<td>${board.bCreateDate }</td>
				<td>${board.boardCount }</td>
				<td>
					<c:if test="${not empty board.boardFilename}">
					O
					</c:if>
					<c:if test="${empty board.boardFilename}">
					X
					</c:if>
				</td>
			</tr>	
		</c:forEach>
					<!--  하단 페이지 목록 -->
		<tr align='center' height="20">
		<td colspan='6' scope="row">
	
			<c:if test="${currentPage != 1 }">
				<a href='/board/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>[이전]</a>
			</c:if>
			
			<c:forEach var='p' begin="${startNavi }" end="${endNavi }">
				<c:if test="${currentPage eq p}">
					 <b>${p }</b>
				</c:if>
				<c:if test="${currentPage ne p}">
					<a href="/board/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p }</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${currentPage < maxPage }">
				<a href='/board/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>[다음]</a>
			</c:if>
	
		</td>
		</tr>
	</c:if>

		
		<c:if test="${empty bList }">
		<tr>			
			<td colspan='6' align = 'center' scope="row"> <b>검색 결과가 없습니다.</b>
			</td>
		</tr>
	</c:if>
	
	<tr>
		<td colspan='5' align='center' >
			<form action="/board/search.kh" method="get">
				<select name="searchCondition">
					<option value='all' <c:if test="${searchCondition eq 'all' } ">selected</c:if>>전체</option>
					<option value='writer' <c:if test="${searchCondition eq 'writer' } ">selected</c:if>>작성자</option>
					<option value='title' <c:if test="${searchCondition eq 'title' } ">selected</c:if>>제목</option>
					<option value='contents'<c:if test="${searchCondition eq 'contents' } ">selected</c:if>>내용</option>
				</select>
				<input type="text" name ='searchValue' value="${searchValue }">
				<input type="submit" value='검색'>			
			</form>
			</td>
			
			<td>
			<button type="button" onclick="location.href='/board/writeView.kh'">글 작성</button> 
			</td>
		</tr>
		
		</tbody>
	</table>
	<br><br><br>
</body>
</html>