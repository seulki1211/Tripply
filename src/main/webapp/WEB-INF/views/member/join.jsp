<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/WEB-INF/resources/css/common-style.css">
</head>
<body>
	<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
	<h1 align="center">회원가입</h1>
	<div class="">
		<form action="/member/register.kh" method="post" name="join_form">
			<table align="center">
				<tr>
					<td> 아이디 *</td>
					<td>
						<input type="text" id="memberId" name="memberId">
						<!-- <button type="button" onclick="id_check();">중복확인</button> -->
					</td>
				</tr>
				<tr>
					<td> 비밀번호 *</td>
					<td>
						<input type="password" id="memberPwd" name="memberPwd">
					</td>
				</tr>
				<tr>
					<td> 비밀번호확인 *</td>
					<td>
						<input type="password" id="memberRePwd" name="memberRePwd">
					</td>
				</tr>
				<tr>
					<td> 이름 *</td>
					<td>
						<input type="text" id="memberName" name="memberName">
					</td>
				</tr>				
				<tr>
					<td> 닉네임 *</td>
					<td>
						<input type="text" id="memberNickname" name="memberNickname">
					</td>
				</tr>
				<tr>
					<td> 이메일 *</td>
					<td>
						<input type="text" id="memberEmail" name="memberEmail">
					</td>
				</tr>
				<tr>
					<td> 생년월일</td>
					<td>
						<input type="text" name="memberBirth">
					</td>
				</tr>
				<tr>
					<td> 성별</td>
					<td>
						<input type="radio" name="memberGender" value="남자">남자
						<input type="radio" name="memberGender" value="여자">여자
					</td>
				</tr>	
				<tr>
					<td> 전화번호</td>
					<td>
						<input type="text" id="memberPhone" name="memberPhone">
					</td>
				</tr>
				<tr>
					<td> 우편번호</td>
					<td>
						<input type="text" name="post" class="postcodify_postcode5">
						<button type="button" id="postcodify_search_button">검색</button>
					</td>
				</tr>
				<tr>
					<td> 도로명 주소</td>
					<td>
						<input type="text" name="address1" class="postcodify_address">
					</td>
				</tr>
				<tr>
					<td> 상세 주소</td>
					<td>
						<input type="text" name="address2" class="postcodify_details">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="joinform_check()">가입하기</button> <!--onclick="formcheck(this.form)">-->
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script>
		$("#postcodify_search_button").postcodifyPopUp();
		
		// joinform_check 함수로 유효성 검사
		function joinform_check() {
/* 
			var memberId = document.getElementById("memberId");
			var memberPwd = document.getElementById("memberPwd");
			var memberPwd = document.getElementById("memberPwd");
			var memberName = document.getElementById("memberName");
			var memberNickname = document.getElementById("memberNickname");
			var memberEmail = document.getElementById("memberEmail"); */
			
			if (memberId.value == "") { // 해당 입력값이 없을 경우 같은말: if(!uid.value)
				alert("아이디를 입력하세요.");
				memberId.focus(); // focus(): 커서가 깜빡이는 현상, blur(): 커서가 사라지는 현상
				return false; //return: 반환하다 return false:  아무것도 반환하지 말아라 아래 코드부터 아무것도 진행하지 말것
			};

			if (memberPwd.value == "") {
				alert("비밀번호를 입력하세요.");
				memberPwd.focus();
				return false;
			};

			// 비밀번호 영문자+숫자+특수조합(8~25자리 입력) 정규식
			var pwdCheck = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/;
			
			if (!pwdCheck.test(memberPwd.value)) {
				alert("비밀번호는 영문자+숫자+특수문자 조합으로 8~25자리 사용해야 합니다.");
				pwd.focus();
				return false;
			};
			
			if (memberRePwd.value !== memberPwd.value) {
				alert("비밀번호가 일치하지 않습니다.");
				memberRePwd.focus();
				return false;
			};

			if (memberName.value == "") {
				alert("이름을 입력하세요.");
				memberName.focus();
				return false;
			};
  
			if (memberNickname.value == "") {
				alert("닉네임을 입력하세요.");
				memberNickname.focus();
				return false;
			};
			
			if (memberEmail.value == "") {
				alert("이메일을 입력하세요.");
				memberEmail.focus();
				return false;
			};

			var reg = /^[0-9]+/g; // 숫자만 입력하는 정규식

		/*	if (!reg.test(memberPhone.value)) {
			    alert("전화번호는 숫자만 입력할 수 있습니다.");
			    memberPhone.focus();
			    return false;
			}; */
		// 입력 값 전송
		document.join_form.submit(); //유효성 검사의 포인트   
		}

		/* // 아이디 중복체크 팝업창
		function id_check() {
			// window.open("팝업될 문서 경로", "팝업될 문서 이름", "옵션");
			window.open("", "b", "width=600, height=200, left=200, top=100");
		} */
	</script>
</body>
</html>