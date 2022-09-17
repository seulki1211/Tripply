<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
	<div id="contents">
		<div id="sideBar"></div>
		<div id="contents-1">
			<form action="/review/write.kh" method="post">
					<input type="hidden" name="reviewWriter" value="임시작성자">
					<select name="rLocationCode">
							<option value="00" label="전국"></option>
							<option value="11" label="서울"></option>
							<option value="21" label="부산"></option>
							<option value="39" label="제주"></option>
							<option value="23" label="인천"></option>
							<option value="25" label="대전"></option>
							<option value="22" label="대구"></option>
							<option value="24" label="광주"></option>
							<option value="26" label="울산"></option>
							<option value="29" label="세종"></option>
							<option value="31" label="경기도"></option>
							<option value="32" label="강원도"></option>
							<option value="33" label="충청북도"></option>
							<option value="34" label="충청남도"></option>
							<option value="37" label="경상북도"></option>
							<option value="38" label="경상남도"></option>
							<option value="35" label="전라북도"></option>
							<option value="36" label="전라남도"></option>
						</select>
						<input type="text" id="inputTitle" name="reviewTitle" placeholder="제목을 입력하세요"><br>
						<textarea id="summernote" name="reviewContents"></textarea>
						
						<input type="hidden" name="reviewFileName" value="">
						<input type="hidden" name="reviewFileReName" value="">
						<input type="hidden" name="reviewFilePath" value="">
						
						<button>저장</button>
			</form>
		</div>
		<div id="contents-2"></div>
	</div>
	<div id="footer"></div>
</body>
</html>