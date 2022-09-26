<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>여행 올인원 플랫폼,Tripply</title>
<link href="/resources/css/menubar-style.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
</head>
<body>
	<table align="center" class="table col-12 table-borderless">
		<tr>
			<td class="table col-2"><a href="/home.kh"><img alt="트리플리 로고" src="/resources/image/tripply-logo.png" width='50%'></a></td>
			<td class="table col-7"><h1 align="left">Tripply</h1></td>
			<td class="table col-3">
		</td>
		</tr>
	</table>
	<div class="login-area">
		<c:if test="${empty loginUser }">
			<form action="/member/login.kh" method="post">
				<table>
					<tr>
						<td>아이디:</td>
						<td><input type="text" name="memberId"></td>
						<td rowspan="2"><input type="submit" value="로그인"></td>
					</tr>
					<tr>
						<td>비밀번호:</td>
						<td><input type="password" name="memberPwd"></td>
					</tr>
					<tr>
						<td colspan="2" align="right"><a href="/member/findId.kh">ID찾기</a>
						</td>
						<td align="right"><a href="/member/joinView.kh">회원가입</a>
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
					<td><button type="button" onclick="messageView('${sessionScope.loginUser.memberNickname}');" class="btn btn-dark">마이쪽지</button>
					<td><a href="/member/myPage.kh">마이페이지</a></td>
				</tr>
				<tr>
					<td><a href="/member/logout.kh">로그아웃</a></td>
						<c:if test="${loginUser.memberId eq 'admin' }">
							<td colspan="3" align="right">
							<button type="button" onclick="location.href='/admin/banner/list.kh'" class="btn btn-dark">관리자 페이지</button>
			                </td>
			             </c:if>
				</tr>
			</table>
		</c:if>
	</div>
	<br>
	<div class="nav-area">
		<div class="menu" onclick="location.href='/plan/plan.kh';">여행 일정</div>
		<div class="menu" onclick="location.href='/review/list.kh';">여행 후기</div>
		<div class="menu" onclick="location.href='/trade/list.kh';">중고 거래</div>
		<div class="menu" onclick="location.href='/party/list.kh';">우리 함께</div>
		<div class="menu" onclick="location.href='/free/list.kh';">자유롭게</div>
	</div>
	
	
	<script type="text/javascript">
	
		function messageView(memberNickname) {
			window.open('/message/recvList.kh?msgReciever='+memberNickname+'', 'window', 'width=800, height=700, menubar=no, status=no, toolbar=no');
		}
	</script>
	
</body>
</html>
