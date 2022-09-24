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
	.reReply{
		background-color:gainsboro;
		position:relative;
		left:10px;
		padding:10px;
		
	}
	#reply-menu{
		background-color:white;
		border: 1px solid black;
	}
	
	li{
		text-decoration: none;
	}
	
	table{
		backgound-color:blue;
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
					<td><c:if test="${trade.soldOut eq 'Y' }">[판매완료]</c:if> ${trade.tradeTitle }</td>
				</tr>
				<tr>
					<td colspan='3'>
						${trade.tradeWriter } 
						<span class="detail viewcount-wrap"> 
							<img alt="눈모양 아이콘" src="/resources/image/viewcount.jpg" width="25px" height="25px"> 
							조회수 ${trade.tradeCount } &nbsp;
					    </span> 
					    <span class="detail date">
					    	${trade.tCreateDate }
					    </span> 
					    <!-- 작성자인 경우에만 수정, 삭제버튼이 노출되도록 		 -->
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
<!-- 댓글 입력창: 구매자 댓글-->
 구매 채택된 사람 아이디: ${trade.buyerId }
		<c:if test="${loginUser.memberId ne trade.tradeWriter }">
		<div class="reply-input" align="center">
			<form onsubmit="inputCheck(this); inputPriceCheck(this);" action="/trade/reply/write.kh" method="post">
				<c:if test="${trade.soldOut ne 'Y' }">
					<input type="text" name="biddingPrice" placeholder="구매희망가격">
				</c:if>
				<input type="text" name="tReplyContents" value="" required="required" placeholder="댓글을 달아보세요!">
				<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
				<input type="hidden" name="boardNo" value="${trade.boardNo }"> 
				<input type="hidden" name="tReplyWriter" value="${loginUser.memberId }">
				<input type="hidden" name="reReplyYn" value="N"> 
				<input type="hidden" name="tRefReplyNo" value="-1">
				<button>등록</button>
			</form>
		</div>
		</c:if>
<!-- 댓글 입력창: 판매자 댓글-->
		<c:if test="${loginUser.memberId eq trade.tradeWriter }">
		<div class="reply-input" align="center">
			<form onsubmit="inputCheck(this)" action="/trade/reply/write.kh" method="post">
				<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
				<input type="text" name="tReplyContents" value="" required="required" placeholder="댓글을 달아보세요!">
				<input type="hidden" name="boardNo" value="${trade.boardNo }"> 
				<input type="hidden" name="tReplyWriter" value="${loginUser.memberId }">
				<input type="hidden" name="reReplyYn" value="N"> 
				<input type="hidden" name="tRefReplyNo" value="-1">
				<button>등록</button>
			</form>
		</div>
		</c:if>
<!-- 댓글출력  -->
		<table id="reply-view" align="center">
			<c:forEach items="${tReplyList }" var="tReply" varStatus="n">
				<tr id="one-reply-area">
					<td <c:if test="${tReply.reReplyYn eq 'Y' }">   class="reReply" </c:if> >
						<div id="replyInfo">
							${tReply.tReplyWriter } ${tReply.trCreateDate }
						</div>
						<div id="replyContents">
							<c:if test="${trade.tradeWriter eq tReply.tReplyWriter }"><b>[판매자]</b> </c:if>
							<c:if test="${trade.tradeWriter ne tReply.tReplyWriter and tReply.biddingPrice ne 0 }"><b>[구매희망가: ${tReply.biddingPrice }원]</b></c:if>
							${tReply.tReplyContents }
<!-- 댓글메뉴버튼 -->
							<span align="right" id="replyMenu">
								<c:if test="${(loginUser.memberId eq tReply.tReplyWriter) || (loginUser.memberId eq trade.tradeWriter) }">
									<a href="#" onclick="replyMenu(this);" class="replyMenuBtn"> ▤ </a>
								</c:if>
							</span>
						</div> 
						
<!-- 댓글메뉴 -->
  <!-- 댓글 수정 창 -->
						<div id="reply-menu" style="display:none">
							<ul>
								<c:if test="${loginUser.memberId eq tReply.tReplyWriter }">
									<li onclick="replyModify(this);" ><a href="#">댓글 수정</a></li>
									<div class="replyModify" style="display:none;">
										<form onsubmit="inputCheck(this)" action="/trade/reply/modify.kh" method="post">
											<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
											<input type="text" name="tReplyContents" value="${tReply.tReplyContents }" >
											<input type="hidden" name="boardNo" value="${trade.boardNo }"> 
											<input type="hidden" name="tReplyNo" value="${tReply.tReplyNo }">
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
										<input type="hidden" name="tReplyNo" value="${tReply.tReplyNo }">
										<input type="hidden" name="biddingPrice" value="${tReply.biddingPrice }">
									</form>
									<li><a href="/trade/reply/choiceCancel.kh">댓글 채택 취소</a></li>
								</c:if>
							</ul>
						</div>
						
<!-- 답글 버튼 -->
						<c:if test="${tReply.reReplyYn ne 'Y' }">
							<div onclick="arcodian(this);">
								<a href="#">답글 달기</a>
							</div>
<!-- 답글 입력창 -->
							<div class="reReply-input" style="display: none">
								<form onsubmit="inputCheck(this)" action="/trade/reply/write.kh" method="post">
										<input type="hidden" name="currentPage" value="${sessionScope.currentPage }"> 
										<input type="text" name="tReplyContents" value="" placeholder="답글을 입력해보세요!">
										<input type="hidden" name="boardNo" value="${trade.boardNo }">
										<input type="hidden" name="tReplyWriter" value="${loginUser.memberId }"> 
										<input type="hidden" name="reReplyYn" value="Y"> 
										<input type="hidden" name="tRefReplyNo" value="${tReply.tReplyNo }"> 
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
		var choiceForm = target.parentNode.nextElementSibling;
		console.log(choiceForm);
		if(confirm("정말 채택하시겠습니까?")){
			choiceForm.submit();
		}
}

//입력값 정규표현식 유효성 검사

	//클린한 댓글문화 정착, form태그하위의 text input태그가 두번째여야함.
	function inputCheck(thisForm){
		var inputReplyContents  = thisForm.childNodes[3].value;
		var regExpReplyContents = /시바견|아저씨발냄새|시바|씨발|씨1발|시1바|존나|존1나|존12나|졸라|쪼다|머저리 /;
		if(regExpReplyContents.test(inputReplyContents)){
			alert("클린한 댓글 문화를 위하여 비속어는 자제해주세요.");
			event.preventDefault();
		}
}

	//구매희망가격 숫자만 입력
	function inputPriceCheck(thisForm){
		var inputPrice = thisForm.childNodes[1].value;
		var regExpPrice = /\d+/;
		if(!regExpPrice.test(inputPrice)){
			alert("가격을 숫자로 입력하세요.");
			event.preventDefault();
		}
}
		
	
		
	</script>

</body>
</html>