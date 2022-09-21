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
 
  
<meta charset="UTF-8">
<title>게시글작성</title>
</head>
<body>
<div>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>
</div>

	<h1 align="center">게시글 등록 페이지</h1>
	<br><br>
		
	<form action="/free/register.kh" method="post" enctype="multipart/form-data">
	<div class="mb-3">
		<table border='1'  align="center">
			<tr>
			<td>제목</td>
			<td><input type="text" name="boardTitle"></td>
			<tr>
			<td>작성자</td>
			<td><input type="text" name="boardWriter" value='' readonly></td>
			</tr>
			<tr>
			<td>내용</td>
<!-- 			<td><textarea name="boardContents" cols='50' rows='30'></textarea></td> -->
						 <td> <textarea class="summernote" name="boardContents"></textarea>  </td>
			
			</tr>
			<tr>
			<td colspan='2' align='right'>
				<input type="submit" value="등록">
				<input type="reset" value="취소">
				<button type="button" onclick="/free/list.kh">리스트로</button> 
				 
			</td>
			</tr>
		</table>
	</form>
	
	<script>
	// 썸머노트 contents안에 들어감
	$('.summernote').summernote({
		  height: 300,
		  width:  700,
		  lang: "ko-KR",
       });

</script>
	
</body>
</html>