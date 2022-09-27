<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/point/pointSend.css">
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
<!-- 사이드바	 -->
		<div id="sideBar">
				<div class="side-bar" onclick="location.href='/member/myPage.kh';"><p class="li-text">회원정보수정</p></div>
				<div class="side-bar" onclick="location.href='/free/myList.kh';"><p class="li-text">작성글</p></div>
				<div class="side-bar" onclick="seeyoulater()"><p class="li-text">북마크</p></div>
				<div class="side-bar" onclick="pointNavi(this);"><p class="li-text">포인트관리</p></div>
<!-- 사이드바 아코디언			 -->
				<div id="point-navi">
						<div id="side-history" onclick="location.href='/point/historyView.kh';" class="point-li">포인트 내역</div>
						<div id="side-charge" onclick="location.href='/point/chargeView.kh';" class="point-li">포인트 충전</div>
						<div id="side-send" onclick="location.href='/point/sendView.kh';" class="point-li">채택 상품 구매</div>
				</div>
		</div>
<!-- 본 컨텐츠 -->
		<div id="send-area">
			<div id="send-title">
				<div id="title"><h1>포인트 전송</h1></div>
				<span>현재 포인트 잔액 : ${loginUser.pointBalance }원</span>			
			</div><br><br>
			<table id="send-table">
				<tr>
					<th>제목</th>
					<th>판매자</th>
					<th>구매완료</th>
					<th>구매 채택 가격</th>
					<th>구매하기</th>
				</tr>
				<c:if test="${!empty tList }">
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
				</c:if>
				<c:if test="${empty tList }">
					<h3>[!!판매 게시물에 채택된 이력이 없습니다!!]</h3>
				</c:if>
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
	
	function seeyoulater() {
		alert("추후 업데이트 예정입니다.");
	}
</script>
</html>