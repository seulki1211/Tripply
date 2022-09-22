<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
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
<jsp:include page="../../common/adminSideBar.jsp"></jsp:include>
 <div class="right-side">

	<br><br>
	<h1 align="center">${notice.noticeNo }번 게시글 수정 페이지</h1>
	<br><br>
	
	<form action="/admin/notice/modify.kh" method="post" >
	
		<input type="hidden" name="noticeNo" value='${notice.noticeNo }'>
		<input type="hidden" name="page" value='${page }'>
		
	
		<table align="center" class="table col-10">
			<tr>
			<td  class="col-2" scope="col" align='center'>제목</td>
			<td><input type="text" class="form-control" id="exampleFormControlInput1" name="noticeTitle" value='${notice.noticeTitle }'></td>
			</tr>
			<tr>
			<td class="col-2" scope="col" align='center'>작성자</td>
			<td><input type="text" class="form-control" id="exampleFormControlInput1" name="noticeWriter" value='${notice.noticeWriter }' readonly ></td>
			</tr>
			<tr>
			<td class="col-2" scope="col" align='center'>내용</td>
			<td><textarea class="summernote" name="noticeContents" >${notice.noticeContents }</textarea></td>
			</tr>
			
			<tr>
			<td colspan='2' align='right'>
				<input class="btn btn-primary"  type="submit" value="수정">
				<input  class="btn btn-danger" type="reset" value="취소">
				<button type="button"  class="btn btn-dark"onclick="location.href='/admin/notice/list.kh'">리스트로</button> 
				
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