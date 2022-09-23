<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작성글</title>
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
</head>
<body>
	<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
	<div class="wrapper-form">
		<div class="my-side">
			<div class="my-side-bar" onclick="location.href='#';">회원정보수정</div>
			<div class="my-side-bar" onclick="location.href='/free/list.kh';">작성글</div>
			<div class="my-side-bar">북마크</div>
		</div>
		<div class="profile-form">
	<h1 align="center">작성글</h1>
			<div class="wrapper">
				<div class="profile-box">
					<img class="profile" alt="본문이미지" src="/resources/buploadFiles/${member.memberFileRename }" >
				</div>
			</div>
			<div class="">
				<form action="/member/modify.kh" method="post" name="modify_form">
					<table align="center">
						<tr>
							<td colspan="2" align="center">
								<input type="file" name="reloadFile">
								<!-- <button type="button" id="memberProfile" value="">프로필 사진 변경</button> -->
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<button type="button" onclick="modifyform_check();">수정하기</button>
								<button type="button" onclick="removeMember();">탈퇴하기</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>