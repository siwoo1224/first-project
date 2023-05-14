<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header2.jsp"%>

<!-- 본문 시작 -->

<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">패키지 여행 삭제</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="packagetourDelete.do" class="Ins"
			enctype="multipart/form-data">
			<input type="hidden" name="pack_no" value="${dto.pack_no}">
			<div class="content">
				<p>해당 여행지(${dto.pack_name}) 를 삭제하시겠습니까?</p>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">삭제</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='/admin/packagetour/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>