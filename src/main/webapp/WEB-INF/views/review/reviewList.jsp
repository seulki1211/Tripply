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
	<div id="contents" width="90%">
		<div id="sideBar"></div>
		<div id="search-area">
			<form action="/review/search.kh" method="get">
				<jsp:include page="/WEB-INF/views/common/search.jsp"></jsp:include>
			</form>
		</div>
		<div id="list-area">
			<table align="center" width="100%">
				<tr>
					<c:forEach items="${rList }" var="review" varStatus="N">
						<td class="list-td" width="30%">
							<div>
								<img class="thumbnail" onerror="this.src='/resources/image/flower1.png';" src="" height="90%" width="90%">
							</div>
							<div>
								<div>${review.reviewTitle }</div>
								<div>${review.reviewWriter }</div>
								<div>${review.reviewCount }</div>
								<div>${review.rCreateDate }</div>
							</div>
						</td>
						<c:if test="${N.count % 3 == 0 }">
							</tr><tr>
						</c:if>			
					</c:forEach>
				</tr>
			</table>
			
			<div id="pageNavi">
				<c:if test="${paging.startNavi > 1 }">
					<a href="/review/list.kh?currentPage=${paging.startNavi-1 }">[이전]</a>
				</c:if>
				<c:forEach begin="${paging.startNavi }" end="${paging.endNavi }" var="p" >
					<a href="/review/list.kh?currentPage=${p }">${p }</a>
				</c:forEach>
				<c:if test="${paging.endNavi < paging.endPage }">
					<a href="/review/list.kh?currentPage=${paging.endNavi+1 }">[다음]</a>
				</c:if>
			</div>
			
			<div id="button">
				<button onclick="location.href='/review/writeView.kh';">글 작성</button>
			</div>
			
		</div>
	</div>
	<div id="footer"></div>
</body>
</html>