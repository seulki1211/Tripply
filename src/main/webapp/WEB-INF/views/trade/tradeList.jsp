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
<link rel="stylesheet" href="/resources/css/trade/tradeList.css">
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	<br><br>
	<h1 align="center" style="font-weight:bolder"> 여행 상품 사고 팔아요</h1>
	<br>
<!-- 컨텐츠	 -->
	<div id="contents" width="90%">
		<div id="sideBar"></div>
<!-- 검색		 -->
		<div id="search-area">
			<form action="/trade/search.kh" method="get">
				<jsp:include page="/WEB-INF/views/common/search.jsp"></jsp:include>
			</form>
		</div>
<!-- 게시물목록출력 -->
		<div id="list-area">
			<table id="list-table">
			<c:if test="${!empty tList }">
				<tr>
					<c:forEach items="${tList }" var="trade" varStatus="N">
						<td class="detail-one-wrap">
						<div class="td-wrap">
							<div class="thumbnale-wrap">
								<img class="listImage" onclick="loginCheck('${loginUser.memberId}','/trade/detail.kh?boardNo=${trade.boardNo }&page=${paging.page }');"  onerror="this.src='/resources/image/forest1.png';" src="${trade.thumbnailPath }" height="90%" width="90%">
							</div>
							<div>
								<div class="detail region">[${trade.tLocationName }]</div>
								<span id="soldOut-area">
									<c:if test="${trade.soldOut eq 'Y' }">[판매완료]</c:if>
									<c:if test="${trade.soldOut eq 'N' }">[판매중]</c:if>
								</span>
								<a href="#" onclick="loginCheck('${loginUser.memberId}','/trade/detail.kh?boardNo=${trade.boardNo }&page=${paging.page }');"  >
									<div class="detail title-wrap">
										<p class="title"><b>${trade.tradeTitle }</b></p>
									</div>
								</a>
								<div class="detail writer">${trade.tradeWriter }</div>
								<div class="detail info-wrap">
									<div class="detail viewcount-wrap">
										<img alt="눈모양 아이콘" src="/resources/image/viewcount.jpg" width="25px" height="25px">
											${trade.tradeCount }
									</div> 
									<div  class="detail date">${trade.tCreateDate }</div>
								</div>
							</div>
						</div>
						</td>
						<c:if test="${N.count % 3 == 0 }">
							</tr><tr>
						</c:if>			
					</c:forEach>
				</tr>
				</c:if>
			<c:if test="${empty tList }">
				<tr>
					<td colspan="3">
						<span>결과가 존재하지 않습니다.</span>
					</td>
				</tr>
			</c:if>
			</table>
<!-- 컨텐츠 하단 버튼	 -->
			<div id="write-wrap">
				<button class="writeBtn" onclick="loginCheck('${loginUser.memberId}','/trade/writeView.kh');">글 작성</button>
			</div>
<!-- 페이징처리 -->
			<c:if test="${paging ne null }">
			<div id="pageNavi">
				<c:if test="${paging.startNavi > 1 }">
					<a href="/trade/${urlVal }.kh?page=${paging.startNavi-1 }&searchCondition=${search.searchCondition }&searchRegion=${search.searchRegion }&searchValue=${search.searchValue}"><p class="naviBtn"> < </p></a>
				</c:if>
				<c:forEach begin="${paging.startNavi }" end="${paging.endNavi }" var="p" >
					<a href="/trade/${urlVal }.kh?page=${p }&searchCondition=${search.searchCondition }&searchRegion=${search.searchRegion }&searchValue=${search.searchValue}"><p class="naviBtn">${p }</p></a>
				</c:forEach>
				<c:if test="${paging.endNavi < paging.endPage }">
					<a href="/trade/${urlVal }.kh?page=${paging.endNavi+1 }&searchCondition=${search.searchCondition }&searchRegion=${search.searchRegion }&searchValue=${search.searchValue}"><p class="naviBtn"> > </p></a>
				</c:if>
			</div>
			</c:if>

			
		</div>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
</body>
<!-- 스크립트영역 -->
<script>
		function loginCheck(loginId,url){
			if(loginId != ""){
				location.href=url;
			}else{
				alert("로그인을 해주세요.")
			}
		}
</script>
</html>