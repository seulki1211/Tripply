<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
</head>
<body>
	<!-- 헤더-메뉴바 -->
	<div id="header">
	<jsp:include page="../common/menuBar.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	<h1 align="center">아이디 찾기</h1>
	<div class="findId-Email">
		<div class="findId-Email">
			<form action="/member/findId" method="post">
			<table align="center">
				<tr>
					<td> 이메일로 찾기</td>
					<td>
						<input type="text" id="memberEmail" name="memberEmail" placeholder="Tripply@iei.or.kr">
						<button type="submit" id="findBtn">확인</button>
						<button type="button" onclick="location.href='/home.kh';">취소</button>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>