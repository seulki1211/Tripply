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
  <script src="/resources/js/summernote/lang"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
  <!--  -->
<meta charset="UTF-8">
<title>수정 페이지</title>
</head>
<body>
	<jsp:include page="../../common/menuBar.jsp"></jsp:include>

<h1 align="center">${notice.noticeNo }번 게시글 수정 페이지</h1>
	<br><br>
	
	<form action="/admin/notice/modify.kh" method="post" >
	
<!-- 	board에 이미 데이터가 다 담겨있음 가져오고 싶은데 보여주기 싫으면 hidden 으로 처리-->
		<input type="hidden" name="noticeNo" value='${notice.noticeNo }'>
		<input type="hidden" name="page" value='${page }'>
		
		
	
		<table border='1'  align="center">
			<tr>
			<td>제목</td>
			<td><input type="text" name="noticeTitle" value='${notice.noticeTitle }'></td>
			</tr>
			<tr>
			<td>작성자</td>
			<td><input type="text" name="noticeWriter" value='${notice.noticeWriter }' readonly ></td>
			</tr>
			<tr>
			<td>내용</td>
			<td><textarea class="summernote" name="noticeContents" >${notice.noticeContents }</textarea></td>
			</tr>
			
			<tr>
			<td colspan='2' align='right'>
				<input type="submit" value="수정">
				<button type="button" onclick="location.href= 'javascript:history.go(-1);'">이전페이지로</button> 
				<button type="button" onclick="location.href='/admin/notice/list.kh'">리스트로</button> 
				
			</td>
			</tr>
		</table>
	</form>
	
	<script>
	$('.summernote').summernote({
		  height: 300,
		  lang: "ko-KR"
		});
	</script>
</body>
</html>