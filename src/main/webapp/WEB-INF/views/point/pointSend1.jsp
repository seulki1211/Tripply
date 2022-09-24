<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/resources/css/common-style.css">
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
<!-- 사이드바	 -->
		<div id="sideBar">
			<div class="my-side" style="float:left">
				<div class="my-side-bar" onclick="location.href='#';">회원정보수정</div>
				<div class="my-side-bar" onclick="location.href='/free/list.kh';">작성글</div>
				<div class="my-side-bar">북마크</div>
				<div class="my-side-bar" onclick="pointNavi(this);">포인트관리</div>
<!-- 사이드바 아코디언			 -->
				<div id="point-navi" style="display:block;">
					<ul>
						<li onclick="location.href='/point/historyView.kh';">포인트 내역</li>
						<li onclick="location.href='/point/chargeView.kh';">포인트 충전</li>
						<li onclick="location.href='/point/sendView.kh';">채택된 상품 구매하기</li>
					</ul>
				</div>
			</div>
		</div>
<!-- 본 컨텐츠 -->
		<div id="contents-2"  style="float:left">
			<h1>포인트 전송</h1><hr><br>
			현재 포인트 잔액 : ${loginUser.pointBalance }원			
			구매 채택 게시글
			<table align="center" border="1px">
				<tr>
					<th>제목</th>
					<th>판매자</th>
					<th>구매완료</th>
					<th>구매 채택 가격</th>
					<th>구매하기</th>
				</tr>
				<c:forEach items="${tList }" var="trade" >
					<tr>
						<td>${trade.tradeTitle }</td>
						<td>${trade.tradeWriter}</td>
						<td>${trade.soldOut }</td>
						<td>${trade.finalBiddingPrice }</td>
						<td onclick="sendPoint(this,'${trade.tradeWriter}','${trade.soldOut }',${loginUser.pointBalance },${trade.finalBiddingPrice });">
							<a href="#">포인트전송>></a>
						</td>
						<form action="/point/send.kh" method="post">
							<input type="hidden" name="pointWorkType" value="S">
							<input type="hidden" name="pointFromUser" value="${loginUser.memberId }">
							<input type="hidden" name="pointToUser" value="${trade.tradeWriter }">
							<input type="hidden" name="pointAmount" value="${trade.finalBiddingPrice }">
							<input type="hidden" name="boardNo" value="${trade.boardNo }">
						</form>
					</tr>
				</c:forEach>
			</table>					
		
		</div>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
</body>
<script>

//사이드 바 포인트관리 아코디언
	function pointNavi(target){
		var pointNaviDiv = target.nextElementSibling;
		var display = pointNaviDiv.style.display;
		if(display == 'none'){
			pointNaviDiv.style.display="block";
		}else{
			pointNaviDiv.style.display="none";
		}
	}
	
//포인트 전송버튼 함수
	function sendPoint(target,seller,soldOutCheck,userBalance,biddingPrice){
		event.preventDefault();
		if(soldOutCheck != 'Y'){
			if(userBalance > biddingPrice){
				if(confirm("정말 구매하시겠습니까?")){
					if(confirm(seller+"님에게 "+biddingPrice+"원을 보내시겠습니까?")){
						var pointSendForm = target.nextElementSibling;
						pointSendForm.submit();
					}
				}
			}else{
				alert("포인트 잔액이 부족합니다. 충전 후에 이용해주세요.");
			}
		}else{
			alert("구매 완료한 게시물입니다.");
		}
		
}
	
</script>
</html>