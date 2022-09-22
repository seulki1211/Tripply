<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>

<h1 align="center">쪽지 보내기</h1>
	<br><br>
		<table border='1'  align="center">			
			<tr>
			<td>받는이</td>
			<c:if test="${(chkResult eq 0) or (chkeResult eq null) }">
			<td><input type="text" name="msgReciever" value="${msgReciever }" placeholder="받는이를 확인해주세요"></td>
			<td><button type="button" onclick="chkReciever(this);"> 받는이 체크 </button><td>
			</c:if>
			</tr>
			</table>
		
			<form action="/message/send.kh" method="post">
					<table border='1'  align="center">			
			<c:if test="${chkResult eq 1}">
			<input type="hidden" name="msgReciever" value="${msgReciever }">

			<tr>
			<td>보낸이</td>
			<td><input type="text" name="msgWriter" value="${loginUser.memberNickname }" readonly></td>
			</tr>
			<tr>
			<td>제목</td>
			<td><input type="text" name="msgTitle"></td>
			<tr>
			<td>내용</td>
			<td> <textarea name="msgContents"></textarea>  </td>
			</tr>
	
			<tr>
			<td colspan='2' align='right'>
				<input type="submit" value="전송">
				<input type="reset" value="취소">
				<button type="button" onclick="location.href='/message/sendList.kh?msgWriter=${loginUser.memberNickname }'">리스트로</button>
				 
			</td>
			
			</tr>
					</c:if>	
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
	</script>
</body>
</html>