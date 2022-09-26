<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
<link rel="stylesheet" href="/resources/css/review/reviewDetail.css">
</head>

<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
<!-- 게시물 출력 -->
		<div id="detail-area">
			<table id="detail-table">
				<tr>
					<td id="detail-title"><h2>${review.reviewTitle }<h2></h3></td>
				</tr>
			   	<tr>
					<td id="detail-info">
						글번호: ${review.boardNo }
						작성자: ${review.reviewWriter }
						<span class="detail viewcount-wrap">
							<img alt="눈모양 아이콘" src="/resources/image/viewcount.jpg" width="25px" height="25px">
							조회수: ${review.reviewCount } &nbsp;
						</span>
						<span  class="detail date">
							날짜: ${review.rCreateDate }
						</span>
						<!-- 작성자인 경우에만 수정, 삭제버튼이 노출되도록 		 -->
						<c:if test="${loginUser.memberId eq review.reviewWriter }">
							<span class="detail btn">
								<a href="/review/modifyView.kh?boardNo=${review.boardNo }">수정</a> 
								<a href="/review/remove.kh?boardNo=${review.boardNo }">삭제</a>
							</span>
						</c:if>
					</td>
				</tr>
				<tr>
					<td id="detail-contents">
							${review.reviewContents }
					</td>
				</tr>
			</table>
		</div>
<!-- 댓글 입력창 -->
		<div class="reply-input">
			<form onsubmit="inputCheck(this);" action="/review/reply/write.kh" method="post">
				<input type="hidden" name="page" value="${sessionScope.page }">
				<input class="reText" type="text" name="rReplyContents" value="" placeholder="댓글을 입력해보세요!">
				<input type="hidden" name="boardNo" value="${review.boardNo }">
				<input type="hidden" name="rReplyWriter" value="${loginUser.memberId }">
				<input type="hidden" name="reReplyYn" value="N">
				<input type="hidden" name ="rRefReplyNo" value="-1">
				<button class="reBtn">등록</button>
			</form>
		</div>
<!-- 댓글출력  -->
	<div id="reply-wrap">
		<table id="reply-table">
			<c:forEach items="${rReplyList }" var="rReply" varStatus="n">
				<tr class="one-reply-area">
					<td   >
						<div id="oneReply"  <c:if test="${rReply.reReplyYn eq 'Y' }"> class="reReply" </c:if>>
							<div id="replyInfo-wrap">
								<p id="reWriter" class="replyInfo">${rReply.rReplyWriter }</p>
								<p id= "reDate" class="replyInfo">${rReply.rrCreateDate }</p>
							</div>
							<div id="replyContents">
								${rReply.rReplyContents }
	<!-- 댓글메뉴버튼 -->
	 						<c:if test="${rReply.rrStatus ne 'N' }">
								<div id="replyMenuBtn-area">
									<c:if test="${(loginUser.memberId eq rReply.rReplyWriter) || (loginUser.memberId eq review.reviewWriter) }">
										<a href="#" onclick="replyMenu(this);" class="replyMenuBtn"> ▤ </a>
									</c:if>
								</div>
							</div>
							
	<!-- 댓글메뉴 -->
	 <!-- 댓글 수정 창 -->
								<div id="reply-menu">
									<ul>
										<c:if test="${loginUser.memberId eq rReply.rReplyWriter }">
											<li onclick="replyModify(this);" ><a href="#">댓글 수정</a></li>
											<div class="replyModify" style="display:none;">
												<form onsubmit="inputCheck(this)" action="/review/reply/modify.kh" method="post">
													<input type="hidden" name="page" value="${sessionScope.page }"> 
													<input type="text" name="rReplyContents" value="${rReply.rReplyContents }" >
													<input type="hidden" name="boardNo" value="${review.boardNo }"> 
													<input type="hidden" name="rReplyNo" value="${rReply.rReplyNo }">
													<button>수정</button>
												</form>
											</div>
		  <!-- 댓글삭제 -->
											<li><a href="#" onclick="replyRemove(this);">댓글 삭제</a></li>
											<form action="/review/reply/remove.kh" method="post">
												<input type="hidden" name="page" value="${sessionScope.page }"> 
												<input type="hidden" name="boardNo" value="${review.boardNo }"> 
												<input type="hidden" name="rReplyNo" value="${rReply.rReplyNo }">
											</form>
										</c:if>
								</div>
	 						</c:if>
							
	<!-- 답글 버튼 -->
							<c:if test="${rReply.reReplyYn ne 'Y' and rReply.rrStatus ne 'N'}">
								<div onclick="arcodian(this);">
									<a href="#">답글 달기</a>
								</div>
							
							<!-- 답글 입력창 -->
								<div class="reReply-input" style="display:none" >
									<form onsubmit="inputCheck(this);" action="/review/reply/write.kh" method="post">
										<input type="hidden" name="page" value="${sessionScope.page }">
										<input type="text" name="rReplyContents" value="" placeholder="답글을 입력해보세요!">
										<input type="hidden" name="boardNo" value="${review.boardNo }">
										<input type="hidden" name="rReplyWriter" value="${loginUser.memberId }">
										<input type="hidden" name="reReplyYn" value="Y">
										<input type="hidden" name ="rRefReplyNo" value="${rReply.rReplyNo }">
										<button>등록</button>
									</form>
								</div>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	
	
<!-- 푸터 -->
	<div id="footer">
	</div>
	
	
	<script>
//답글 아코디언 함수
		function arcodian(reReply){
			event.preventDefault();
			var reReplyInput = reReply.nextElementSibling;
			var display = reReplyInput.style.display;
			
			if(display == "none"){
				reReplyInput.style.display="block";
			}else{
				reReplyInput.style.display="none";
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

//입력값 정규표현식 유효성 검사
//클린한 댓글문화 정착, form태그하위의 text input태그가 두번째여야함.
		function inputCheck(thisForm){
			var inputReplyContents  = thisForm.childNodes[3].value;
			var regExpReplyContents = /(시|씨)(발|바|빨|박|벌|붤|봘|뽤)|(조|존|졸)(라|나|)|(새|색|섹)(기|끼)|(병|빙|븅)(신|쉰|싄)/;
			if(regExpReplyContents.test(inputReplyContents)){
				alert("클린한 댓글 문화를 위하여 비속어는 자제해주세요.");
				event.preventDefault();
			}
	}

	</script>
</body>
</html>