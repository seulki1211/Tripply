<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>여행 올인원 플랫폼,Tripply</title>
 <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css">
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<style>
.header{
    display : block;
}
    
    /* gallary */
	.gallary {position: relative; width: 100%; height: 400px; top: 0px;  margin:0 auto; padding:0; overflow:hidden; }
	.gallary ul {position: absolute; margin: 0px; padding: 0px; list-style: none;  }
	.gallary ul li {float: left; width: 100%; height: 500; margin:0; padding:0; }
    .gallary ul li img{   margin: 0; padding: 0%;}

    /* logo */
   .logo{
    display: flex;
    position: absolute;
    justify-content: center;
    margin: 0;
    padding: 0%;
   }

 /* nav */
 
.nav{
    background-color: rgba(0, 0, 0, 0.9);
    height: 50px;
}

 .nav-bar-menu{
    position: relative;
    margin-top: -25px;
    display : flex;
	width : 200px;
	height : 50px;
	text-align : center;
	vertical-align : middle;
	font-size : 30px;
	background : black;
	color : white;
	justify-content:center;
	float:left;
	align-items:center;
    list-style: none;
    font-family: 'Jua', sans-serif;

   }
   #menu1{
    border-top-left-radius: 15px;
    border-bottom-left-radius: 15px;
   }

 
   #menu3{
    border-top-right-radius: 15px;
    border-bottom-right-radius: 15px;
   }

   .nav-bar-menu:hover{
    background : coral;
	cursor : pointer;
   }  


   </style>
</head>
<body>
	   <div class="header" style="height: 450px;">
        <div class="gallary">
			<ul>
                <li class="col-12"><img src="/resources/image/header/flower1.PNG" alt="배너이미지" width="1920px" ></li>
                <li class="col-12"><img src="/resources/image/header/flower2.PNG" alt="배너이미지" width="1920px" ></li>
                <li class="col-12"><img src="/resources/image/header/flower3.PNG" alt="배너이미지" width="1920px" ></li>
                <li class="col-12"><img src="/resources/image/header/flower4.PNG" alt="배너이미지" width="1920px" ></li>
            </ul>
      
            <div class="logo col-12">
               <a href="/home.kh"> <img src="/resources/image/header/tripply-logo.png" alt="로고이미지" width="400px"></a>
            </div>
		</div>
        <div class="nav justify-content-center">
            <ul class="nav-bar">
                <li class="nav-bar-menu" id="menu1" onclick="location.href='/plan/plan.kh';">여행 일정</li>
                <li class="nav-bar-menu" id="menu2" onclick="location.href='/review/list.kh';">여행 후기</li>
                <li class="nav-bar-menu" id="menu2" onclick="location.href='/trade/list.kh';">중고 거래</li>
                <li class="nav-bar-menu" id="menu2" onclick="location.href='/party/list.kh';"">우리 함께</li>
                <li class="nav-bar-menu" id="menu3" onclick="location.href='/free/list.kh';"">자유롭게</li>
            </ul>
        </div>

    </div>
    
    <script type="text/javascript">
    
		 $(document).ready(function() {
	            var $gallary = $(".gallary").find("ul");
	    
	            var $gallaryWidth = $gallary.children().outerWidth();//이미지의 폭
	            var $gallaryHeight = $gallary.children().outerHeight(); // 높이
	            var $length = $gallary.children().length;//이미지의 갯수
	            var rollingId;
	    
	            //정해진 초마다 함수 실행
	            rollingId = setInterval(function() { rollingStart(); }, 15000);//다음 이미지로 롤링 애니메이션 할 시간차
	        
	            function rollingStart() {
	                $gallary.css("width", $gallaryWidth  + "px");
	                $gallary.css("height", $gallaryHeight * length + "px");
	                //alert(bannerHeight);
	                //배너의 좌측 위치를 옮겨 준다.
	                $gallary.animate({top: - $gallaryHeight + "px"}, 3000, function() { //숫자는 롤링 진행되는 시간이다.
	                    //첫번째 이미지를 마지막 끝에 복사(이동이 아니라 복사)해서 추가한다.
	                    $(this).append("<li>" + $(this).find("li:first").html() + "</li>");
	                    //뒤로 복사된 첫번재 이미지는 필요 없으니 삭제한다.
	                    $(this).find("li:first").remove();
	                    //다음 움직임을 위해서 배너 좌측의 위치값을 초기화 한다.
	                    $(this).css("top", 0);
	                    //이 과정을 반복하면서 계속 롤링하는 배너를 만들 수 있다.
	                });
	            }
		 }); 
	 </script>
</body>
</html>
