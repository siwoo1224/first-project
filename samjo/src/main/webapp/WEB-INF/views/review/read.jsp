<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 list.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">리뷰 상세보기</h2>
			</div>
		</div>
	</div>
</aside>

<div class="row-read">
	<div class="col-md-12">
		<form name="frm" method="post" action="reviewupdate.do"
			class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="review_code">예약번호</label> <input type="text"
					class="form-control" id="review_code" name="review_code" readonly>
			</div>
			<div class="form-group">
				<label for="s_code">예약 상품</label> <input type="text"
					class="form-control" id="s_code" name="s_code" readonly>
			</div>
			<div class="form-group">
				<label for="review_content">리뷰 내용</label>
				<textarea class="form-control" id="review_content"
					name="review_content" rows="3" readonly></textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-danger"
					onclick="location.href='reviewdelete.do'">삭제</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='index.do'">돌아가기</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>