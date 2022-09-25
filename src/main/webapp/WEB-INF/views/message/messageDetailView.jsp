<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>

<h1 align="center">쪽지 확인</h1>
	<br><br>
	<table align="center" class="table col-10">
			<tr>
			<td class="col-2" scope="col" align='center' >받는이</td>
			<td>${msg.msgReciever }</td>
			
			<tr>
			<td class="col-2" scope="col" align='center' >보낸이</td>
			<td>${msg.msgWriter }</td>
			</tr>
			<tr>
			<td class="col-2" scope="col" align='center' >제목</td>
			<td>${msg.msgTitle }</td>
			<tr>
			<td class="col-2" scope="col" align='center' >내용</td>
			<td>${msg.msgContents }</td>
			</tr>
			<tr>
			<td class="col-2" scope="col" align='center' >보낸날</td>
			<td>${msg.msgCreateDate }</td>
			</tr>
	
			<tr>
			<td colspan='2' align='right'>
				<button type="button" onclick="location.href='/message/recvList.kh?msgReciever=${loginUser.memberNickname }'" class='btn btn-dark'>받은 쪽지함</button>
				<button type="button" onclick="location.href='/message/sendList.kh?msgWriter=${loginUser.memberNickname }'" class='btn btn-dark'>보낸 쪽지함</button>
				<button type="button" onclick="location.href='/message/replyView.kh?msgReciever=${msg.msgWriter}'" class='btn btn-dark'>답장 보내기</button>
				<button type="button" onclick="location.href= 'javascript:history.go(-1);'" class="btn btn-dark">이전페이지로</button> 
				
			</td>
			
			</tr>
			</table>
			
</body>
</html>