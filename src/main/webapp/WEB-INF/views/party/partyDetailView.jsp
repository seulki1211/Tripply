<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세 정보</title>
</head>
<body>
	<jsp:include page="../common/menuBar.jsp"></jsp:include>


	<h1 align='center'> ${board.boardNo }번 게시글 상세 보기</h1>
	<br><br>
	<table border='1'  align="center"  border="1">
			<tr>
			<td width = '80px' >제목</td>
			<td>${board.boardTitle }</td>
			</tr>
			<tr>
			<td>작성자</td>
			<td>${board.boardWriter }</td>
			</tr>
			<tr>
			<td>작성날짜</td>
			<td>${board.bCreateDate }</td>
			</tr>
			<tr>
			<td>조회수</td>
			<td>${board.boardCount }</td>
			</tr>
			<tr height="300" width='100'>
			<td>내용</td>
			<td>${board.boardContents }
			</tr>
			
			<tr >
			<td>첨부파일</td>
				<td>
					<c:if test="${not empty board.boardFilename}">
						<img alt="본문이미지" src="/resources/buploadFiles/${board.boardFileRename }" width = '50%'>
					</c:if>
				</td>
			</tr>
			
			<tr>
				<td colspan='2' align='center'>
				<button type="button" onclick="location.href='/board/list.kh?&page=${page }'">리스트로</button> 
				
					<c:if test="${board.boardWriter eq loginUser.memberName }">
						<button type="button" onclick="location.href='/board/modifyView.kh?boardNo=${board.boardNo }&page=${page }'">수정하기</button>
					</c:if> 	
					<c:if test="${board.boardWriter eq loginUser.memberName }">
						<button type="button" onclick="removeBoard(${page});">삭제하기</button> 
					</c:if> 	
							
				</td>
			</tr>
		</table>
		
<!-- 		댓글 등록 -->
<form action="/board/addReply.kh" method="post">
		<table align='center' width = "500" border = "1">
			<tr>
				<td>
					<textarea rows="3" cols="55"></textarea>
				</td>
				<td>
				<button type="button">등록하기</button>
				</td>
			</tr>
		</table>
</form>
<!-- 		댓글 목록 -->
		<table align='center' width='500' border='1'>
			
		</table>

<script type="text/javascript">
function removeBoard(page) {
 	event.preventDefault();// 하이퍼링크 이동 방지
	if(confirm("삭제하시려면 '확인'을 클릭하세요")){
		location.href="/board/remove.kh?page="+page;
	}
}
	
</script>
</body>
</html>