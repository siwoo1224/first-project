<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">예약 하기</h2>
			</div>
		</div>
	</div>
</aside>
<form name="frm" method="post" action="reserve.do">
	<input type="hidden" name="s_id" value=${requestScope.user_id}>
	<div class="row-read">
		<table class="table">
			<tr>
				<th>상품명</th>
				<th>수량</th>
				<th>인원</th>
				<th>이용시작일</th>
				<th>이용 끝일</th>
			</tr>
			<c:forEach var="dto" items="${map.list}">
				<tr>
					<td>${dto.s_name}</td>
					<td>${dto.cnt}</td>
					<td>${dto.p_cnt}</td>
					<td>${dto.sdate}</td>
					<td>${dto.fdate}</td>
				</tr>
			</c:forEach>
			<tr>
				<th>총금액</th>
				<th>결제수단</th>
			</tr>
			<tr>
				<td><input type='text' name='amount' size='50'
					value="${map.amount}" readonly></td>
				<td><select name="pay">
						<option value="card" selected="selected">카드</option>
						<option value="realtime" selected="selected">현장결제</option>
						<option value="none" selected="selected">무통장입금</option>
				</select></td>
			</tr>
		</table>
		<p>정말로 예약 하시겠습니까?</p>
		<input type="submit" value="예약"> 
		<input type="button" value="목록" onclick="location.href='../cart/list.do'">

	</div>
</form>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>