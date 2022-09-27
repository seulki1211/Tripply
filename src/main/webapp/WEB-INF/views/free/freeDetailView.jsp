<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 조회</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div>
		<jsp:include page="../common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	<br><br>
	<table align="center" width="500" border="1">
		<tr>
			<td width="15%">제목</td>
			<td>${free.freeTitle }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${free.freeWriter }</td>
		</tr>
		<tr>
			<td>작성날짜</td>
			<td>${free.fCreateDate }</td>
		</tr>
		<tr>
			<td>조회수</td>
			<td>${free.freeCount }</td>
		</tr>
		<tr height="300">
			<td>내용</td>
			<td>${free.freeContents }</td>
		</tr>
		<c:if test="${loginUser.memberNickname eq free.freeWriter }">
			<tr>
				<td colspan="2" align="center">
					<a href="/free/modifyView.kh?boardNo=${free.boardNo }&page=${page}">글 수정</a>
					<a href="#" onclick="freeRemove(${page});">삭제하기</a>
				</td>
			</tr>
		</c:if>
	</table>
	<!-- 	댓글 등록 -->
	<form action="/free/addReply.kh" method="post">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="boardNo" value="${free.boardNo }">
		<table align="center" width="500" border="1">
			<tr>
				<td>
					<textarea rows="3" cols="55" name="freeReplyContents"></textarea>
				</td>
				<td>
					<input type="submit" value="등록하기">
				</td>
			</tr>
		</table>
	</form>
	<!-- 	댓글 목록 -->
	<table align="center" width="500" border="1">
		<c:forEach items="${fRList }" var="freeReply" >
			<tr>
				<td width="100">${freeReply.freeReplyWriter }</td>
				<td>${freeReply.freeReplyContents }</td>
				<td>${freeReply.fUpdateDate }</td>
				<td><a href="#" onclick="modifyView(this, '${freeReply.freeReplyContents}', ${freeReply.freeReplyNo });">수정</a>
				<a href="#" onclick="removeReply(${freeReply.freeReplyNo});">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<script>
		function freeRemove(value) {
			event.preventDefault(); // 하이퍼링크 이동 방지
			if(confirm("게시물을 삭제하시겠습니까?")) {
				location.href="/free/remove.kh?page="+value;
			}
		}
		function removeReply(replyNo) {
			event.preventDefault();
			console.log(replyNo);
			if(confirm("정말 삭제하시겠습니까?")) {
				var $delForm = $("<form>");
				$delForm.attr("action", "/free/removeReply.kh");
				$delForm.attr("method", "POST");
				$delForm.append("<input type='hidden' name='freeReplyNo' value='"+freeReplyNo+"'>");
				$delForm.appendTo("body");
				$delForm.submit();
			}
		}
		function modifyView(obj, fReplyContents, freeReplyNo) {
			event.preventDefault();
			var $tr = $("<tr>");
			$tr.append("<td colspan='3'><input type='text' size='50' value='"+fReplyContents+"'></td>");
			$tr.append("<td><button onclick='modifyReply(this, "+freeReplyNo+");'>수정</button></td>");
			//console.log($tr[0]);
			//console.log(obj); // obj는 this를 통해 이벤트가 발생한 태그
			$(obj).parent().parent().after($tr);
		}
		function modifyReply(obj, rNo) {
			var inputTag = $(obj).parent().prev().children();
			console.log(inputTag.val());
			var freeReplyContents = inputTag.val(); //= $("#modifyInput").val();
			var $form =$("<form>");
			$form.attr("action", "/free/modifyReply.kh");
			$form.attr("method", "post");
			$form.append("<input type='hidden' value='"+freeReplyContents+"' name='freeReplyContents'>");
			$form.append("<input type='hidden' value='"+rNo+"' name='freeReplyNo'>");
			console.log($form[0]);
			$form.appendTo("body");
			$form.submit();
		}
		
	</script>
</body>
</html>