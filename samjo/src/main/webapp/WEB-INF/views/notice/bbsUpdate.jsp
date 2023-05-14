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
					class="heading-section">공지사항 수정</h2>
			</div>
		</div>
	</div>
</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="update.do" class="tourcreate"
			enctype="multipart/form-data">
			<input type="hidden" name="board_no" value="${dto.board_no}">
			<div class="form-group">
				<label for="t_cont">제목</label>
				<textarea class="form-control" id="board_title" name="board_title"
					rows="3">${dto.board_title}</textarea>
			</div>
			<div class="form-group">
				<label for="t_cont">내용</label>
				<textarea class="form-control" id="board_content"
					name="board_content" rows="3">${dto.board_content}</textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>