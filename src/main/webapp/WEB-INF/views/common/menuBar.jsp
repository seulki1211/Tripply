<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>여행 올인원 플랫폼,Tripply</title>
<link href="/resources/css/menubar-style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
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
				<tr>
					<td><a href="#">마이 쪽지</a></td>
					<td><a href="/member/modify.kh">마이페이지</a></td>
				</tr>
				<tr>
					<td><a href="/member/logout.kh">로그아웃</a></td>
				</tr>
			</table>
		</c:if>
		<c:if test="${loginUser.memberId eq 'admin' }">
               <tr>
                  <td colspan="3" align="right"><a href="#">관리자 페이지로</a>
                  </td>
               </tr>
        </c:if>
	</div>
	<div class="nav-area">
		<div class="menu" onclick="location.href='#';">여행 일정</div>
		<div class="menu" onclick="location.href='/review/list.kh';">여행 후기</div>
		<div class="menu" onclick="location.href='#';">중고 거래</div>
		<div class="menu" onclick="location.href='/party/list.kh';">우리 함께</div>
		<div class="menu" onclick="location.href='#';">자유롭게</div>
	</div>
</body>
</html>
