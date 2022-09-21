<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="/resources/js/summernote-lite.js"></script>
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
  <!--  -->
  
<meta charset="UTF-8">
<title>게시글작성</title>
</head>
<body>
<div>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>
</div>

	<h1 align="center">파티 등록 페이지</h1>
	<br><br>

		
	<form action="/party/register.kh" method="post" enctype="multipart/form-data">
	
	
		<table border='1'  align="center">
		
			<tr>
			<td>썸네일 등록</td>
			<td><input type="file" name="uploadFile"></td>
			<tr>
			<tr>
			<td>제목</td>
			<td><input type="text" name="partyTitle"></td>
			<tr>
			<td>작성자</td>
			<td><input type="text" name="partyWriter" value = "${loginUser.memberNickname }" readonly></td>
			</tr>
			<tr>
			<td>일정 시작일</td>
			<td><input type="date" name="partyFirstDate" ></td>
			</tr>
			<tr>
			<td>일정 마지막일</td>
			<td><input type="date" name="partyLastDate" ></td>
			</tr>
			<tr>
			<td>여행 장소</td>
			<td>
					<select name="partyLocation">
							<option value="전국" label="전국"></option>
							<option value="서울" label="서울"></option>
							<option value="부산" label="부산"></option>
							<option value="제주" label="제주"></option>
							<option value="인천" label="인천"></option>
							<option value="대전" label="대전"></option>
							<option value="대구" label="대구"></option>
							<option value="광주" label="광주"></option>
							<option value="울산" label="울산"></option>
							<option value="세종" label="세종"></option>
							<option value="경기도" label="경기도"></option>
							<option value="강원도" label="강원도"></option>
							<option value="충청북도" label="충청북도"></option>
							<option value="충청남도" label="충청남도"></option>
							<option value="경상북도" label="경상북도"></option>
							<option value="경상남도" label="경상남도"></option>
							<option value="전라북도" label="전라북도"></option>
							<option value="전라남도" label="전라남도"></option>
						</select>
						</td>
			</tr>
			<tr>
			<td>내용</td>
			<td> <textarea class="summernote" name="partyContents"></textarea>  </td>
			</tr>
			<tr>
			<td colspan='2' align='right'>
				<input type="submit" value="등록">
				<input type="reset" value="취소">
				<button type="button" onclick="location.href='/party/list.kh'">리스트로</button> 
				 
			</td>
			</tr>
		</table>
	</form>

	<script>
	
		$('.summernote').summernote({
			height : 300,
			width : 700,
			lang : "ko-KR",
		});
	</script>

</body>
</html>