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
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/point/pointHistory.css">
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
				<div class="my-side-bar" onclick="location.href='#';">회원정보수정</div>
				<div class="my-side-bar" onclick="location.href='/free/list.kh';">작성글</div>
				<div class="my-side-bar">북마크</div>
				<div class="my-side-bar" onclick="pointNavi(this);">포인트관리</div>
<!-- 사이드바 아코디언			 -->
				<div id="point-navi">
					<ul>
						<div class="navi li"><li onclick="location.href='/point/historyView.kh';">포인트 내역</li></div>
						<div class="navi li"><li onclick="location.href='/point/chargeView.kh';">포인트 충전</li></div>
						<div class="navi li"><li onclick="location.href='/point/sendView.kh';">채택된 상품 구매하기</li></div>
					</ul>
				</div>
		</div>
<!-- 본 컨텐츠 -->
		<div id="history-area">
			<div id="history-title">
				<div id="title"><h1>포인트 내역확인</h1></div>
				<span id="balance">현재 포인트 잔액 : ${loginUser.pointBalance }원</span>
			</div><br><br>
			<table id="history-table" >
				<c:if test="${!empty pList }">
				<tr>
					<th>날짜</th>
					<th>내용</th>
				</tr>
				<c:forEach items="${pList }" var="point" varStatus="n">
					<tr>
						<td>${point.pCreateDate}</td>
						<td>
						<c:if test="${point.pointWorkType eq 'C' }">
								    ${point.pointAmount }포인트를 충전하였습니다.
						</c:if>
						<c:if test="${point.pointWorkType eq 'S' }">
							<c:if test="${point.pointFromUser eq loginUser.memberId }">
								${point.pointToUser }님에게 ${point.pointAmount }포인트를 보냈습니다.
							</c:if>
							<c:if test="${point.pointToUser eq loginUser.memberId }">
									${point.pointFromUser }님에게 ${point.pointAmount }포인트를 받았습니다.
							</c:if>
						</c:if>
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty pList }">
					<tr>
						<td colspan="2">
							<span>결과가 존재하지 않습니다.</span>
						</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
<!-- 푸터 -->
	<div id="footer">
	</div>
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
</script>
</html>