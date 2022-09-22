<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 조회</title>
</head>
<body>
<h1 align="center">${free.boardNo }번 게시글 상세 보기</h1>
	<br><br>
	<table align="center" width="500" border="1">
		<tr>
			<td>제목</td>
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
			<td>${free.foardCount }</td>
		</tr>
		<tr height="300">
			<td>내용</td>
			<td>${free.freeContents }
			<%-- <img alt="본문이미지" src="/resources/buploadFiles/${board.boardFileRename }"> --%>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>
				<img alt="본문이미지" src="/resources/buploadFiles/${free.freeFileRename }" 
				width="300" height="300">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<a href="/free/modifyView.kh?boardNo=${free.boardNo }&page=${page}">수정 페이지로 이동</a>
				<a href="#" onclick="freeRemove(${page});">삭제하기</a>
			</td>
		</tr>
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
		<c:forEach items="${rList }" var="reply">
			<tr>
				<td width="100">${reply.replyWriter }</td>
				<td>${reply.replyContents }</td>
				<td>${reply.rUpdateDate }</td>
				<td><a href="#" onclick="modifyView(this, '${reply.replyContents}', ${reply.replyNo });">수정</a>
				<a href="#" onclick="removeReply(${reply.replyNo});">삭제</a></td>
			</tr>
	<%-- 		<tr>
				<td colspan="3"><input type="text" size="50" value="${reply.replyContents }"></td>
				<td><button>수정</button></td>
			</tr> --%>
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
			//console.log(replyContents);
			//console.log(rNo);
			// <form action="" method=""></form>
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