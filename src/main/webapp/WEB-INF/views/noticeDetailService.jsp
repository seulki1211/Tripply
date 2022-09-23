<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<title>게시글 상세 정보</title>

</head>
<body>
	<br><br>
	<h1 align='center'> ${notice.noticeNo }번 공지</h1>
	<br><br>
	
	<table align="center" class="table table-hover col-10">
	<tbody class="table-group-divider"">
			<tr>
			<td class="col-2" scope="col" align='center' ><b>제목</b></td>
			<td>${notice.noticeTitle }</td>
			</tr>
			<tr>
			<td align='center'><b>작성자<b></td>
			<td>${notice.noticeWriter }</td>
			</tr>
			<tr>
			<td align='center'><b>작성날짜</b></td>
			<td>${notice.nUpdateDate }</td>
			</tr>
			<tr>
			<td align='center'><b>조회수</b></td>
			<td>${notice.noticeCount }</td>
			</tr>
			<tr>
			<td align='center'><b>내용</b></td>
			<td>${notice.noticeContents }</td>
			</tr>
		</tbody>	
		</table>
		
</body>
</html>