<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 -->

<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">자유게시판 작성</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="/board/create.do" class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="bbs_title">제목</label> 
				<input type="text" class="form-control" id="bbs_title" name="bbs_title">
			</div>
			<div class="form-group">
				<label for="bbs_id">작성자</label> 
				<input type="text" class="form-control" id="bbs_id" name="bbs_id" value="<%=s_id%>" readonly="readonly">
			</div>
			<div class="form-group">
				<label for="posterMF">이미지</label>
				<input type="file" class="form-control-file" id="posterMF" name="posterMF">
			</div>
			<div class="form-group">
				<label for="bbs_content">내용</label>
				<textarea class="form-control" id="bbs_content" name="bbs_content" rows="3"></textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary handleClick()" onclick="location.href='/board/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>