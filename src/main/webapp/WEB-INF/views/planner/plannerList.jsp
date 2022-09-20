<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.wrap{
position:relative;
} 
.p_table{
 position: absolute;
  margin-left:auto; 
  margin-right:auto;
    z-index: 1;
}
.planL-popupcontainer {
    position: absolute;
    z-index: 2;
 
    top: 20px;
  	left: 20px;
    width: 100;
    height: 100;
    display: none;
    background: rgba(0, 0, 0, 0.7);
}

.planL-popbox {
    width: 700px;
    height: 450px;
    position: relative;
    z-index: 2;
    background: white;
    box-shadow: 3px 3px 5px black;
    margin: 150px auto;
    cursor: pointer;
    text-align: center;
}

.planL-popheadbox {
    width: 693px;
    height: 80px;
    border: 4px solid #abb2c7;
    position: relative;
}

.planL-popheadbox__span--big {
    position: absolute;
    top: 15px;
    left: 20px;
    font-size: 40px;
    font-weight: bold;
    font-family: 'Jua', sans-serif;
}

.planL-popheadbox__button--big {
    position: absolute;
    top: 20px;
    right: 20px;
    width: 120px;
    height: 45px;
    background-color: #5882fa;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 20px;
    font-family: 'Jua', sans-serif;
    cursor: pointer;
}

.planL-popcontentbox {
    height: 360px;
    width: 693px;
    border-left: 4px solid #abb2c7;
    border-right: 4px solid #abb2c7;
    border-bottom: 4px solid #abb2c7;
}

.planL-popdetailbox {
    width: 100%;
    height: 80px;
    position: relative;
}

.planL-popdetailbox__span--big {
    width: 100px;
    height: 100px;
    font-size: 23px;

    position: absolute;
    top: 40px;
    left: 60px;
    font-family: 'Jua', sans-serif;
}

.planL-popdetailbox__span--small {
    position: absolute;
    top: 44px;
    right: 275px;
    font-weight: bolder;
}

.planL-popdetailbox__input--gray {
    position: absolute;
    top: 36px;
    right: 100px;
    width: 350px;
    height: 30px;
    border: 2.5px solid #dadce0;
    font-size: 16px;
}

.planL-popdetailbox__input--date {
    width: 150px;
    height: 35px;
    margin-left: 87px;
    margin-right: -47px;
    margin-top: 34px;
    border: 2.5px solid #dadce0;
    font-size: 14px;
}

.planL-popbtnbox__input--blue {
    width: 200px;
    height: 50px;
    background-color: #1b3067;
    color: white;
    border: none;
    border-radius: 7px;
    font-size: 20px;
    font-family: 'Jua', sans-serif;
    margin: 30px auto;
    cursor: pointer;
}


</style>
</head>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer">
	</script>
<script>
        $(document).ready(function () {
            $("#btn").click(function () {
                $("#popup").fadeIn();
            });
            $("#popdown").click(function () {
                $("#popup").fadeOut();
            });
        });
   </script>
<body>
<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
</div>
<div class="wrap">
 <h1 align="center">게시글 목록</h1>
	<br><br>
	<table class="p_table" border="1">
				<tr>
			<td colspan="5"align="center">
			<form action="/plan/search.kh" method="get">
			
				<select name = "searchCondition" >
					<option value="all" <c:if test="${searchCondition eq 'all' }">selected</c:if>>전체</option>
					<option value="writer" <c:if test="${searchCondition eq 'writer' }">selected</c:if>>작성자</option>
					<option value="title"<c:if test="${searchCondition eq 'title'}">selected</c:if>>제목</option>
				</select>
				<input type="text" name="searchValue" value="${searchValue }">
				<input type="submit" value="검색">
			</form>
				</td> 
				<td align="center">
					<div>
 <button class="planL-titlebox--button__blue" id="btn">플래너 작성</button>
</div>
<div class="planL-popupcontainer" id="popup">

    <div class="planL-popbox">

        <!-- 플래너 팝업창 header 부분 -->
        <div class="planL-popheadbox">
            <span class="planL-popheadbox__span--big">플래너 만들기</span>
            <button class="planL-popheadbox__button--big" id="popdown">닫기</button>
        </div>
        <!-- // 플래너 팝업창 header 부분 -->

        <!-- 플래너 팝업창 입력 부분 -->
        <div class="planL-popcontentbox">
            <form action="/plan/regist.kh" method="post"> <!-- name="popupFrm" onsubmit="return popupCheck()" -->
			<%-- <input type="hidden" name=boardNo value="${planner.boardNo}"> --%>
			<%-- <input type="text" name=memberId value="${planner.memberId}"> --%>
                <div class="planL-popdetailbox">
                    <span class="planL-popdetailbox__span--big">여행제목</span>
                    <input type="text" name="planTitle" placeholder="20자 내로 입력해주세요" maxlength="20" class="planL-popdetailbox__input--gray" required>
                </div>

                <div class="planL-popdetailbox">
                    <span class="planL-popdetailbox__span--big">여행기간</span>
                    <input type="date" name="firstDay" class="planL-popdetailbox__input--date" required>
                    <span class="planL-popdetailbox__span--small">~</span>
                    <input type="date" name="lastDay" class="planL-popdetailbox__input--date" required>
                </div>

               <!--  <div class="planL-popdetailbox">
                    <span class="planL-popdetailbox__span--big">설명</span>
                    <input type="text" name="intro" placeholder="30자 내로 입력해주세요" maxlength="30" class="planL-popdetailbox__input--gray" value="">
                </div> -->

                <div class="planL-popbtnbox">
                    <input type="submit" class="planL-popbtnbox__input--blue" value="플래너 만들기">
                </div>

            </form>
        </div>
        <!-- // 플래너 팝업창 입력 부분 -->

    </div>
</div>
		 <tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>여행날짜</td>
			<td>조회수</td>
		</tr>
		<c:if test="${!empty pList }">
		<c:forEach items ="${pList }" var="planner" varStatus = "i">
		<!--var는 여기서 사용하는 변수명을 적음  -->
		<tr>
				<td>${i.count }</td>
				<td><%-- <a href="/board/detail.kh?boardNo=${planner.boardNo }&page=${currentPage}#"> --%>${planner.planTitle }</td>
				<td>${planner.planWriter }</td>
				<td>${planner.firstDay}-${planner.lastDay }</td>
				<td>${planner.plannerCount }</td>
			</tr>
		
		</c:forEach>
		<tr align="center" height="20">
			<td colspan="6">
				<c:if test="${currentPage != 1}">
					<a href="/plan/${urlVal }.kh?page=${currentPage - 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[이전]</a>
				</c:if>
				<c:forEach var="p" begin="${startNavi }" end="${endNavi }">
					<c:if test="${currentPage eq p }">
					<b>${p }</b>						
					</c:if>
					<c:if test="${currentPage ne p }">						
					<a href="/plan/${urlVal }.kh?page=${p }&searchCondition=${searchCondition}&searchValue=${searchValue}">${p }</a>
					</c:if>
				</c:forEach>
				<c:if test="${maxPage > currentPage }">
				<a href="/plan/${urlVal }.kh?page=${currentPage + 1 }&searchCondition=${searchCondition}&searchValue=${searchValue}">[다음]</a>
				</c:if>
			</td>
		</tr>
		</c:if>
		<c:if test="${empty pList }">
			<tr>
			<td colspan="6" aligin="center">데이터가 존재하지 않습니다</td>
			</tr>
		</c:if>
		
	
 
	</table>
	</div>
		


</body>
</html>