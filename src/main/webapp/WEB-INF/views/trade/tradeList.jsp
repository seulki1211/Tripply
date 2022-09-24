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
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
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
	로그인 유저 체크 :${loginUser.memberId }
		<div id="list-area">
			<table align="center" width="100%">
				<tr id="detail-raw-wrap">
					<c:forEach items="${tList }" var="trade" varStatus="N">
						<td id="detail-one-wrap" aligh="center" width="30%">
							<div class="detail title thumbnale-wrap" align="center">
								<img onclick="loginCheck('${loginUser.memberId}','/trade/detailView.kh?boardNo=${trade.boardNo }&currentPage=${paging.currentPage }');"  onerror="this.src='/resources/image/forest1.png';" src="${trade.thumbnailPath }" height="90%" width="90%">
							</div>
							<div align="center">
								<div id="soldOut-area">
									<c:if test="${trade.soldOut eq 'Y' }">[판매완료]</c:if>
									<c:if test="${trade.soldOut eq 'N' }">[판매중]</c:if>
								</div>
								<span class="detail region">[${trade.tLocationName }]</span>
								<a href="#" onclick="loginCheck('${loginUser.memberId}','/trade/detailView.kh?boardNo=${trade.boardNo }&currentPage=${paging.currentPage }');"  >
									<span class="detail title">
										<b>${trade.tradeTitle }</b>
									</span>
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
						</td>
						<c:if test="${N.count % 3 == 0 }">
							</tr><tr>
						</c:if>			
					</c:forEach>
				</tr>
			</table>
<!-- 페이징처리 -->
			<div id="pageNavi">
				<c:if test="${paging.startNavi > 1 }">
					<a href="/trade/${urlVal }.kh?currentPage=${paging.startNavi-1 }&searchCondition=${search.searchCondition }&searchRegion=${search.searchRegion }&searchValue=${search.searchValue}">[이전]</a>
				</c:if>
				<c:forEach begin="${paging.startNavi }" end="${paging.endNavi }" var="p" >
					<a href="/trade/${urlVal }.kh?currentPage=${p }&searchCondition=${search.searchCondition }&searchRegion=${search.searchRegion }&searchValue=${search.searchValue}">${p }</a>
				</c:forEach>
				<c:if test="${paging.endNavi < paging.endPage }">
					<a href="/trade/${urlVal }.kh?currentPage=${paging.endNavi+1 }&searchCondition=${search.searchCondition }&searchRegion=${search.searchRegion }&searchValue=${search.searchValue}">[다음]</a>
				</c:if>
			</div>
<!-- 컨텐츠 하단 버튼	 -->
			<div id="button">
				<button onclick="loginCheck('${loginUser.memberId}','/trade/writeView.kh');">글 작성</button>
			</div>
			
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