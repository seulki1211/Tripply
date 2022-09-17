<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="search-inputframe">
					<select name="searchCondition">
						<option value="all">전체</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="contents">내용</option>
					</select>
						<select name="searchRegion">
							<option value="00" label="전국"></option>
							<option value="11" label="서울"></option>
							<option value="21" label="부산"></option>
							<option value="39" label="제주"></option>
							<option value="23" label="인천"></option>
							<option value="25" label="대전"></option>
							<option value="22" label="대구"></option>
							<option value="24" label="광주"></option>
							<option value="26" label="울산"></option>
							<option value="29" label="세종"></option>
							<option value="31" label="경기도"></option>
							<option value="32" label="강원도"></option>
							<option value="33" label="충청북도"></option>
							<option value="34" label="충청남도"></option>
							<option value="37" label="경상북도"></option>
							<option value="38" label="경상남도"></option>
							<option value="35" label="전라북도"></option>
							<option value="36" label="전라남도"></option>
						</select>
					<input type="text" name="searchValue" placeholder="검색">
					<input type="submit" value="검색">
				</div>

</body>
</html>