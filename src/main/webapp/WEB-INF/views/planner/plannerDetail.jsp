<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>플래너 상세</title>
</head>
	<style>
	.wrap{
		width: 2000px;
		height: 3000px;
	box-sizing: border-box;
	}
	.header {
		width: 100%;
		height: 20%;
		border: 1px solid;
		box-sizing: border-box;
	}
	.info{
		width:100%;
		height:70%;
		box-sizing: border-box;
		
	}
	.writeInfo{
		width:100%;
		height:30%;
		border: 1px solid;
	}
	.planerContents{
		width: 100%;
		height: 70%;
		border: 1px solid;
		box-sizing: border-box;
		
	}
	.planDetail{
		width:80%;
		height:100%;
		border: 1px solid;
		box-sizing: border-box;
		float:left;
		
		
	}
	.button{
		width:20%;
		height:100%;
		border: 1px solid;
		box-sizing: border-box;
		float:left;
		
		
	}
	.reply{
		width:100%;
		height:10%;
		border: 1px solid;
		box-sizing: border-box;
	
	}
	</style>
<body>
	<div class="wrap">
		<div class=header>
			<div class="info">	
			<!-- 여행 제목 날짜 -->
			</div>
			<div class="writeInfo">
			<!--작성자/등록일자 조회수  -->
			</div>
		</div>
		<div class="planerContents">
			<div class="planDetail">
			<!-- 장소 메모 지도마커표시 보이기 foreach -->
			</div>
			<div clss="button">
			<!-- 수정 삭제 pdf  -->
			</div>
		</div>
		<div class="reply">
		<!-- 댓글 -->
		</div>


	</div>


</body>
</html>