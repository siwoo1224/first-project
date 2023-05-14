<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="./member/auth.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>여기놀자</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
<meta name="keywords"
	content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
<meta name="author" content="FREEHTML5.CO" />

<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FREEHTML5.CO
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="shortcut icon" href="favicon.ico">

<link
	href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700,900'
	rel='stylesheet' type='text/css'>
<link
	href="https://fonts.googleapis.com/css?family=Roboto+Slab:300,400,700"
	rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="../../css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="../../css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="../../css/bootstrap.css">
<!-- Superfish -->
<link rel="stylesheet" href="../../css/superfish.css">
<!-- Flexslider  -->
<link rel="stylesheet" href="../../css/flexslider.css">

<link rel="stylesheet" href="../../css/style.css">


<!-- Modernizr JS -->
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->
	
<script src="../../js/jquery-3.1.1.min.js"></script>

<script src="../../js/datepicker.min.js"></script>
<script src="../../js/datepicker.ko.js"></script>
<link href="../../css/datepicker.min.css" rel="stylesheet" type="text/css" media="all">

</head>
<body>
	<div id="fh5co-wrapper">
		<div id="fh5co-page">
			<div id="fh5co-header">

				<header id="fh5co-header-section">
					<div class="container">
						<div class="nav-header">
							<a href="/index.do" class="js-fh5co-nav-toggle fh5co-nav-toggle"><i></i></a>
							<h1 id="fh5co-logo">
								<a href="/index.do"><i class="icon-home2"></i>여기놀자</a>
							</h1>
							<!-- START #fh5co-menu-wrap -->
							<nav id="fh5co-menu-wrap" role="navigation">
								<ul class="sf-menu" id="fh5co-primary-menu">
									<li><a class="bold" href="#">여행지소개</a>
										<ul class="fh5co-sub-menu">
											<li><a href="/tour/tourist.do">관광지</a></li>
											<li><a href="/tour/festivalList.do">문화행사</a></li>
										</ul></li>
									<li><a class="bold" href="#">예약</a>
										<ul class="fh5co-sub-menu">
											<li><a href="/sookso/List.do">숙박</a></li>
											<li><a href="/rentalcar/upcheList.do">렌트카</a></li>
											<li><a href="/maszip/List.do">맛집</a></li>
											<li><a href="/packagetour/List.do">패키지</a></li>
										</ul></li>
									<li><a class="bold" href="#"
										class="fh5co-sub-ddown">커뮤니티</a>
										<ul class="fh5co-sub-menu">
											<li><a href="/notice/List.do">공지사항</a></li>
											<li><a href="/board/List.do">자유게시판</a></li>
										</ul></li>

									<%
									if (s_id.equals("guest") || s_passwd.equals("guest") || s_mlevel.equals("guest")) {

										//아이디저장 쿠키 확인------------------------
										//사용자PC에 저장된 모든 쿠키값 가져오기
										Cookie[] cookies = request.getCookies();
										String c_id = "";
										if (cookies != null) {
											for (int i = 0; i < cookies.length; i++) { //모든 쿠키값 가져오기
										Cookie cookie = cookies[i]; //쿠키 하나씩 가져오기
										if (cookie.getName().equals("c_id") == true) {
											c_id = cookie.getValue();//쿠키변수값 가져오기
										} //if end
											} //for end
										} //if end
											//--------------------------------------
									%>
									<li><a class="bold" href="/member/loginForm.do">로그인</a></li>


									<%
									} else {
									//로그인 성공했다면
									out.print("<li><a class='bold' href='#'>마이페이지</a>");
									out.print("<ul class='fh5co-sub-menu'>");
									out.print("<li><a href='/member/memberModify.do'>개인정보수정</a></li>");
									out.print("<li><a href='/res/list.do'>예약관리</a></li>");
									out.print("</ul></li>");
									out.print("<li>");
									out.print("<a class='bold' href='/member/loginForm.do'>로그아웃</a>");
									out.print("</li>");
									out.print("<li><a class='bold' href='../../cart/list.do'>장바구니</a></li>");
									if (s_mlevel.equals("1")) {
										out.print("<li><a class='bold' href='/admin/index.do'>관리자 페이지</a></li>");
									}
									} //if end
									%>
								</ul>
							</nav>
						</div>
						<div class="bottomMenu">
							<div class="searchWrap">
								<div class="search">
									<form method="post" name="SearchForm" action="" target="_blank"
										onsubmit="return ">
										<input type="hidden" name="menu" id="menu" value="통합검색" /> <label
											class="hidden" for="qt2">검색어 입력</label> <input type="text"
											name="qt" id="qt2" value="필요한 정보를 검색해보세요." onfocus="value=''" />
										<input type="submit" value="검색" class="btn btn-secondary"
											style="font-weight: bold; font-family: Arial; width: 100px;">
									</form>
								</div>
							</div>
							<ul class="weather">
								<li class="todayWeather">오늘<span class="wTemp">30℃</span></li>
								<li class="tomorrowWeather">내일<span class="wState">31℃</span></li>
								<li class="afterTomorrowWeather">모레<span
									class="wState2">32℃</span></li>
							</ul>
						</div>
					</div>

				</header>
			</div>
			<!-- end:fh5co-header -->