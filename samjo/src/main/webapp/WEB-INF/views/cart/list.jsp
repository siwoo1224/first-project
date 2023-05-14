<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->
<aside id="fh5co-hero-T" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">장바구니 목록</h2>
			</div>
			
			
<c:choose>
	<c:when test="${map.count ==0}">
	<div class="row-read">
		장바구니가 비었습니다.
		</div>
	</c:when>
	<c:otherwise>
	<div class="row-read">
		<table class="table">
			<tr>
				<th>상품명</th>
				<th>수량</th>
				<th>인원</th>
				<th>이용시작일</th>
				<th>이용 끝일</th>
				<th>삭제</th>
			</tr>

			<c:forEach var="dto" items="${map.list}">
				<tr>
					<td>${dto.s_name}</td>
					<td>${dto.cnt}</td>
					<td>${dto.p_cnt}</td>
					<td>${dto.sdate}</td>
					<td>${dto.fdate}</td>
					<td><input type="button" value="삭제"
						onclick="location.href='delete.do?c_no=${dto.c_no}'"></td>
				</tr>
			</c:forEach>
			<tr>
				<th>총 금액 : ${map.amount}</th>
				<td colspan="1"><input type="button" value="예약" onclick="location.href='/res/reserve.do'"></td>
			</tr>
		</table>
	</div>
	</c:otherwise>
</c:choose>
		</div>
	</div>
</aside>
<script>
	$(document).ready(function() {
		$("#btnList").click(function() {
			location.href = "../cart/list.do";
		});
	});
</script>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>