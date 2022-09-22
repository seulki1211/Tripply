<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
</head>

<!-- css -->
<style>
	.modal{
		background-color:white;
		position:absolute;
		display:flex;
	}
</style>
<body>

<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
		<div id="sideBar"></div>
<!-- 게시물 출력 -->
		<div id="contents-1">
			<table align="center" border="1px">
				<tr>
					<td>${trade.boardNo }</td>
					<td>제목</td>
					<td>${trade.tradeTitle }</td>
				</tr>
				<tr>
					<td colspan='3'>${trade.tradeWriter } <span
						class="detail viewcount-wrap"> <img alt="눈모양 아이콘"
							src="/resources/image/viewcount.jpg" width="25px" height="25px">
							조회수 ${trade.tradeCount } &nbsp;
					</span> <span class="detail date">${trade.tCreateDate }</span> <!-- 작성자인 경우에만 수정, 삭제버튼이 노출되도록 		 -->
						<c:if test="${loginUser.memberId eq trade.tradeWriter }">
							<span class="detail btn"> <a
								href="/trade/modifyView.kh?boardNo=${trade.boardNo }">수정</a> <a
								href="/trade/remove.kh?boardNo=${trade.boardNo }">삭제</a>
							</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">${trade.tradeContents }</td>
				</tr>
			</table>
		</div>
<!-- 댓글 입력창 -->
 구매 채택된 사람 아이디: ${trade.buyerId }

		<div id="reply-input" align="center">
			<form action="/trade/reply/write.kh" method="post">
				<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
				<input type="hidden" name="boardNo" value="${trade.boardNo }"> 
				<input type="hidden" name="tReplyWriter" value="${loginUser.memberId }">
				<input type="hidden" name="reReplyYn" value="N"> 
				<input type="hidden" name="tRefReplyNo" value="-1"> 
				<input type="text" name="tReplyContents" value="" placeholder="댓글을 입력해보세요!">
				<button>등록</button>
			</form>
		</div>
<!-- 댓글출력  -->
		<table id="reply-view" align="center">
			<c:forEach items="${tReplyList }" var="tReply" varStatus="n">
				<tr id="one-reply-area">
					<td <c:if test="${tReply.reReplyYn eq 'Y' }"> style="background-color:Silver;" </c:if> >
						<div id="replyInfo">
							${tReply.tReplyWriter } ${tReply.trCreateDate }
						</div>
						<div id="replyContents">
							${tReply.tReplyContents }
							<span align="right" id="replyMenu">
<!-- 댓글메뉴 -->
								<c:if test="${(loginUser.memberId eq tReply.tReplyWriter) || (loginUser.memberId eq trade.tradeWriter) }">
									<a href="#" onclick="replyMenu(this);" > ▤ </a>
								</c:if>
							</span>
						</div> 
<!-- 댓글 수정 창 -->
						<div id="replyMenu" class="modal" style="display:none">
							<ul>
								<c:if test="${loginUser.memberId eq tReply.tReplyWriter }">
									<li onclick="replyModify(this);" ><a href="#">댓글 수정</a></li>
									<div class="replyModify" style="display:none;">
										<form action="/trade/reply/modify.kh" method="post">
											<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
											<input type="hidden" name="boardNo" value="${trade.boardNo }"> 
											<input type="hidden" name="tReplyNo" value="${tReply.tReplyNo }">
											<input type="text" name="tReplyContents" value="" placeholder="${tReply.tReplyContents }">
											<button>수정</button>
										</form>
									</div>
<!-- 댓글삭제 -->
									<li><a href="#" onclick="replyRemove(this);">댓글 삭제</a></li>
									<form action="/trade/reply/remove.kh" method="post">
										<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
										<input type="hidden" name="boardNo" value="${trade.boardNo }"> 
										<input type="hidden" name="tReplyNo" value="${tReply.tReplyNo }">
									</form>
								</c:if>
<!-- 댓글 채택		 -->
								<c:if test="${(loginUser.memberId eq trade.tradeWriter) and (loginUser.memberId ne tReply.tReplyWriter) }">
									<li><a href="#" onclick="submitChoice(this);">댓글 채택</a></li>
									<form class="choiceForm" action="/trade/reply/choice.kh" method="post">
										<input type="hidden" name="currentPage" value="${sessionScope.currentPage }">
										<input type="hidden" name="buyer" value="${tReply.tReplyWriter }">
										<input type="hidden" name="boardNo" value="${trade.boardNo }">
									</form>
									<li><a href="/trade/reply/choiceCancel.kh">댓글 채택 취소</a></li>
								</c:if>
							</ul>
						</div>
<!-- 답글 버튼 -->
						<c:if test="${tReply.reReplyYn ne 'Y' }">
							<div onclick="arcodian(this);">
								<a href="#">답글</a>
							</div>
<!-- 답글 입력창 -->
							<div class="reReply-input" style="display: none">
								<form action="/trade/reply/write.kh" method="post">
										<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
										<input type="hidden" name="boardNo" value="${trade.boardNo }">
										<input type="hidden" name="tReplyWriter" value="${loginUser.memberId }"> 
										<input type="hidden" name="reReplyYn" value="Y"> 
										<input type="hidden" name="tRefReplyNo" value="${tReply.tReplyNo }"> 
										<input type="text" name="tReplyContents" value="" placeholder="답글을 입력해보세요!">
									<button>등록</button>
								</form>
							</div>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
	<script>
//답글 아코디언 함수
		function arcodian(reReply) {
			event.preventDefault();
			var reReplyInput = reReply.nextElementSibling;
			var display = reReplyInput.style.display;

			if (display == "none") {
				reReplyInput.style.display = "block";
			} else {
				reReplyInput.style.display = "none";
			}
		}
//댓글 메뉴 아코디언 함수		
		function replyMenu(target){
			event.preventDefault();
			var replyMenu = target.parentNode.parentNode.nextElementSibling;
			var display = replyMenu.style.display;
			
			if (display == "none"){
				replyMenu.style.display ="block";
			}else{
				replyMenu.style.display ="none";
			}
		}
//댓글 수정 창 아코디언 함수
		function replyModify(target){
			event.preventDefault();
			var replyModifyInput = target.nextElementSibling;
			var display = replyModifyInput.style.display;
			
			if (display == "none"){
				replyModifyInput.style.display ="block";
			}else{
				replyModifyInput.style.display ="none";
			}
}

//댓글 삭제 함수
	function replyRemove(target){
	
	event.preventDefault();
	var replyRemoveForm = target.parentNode.nextElementSibling;
	console.log(replyRemoveForm);
	
	if(confirm("정말 삭제하시겠습니까?")){
		replyRemoveForm.submit();
	}else{
		
	}
}


//댓글 채택 실행 함수
	function submitChoice(target){
		event.preventDefault();
		var choiceForm = target.parentNote.nextElementSibling;
		console.log(choiceForm);
		if(confirm("정말 채택하시겠습니까?")){
			choiceForm.submit();
		}
}

//
		
	
		
	</script>

</body>
</html>