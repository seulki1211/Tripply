<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>

<h1 align="center">쪽지 확인</h1>
	<br><br>
		<table border='1'  align="center">			
			<tr>
			<td>받는이</td>
			<td>${msg.msgReciever }</td>
			
			<tr>
			<td>보낸이</td>
			<td>${msg.msgWriter }</td>
			</tr>
			<tr>
			<td>제목</td>
			<td>${msg.msgTitle }</td>
			<tr>
			<td>내용</td>
			<td>${msg.msgContents }</td>
			</tr>
			<tr>
			<td>보낸날</td>
			<td>${msg.msgCreateDate }</td>
			</tr>
	
			<tr>
			<td colspan='2' align='right'>
				<button type="button" onclick="location.href='/message/recvList.kh?msgReciever=${loginUser.memberNickname }'">받은 쪽지함</button>
				<button type="button" onclick="location.href='/message/sendList.kh?msgWriter=${loginUser.memberNickname }'">보낸 쪽지함</button>
			</td>
			
			</tr>
			</table>
			
</body>
</html>