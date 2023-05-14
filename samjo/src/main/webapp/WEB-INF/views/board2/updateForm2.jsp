<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

<!-- 본문 시작 -->
<%
	int cmt_bbs_idx = Integer.parseInt(request.getParameter("bbs_idx"));
	int cmt_idx = Integer.parseInt(request.getParameter("cmt_idx"));
%>
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">댓글 수정</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<div>
			<h4>댓글</h4>
						<hr>
			<form name="frm" method="post" action="/cmt/update.do"  enctype="multipart/form-data">
			<input type="hidden" name="cmt_bbs_idx" id="cmt_bbs_idx" value="<%=cmt_bbs_idx%>">
			<input type="hidden" name="cmt_idx" id="cmt_idx" value="<%=cmt_idx%>">			
			<div class="form-group">
				<label for="cmt_content">내용</label>
				<textarea class="form-control" id="cmt_content" name="cmt_content" rows="3">${$dto.cmt_content}</textarea>
			</div>
			<button type="submit" class="btn btn-primary">수정</button>
			</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>