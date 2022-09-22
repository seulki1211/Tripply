<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 정보</title>
<script src="/resources/js/jquery-3.6.1.min.js"></script>

</head>
<body>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>


	<h1 align='center'> ${party.partyNo }번 게시글 상세 보기</h1>
	<br><br>
	<table border='1'  align="center"  border="1">
			<tr>
			<td width = '80px' >제목</td>
			<td>${party.partyTitle }</td>
			</tr>
			<tr>
			<td>작성자</td>
			<td>${party.partyWriter }</td>
			</tr>
			<tr>
			<td>작성날짜</td>
			<td>${party.pUpdateDate }</td>
			</tr>
			<tr>
			<td>조회수</td>
			<td>${party.partyCount }</td>
			</tr>
			<tr height="300" width='100'>
			<td>내용</td>
			<td>${party.partyContents }
			</tr>
			
<!-- 			<tr > -->
<!-- 			<td>첨부파일</td> -->
<!-- 				<td> -->
<%-- 					<c:if test="${not empty party.boardFilename}"> --%>
<%-- 						<img alt="본문이미지" src="/resources/buploadFiles/${board.boardFileRename }" width = '50%'> --%>
<%-- 					</c:if> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>
				<td colspan='2' align='center'>
				<button type="button" onclick="location.href='/party/list.kh?&page=${page }'">리스트로</button> 
				
					<c:if test="${party.partyWriter eq loginUser.memberNickname }">
						<button type="button" onclick="location.href='/party/modifyView.kh?partyNo=${party.partyNo }&page=${page }'">수정하기</button>
					</c:if> 	
					<c:if test="${party.partyWriter eq loginUser.memberNickname }">
						<button type="button" onclick="removeBoard(${party.partyNo}, ${page});">삭제하기</button> 
					</c:if> 	
				</td>
			</tr>
		</table>
		
<!-- 		댓글 등록 -->
<form action="/party/addReply.kh" method="post">
<input type="hidden" name="pReplyWriter" value = ${loginUser.memberNickname }>
<input type="hidden" name="refBoardNo" value=${party.partyNo }>
<input type="hidden" name="page" value=${page }>

		<table align='center' width = "500" border = "1">
			<tr>
				<td>
					<textarea rows="3" cols="55" name="pReplyContents"></textarea>
				</td>
				<td>
				<input type="submit" value='등록하기'>
				</td>
			</tr>
		</table>
</form>

<!-- 		댓글 목록 -->
		<table align='center' width='500' border='1'>
			<c:forEach items="${prList }" var="pReply" varStatus="i">
			<tr>
				<td width='100'> ${pReply.pReplyWriter }</td>
				<td>${pReply.pReplyContents }</td>
				<td>${pReply.prUpdateDate }</td>
				<td>
				<c:if test="${pReply.pReplyWriter eq loginUser.memberNickname }">
				<button type="button" onclick="modifyView(this, '${pReply.pReplyContents }', ${page} ,${pReply.pReplyNo } ,${pReply.refBoardNo });"> 수정 </button></c:if>
				<c:if test="${pReply.pReplyWriter eq loginUser.memberNickname }">
				<button type="button" onclick="removeReply(${pReply.refBoardNo },${page},${pReply.pReplyNo });"> 삭제 </button></c:if>
				</td>
					
			</tr>
			</c:forEach>
			
		</table>

<script type="text/javascript">
function removeBoard(partyNo,page) {
 	event.preventDefault();// 하이퍼링크 이동 방지
	if(confirm("삭제하시려면 '확인'을 클릭하세요")){
		location.href="/party/remove.kh?partyNo="+partyNo+"&page="+page;

	}
 	
};

 	function removeReply(partyNo, page, pReplyNo) {
 	 	event.preventDefault();// 하이퍼링크 이동 방지
 	 	if(confirm("삭제하시려면 '확인'을 클릭하세요")){
		location.href="/party/removeReply.kh?partyNo="+partyNo+"&page="+page+"&pReplyNo="+pReplyNo;
 	 	}
	};
	
	function modifyView(obj, pReplyContents, page, pReplyNo, refBoardNo) {
 	 	event.preventDefault();// 하이퍼링크 이동 방지
 	// alert("성공");
		var $tr = $("<tr>");
		$tr.append("<td colspan='3'><input type='text' size='50' value='"+pReplyContents+"' id='modifyInput'></td>");
		$tr.append("<td colspan='3'><button type='button' onclick='modifyReply(this, "+page+", "+pReplyNo+", "+refBoardNo+");'> 수정 </button></td>");
		console.log($tr[0]);
		console.log(obj); //obj는 this를 통해 이벤트가 발생한 태그
		$(obj).parent().parent().after($tr);
 	};
 	

 	function modifyReply(obj,page, pReplyNo, refBoardNo) {
		var inputTag = $(obj).parent().siblings().eq(0).children();
 		var pReplyContents = $("#modifyInput").val();
 		console.log(inputTag.val());
 		// console.log(replyContents);
 		// console.log(replyNo);
 		var $form = $("<form>"); // form태그
 		$form.attr("action", "/party/modifyReply.kh" );
		$form.attr("method", "post"); // form태그에 속성 추가
		$form.append("<input type='hidden' value='"+pReplyContents+"' name='pReplyContents'>");
		$form.append("<input type='hidden' value='"+pReplyNo+"' name='pReplyNo'>");
		$form.append("<input type='hidden' value='"+refBoardNo+"' name='refBoardNo'>");

		$form.append("<input type='hidden' value='"+page+"' name='page'>");

		$form.appendTo("body");
		$form.submit(); // 전송

		};
 	
	
</script>
</body>
</html>