<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!-- 본문시작 bbsRead.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">공지사항 상세보기</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row-bbs">
	<div class="col-md-12 text-center project">
		<table class="table table-sm">
			<thead>
				<tr>
					<th colspan="4" scope="col">${dto.board_title}</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2" style="text-align:left">등록일 : ${dto.board_date}</td>
					<td colspan="2">조회수 : ${dto.board_readcnt}</td>
				</tr>
				<tr>
					<td colspan="4" rowspan="4" style="text-align:left; white-space: pre; overflow: auto;">${dto.board_content}</td>
				</tr>
			</tbody>
		</table>

		<div style="text-align: center;">
			<div class='bottom'>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='/notice/List.do'">목록</button>
			</div>
		</div>

	</div>
</div>



<!-- 본문끝 -->
<%@ include file="../footer.jsp"%>
