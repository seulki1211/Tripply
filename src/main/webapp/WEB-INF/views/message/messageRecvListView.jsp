<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<title>받은 쪽지함</title>
</head>
<body>


<br><br>
	<h3 align="center">받은 쪽지함</h3>
	
	<table align="center" class="table  col-10">
	<tr>
	<td colspan='10' align='right'>
	<button type="button" class='btn btn-primary' onclick="location.href='/message/sendList.kh?msgWriter=${loginUser.memberNickname}'">보낸 쪽지함</button></td>
	</tr>
	<tr>
		<th class="col-1">#</th>
		<th class="col-4">제목</th>
		<th scope="col-3">발신자</th>
		<th scope="col-2">날짜</th>
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
			<td colspan='4'  align='center'>
			<ul class="pagination justify-content-center">
				
			<c:if test="${currentPage != 1 }">
			<li class="page-item"><a class="page-link"  href='/message/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${mSearch.searchCondition }&searchValue=${mSearch.searchValue }&msgWriter=${mSearch.msgWriter }&msgReciever=${loginUser.memberNickname }'>이전</a>
			</li></c:if>
		
			
			<c:forEach var='p' begin="${startNavi }" end="${endNavi }">
				<c:if test="${currentPage eq p}">
				<li class="page-item disabled"><a class="page-link" href='#' >${p }</a></li>
				</c:if>
				<c:if test="${currentPage ne p}">
				<li class="page-item"><a class="page-link"  href="/message/${urlVal }.kh?page=${p }&searchCondition=${mSearch.searchCondition }&searchValue=${mSearch.searchValue }&msgWriter=${mSearch.msgWriter }&msgReciever=${loginUser.memberNickname }">${p }</a>
				</li></c:if>
			</c:forEach>
			
			<c:if test="${currentPage < maxPage }">
			<li class="page-item"><a class="page-link"  href='/message/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${mSearch.searchCondition }&searchValue=${mSearch.searchValue }&msgWriter=${mSearch.msgWriter }&msgReciever=${loginUser.memberNickname }'>다음</a>
			</li></c:if>
		</ul>
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
					<form action="/message/search.kh" method="post" name="searchForm">
						<select name="searchCondition">
							<option value='all'
								<c:if test="${searchCondition eq 'all' } ">selected</c:if>>전체</option>
							<option value='writer'
								<c:if test="${searchCondition eq 'writer' } ">selected</c:if>>발신자</option>
							<option value='title'
								<c:if test="${searchCondition eq 'title' } ">selected</c:if>>제목</option>
							<option value='contents'
								<c:if test="${searchCondition eq 'contents' } ">selected</c:if>>내용</option>
						</select> 
						<input type="hidden" name ="loginUserNickname" value="${loginUser.memberNickname }" >
						<input type="hidden" name ="searchArea" value="recvList">
						<input type="text" name='searchValue' value="${searchValue }">
						<input type="button" value='검색' onclick="valueChk();">
					</form>
				</td>
			</tr>
			<tr>
				<td colspan='10' align='center'>
					<button type="button" class='btn btn-dark'onclick="location.href='/message/writeView.kh'">쪽지 보내기</button>
				</td>
				
			</tr>
		</tbody>
	</table>
	<br><br><br>
	<script type="text/javascript">
	function valueChk() {
		if(searchForm.searchValue.value=="") { // document 를 생략해도 됨
	        alert("검색어를 입력하세요!");
	        searchForm.searchValue.focus();
	    	return false;
	    }
		return searchForm.submit();	
	}
	
	</script>
</body>
</html>