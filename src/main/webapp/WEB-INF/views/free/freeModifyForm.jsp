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
<title>게시글 수정</title>
</head>
<body>
	<div>
		<jsp:include page="../common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
		<br>
		<form action="/free/modify.kh" method="post" enctype="multipart/form-data">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="boardNo" value="${free.boardNo }">
			<table align="center" border="1">
				<tr>
					<td>제목</td>
					<td><input type="text" name="freeTitle" value="${free.freeTitle }"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="freeWriter" value="${free.freeWriter }" readonly></td>
				</tr>
				<tr>
					<td>내용</td>
					<td> <textarea class="summernote" name="freeContents" value="${free.freeContents }"></textarea>  </td>
				</tr>
				<tr>
					<td colspan="2" align="right">
						<input type="submit" value="수정">
						<button type="button" onclick="javascript:history.go(-1);">취소</button> 
						<%-- <a href="/free/list.kh?page=${page }">목록으로</a>
						<a href="javascript:history.go(-1);">이전 페이지로</a> --%>
					</td>
				</tr>
			</table>
		</form>
		<script>
			$('.summernote').summernote({
			  height: 300,
			  width:  700,
			  lang: "ko-KR",
	    	});
		</script>
</body>
</html>