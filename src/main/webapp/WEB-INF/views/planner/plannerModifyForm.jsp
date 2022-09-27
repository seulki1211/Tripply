<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<title>수정페이지</title>
<link href="../../resources/css/map.css" rel="stylesheet">
</head>
<style>
.header {
	width: 2000px;
	height: 100px;
	border: 1px solid;
	box-sizing: border-box;
}

.readinfo {
	text-align:center;
	width: 30%;
	height: 100%;
	float: left;
	box-sizing: border-box;
}
.dayInfo{
	margin-top:30px;
	width: 30%;
	height: 50%;
	float: left;
	box-sizing: border-box;
}
.resultButton {
	margin-top:30px;
	width: 30%;
	height: 50%;
	float: left;
	float: left;
	box-sizing: border-box;
}

.plan-container {
	width: 2000px;
	height: 800px;
	border: 1px solid;
}

.day-area {
	width: 10%;
	height: 100%;
	float: left;
	border: 1px solid;
	box-sizing: border-box;
}

.plan-area {
	width: 20%;
	height:100%;
	float: left;
	border: 1px solid;
	box-sizing: border-box;
}

.map_wrap {
	width: 70%;
	height:100%;
	float: left;
	border: 1px solid;
	box-sizing: border-box;
}
#plan{
border:1px solid;
}
.planNum{
border:1px solid;
}
</style>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer">
	
</script>
<body>
<div id="head">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
</div>
<div class="wrap">
	
	<div class="header">
		<div class="readinfo">
			<input type="hidden" name="page" id="page"value="${page }" />
			<input type="hidden" name="boardNo" id="boardNo"value="${planner.boardNo }" />
			<%-- <input type="text" name="title" id="planTitle"value="${planner.planTitle }" />
			<input type="text" name="firstDay" id="firstDay"value="<fmt:formatDate value="${planner.firstDay}" pattern="yyyy-MM-dd" />" />
			<input type="text" name="lastDate" id="lastDay"value="<fmt:formatDate value="${planner.lastDay}" pattern="yyyy-MM-dd" />" />  --%>
		
		<span>[${planner.plannerLocation}]</span>	
			<h2>${planner.planTitle}</h2>
		</div>
		<div class="dayInfo">
			<input type="text" name="firstDay" value="${planner.firstDay} "readonly/>
			-	
			<input type="text" name="lastDay" value="${planner.lastDay} "readonly/>	
		</div>	
		<!-- 타이틀 시작 날짜 끝날짜 보더 넘버->히든 -->
		
		<div class="resultButton">
			<button  form="submitForm" type="submit" class="btn btn-outline-info" >저장</button>
			<button class="btn btn-outline-danger" onclick="plannerRemove(${page})">닫기</button>
		</div>
		
	</div>

	<div class="plan-container">
		<div class="day-area">
			<div class="planI-daysboxtitle"><span class="list-group-item list-group-item-dark">일정</span></div>
			
			
			<c:forEach items="${dayList}" var="day" varStatus="status">
			<div class="planI-daybox" onclick="plansChange(${status.count})">
				<span class="list-group-item list-group-item-light">
				<span class="planI-day">DAY${status.count}</span> <span class="planI-date">
				 <fmt:parseDate value="${day}" var="stringday" pattern="yyyyMMdd" /> 
				 <fmt:formatDate value="${stringday}" pattern="MM.dd (E)" /></span></span>
			</div>
		</c:forEach> 
		
		</div>
		<form action="/plan/modifyplan.kh" method="post" id="submitForm">
		<input type="text" name="boardNo" value="${planner.boardNo }">
		<div class="plan-area">
			<c:forEach items="${dayList }" var="day" varStatus="status">
			<div class="planI-plansbox" data-date="${day}">
				<div class="planI-plansboxtitle">
				<span class="list-group-item list-group-item-light">
					DAY${status.count} |
					<fmt:parseDate value="${day}" var="stringday" pattern="yyyyMMdd" />
					<fmt:formatDate value="${stringday}" pattern="MM.dd E요일" />
					</span>
				</div>
	<!-- <table border="1"> -->
	<c:forEach items="${planList }" var="plan" varStatus="i">
 	<c:if test= "${plan.day eq day}">
 	
	<div id="plan" class="planNum${i.count }"><span class="badge badge-secondary">${i.count }</span>
	<input type="hidden" name="planList[${i.count-1}].boardNo" id="boardNo" value="${plan.boardNo}"/>		
	<input type="hidden" name="planList[${i.count-1}].day" id="planDate" value="${plan.day}"/>		
	<input type="hidden" name="planList[${i.count-1}].Y" id="planY" value="${plan.y}"/>		
	<input type="hidden" name="planList[${i.count-1}].X" id="planX" value="${plan.x}"/>		 
	<input type="text"  name="planList[${i.count-1}].address" id="planTitle" class="form-control"value="${plan.address}"readonly/>		
	<div>Memo</div>	
	<textarea cols="40" rows="3" name="planList[${i.count-1}].Memo" id="planMemo">${plan.memo} </textarea>
	<button class="planDetailButton" onclick="planDelete(${i.count},${i.count-1},${plan.boardNo},${plan.day},${plan.y},${plan.x})">&times;</button></div>
			
			<%--  <tr>
			<td>${i.count }</td>
			<td>${plan.day }</td>
			<td>${plan.address }</td>
			<td>${plan.y }</td>
			<td>${plan.x }</td>
			<td>${plan.memo }</td>
			<td><button class="planDetailButton" onclick="planDelete("${i.count }")">&times;</button></td>
			</tr>  --%>
			
			</c:if> 
			</c:forEach>
			<!-- </table> -->
   	<!-- <input type='hidden' name='planList["+i+"].boardNo' id='boardNo'value="+boardNo +" />"
   	<input type='hidden' name='planList["+i+"].day' id='planDate'value="+data_date +" />"
   	<input type='hidden' name='planList["+i+"].Y' id='planY'value="+place_y+" />"
   	<input type='hidden' name='planList["+i+"].X' id='planX'value="+place_x +" />"
   	<input type='text' name='planList["+i+"].address' id='planTitle'value="+place_name+" /><br>"
   	<input type='text' name='planList["+i+"].Memo' id='planMemo'placeholder='20자 이내로 입력하세요'maxlength='20' />"
    <button class=\"planDetailButton\" onclick=\"planDelete(\'" + num  +"\')\">&times;</button></div> </div>"; -->
			</div>
		</c:forEach>
		
		</div>
		</form>

		<div class="map_wrap">
			<div id="map" style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>

		<div id="menu_wrap" class="bg_white">
			<div class="option">
				<div>
					<form onsubmit="searchPlaces(); return false;">
						키워드 : <input type="text" value="이태원 맛집" id="keyword" size="15">
						<button type="submit">검색하기</button>
					</form>
					
				</div>
			</div>
			<hr>
			<ul id="placesList"></ul>
			<div id="pagination"></div>
		</div>
		

		</div>
	</div>
	</div>
	</body>
