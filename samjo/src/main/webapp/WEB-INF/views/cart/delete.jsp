<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->
<aside id="fh5co-hero-T" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">장바구니 항목 삭제</h2>
			</div>
		</div>
	</div>
</aside>
<div class="tourcreate">
	<form name="frm" method="post" action="delete.do">
		<input type="hidden" name="c_no" value=${requestScope.c_no}>
		<div class="content">
			<p>장바구니 항목을 삭제하시겠습니까?</p>
		</div>
		<div class="bottom">
			<input type="submit" value="삭제"> <input type="button"
				value="목록" onclick="location.href='list.do'">
		</div>
	</form>
</div>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>