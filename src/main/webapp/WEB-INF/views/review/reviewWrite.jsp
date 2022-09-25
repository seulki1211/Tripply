<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 공용css -->
  <link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
<!-- 부트스트랩,jQuery -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<!-- 썸대노트 -->
  <script src="/resources/js/summernote-lite.js"></script>
  <script src="/resources/js/summernote/lang/summernote-ko-KR.js"></script>
  <link rel="stylesheet" href="/resources/css/summernote-lite.css">
</head>
<!-- 스크립트태그-썸머노트설정 -->
<script>
		$(document).ready(function(){
			$('#summernote').summernote({
				height : 300,
				width : 700,
				lang : "ko-KR",
				callbacks:{
					onImageUpload : function(files){
						uploadSummernoteImageFile(files[0],this);
					},
					onPaste: function(e){
						var clipboardData = e.originalEvent.clipboardData;
						if(clipboardData && clipboardData.items && clipboardData.items.length){
							var item = clipboardData.items[0];
							if(item.kind === 'file' && item.type.indexOf('image/') !== -1){
								e.preventDefault();
							}
						}
					},
				}
			});
// 섬머노트에디터 이미지 업로드 시 동작			
			function uploadSummernoteImageFile(file,editor){
				data = new FormData();
				data.append("file",file);
				$.ajax({
					data:data,
					type:"POST",
					url:"/review/uploadSummernoteImageFile",
					dataType:"JSON",
					contentType:false,
					processData:false,
					success:function(data){
						$(editor).summernote("insertImage",data.url);
					}
				});
			}
		});
</script>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
		<div id="sideBar"></div>
		<div id="contents-1">
			<form action="/review/write.kh" method="post">
					<input type="hidden" name="reviewWriter" value="${loginUser.memberId }">
					<select name="rLocationCode">
							<option value="00" label="전국"></option>
							<option value="11" label="서울"></option>
							<option value="21" label="부산"></option>
							<option value="39" label="제주"></option>
							<option value="23" label="인천"></option>
							<option value="25" label="대전"></option>
							<option value="22" label="대구"></option>
							<option value="24" label="광주"></option>
							<option value="26" label="울산"></option>
							<option value="29" label="세종"></option>
							<option value="31" label="경기도"></option>
							<option value="32" label="강원도"></option>
							<option value="33" label="충청북도"></option>
							<option value="34" label="충청남도"></option>
							<option value="37" label="경상북도"></option>
							<option value="38" label="경상남도"></option>
							<option value="35" label="전라북도"></option>
							<option value="36" label="전라남도"></option>
						</select>
						<input type="text" id="inputTitle" name="reviewTitle" placeholder="제목을 입력하세요" required="required"><br>
						<textarea id="summernote" name="reviewContents"></textarea>
<!-- 썸네일 선택-->
						<span onclick="clickThumbnailAppend();" id="test"><button type="button">썸네일 새로고침</button></span>
						<select id="thumbnailPath" name="thumbnailPath">
						</select>
						<button>저장</button>
			</form>
		</div>
		<div id="contents-2"></div>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
</body>

<script>
function clickThumbnailAppend(){
	
	//1.셀렉트 박스 하위 옵션을 먼저 모두 지워준다.
	$("#thumbnailPath").empty();
	
	//2.썸머노트의 컨텐츠 HTML을 가져와서 큰따옴표로 분리한다.
	var contentsHtml = $(".note-editable").html();
	var regExSplit = /[\"]/;
	var contentsHtmlArr = contentsHtml.split(regExSplit);
	
	//3.분리한 요소 중 이미지 저장 경로를 포함하고 있는 것을 이미지의 경로로 판단하고
	//  이를 반복문을 사용하여 셀렉트박스의 옵션값으로 추가한다.
	var index = 1;
	for(var i=0; i<contentsHtmlArr.length; i++){
		if(contentsHtmlArr[i].includes("/resources/image/")){
			$("#thumbnailPath").append("<option value="+contentsHtmlArr[i]+">"+index+"번째 이미지"+"</option>");
			index++;
		}
	}
}
</script>
</html>