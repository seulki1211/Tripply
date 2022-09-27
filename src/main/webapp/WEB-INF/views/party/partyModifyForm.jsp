<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> -->
<!--   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> -->
  
  <!-- 서머노트를 위해 추가해야할 부분 -->
  <script src="/resources/js/summernote-lite.js"></script>
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
  
<meta charset="UTF-8">
<title>수정 페이지</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	
<div class="right-side">
	
	<br><br>
<h1 align="center">게시글 수정</h1>
	<br><br>
	
	<form action="/party/modify.kh" method="post" enctype="multipart/form-data">
		<input type="hidden" name="partyNo" value='${party.partyNo }'>
		<input type="hidden" name="partyFileName" value='${party.partyFileName }'>
		<input type="hidden" name="partyFileRename" value='${party.partyFileRename }'>
		<input type="hidden" name="page" value='${page }'>
	
		<table align="center" class="table col-10">
		<tr>
			<td  class="col-2" scope="col" align='center'>썸네일 수정</td>
			<td><input type="file" name="reloadFile">
				<a href="">${party.partyFileName }</a>
				<br>*200px*200px을 권장합니다!
			</td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>제목</td>
			<td><input type="text" name="partyTitle" class="form-control" id="exampleFormControlInput1" value='${party.partyTitle }'></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>작성자</td>
			<td><input type="text" name="partyWriter" class="form-control" id="exampleFormControlInput1" value='${party.partyWriter }' readonly ></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>내용</td>
			<td><textarea class="summernote" name="partyContents" >${party.partyContents }</textarea></td>
			</tr>
			
			<tr>
			<td colspan='2' align='right'>
				<input type="submit" value="수정하기" class="btn btn-warning" >
				<button type="button" onclick="location.href= 'javascript:history.go(-1);'" class="btn btn-dark">이전페이지로</button> 
				<button type="button" onclick="location.href='/party/list.kh'"class="btn btn-dark">리스트로</button> 
				
			</td>
			</tr>
		</table>
	</form>
	
	</div>
	
	<script>
	$('.summernote').summernote({
		  height: 300,
		  lang: "ko-KR"
		});
	</script>
</body>
</html>