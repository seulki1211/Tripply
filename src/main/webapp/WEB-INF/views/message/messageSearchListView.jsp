<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 쪽지함</title>
</head>
<body>

	<jsp:include page="../common/menuBar.jsp"></jsp:include>


	<h1 align="center">받은 쪽지함</h1>

	<table align="center" class="table table-hover">
	<tr>
		<th scope="col">번호</th>
		<th scope="col">제목</th>
		<th scope="col">보낸이</th>
		<th scope="col">날짜</th>
	</tr>
	
	<tbody class="table-group-divider">
		<c:forEach items="${recvList }" var="msg" varStatus="i">
			<tr>
				<td scope="row">${i.count }</td>
				
				<td><a href='/message/detail.kh?msgNo=${msg.msgNo }&page=${currentPage }'>${msg.msgTitle }</a></td>
				<td scope="row">${msg.msgWriter }</td>
				<td scope="row">${msg.msgCreateDate }</td>
				
			</tr>	
		</c:forEach>

<!-- 					 하단 페이지 목록 -->
		<tr align='center' height="20">
		<td colspan='5' scope="row">
	
			<c:if test="${currentPage != 1 }">
				<a href='/message/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${mSearch.searchCondition }&searchValue=${mSearch.searchValue }&msgWriter=${mSearch.msgWriter }&msgReciever=${loginUser.memberNickname }'>[이전]</a>
			</c:if>
			
			<c:forEach var='p' begin="${startNavi }" end="${endNavi }">
				<c:if test="${currentPage eq p}">
					 <b>${p }</b>
				</c:if>
				<c:if test="${currentPage ne p}">
					<a href="/message/${urlVal }.kh?page=${p }&searchCondition=${mSearch.searchCondition }&searchValue=${mSearch.searchValue }&msgWriter=${mSearch.msgWriter }&msgReciever=${loginUser.memberNickname }">${p }</a>
				</c:if>
			</c:forEach>
			
			<c:if test="${currentPage < maxPage }">
				<a href='/message/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${mSearch.searchCondition }&searchValue=${mSearch.searchValue }&msgWriter=${mSearch.msgWriter }&msgReciever=${loginUser.memberNickname }'>[다음]</a>
			</c:if>
	
		</td>
		</tr>


			<c:if test="${empty recvList }">
				<tr>
					<td colspan='5' align='center' scope="row"><b>받은 쪽지가 없습니다.</b>
					</td>
				</tr>
			</c:if>

			<tr>
				<td colspan='5' align='center'>
					<form action="/admin/admin/notice/search.kh" method="get">
						<select name="searchCondition">
							<option value='all'
								<c:if test="${searchCondition eq 'all' } ">selected</c:if>>전체</option>
							<option value='writer'
								<c:if test="${searchCondition eq 'writer' } ">selected</c:if>>작성자</option>
							<option value='title'
								<c:if test="${searchCondition eq 'title' } ">selected</c:if>>제목</option>
							<option value='contents'
								<c:if test="${searchCondition eq 'contents' } ">selected</c:if>>내용</option>
						</select> <input type="text" name='searchValue' value="${searchValue }">
						<input type="submit" value='검색'>
					</form>
				</td>


			</tr>

			<tr>
				<td>
					<button type="button"
						onclick="location.href='/message/writeView.kh'">쪽지 보내기</button>
				</td>
			</tr>
		</tbody>
	</table>
	<br><br><br>
</body>
</html>