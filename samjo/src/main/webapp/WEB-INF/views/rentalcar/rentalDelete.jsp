<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header2.jsp"%>

<!-- rentalIns.jsp 본문 시작 -->

<aside id="fh5co-hero-T" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">렌트카 업체 삭제</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="rentalDelete.do" class="Ins"
			enctype="multipart/form-data">
			<input type="hidden" name="u_code" value="${dto.u_code}">
			<div class="content">
				<p>해당 렌트카 업체(${dto.u_name}) 를 삭제하시겠습니까?</p>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">삭제</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='/admin/rentalcarList.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>