<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<style type="text/css">

</style>
<title>공지사항</title>
</head>


<body>
<div>
<jsp:include page="../../common/adminSideBar.jsp"></jsp:include>
</div>
 <div class="right-side">

   <br><br>
	<h1 align="center">공지사항</h1>
   <br><br>

	<table align="center" class="table table-hover col-10">
	<tr>
		<td colspan='10'>선택한 공지사항 갯수: ${countResult}<br> 공지는 5개만 선택 가능합니다! </td>
		</tr>
	<tr>
		<th class="col-1" scope="col">#</th>
		<th class="col-3" scope="col">제목</th>
		<th class="col-2" scope="col">작성자</th>
		<th class="col-1" scope="col">날짜</th>
		<th class="col-1" scope="col">노출 상태</th>
		
<!-- 		<th class="col-1" scope="col">조회수</th> -->
	</tr>
	
	<tbody class="table-group-divider"">
		
	
		<c:forEach items="${nList }" var="notice" varStatus="i">
			<tr>
				<td scope="row">${i.count }</td>
				<td><a href='/admin/notice/detail.kh?noticeNo=${notice.noticeNo }&page=${currentPage }'>${notice.noticeTitle }</a></td>
				<td>${notice.noticeWriter }</td>
				<td>${notice.nUpdateDate }</td>
				<td> ${notice.nStatus }  
				<button type="button" onclick="location.href='/admin/notice/chooseNotice.kh?noticeNo=${notice.noticeNo}&nStatus=${notice.nStatus}&page=${currentPage}'" class="btn btn-dark">보이기</button></td>
				
<%-- 				<td>${notice.noticeCount }</td> --%>
			</tr>	
		</c:forEach>
		

		
		<c:if test="${empty nList }">
		<tr>			
			<td colspan='5' align = 'center' scope="row"> <b>검색 결과가 없습니다.</b>
			</td>
		</tr>
		</c:if>
	
	</tbody>
		
					<!--  하단 페이지 목록 -->
		<tr align='center' height="20" align='center'>
		<td colspan='4'  align='center'>
			<ul class="pagination justify-content-center">
			<c:if test="${currentPage != 1 }">
			<li class="page-item"><a class="page-link" href='/admin/notice/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>이전</a></li>
			</c:if>
			
			<c:forEach var='p' begin="${startNavi }" end="${endNavi }">
				<c:if test="${currentPage eq p}">
				<li class="page-item disabled"><a class="page-link" href='#' >${p }</a></li>
				</c:if>
				<c:if test="${currentPage ne p}">
				<li class="page-item"><a class="page-link" href="/admin/notice/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p }</a></li>
				</c:if>
			</c:forEach>
			
			<c:if test="${currentPage < maxPage }">
			<li class="page-item"><a class="page-link" href='/admin/notice/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>다음</a></li>
			</c:if>
	 	  </ul>
		</td>
		<td>
			<button type="button" onclick="location.href='/admin/notice/writeView.kh'" class="btn btn-dark">글 작성</button> 
		</td>
		</tr>
		
		
<!-- 	<tr> -->
<!-- 		<td colspan='4' align='center' > -->
<!-- 			<form action="/admin/notice/search.kh" method="get"> -->
<!-- 				<select name="searchCondition"> -->
<%-- 					<option value='all' <c:if test="${searchCondition eq 'all' } ">selected</c:if>>전체</option> --%>
<%-- 					<option value='writer' <c:if test="${searchCondition eq 'writer' } ">selected</c:if>>작성자</option> --%>
<%-- 					<option value='title' <c:if test="${searchCondition eq 'title' } ">selected</c:if>>제목</option> --%>
<%-- 					<option value='contents'<c:if test="${searchCondition eq 'contents' } ">selected</c:if>>내용</option> --%>
<!-- 				</select> -->
<%-- 				<input type="text" name ='searchValue' value="${searchValue }"> --%>
<!-- 				<input type="submit" value='검색'>		 -->
					
<!-- 			</form> -->
<!-- 			</td> -->
			
<!-- 			<td colspan='1'> -->
		
<!-- 			</td> -->
<!-- 		</tr> -->
	</table>
</div>

<script type="text/javascript">
	function chooseNotice(noticeNo,nStatus,currentPage) {
		
	}
</script>
</body>
</html>