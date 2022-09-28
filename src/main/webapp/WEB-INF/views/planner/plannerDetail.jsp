<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<title>플래너 상세</title>
<link href="../../resources/css/map.css" rel="stylesheet">
</head>

	<style>
	.wrap{
		width: 100%;
		height: 100%;
		box-sizing: border-box;
	}
	.header {
		background-color:lightblue;
		width: 100%;
		height: 10%;
		 border: 1px solid; 
		box-sizing: border-box;
	}
	.info{
		
		margin-left:60px;
		margin-top:20px;
		margin-bottom:20px;
		width:100%;
		height:70%;
		box-sizing: border-box;
		
	}
	.writeInfo{
		background-color:white;
		width:100%;
		height:30%;
		border: 1px solid;
	}
	.planerContents{
		width: 100%;
		height: 80%;
		/* border: 1px solid; */
		box-sizing: border-box;
		
	}
	.planDetailWrap{
		
		text-align:center;
		width:80%;
		height:100%;
		/* border: 1px solid; */
		box-sizing: border-box;
		float:left;
		
		
	}
	.planDetail{
		display: inline-block;
	}
	.button{
		width:20%;
		height:100%;
		/* border: 1px solid; */
		box-sizing: border-box;
		float:left;
		
		
	}
	.reply{
		width:100%;
		height:10%;
		border: 1px solid;
		box-sizing: border-box;
	
	}
	.r_table{
		width:80%;
		height:100%;
	}
	.table-title{
		font-weight:bold;
	}
	.pList-width{
		width:500px;
	}
	</style>
	<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<body>

	<div class="wrap">
	<div id="head">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/common/sideBar.jsp"></jsp:include>
	</div>
	<div class="right-side">
		<div class=header>
			<div class="info">
			<input type="hidden" name="boardNo" value="${planner.boardNo} "/>	
			<input type="hidden" name="page" value="${page} "/>	
			<span style="color:#b91a4d">[${planner.plannerLocation}]</span>	
			<h2 style="color:#053641;font-weight:bold">${planner.planTitle}</h2>	
			<input type="text" name="firstDay" value="${planner.firstDay} "readonly/>
			-	
			<input type="text" name="lastDay" value="${planner.lastDay} "readonly/>	
			<!-- 여행 제목 날짜 -->
			</div>
			
			<div class="writeInfo">
			<table>
			<tr>
			<td class="table-title"> 작성자 : </td>
			<td name="planWriter">${planner.planWriter }</td>
			<td class="table-title"> 작성일 : </td>
			<td name="pCreateDate">${planner.pCreateDate }</td>
			<td class="table-title"> 조회수 : </td>
			<td name="plannerCount">${planner.plannerCount }</td>
			<!--작성자/등록일자 조회수  -->
			</tr>
			</table>
			</div>
		</div>
		<br>
		<div class="planerContents">
			<div class="planDetailWrap">
			<div class="planDetail">
			<!-- 장소 메모 지도마커표시 보이기 foreach -->
			<table  border="1">
			<div class="card">
			<c:forEach items="${dayList}" var="day" varStatus="status">
				<tr> 
				<td style="border-right: none; font-weight:bold; background-color:#0067a3; color:white">Day${status.count }</td>
				<td colspan="3" align="center"style="border-left: none; font-weight:bold;"class="list-group-item list-group-item-info">
				<fmt:parseDate value="${day}" var="stringday" pattern="yyyyMMdd" /> 
				 <fmt:formatDate value="${stringday}" pattern="YY.MM.dd (E)" />
				 </td>
				 </tr>
		<c:forEach items="${planList }" var="plan" varStatus="i">
		<c:if test="${plan.day eq day}">  
			<tr>
			<td rowspan="2"><span class="badge badge-secondary">${i.count }</span></td>
			<%-- <td>${plan.day }</td> --%>
			<td id="pList-width" class='form-control'>${plan.address }</td>
			<%-- <td>${plan.y }</td>
			<td>${plan.x }</td> --%>
			<td rowspan="2"><div id="map${i.count }" style="width:200px; height:200px;"></div></td></tr>
			<tr>
			
			<td class="pList-width"  style="text-align:left";><textarea cols="60px" rows="5px" readonly >${plan.memo }</textarea></td>
			
			</tr>
			 
			
				</c:if>
			</c:forEach>
		</c:forEach> 
		</div>
		</table>
		</div>
			</div>
			<div class="button">
			<button class="btn btn-outline-success" onclick="location.href='/plan/plan.kh?page=${page}'">리스트</button><br><br>
			<c:if test="${sessionScope.loginUser.memberNickname eq planner.planWriter }">
			<button class="btn btn-outline-info" onclick="plannerModify(${planner.boardNo},${page});">수정</button><br><br>
			<button class="btn btn-outline-danger" onclick="plannerRemove(${page});">삭제</button>
			</c:if>
			<!-- 수정 삭제 pdf  -->
			</div>
		</div>
		
		<div class="r_table">
		<!-- 댓글 -->
		<div class="card mb-2">
		<form action = "/plan/addReply.kh" method="post">
		 <input type ="hidden" name="page" value="${page}"> 
		<input type="hidden"name = "boardNo" value="${planner.boardNo }">
	<div class="card-header bg-light">
	   <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-chat-dots-fill" viewBox="0 0 16 16">
  		<path d="M16 8c0 3.866-3.582 7-8 7a9.06 9.06 0 0 1-2.347-.306c-.584.296-1.925.864-4.181 1.234-.2.032-.352-.176-.273-.362.354-.836.674-1.95.77-2.966C.744 11.37 0 9.76 0 8c0-3.866 3.582-7 8-7s8 3.134 8 7zM5 8a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm4 0a1 1 0 1 0-2 0 1 1 0 0 0 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z"/>
		</svg>    
	    REPLY
	</div>
	<div class="card-body">
		<ul class="list-group list-group-flush">
		    <li class="list-group-item">
			<textarea class="form-control" id="exampleFormControlTextarea1" name="pReplyContents"rows="3"></textarea>
			<input type="submit" class="btn btn-outline-dark" value="등록하기">
		    </li>
		</ul>
	</div>
	</form>
