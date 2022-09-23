<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
  
<meta charset="UTF-8">
<title>게시글작성</title>
</head>
<body>
<div>
	<jsp:include page="../../common/adminSideBar.jsp"></jsp:include>
</div>


	<div class="right-side">
	<br><br>
	<h1 align="center">배너 등록 및 리스트 페이지</h1>
	<br><br>
	
		
		<form action="/admin/banner/register.kh" method="post" enctype="multipart/form-data">
		<table align="center" class="table col-10 mb-3">
				<tr>
					<td align='center' ><input type="hidden" name="bannerNo" value='1' > 1</td>
					<td align='center' ><c:if test="${not empty bList[0].bannerFileName}">
							<img alt="본문이미지" src="/resources/buploadFiles/${bList[0].bannerFileRename }" width = '50%'>
						</c:if><td>
				</tr>
				<tr>
					<td><input type="hidden" name="bannerWriter" value="${bList[0].bannerWriter }"></td>
					<td><input type="file" name="uploadFile" ></td>
					<td><input type="submit" value="저장" class="btn btn-primary"></td>
				</tr>
				</table>
		</form>
	
		<form action="/admin/banner/register.kh" method="post" enctype="multipart/form-data">
		<table align="center" class="table col-10 mb-3">
				<tr>
					<td align='center' ><input type="hidden" name="bannerNo" value='2' > 2</td>
					<td align='center' ><c:if test="${not empty bList[1].bannerFileName}">
							<img alt="본문이미지" src="/resources/buploadFiles/${bList[1].bannerFileRename }" width = '50%'>
						</c:if><td>
				</tr>
				<tr>
					<td><input type="hidden" name="bannerWriter" value="${bList[1].bannerWriter }"></td>
					<td><input type="file" name="uploadFile" ></td>
					<td><input type="submit" value="저장" class="btn btn-primary"></td>
				</tr>
				</table>
		</form>
		
		<form action="/admin/banner/register.kh" method="post" enctype="multipart/form-data">
		<table align="center" class="table col-10 mb-3">
				<tr>
					<td align='center' ><input type="hidden" name="bannerNo" value='3' > 3</td>
					<td align='center' ><c:if test="${not empty bList[2].bannerFileName}">
							<img alt="본문이미지" src="/resources/buploadFiles/${bList[2].bannerFileRename }" width = '50%'>
						</c:if><td>
				</tr>
				<tr>
					<td><input type="hidden" name="bannerWriter" value="${bList[2].bannerWriter }"></td>
					<td><input type="file" name="uploadFile" ></td>
					<td><input type="submit" value="저장" class="btn btn-primary"></td>
				</tr>
				</table>
		</form>
		
		<form action="/admin/banner/register.kh" method="post" enctype="multipart/form-data">
		<table align="center" class="table col-10 mb-3">
				<tr>
					<td align='center' ><input type="hidden" name="bannerNo" value='4' > 4</td>
					<td align='center' ><c:if test="${not empty bList[3].bannerFileName}">
							<img alt="본문이미지" src="/resources/buploadFiles/${bList[3].bannerFileRename }" width = '50%'>
						</c:if><td>
				</tr>
				<tr>
					<td><input type="hidden" name="bannerWriter" value="${bList[3].bannerWriter }"></td>
					<td><input type="file" name="uploadFile" ></td>
					<td><input type="submit" value="저장" class="btn btn-primary"></td>
				</tr>
				</table>
		</form>
		
		<form action="/admin/banner/register.kh" method="post" enctype="multipart/form-data">
		<table align="center" class="table col-10 mb-3">
				<tr>
					<td align='center' ><input type="hidden" name="bannerNo" value='5' > 5</td>
					<td align='center' ><c:if test="${not empty bList[4].bannerFileName}">
							<img alt="본문이미지" src="/resources/buploadFiles/${bList[4].bannerFileRename }" width = '50%'>
						</c:if><td>
				</tr>
				<tr>
					<td><input type="hidden" name="bannerWriter" value="${bList[4].bannerWriter }"></td>
					<td><input type="file" name="uploadFile" ></td>
					<td><input type="submit" value="저장" class="btn btn-primary"></td>
				</tr>
				</table>
		</form>
		
	
	</div>
	<br><br><br><br><br>
</body>
</html>