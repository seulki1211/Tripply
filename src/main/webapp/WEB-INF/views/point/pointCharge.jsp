<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트리플리,Tripply</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/resources/css/common-style.css">
</head>
<body>
<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
<!-- 컨텐츠 -->
	<div id="contents">
<!-- 사이드바	 -->
		<div id="sideBar"  style="float:left">
			<div class="my-side">
				<div class="my-side-bar" onclick="location.href='#';">회원정보수정</div>
				<div class="my-side-bar" onclick="location.href='/free/list.kh';">작성글</div>
				<div class="my-side-bar">북마크</div>
				<div class="my-side-bar" onclick="pointNavi(this);">포인트관리</div>
<!-- 사이드바 아코디언			 -->
				<div id="point-navi">
					<ul>
						<li onclick="location.href='/point/chargeView.kh';">포인트 충전</li>
						<li onclick="location.href='/point/historyView.kh';">포인트 내역확인</li>
						<li onclick="location.href='/point/send.kh';">포인트 전송</li>
					</ul>
				</div>
			</div>
		</div>
<!-- 본 컨텐츠 -->
		<div id="pointCharge-input"  style="float:left">
			<div id="pointCharge-title">
			</div>
			<form id="pointCharge-form" action="/point/charge.kh" method="post">
				<input type="hidden" name="pointWorkType" value="C">
				<input type="hidden" name="pointToUser" value="${loginUser.memberId }">
			
			
				<div class="pointCharge method ">
					<br>
					<h2>충전 수단 선택</h2>
					<labal  onclick="choiceBank();"> 은행 <input type="radio" name="chargeMethod" value="bank"></labal>
					<label  onclick="choiceCard();"> 카드 <input type="radio" name="chargeMethod" value="card"></label>
					<label  onclick="choiceDeposit();"> 계좌 입금 <input type="radio" name="chargeMethod" value="deposit"></label>
					
					<div id="bank-info" style="display:none">
						은행 선택:
						<select id="bank-choice" name="bankChoice">
							<option value="kb" >국민</option>
							<option value="nh" >농협</option>
							<option value="sh" >신한</option>
							<option value="kaka" >카카오</option>
						</select><br>
						<input id="bank-accountNo" name="bankAccNo" type="text" placeholder=" '-'없이 계좌번호를 입력해주세요."  style="width:300px">
					</div>
				
					<div id="card-info" style="display:none">
						카드사 선택:
						<select id="card-choice" name="cardChoice">
							<option value="kb">KB</option>
							<option value="ss">삼성</option>
							<option value="hd">현대</option>
							<option value="kaka">카카오</option>
						</select><br>
						<input id="card-no" name="cardNo" type="text" placeholder=" '-'없이 카드번호를 입력해주세요."  style="width:300px">
					</div>
					<div id="card-info"></div>
					
					<hr><br>
				</div>
				<div class="pointCharge amount">
					<h2>충전 금액 입력</h2>
					<select name="pointAmount">
						<option value="1000">1,000원</option>
						<option value="5000">5,000원</option>
						<option value="10000">10,000원</option>
						<option value="20000">20,000원</option>
						<option value="30000">30,000원</option>
						<option value="40000">40,000원</option>
						<option value="50000">50,000원</option>
					</select>
					<hr><br>
				</div>
				<div class="pointCharge btn">
					<button>충전하기</button>
				</div>
			</form>
			
		</div>
	</div>
<!-- 푸터 -->
	<div id="footer"></div>
</body>
<script>

//사이드 바 포인트관리 아코디언
	function pointNavi(target){
		var pointNaviDiv = target.nextElementSibling;
		var display = pointNaviDiv.style.display;
		if(display == 'none'){
			pointNaviDiv.style.display="block";
		}else{
			pointNaviDiv.style.display="none";
		}
	}
	
//충전수단 선택 관련 함수

	
	function choiceBank(){
		//1.은행과 카드 입력 요소를 변수에 저장한다.
		var bankInfo = document.querySelector("#bank-info");
		var bankDisplay = bankInfo.style.display;
		var cardInfo = document.querySelector("#card-info");
		var cardDisplay = cardInfo.style.display;
	
		
		//3.은행 입력 요소를 토글한다.
		if(bankDisplay == 'none'){
			bankInfo.style.display = "block";
		}else{
			bankInfo.style.disiplay = "none";
		}
}

	function choiceDeposit(){}

</script>
</html>