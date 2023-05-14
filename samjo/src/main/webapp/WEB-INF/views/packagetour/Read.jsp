<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 Read.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">${dto.pack_name}</h2>
			</div>
		</div>
	</div>
</aside>

<div class="row-read">
	<div class="col-md-12 text-center project">
		<div class="grid-project">
			<div class="image-T">
				<img src="../../storage/${dto.pack_img}" class="img-responsive"
					style="width: 40%; float: left;">
				<div class="desc-T">
					<ul>
						<strong>패키지 여행 정보</strong>						
						<hr>
						<li>여행코스&emsp;&emsp;&emsp;${dto.pack_cose}</li>
						<li>모집일정&nbsp;&nbsp;&nbsp;&nbsp;${dto.pack_plan_start}-${dto.pack_plan_end}</li>
						<li>비용&emsp;&emsp;&emsp;${dto.pack_price}</li>
						<li>모집인원&emsp;&emsp;&emsp;${dto.pack_people}</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-md-12" id="tab-menu">
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="col-md-6 nav-item"><a class="nav-link active in"
				id="info-tab" data-toggle="tab" href="#info" role="tab"
				aria-controls="info" aria-selected="true">상세정보</a></li>
			<li class="col-md-6 nav-item"><a class="nav-link"
				id="review-tab" data-toggle="tab" href="#review" role="tab"
				aria-controls="review" aria-selected="false">리뷰</a></li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="info" role="tabpanel"
				aria-labelledby="info-tab">${dto.pack_cont}</div>
			<div class="tab-pane fade" id="review" role="tabpanel"
				aria-labelledby="review-tab">${dto.review_content}</div>
		</div>
	</div>
</div>


</div>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>