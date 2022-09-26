<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
		width:100%;
		height:30%;
		border: 1px solid;
	}
	.planerContents{
		width: 100%;
		height: 80%;
		border: 1px solid;
		box-sizing: border-box;
		
	}
	.planDetailWrap{
		
		text-align:center;
		width:80%;
		height:100%;
		border: 1px solid;
		box-sizing: border-box;
		float:left;
		
		
	}
	.planDetail{
		display: inline-block;
	}
	.button{
		width:20%;
		height:100%;
		border: 1px solid;
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
	crossorigin="anonymous" referrerpolicy="no-referrer">
	
</script>
<body>
	<div class="wrap">
	<div id="head">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
		<div class=header>
			<div class="info">
			<input type="hidden" name="boardNo" value="${planner.boardNo} "/>	
			<input type="hidden" name="page" value="${page} "/>	
			<span>[${planner.plannerLocation}]</span>	
			<h2>${planner.planTitle}</h2>	
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
		<div class="planerContents">
			<div class="planDetailWrap">
			<div class="planDetail">
			<!-- 장소 메모 지도마커표시 보이기 foreach -->
			<table  border="1">
			<c:forEach items="${dayList}" var="day" varStatus="status">
				<tr> 
				<td style="border-right: none; font-weight:bold;">Day${status.count }</td>
				<td colspan="3" align="center"style="border-left: none; font-weight:bold;">
				<fmt:parseDate value="${day}" var="stringday" pattern="yyyyMMdd" /> 
				 <fmt:formatDate value="${stringday}" pattern="YY.MM.dd (E)" />
				 </td>
				 </tr>
		<c:forEach items="${planList }" var="plan" varStatus="i">
		<c:if test="${plan.day eq day}">  
			<tr>
			<td rowspan="2">${i.count }</td>
			<%-- <td>${plan.day }</td> --%>
			<td class="pList-width">${plan.address }</td>
			<%-- <td>${plan.y }</td>
			<td>${plan.x }</td> --%>
			<td rowspan="2"><div id="map${i.count }" style="width:200px; height:200px;"></div></td></tr>
			<tr>
			
			<td class="pList-width"  style="text-align:left";><textarea cols="60px" rows="5px" readonly >${plan.memo }</textarea></td>
			
			</tr>
			 
			
				</c:if>
			</c:forEach>
		</c:forEach> 
		</table>
		</div>
			</div>
			<div class="button">
			<button onclick="plannerModify(${planner.boardNo},${page});">수정</button>
			<button onclick="plannerRemove(${page});">삭제</button>
			<button onclick="location.href='/plan/pdf.kh?boardNo=${planner.boardNo }'">PDF</button>
			<!-- 수정 삭제 pdf  -->
			</div>
		</div>
		<div class="reply">
		<form action = "/plan/addReply.kh" method="post">
		 <input type ="hidden" name="page" value="${page}"> 
		<input type="hidden"name = "boardNo" value="${planner.boardNo }">
			<table class="r_table" align="center" width="500" border="1">
			<tr><td colspan="2">댓글</td></tr>
			<tr>
				<td>
					<textarea row="100%" cols="100%" name="pReplyContents"></textarea>
				</td>
				<td>
					<input type="submit" value="등록하기">
				</td>
				
			</tr>
			</table>
		</form> 
		<!-- 댓글 -->
			<table class="r_table" align="center"width="500"border="1">
		<c:forEach items="${rList }" var="reply"><!-- 갯수만큼 반복됨 -->
			<tr>
				<td>${reply.pReplyWriter }</td>
				<td>${reply.pReplyContents }</td>
				<td>${reply.pRUpdateDate }</td>
				<td>
				<button type="button"onclick="modifyView(this,'${reply.pReplyContents}',${reply.pReplyNo },${reply.boardNo },${page});">수정</button>
				<button type="button" onclick="removeReply(${reply.boardNo },${page},${reply.pReplyNo });"> 삭제 </button></td>
			</tr>
			
			</c:forEach>
			</table> 
		
		
		<!-- 댓글 목록 -->
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
			$tr.append("<td colspan='3'><button type='button' onclick='modifyReply(this, "+replyNo+", "+boardNo+", "+page+");'> 수정 </button></td>");
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