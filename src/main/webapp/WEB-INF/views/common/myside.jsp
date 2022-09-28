<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
/* 사이드바 */
#sideBar{
	position:relative;
	display:block;
	float: left;
	height:800px;
	width:160px;
	border-right: 2px solid rgb(210,210,210);
	font-weight: bold;
}
	.side-bar{
		height:10%;
		text-align:center;
		border-bottom: 2px solid rgb(210,210,210);
	}
		.side-bar:hover{
			cursor:pointer;
			background-color:rgb(180,180,180);
		}

</style>

<body>

<div id="contents">
	<!-- 사이드바	 -->
		<div id="sideBar">
				<div class="side-bar" onclick="location.href='/member/myPage.kh';"><p class="li-text">회원정보수정</p></div>
				<div class="side-bar" onclick="location.href='/free/myList.kh';"><p class="li-text">작성글</p></div>
				<div class="side-bar" onclick="seeyoulater()"><p class="li-text">북마크</p></div>
				<div class="side-bar" onclick="location.href='/point/historyView.kh';"><p class="li-text">포인트관리</p></div>
		</div>
</div>
<script>
function seeyoulater() {
	alert("추후 업데이트 예정입니다.");
}
</script>
</body>
</html>