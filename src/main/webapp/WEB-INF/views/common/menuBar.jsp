<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>여행 올인원 플랫폼,Tripply</title>
<!-- 	상대경로 -->
<link href="../../resources/css/menubar-style.css" rel="stylesheet">
<!-- 	/resources/css/menubar-style.css 절대경로 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>

<body>

	<!-- jstl쓰기 위해 -->
	<!-- 1. jar파일 4개 -->
	<!-- 2. taglib jsp추가 -->
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
						<!-- 					<td></td> -->
						<!-- 					<td></td> -->
					</tr>
				</table>
			</form>
		</c:if>
		<%-- 				<c:if test="${sessionScope.loginUser.memberId ne null }"> --%>
		<c:if test="${not empty loginUser }">
			<table align="right">
				<tr>
					<td colspan='2'>${sessionScope.loginUser.memberName }님환영합니다.</td>

				</tr>
				<tr>
					<td><a href="#">마이 쪽지</a></td>
					<td><a href="#">마이페이지</a></td>
				</tr>
			</table>
		</c:if>

	</div>

	<div class="nav-area">
		<div class="menu" onclick="location.href='#';">여행 일정</div>
		<div class="menu" onclick="location.href='#';">여행 후기</div>
		<div class="menu" onclick="location.href='#';">중고 거래</div>
		<div class="menu" onclick="location.href='#';">우리 함께</div>
		<div class="menu" onclick="location.href='#';">자유롭게</div>
	</div>

	<script type="text/javascript">
		function showNoticeList() {

		}
	</script>
</body>
</html>
