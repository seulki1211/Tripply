<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>여행 올인원 플랫폼,Tripply</title>
<link href="/resources/css/sidebar-style.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div id="header-logo">
		<img alt="트리플리 로고" src="#">
	</div>
	<h1 align="left">Tripply</h1>
	<div class="login-area">
		<c:if test="${empty loginUser }">
			<form action="/member/login.kh" method="post">
				<table align="right">
					<tr>
						<td>아이디:</td>
						<td><input type="text" name="memberId"></td>
						<td rowspan="2"><input type="submit" value="로그인"></td>
					</tr>
					<tr>
						<td>비밀번호:</td>
						<td><input type="password" name="memberPwd"></td>
						<!-- 					<td></td> -->
					</tr>
					<tr>
						<td colspan="3" align="right"><a href="/member/joinView.kh">회원가입</a>
						</td>
					</tr>
					
				</table>
			</form>
		</c:if>
		<c:if test="${not empty loginUser }">
			<table align="right">
				<tr>
					<td colspan='2'>${sessionScope.loginUser.memberNickname }님 환영합니다.</td>
				</tr>
						
			</table>
		</c:if>
	</div>
	<ul class="nav flex-column">
  <li class="nav-item">
    <a class="nav-link" href="#">회원 관리</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">배너 관리</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#">공지사항 관리</a>
  </li>
</ul>
<!-- 	<div class="nav-area"> -->
<!-- 		<div class="menu" onclick="location.href='#';">회원 관리</div> -->
<!-- 		<div class="menu" onclick="location.href='#';">배너 관리</div> -->
<!-- 		<div class="menu" onclick="location.href='#';">공지사항 관리</div> -->
<!-- 	</div> -->
</body>
</html>
