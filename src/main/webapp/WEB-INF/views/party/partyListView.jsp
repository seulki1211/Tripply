<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>동행자 게시판</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	
	 <div class="right-side">
	<br><br>
	<h1 align="center"> 우리 함께 여행가요!</h1>
	<br><br>
	<div class="row justify-content-center">
		<form action="/party/search.kh" method="get" >
		
					<select class="form-select" name="searchCondition" >
						<option <c:if test="${search.searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
						<option <c:if test="${search.searchCondition eq 'title'}">selected</c:if> value="title">제목</option>
						<option <c:if test="${search.searchCondition eq 'writer'}">selected</c:if> value="writer">작성자</option>
						<option <c:if test="${search.searchCondition eq 'contents'}">selected</c:if> value="contents">내용</option>
					</select>
	
						<select class="form-select" name="searchRegion" >
							<option <c:if test="${search.searchRegion eq '전국'}">selected</c:if> value="전국" label="전국"></option>
							<option <c:if test="${search.searchRegion eq '서울'}">selected</c:if> value="서울" label="서울"></option>
							<option <c:if test="${search.searchRegion eq '부산'}">selected</c:if> value="부산" label="부산"></option>
							<option <c:if test="${search.searchRegion eq '제주'}">selected</c:if> value="제주" label="제주"></option>
							<option <c:if test="${search.searchRegion eq '인천'}">selected</c:if> value="인천" label="인천"></option>
							<option <c:if test="${search.searchRegion eq '대전'}">selected</c:if> value="대전" label="대전"></option>
							<option <c:if test="${search.searchRegion eq '대구'}">selected</c:if> value="대구" label="대구"></option>
							<option <c:if test="${search.searchRegion eq '광주'}">selected</c:if> value="광주" label="광주"></option>
							<option <c:if test="${search.searchRegion eq '울산'}">selected</c:if> value="울산" label="울산"></option>
							<option <c:if test="${search.searchRegion eq '세종'}">selected</c:if> value="세종" label="세종"></option>
							<option <c:if test="${search.searchRegion eq '경기도'}">selected</c:if> value="경기도" label="경기도"></option>
							<option <c:if test="${search.searchRegion eq '강원도'}">selected</c:if> value="강원도" label="강원도"></option>
							<option <c:if test="${search.searchRegion eq '충청북도'}">selected</c:if> value="충청북도" label="충청북도"></option>
							<option <c:if test="${search.searchRegion eq '충청남도'}">selected</c:if> value="충청남도" label="충청남도"></option>
							<option <c:if test="${search.searchRegion eq '경상북도'}">selected</c:if> value="경상북도" label="경상북도"></option>
							<option <c:if test="${search.searchRegion eq '경상남도'}">selected</c:if> value="경상남도" label="경상남도"></option>
							<option <c:if test="${search.searchRegion eq '전라북도'}">selected</c:if> value="전라북도" label="전라북도"></option>
							<option <c:if test="${search.searchRegion eq '전라남도'}">selected</c:if> value="전라남도" label="전라남도"></option>
						</select>

					<input type="text" name="searchValue" placeholder="검색"  value="${search.searchValue }">
					<input type="submit" value="검색" class="btn btn-dark">
			</form>
		</div>
						<br>
			<div id="list-area">
			<table align="center" width="100%">
				<c:if test="${!empty pList }">
				<tr id="detail-raw-wrap" >
				<c:forEach items="${pList }" var="party" varStatus="i">
						<td id="detail-one-wrap" aligh="center" width="30%">
							<div class="detail title thumbnale-wrap" align="center" style="cursor:pointer" >
							<c:if test="${empty party.partyFileName }">
								<img alt="기본이미지" src="/resources/image/tripply-logo.png" width='80%' height='80%'>
							</c:if>
							<c:if test="${!empty party.partyFileName }">
								<img alt="유저이미지" src="/resources/partyuploadFiles/${party.partyFileRename}" width='80%' height='80%'>
							</c:if>							
							</div>
							<div align="center">
								<span class="detail region">[${party.partyLocation }]</span>
								
								
								<c:if test="${(!empty loginUser) and (today le party.partyFirstDate) }">
								<a href='/party/detail.kh?partyNo=${party.partyNo }&page=${currentPage }'>${party.partyTitle }</a>
								</c:if>
								
								<c:if test="${(!empty loginUser) and (today gt party.partyFirstDate) }">
								여행 기간이 만료되었습니다ㅜㅜㅜ
								</c:if>
								
								<c:if test="${empty loginUser }">
								${party.partyTitle }
								</c:if>
								
								<div class="detail writer">${party.partyWriter }</div>
								<div class="detail info-wrap">
									<div class="detail viewcount-wrap">
										<img alt="눈모양 아이콘" src="/resources/image/viewcount.jpg" width="25px" height="25px">
											${party.partyCount }
									</div> 
									<div  class="detail date">${party.pCreateDate }</div>
								</div>
							</div>
						</td>
						
						<c:if test="${i.count % 3 == 0 }">
						<tr></tr>
						</c:if>			
					</c:forEach>
		</tr>
						</c:if>
			<c:if test="${empty pList }">
			<tr>			
				<td colspan='6' align = 'center' scope="row"> <b>검색 결과가 없습니다.</b></td>
			</tr>
			</c:if>
			</table>
		</div>
	
				<!--  하단 페이지 목록 -->
	<table align="center" class="table col-12 table-borderless" width="100%">
		<tr align='center' height="20">
		<td   align='center'>
		<ul class="pagination justify-content-center">
			<c:if test="${currentPage != 1 }">
			<li class="page-item"><a class="page-link" href='/party/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>이전</a>
			</li>
			</c:if>
			
			<c:forEach var='p' begin="${startNavi }" end="${endNavi }">
				<c:if test="${currentPage eq p}">
				<li class="page-item disabled"><a class="page-link" href='#' >${p }</a></li>
				</c:if>
				<c:if test="${currentPage ne p}">
				<li class="page-item"><a class="page-link"  href="/party/${urlVal }.kh?page=${p }&searchCondition=${searchCondition }&searchValue=${searchValue }">${p }</a>
				</li>
				</c:if>
			</c:forEach>
			
			<c:if test="${currentPage < maxPage }">
			<li class="page-item"><a class="page-link"  href='/party/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${searchCondition }&searchValue=${searchValue }'>다음</a>
			</li>
			</c:if>
		</ul>
		</td>
		</tr>
		<tr>
		<td>
		
					
					<div class="row justify-content-center">
					<c:if test="${!empty loginUser }"><button type="button" class="btn btn-primary" onclick="location.href='/party/writeView.kh'">글 작성</button> 
					</c:if>
					</div>
	
	
		</td>
		</tr>
		<tr></tr>
		</tbody>
	</table>
	
</div>
</body>
</html>