<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">

</head>
<body>
	<br><br>
<h3 align="center">답장 보내기</h3>
		
		
			<form action="/message/send.kh" method="post" name='messageForm'>
			<table align="center" class="table col-10">
			<tr>
			<td  class="col-2" scope="col" align='center'>받는이</td>
			<td><input type="text" name="msgReciever" class="form-control"  value="${msgReciever }" readonly></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>보낸이</td>
			<td><input type="text" name="msgWriter"   class="form-control" value="${loginUser.memberNickname }" readonly></td>
			</tr>
			<tr>
			<td  class="col-2" scope="col" align='center'>제목</td>
			<td><input type="text" name="msgTitle"  class="form-control" ></td>
			<tr>
			<td  class="col-2" scope="col" align='center'>내용</td>
			<td> <textarea name="msgContents" class="form-control"></textarea>  </td>
			</tr>
	
			<tr>
			<td colspan='2' align='right'>
				<input type="button" value="전송" class="btn btn-primary" onclick='messageChk();'>
				<input type="reset" value="취소" class="btn btn-warning">
				<button type="button" class="btn btn-dark" onclick="location.href='/message/sendList.kh?msgWriter=${loginUser.memberNickname }'">리스트로</button>
				 
			</td>
			
			</tr>
			</table>
			</form>
			
	<script type="text/javascript">
	
	function chkReciever(obj) {
 	 	event.preventDefault();// 하이퍼링크 이동 방지
		var msgReciever = $(obj).parent().prev().children(0).val();
		
		var $form = $("<form>"); // form태그
 		$form.attr("action", "/message/chkReciever.kh" );
		$form.attr("method", "post"); // form태그에 속성 추가
		$form.append("<input type='hidden' value='"+msgReciever+"' name='msgReciever'>")
		$form.appendTo("body");
		$form.submit(); // 전송
	}

	function messageChk() {
		if(messageForm.msgTitle.value=="") { // document 를 생략해도 됨
		        alert("제목을 입력하세요!");
		        messageForm.msgTitle.focus();
		    	return false;
			}else if(messageForm.msgContents.value==""){
		        alert("내용을 입력하세요!");
		        messageForm.msgContents.focus();
		        return false;

		    }
			return messageForm.submit();
	}
	</script>
</body>
</html>