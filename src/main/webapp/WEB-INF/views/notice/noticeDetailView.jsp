<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 정보</title>
</head>
<body>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>


	<h1 align='center'> ${notice.noticeNo }번 공지</h1>
	<br><br>
	<table border='1'  align="center"  border="1">
			<tr>
			<td width = '80px' >제목</td>
			<td>${notice.noticeTitle }</td>
			</tr>
			<tr>
			<td>작성자</td>
			<td>${notice.noticeWriter }</td>
			</tr>
			<tr>
			<td>작성날짜</td>
			<td>${notice.nUpdateDate }</td>
			</tr>
			<tr>
			<td>조회수</td>
			<td>${notice.noticeCount }</td>
			</tr>
			<tr height="300" width='100'>
			<td>내용</td>
			<td>${notice.noticeContents }
			</tr>
			
			
			
			<tr>
				<td colspan='2' align='center'>
				<button type="button" onclick="location.href='/notice/list.kh?&page=${page }'">리스트로</button> 
						<button type="button" onclick="location.href='/notice/modifyView.kh?noticeNo=${notice.noticeNo }&page=${page }'">수정하기</button>
						<button type="button" onclick="removeNotice(${page});">삭제하기</button> 
				</td>
			</tr>
		</table>
		

<script type="text/javascript">
 function removeNotice(page) {
  	event.preventDefault();// 하이퍼링크 이동 방지
 	if(confirm("삭제하시려면 '확인'을 클릭하세요")){
 		location.href="/notice/remove.kh?page="+page;
 	}
 }
	
</script>
</body>
</html>