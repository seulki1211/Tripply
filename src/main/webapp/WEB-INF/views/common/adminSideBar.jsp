<%-- <%@ page session="false" %> 이거 있으면 세션스코프 동작 안함--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>관리자 페이지입니다</title>

<style type="text/css">

ul.admin-menu {
    list-style-type: none;
    padding: 0px;
    margin: 0px;
    width: 150px;
    background: #101c4f;
    height: 100%;
    overflow: auto;
    position: fixed;
  }
  
  li.admin-menu  {
    text-decoration: none;
    padding: 10px;
    display: block;
    color: #fff;
    font-weight: bold;
  }
  
  li.admin-menu:hover {
    background: rgb(190, 189, 229);
        font-weight: bold;
    
    color: #000;
  }
  
  li.admin {
   margin-top : 10px;
   background: rgb(190, 189, 229);
   padding: 10px;
   display: block;
   color: #000;
   font-weight: bold;
    
  }
  
  .right-side {
    margin-left: 150px;
  }

</style>
</head>

<body>
	<div id="left-sidebar" >
            <ul class="admin-menu">
            	<li class="admin" > 관리자 페이지 </li>
                <li class="admin-menu" onclick="goToServicePage();">서비스 페이지</li>
                <li class="admin-menu" onclick="location.href='/admin/banner/list.kh';">배너 관리</li>
                <li class="admin-menu" onclick="location.href='/admin/notice/list.kh'">공지사항 관리</li>
            </ul>
        </div>
        
        
        <script type="text/javascript">
        function goToServicePage() {
         	event.preventDefault();// 하이퍼링크 이동 방지
			if(confirm("서비스페이지로 이동하시겠습니까?")){
				location.href='/home.kh';
			}
		}
        </script>
       
</body>
</html>
