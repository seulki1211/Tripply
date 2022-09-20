<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
</script>
<body>
<div id="search-inputframe">
					<select name="searchCondition">
						<option <c:if test="${search.searchCondition eq 'title'}">selected</c:if> value="title">제목</option>
						<option <c:if test="${search.searchCondition eq 'writer'}">selected</c:if> value="writer">작성자</option>
						<option <c:if test="${search.searchCondition eq 'all'}">selected</c:if> value="all">전체</option>
						<option <c:if test="${search.searchCondition eq 'contents'}">selected</c:if> value="contents">내용</option>
					</select>
						<select name="searchRegion">
							<option <c:if test="${search.searchRegion eq '00'}">selected</c:if> value="00" label="전국"></option>
							<option <c:if test="${search.searchRegion eq '11'}">selected</c:if> value="11" label="서울"></option>
							<option <c:if test="${search.searchRegion eq '21'}">selected</c:if> value="21" label="부산"></option>
							<option <c:if test="${search.searchRegion eq '39'}">selected</c:if> value="39" label="제주"></option>
							<option <c:if test="${search.searchRegion eq '23'}">selected</c:if> value="23" label="인천"></option>
							<option <c:if test="${search.searchRegion eq '25'}">selected</c:if> value="25" label="대전"></option>
							<option <c:if test="${search.searchRegion eq '22'}">selected</c:if> value="22" label="대구"></option>
							<option <c:if test="${search.searchRegion eq '24'}">selected</c:if> value="24" label="광주"></option>
							<option <c:if test="${search.searchRegion eq '26'}">selected</c:if> value="26" label="울산"></option>
							<option <c:if test="${search.searchRegion eq '29'}">selected</c:if> value="29" label="세종"></option>
							<option <c:if test="${search.searchRegion eq '31'}">selected</c:if> value="31" label="경기도"></option>
							<option <c:if test="${search.searchRegion eq '32'}">selected</c:if> value="32" label="강원도"></option>
							<option <c:if test="${search.searchRegion eq '33'}">selected</c:if> value="33" label="충청북도"></option>
							<option <c:if test="${search.searchRegion eq '34'}">selected</c:if> value="34" label="충청남도"></option>
							<option <c:if test="${search.searchRegion eq '37'}">selected</c:if> value="37" label="경상북도"></option>
							<option <c:if test="${search.searchRegion eq '38'}">selected</c:if> value="38" label="경상남도"></option>
							<option <c:if test="${search.searchRegion eq '35'}">selected</c:if> value="35" label="전라북도"></option>
							<option <c:if test="${search.searchRegion eq '36'}">selected</c:if> value="36" label="전라남도"></option>
						</select>
					<input type="text" name="searchValue" placeholder="검색" value="${search.searchValue }">
					<input type="submit" value="검색">
				</div>

</body>
</html>