<script type="text/javascript">
function plannerRemove(page){
	event.preventDefault();//하이퍼링크 이동 방지
	if(confirm("게시물작성을 취소하시겠습니까?")){
		location.href="/plan/infoRremove.kh";
		
	}
}
/* function plannerSave(){
	event.preventDefault();//하이퍼링크 이동 방지
	if(confirm("게시물을 저장하겠습니까?")){
		planList.removeAll(Arrays.asList("", null));
		location.href="/plan/registplan.kh";
	}
	
} */
</script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=55f8ef22507421de3927e33382a4c630&libraries=services,clusterer,drawing"></script>
	<script>
	
var markers = [];
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places();  
	
	// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({zIndex:1});
	
	// 키워드로 장소를 검색합니다
	searchPlaces();
	
	// 키워드 검색을 요청하는 함수입니다
	function searchPlaces() {
	
	    var keyword = document.getElementById('keyword').value;
	
	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('키워드를 입력해주세요!');
	        return false;
	    }
	
	    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	    ps.keywordSearch( keyword, placesSearchCB); 
	}
	
	// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {
	
	        // 정상적으로 검색이 완료됐으면
	        // 검색 목록과 마커를 표출합니다
	        displayPlaces(data);
	
	        // 페이지 번호를 표출합니다
	        displayPagination(pagination);
	
	    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
	
	        alert('검색 결과가 존재하지 않습니다.');
	        return;
	
	    } else if (status === kakao.maps.services.Status.ERROR) {
	
	        alert('검색 결과 중 오류가 발생했습니다.');
	        return;
	
	    }
	}
	
	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {
	
	    var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
	    
	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	    removeAllChildNods(listEl);
	
	    // 지도에 표시되고 있는 마커를 제거합니다
	    removeMarker();
	    
	    for ( var i=0; i<places.length; i++ ) {
	
	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
	            marker = addMarker(placePosition, i), 
	            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
	
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);
	
	        // 마커와 검색결과 항목에 mouseover 했을때
	        // 해당 장소에 인포윈도우에 장소명을 표시합니다
	        // mouseout 했을 때는 인포윈도우를 닫습니다
	        (function(marker, title,placePosition) {
	            kakao.maps.event.addListener(marker, 'click', function() {
	            	var Lat=marker.getPosition().getLat();
	            	var Lng=marker.getPosition().getLng(); 
	                displayInfowindow(marker, title,Lat,Lng);
	                
	               
	            });
	            
	
	            kakao.maps.event.addListener(marker, 'mouseout', function() {
	                infowindow.close();
	            });
	
/* 	            itemEl.onmouseover =  function () {
	            	var Lat=marker.getPosition().getLat();
	            	var Lng=marker.getPosition().getLng(); 
	                displayInfowindow(marker, title,Lat,Lng);
	            };
	
	            itemEl.onmouseout =  function () {
	                infowindow.close();
	            }; */
	            
	         
	        })(marker, places[i].place_name);
	
	        fragment.appendChild(itemEl);
	    }
	
	    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
	    listEl.appendChild(fragment);
	    menuEl.scrollTop = 0;
	
	    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	    map.setBounds(bounds);
	}
	
	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {
	
	    var el = document.createElement('li'),
	    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
	                '<div class="info">' +
	                '   <h5>' + places.place_name + '</h5>';
	
	    if (places.road_address_name) {
	        itemStr += '    <span>' + places.road_address_name + '</span>' +
	                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
	    } else {
	        itemStr += '    <span>' +  places.address_name  + '</span>'; 
	    }
	                 
	      itemStr += '  <span class="tel">' + places.phone  + '</span>'
	               ;           
	
	   	itemStr += '<div class="placelist-div"><button class="placelist-div__button" onclick="planInsert(\'' + places.place_name + '\',\'' + places.y + '\',\'' + places.x +  '\')">+</button></div>'+'</div>';
	    el.innerHTML = itemStr;
	    el.className = 'item';
	
	    return el;
	}
	
	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
	    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
	        imgOptions =  {
	            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
	            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	        },
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });
	
	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    markers.push(marker);  // 배열에 생성된 마커를 추가합니다
	
	    return marker;
	}
	
	// 지도 위에 표시되고 있는 마커를 모두 제거합니다
	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	        markers[i].setMap(null);
	    }   
	    markers = [];
	}
	
	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {
	    var paginationEl = document.getElementById('pagination'),
	        fragment = document.createDocumentFragment(),
	        i; 
	
	    // 기존에 추가된 페이지번호를 삭제합니다
	    while (paginationEl.hasChildNodes()) {
	        paginationEl.removeChild (paginationEl.lastChild);
	    }
	
	    for (i=1; i<=pagination.last; i++) {
	        var el = document.createElement('a');
	        el.href = "#";
	        el.innerHTML = i;
	
	        if (i===pagination.current) {
	            el.className = 'on';
	        } else {
	            el.onclick = (function(i) {
	                return function() {
	                    pagination.gotoPage(i);
	                }
	            })(i);
	        }
	
	        fragment.appendChild(el);
	    }
	    paginationEl.appendChild(fragment);
	}
	
	// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
	// 인포윈도우에 장소명을 표시합니다
	function displayInfowindow(marker,title,Lat,Lng) {
	    var content = '<div style="padding:5px;z-index:1;">' + title+'위도:'+Lat+'경도:' +Lng+ '</div>';
	 /*    document.getElementById('title').innerHTML=title; */
	    $("#title").val(title);
	    $("#Lat").val(Lat);
	    $("#Lng").val(Lng);
	    infowindow.setContent(content);
	    infowindow.open(map, marker);
	    console.log(content)
	    alert(content)
	}
	
	 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {   
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}
	var planslide =  document.querySelectorAll(".planI-plansbox");
    

    var plans_current = 1;
    // 왜냐면 index로 적용해야 하는데 지금은 시험삼아 1번부터 시작하기에

    function plansChange(n){
        n -= 1;
        for(var i=0;i<planslide.length;i++){
            planslide[i].style.display = "none";
        }
        planslide[n].style.display = "block";
    //console.log(planslide[n])
    }
    plansChange(plans_current);
	 
	
    function planInsert(place_name,place_y,place_x){
    	
        var parentss =  $('.planI-plansbox');
        var parent =  $('.planI-plansbox[style*="display: block"]');
        var data_date = parent.attr('data-date');
        var boardNo=$('#boardNo').attr('value');
        var num = parent.after().length; // 하위 엘리먼트기에 일정 - 제목 (DAY) 부분도 포함됨
        var i = parentss.children().length-$('.planI-plansboxtitle').length;
        if(num<10){ // 일정은 9개까지만 추가 가능
           parent.append(getHtml(place_name,place_y,place_x,num, data_date,i,boardNo));
       }else{
           alert("일정은 최대 9개로 제한됩니다.");
       }
   }

 /*   function getHtml(place_name,place_y,place_x,num, data_date){
       var div = "<div class=\"planDataBox\" data-date=\"" + data_date + "\" data-y=\"" + place_y + "\" data-x=\"" + place_x + "\" data-planNo=\"\">";
       div += "<div class=\"planNum\""+num+">";
       div += " <div class=\"planDetail\">";
       div += " <span class=\"planPlace\" title=\"" + place_name + "\">" + place_name + "</span>";
       div += "<span class=\"planNum-memo\">메모</span></div>";
       div += "<input type=\"text\" name=\"memo\" class=\"planMemo\" placeholder=\"20자 내로 메모를 입력해주세요.\"  maxlength=\"20\">";
       div += "<button class=\"planDetailButton\" onclick=\"planDelete(\'" + num +  "\')\">&times;</button></div> </div>";

       return div;
   } */
   function getHtml(place_name,place_y,place_x,num, data_date,i,boardNo){
	
	var div="<div class=\"planNum\""+num+"><span class='badge badge-secondary'>"+num+"</span>";
   	div +="<input type='hidden' name='planList["+i+"].boardNo' id='boardNo'value="+boardNo +" />"
   	div +="<input type='hidden' name='planList["+i+"].day' id='planDate'value="+data_date +" />"
   	div +="<input type='hidden' name='planList["+i+"].Y' id='planY'value="+place_y+" />"
   	div +="<input type='hidden' name='planList["+i+"].X' id='planX'value="+place_x +" />"
   	div +="<input class='form-control' type='text' name='planList["+i+"].address' id='planTitle'value="+place_name+" /><br>"
   	div +="<div>Memo</div>"
   	div +="<textarea cols='40'rows='3' name='planList["+i+"].Memo' id='planTitle' > </textarea>"
    div += "<button class=\"planDetailButton\" onclick=\"planDelete(\'" + num  +"\','"+i+"','"+boardNo+"','"+data_date+"','"+place_y+"','"+place_x+"','"+place_name+"')\">&times;</button></div>";
   
    return div;
   }
  
   
   function planDelete(num,i,boardNo,data_date,place_y,place_x){
	   event.preventDefault();
	   var parent =  $('.planI-plansbox[style*="display: block"]');
       var kid = parent.children().eq(num); // 일정 부분에 제목도 자식에 포함되기에 index +1
       var next_kids = parent.nextAll();

       kid.detach();
       parent.append(getdelHtml(num,i,boardNo,data_date,place_y,place_x));
        next_kids.each(function (index, element){
      /*      var url = "_image/plan/num/number" + num + ".png";
           $(this).find('img').attr("src", url); */

           var btn = "planDelete(" + num + ")";
           $(this).find('button').attr("onclick", btn);
           ++ num;
       }); 
   }
   function getdelHtml(num,i,boardNo,data_date,place_y,place_x){
		  var div="<div class=\"planNum\""+i+"><span class='badge badge-secondary'>"+i+"</span>";
		div +="<input type='text' name='planList["+i+"].boardNo' id='boardNo'value='"+boardNo +"'/>"
	   	div +="<input type='hidden' name='planList["+i+"].day' id='planDate'value='"+data_date +"' />"
	    div +="<input type='hidden' name='planList["+i+"].Y' id='planY'value='"+place_y+"'/>"
	  	div +="<input type='hidden' name='planList["+i+"].X' id='planX' value='"+place_x+"'/>"
	    return div;
	   }

	 
	 </script>	


</html>