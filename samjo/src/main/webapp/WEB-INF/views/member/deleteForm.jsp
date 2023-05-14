<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header2.jsp"%>

<!-- 본문 시작 -->

<aside id="fh5co-hero-T" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">회원 삭제</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="userdelete.do" class="tourcreate"
			enctype="multipart/form-data">
			<input type="hidden" name="user_id" value="${dto.user_id}">
			<div class="content">
				<p>해당 유저(${dto.user_id}) 를 삭제하시겠습니까?</p>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">삭제</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='/admin/tourist.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>