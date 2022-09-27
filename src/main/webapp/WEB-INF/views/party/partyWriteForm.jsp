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
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
  
<meta charset="UTF-8">
<title>게시글작성</title>
</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	
<div class="right-side">

	<br><br>
	<h1 align="center">동행자 구하기!</h1>
	<br><br>
		
	<form action="/party/register.kh" method="post" enctype="multipart/form-data" name='partyForm'>
	
		<table align="center" class="table col-10">
			<tr>
			<td  class="col-2" scope="col" align='center'>썸네일 등록</td>
			<td><input type="file" name="uploadFile"><br>*200px*200px을 권장합니다!</td>
			<tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>제목</td>
			<td><input type="text" name="partyTitle" class="form-control" id="exampleFormControlInput1"></td>
			<tr>
			<td  class="col-2" scope="col" align='center'>작성자</td>
			<td><input type="text" name="partyWriter" class="form-control" id="exampleFormControlInput1" value = "${loginUser.memberNickname }" readonly></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>일정 시작일</td>
			<td><input type="date" name="partyFirstDate"></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>일정 마지막일</td>
			<td><input type="date" name="partyLastDate" ></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>여행 장소</td>
			<td>
				<select  name="partyLocation">
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
			<td  class="col-2" scope="col" align='center'>내용</td>
			<td> <textarea class="summernote" name="partyContents"></textarea>  </td>
			</tr>
			<tr>
			<td colspan='2' align='right'>
				<input onclick="partyCheck();" type="button" value="등록" class='btn btn-dark'>
				<input type="reset" value="취소" class='btn btn-dark'>
				<button type="button" onclick="location.href='/party/list.kh'" class='btn btn-dark'>리스트로</button> 
				 
			</td>
			</tr>
		</table>
	</form>
	</div>

	<script>
	
		$('.summernote').summernote({
			height : 300,
			lang : "ko-KR",
		});
		
		function partyCheck() {
			if(partyForm.partyTitle.value=="") { // document 를 생략해도 됨
		        alert("제목을 입력하세요!");
		        partyForm.partyTitle.focus();
		    	return false;
			}else if(partyForm.partyFirstDate.value==""){
		        alert("일정 시작일을 입력하세요");
		        partyForm.partyFirstDate.focus();
		        return false;
		    }else if(partyForm.partyLastDate.value==""){
		        alert("일정 마지막일을 입력하세요");
		        partyForm.partyLastDate.focus();
		        return false;
		    }else if(partyForm.partyContents.value==""){
		        alert("내용을 입력하세요");
		        partyForm.partyContents.focus();
		        return false;

		    }
			return partyForm.submit();
		 }
			
		
	</script>

</body>
</html>