<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header2.jsp" %>    
<!-- 본문시작 bbsIns.jsp -->

<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">공지사항 등록</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="bbsIns" id="bbsIns" method="post" action="create.do" onsubmit="return bbsCheck()" class="tourcreate">
			<div class="form-group">
				<label for="board_title">제목</label> 
				<input type="text" name="board_title" id="board_title" class="form-control" maxlength="100" required>
			</div>

			<div class="form-group">
				<label for="board_content">내용</label>
				<textarea rows="5" class="form-control" name="board_content" id="board_content"></textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='List.do'">목록</button>
			</div>
		</form>
	</div>
</div>
=======
<aside id="fh5co-hero-T" class="js-fullheight">
		<div class="flexslider js-fullheight">
			<div class="container">
				<div class="slider-text-inner desc">
					<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">공 지 사 항</h2>
						<form name="bbsIns" id="bbsIns" method="post" action="bbsIns.do" onsubmit="return bbsCheck()"><!-- ★myscript.js에 함수 작성함 -->
						<table class="table">
						<tr>
							<th class="success">제목</th>
							<td><input type="text" name="board_title" id="board_title" class="form-control" maxlength="100" required></td>
						</tr>
						<tr>
							<th class="success">내용</th>
							<td><textarea rows="5" class="form-control" name="board_content" id="board_content"></textarea></td>
						</tr>
							<!-- 공지사항 등록 -->	
						</table>
						<div style="float:right">
								<button type="submit" class="btn btn-primary">등록</button>
								<button type="button" class="btn btn-secondary" onclick="location.href='bbsList.do'">목록</button>
						</div>
						</form>
				</div>
			</div>
		</div>
	</aside>
>>>>>>> 4ad7e09d3c642a0a359f0053a205e37b587ce96e

<!-- 본문끝 -->
<%@ include file="../footer.jsp" %>    