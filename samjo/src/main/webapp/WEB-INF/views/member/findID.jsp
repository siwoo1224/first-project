<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문시작 findID.jsp -->
<aside id="fh5co-hero" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2 style="margin-top: 300px; text-align: center; font-weight: bold;" class="heading-section">아이디/비번찾기</h2>
				<form method="post" action="findIDProc.do" onsubmit="return findIDCheck()"><!-- myscript.js -->
				<table class="table">
				<tr>
				   <th>이름</th>
				   <td>
				      <input type="text" name="user_name" id="user_name" placeholder="이름" maxlength="20" required>
				   </td>
				</tr>
				<tr>
				   <th>이메일</th>
				   <td>
				      <input type="email" name="user_email" id="user_email" placeholder="이메일" required>
				   </td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="아이디/비번찾기"  class="btn btn-primary"/>
						<input type="reset"  value="취소"  class="btn btn-primary" onclick="javascript:history.back()"/>
					</td>
				</tr>
				</table>
				</form>
			</div>
		</div>
	</div>
</aside>
<!-- 본문끝 -->
<%@ include file="../footer.jsp" %>