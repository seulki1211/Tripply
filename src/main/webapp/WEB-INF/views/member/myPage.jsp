<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<!-- 화면 뼈대 설정용 css -->
<link rel="stylesheet" href="/resources/css/common-style.css">
</head>
<body>
	<!-- 헤더-메뉴바 -->
	<div id="header">
		<jsp:include page="/WEB-INF/views/common/menuBar.jsp"></jsp:include>
	</div>
	<div class="wrapper-form">
		<div class="my-side">
			<div class="my-side-bar" onclick="location.href='#';">회원정보수정</div>
			<div class="my-side-bar" onclick="location.href='/free/list.kh';">작성글</div>
			<div class="my-side-bar">북마크</div>
			<div class="my-side-bar" onclick="location.href='/point/chargeView.kh';">포인트관리</div>
		</div>
		<div class="profile-form">
	<h1 align="center">회원정보수정</h1>
			<div class="wrapper">
				<div class="profile-box">
					<img class="profile" alt="본문이미지" src="/resources/buploadFiles/${member.memberFileRename }" >
				</div>
			</div>
			<div class="">
				<form action="/member/modify.kh" method="post" name="modify_form">
					<table align="center">
						<tr>
							<td colspan="2" align="center">
								<input type="file" name="reloadFile">
								<!-- <button type="button" id="memberProfile" value="">프로필 사진 변경</button> -->
							</td>
						</tr>
						<tr>
							<td> 아이디 *</td>
							<td>
								<input type="text" id="memberId" name="memberId" value="${member.memberId }" readonly>
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
								<input type="text" name="memberName" value="${member.memberName }" readonly>
							</td>
						</tr>				
						<tr>
							<td> 닉네임 *</td>
							<td>
								<input type="text" name="memberNickname" value="${member.memberNickname }" readonly>
							</td>
						</tr>
						<tr>
							<td> 이메일 *</td>
							<td>
								<input type="text" id="memberEmail" name="memberEmail" value="${member.memberEmail }">
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
								<input type="text" name="memberPhone">
							</td>
						</tr>
						<tr>
							<td> 우편번호</td>
							<td>
								<input type="text" name="post" class="postcodify_postcode5" value="${addrInfos[0]}">
								<button type="button" id="postcodify_search_button">검색</button>
							</td>
						</tr>
						<tr>
							<td> 도로명 주소</td>
							<td>
								<input type="text" name="address1" class="postcodify_address" value="${addrInfos[1]}">
							</td>
						</tr>
						<tr>
							<td> 상세 주소</td>
							<td>
								<input type="text" name="address2" class="postcodify_details" value="${addrInfos[2]}">
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<button type="button" onclick="modifyform_check();">수정하기</button>
								<button type="button" onclick="removeMember();">탈퇴하기</button>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	<script>
		function removeMember() {
			if(confirm("탈퇴하시겠습니까?")) {
				location.href = "/member/remove.kh";
			}
		}
		$("#postcodify_search_button").postcodifyPopUp();
		
		function modifyform_check() {

			if (memberPwd.value == "") {
				alert("비밀번호를 입력하세요.");
				memberPwd.focus();
				return false;
			};

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
			
			if (memberEmail.value == "") {
				alert("이메일을 입력하세요.");
				memberEmail.focus();
				return false;
			};

			// 입력 값 전송
		document.modify_form.submit(); //유효성 검사의 포인트   
		}

	</script>
</body>
</html>