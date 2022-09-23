<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
</script>
<body>
					<select class="form-select form-select-lg mb-3" name="searchCondition" >
						<option <c:if test="${search.searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
						<option <c:if test="${search.searchCondition eq 'title'}">selected</c:if> value="title">제목</option>
						<option <c:if test="${search.searchCondition eq 'writer'}">selected</c:if> value="writer">작성자</option>
						<option <c:if test="${search.searchCondition eq 'contents'}">selected</c:if> value="contents">내용</option>
					</select>
						<select class="form-select form-select-lg mb-3" name="searchRegion" >
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
					<input type="text" name="searchValue" placeholder="검색" value="${search.searchValue }">
					<input type="submit" value="검색">

</body>
</html>