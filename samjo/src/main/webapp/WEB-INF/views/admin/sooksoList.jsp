<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header2.jsp"%>
<!-- 본문 시작 tourist.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">숙소 목록</h2>
				<div style="text-align: center;">
					<button type="button" class="btn btn-primary"
						onclick="location.href='create.do'">등록</button>
				</div>
			</div>
		</div>
	</div>

	<!-- 검색 시작 -->
	<div style='text-align: right; height: 50px; margin-right: 50px;'>
		<form action="tourist.jsp">
			<input type="text" name="word" id="word"
				style="border: 2px solid black; border-radius: 5px 5px 5px 5px">
			&nbsp;&nbsp; <input type="submit" value="검색"
				class="btn btn-secondary"
				style="font-weight: bold; font-family: Arial;">
		</form>
	</div>
	<!-- 검색 끝 -->
</aside>
<div id="fh5co-work-section">

	<div class="container">
		<div class="row">
			<div class="col-md-12 text-center project">
				<div class="grid-project">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col">숙소코드</th>
								<th scope="col">숙소명</th>
								<th scope="col">숙소주소</th>
								<th scope="col">수정 / 삭제</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dto" items="${list}">
								<tr>
									<th scope="row">${dto.s_cn}</th>
									<td onclick="location.href='Listread.do?s_cn=${dto.s_cn}'">${dto.s_name}</td>
									<td>${dto.s_addr}</td>
									<td><button type="button" class="btn btn-light"
											onclick="location.href='update.do?s_cn=${dto.s_cn}'">수정</button>
										<button type="button" class="btn btn-danger"
											onclick="location.href='delete.do?s_cn=${dto.s_cn}'">삭제</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>

	<!-- 페이지 리스트 -->
	<div class="paging">
		<c:if test="${requestScope.count>0 }">
			<c:set var="pageCount" value="${requestScope.totalPage}" />
			<c:set var="startPage" value="${requestScope.startPage}" />
			<c:set var="endPage" value="${requestScope.endPage}" />

			<div class="content">
				<c:if test="${endPage>pageCount}">
					<c:set var="endPage" value="${pageCount+1}" />
				</c:if>

				<c:if test="${startPage>0}">
					<a href="/tour/tourist.do?pageNum=${startPage}">[이전]</a>
				</c:if>

				<c:forEach var="i" begin="${startPage+1}" end="${endPage-1}">
					<a href="/tour/tourist.do?pageNum=${i}">[${i}]</a>
				</c:forEach>

				<c:if test="${endPage<pageCount}">
					<a href="/tour/tourist.do?pageNum=${startPage+11}">[다음]</a>
				</c:if>
			</div>
		</c:if>
	</div>
</div>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>