</div>
		
		<div class="card md-2">
			<c:forEach items="${rList }" var="reply"><!-- 갯수만큼 반복됨 -->
			<div class="card-header">
			<p class="card-text">
			<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
			  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
			  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
			</svg>
			${reply.pReplyWriter }</p>
			<p class="card-text"><small class="text-muted">${reply.pRUpdateDate }</small></p>
			</div>
			<div class="card-body">
			<p class="card-text">${reply.pReplyContents }
			<c:if test="${sessionScope.loginUser.memberNickname eq reply.pReplyWriter }">
			<button type="button"class="btn btn-outline-info"onclick="modifyView(this,'${reply.pReplyContents}',${reply.pReplyNo },${reply.boardNo },${page});">수정</button>
			<button type="button" class="btn btn-outline-danger" onclick="removeReply(${reply.boardNo },${page},${reply.pReplyNo });"> 삭제 </button>
			</c:if>
			</p>
			</div>
				</c:forEach>
		</div>

		<!-- 댓글 목록 -->
		</div>
		

		</div>

	</div>


</body>
<script type="text/javascript">
function plannerRemove(page){
	event.preventDefault();//하이퍼링크 이동 방지
	if(confirm("게시물을 삭제하시겠습니까?")){
		location.href="/plan/remove.kh?page="+page;
		
	}
}
function plannerModify(boardNo,page){
	event.preventDefault();//하이퍼링크 이동 방지
	if(confirm("게시물을 수정하시겠습니까?")){
		location.href="/plan/mobify.kh?boardNo="+boardNo+"&page="+page;
		
	}
}
	function removeReply(boardNo, page, replyNo) {
 	 	event.preventDefault();// 하이퍼링크 이동 방지
 	 	if(confirm("댓글을 삭제하시겠습니까?")){
		location.href="/plan/removeReply.kh?boardNo="+boardNo+"&page="+page+"&replyNo="+replyNo;
 	 	
 	 	}
	};
	function modifyView(obj,rContents,replyNo,boardNo, page) {
		event.preventDefault();// 하이퍼링크 이동 방지
			var $tr = $("<tr>");
			$tr.append("<td colspan='3'><input type='text' size='50' value='"+rContents+"' id='modifyInput'></td>");
			$tr.append("<td colspan='3'><button type='button'class='btn btn-outline-info' onclick='modifyReply(this, "+replyNo+", "+boardNo+", "+page+");'> 수정 </button></td>");
			$(obj).parent().parent().after($tr); 
	};
	
	function modifyReply(obj,replyNo,boardNo, page){
		event.preventDefault();// 하이퍼링크 이동 방지
		var inputTag = $(obj).parent().siblings().eq(0).children();
 		var rContents = $("#modifyInput").val();
 		var $form = $("<form>"); // form태그
 		$form.attr("action", "/plan/modifyReply.kh" );
		$form.attr("method", "post"); // form태그에 속성 추가
		$form.append("<input type='hidden' value='"+rContents+"' name='pReplyContents'>");
		$form.append("<input type='hidden' value='"+replyNo+"' name='pReplyNo'>");
		$form.append("<input type='hidden' value='"+boardNo+"' name='boardNo'>");

		$form.append("<input type='hidden' value='"+page+"' name='page'>");

		$form.appendTo("body");
		$form.submit(); // 전송

	}
</script>
<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=55f8ef22507421de3927e33382a4c630&libraries=services,clusterer,drawing"></script>
	<script>
		<c:forEach items='${planList }' var='plan' varStatus='i'>
			var mapContainer = document.getElementById('map${i.count}'), // 지도를 표시할 div 
			    mapOption = { 
			        center: new kakao.maps.LatLng('${plan.y}', '${plan.x}'), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			};
			
			// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
			var map = new kakao.maps.Map(mapContainer, mapOption); 
			
			var markerPosition  = new kakao.maps.LatLng('${plan.y}', '${plan.x}'); 
			
			//마커를 생성합니다
			var marker = new kakao.maps.Marker({
			 position: markerPosition
			});
			
			//마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
	
			
			//아래 코드는 지도 위의 마커를 제거하는 코드입니다
			//marker.setMap(null); 
			</c:forEach>
			

</script>
</html>