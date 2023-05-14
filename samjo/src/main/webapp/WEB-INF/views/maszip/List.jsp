<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 tourist.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">경주 맛집</h2>
			</div>
		</div>
		<hr>
	</div>

	<!-- 검색 시작 -->
	<div style='text-align: right; height: 50px; margin-right: 50px;'>
		<form action="/maszip/List.do">
			<input type="text" name="word" id="word" style="border: 2px solid black; border-radius: 5px 5px 5px 5px">
			&nbsp;&nbsp; 
			<input type="submit" value="검색" class="btn btn-secondary" style="font-weight: bold; font-family: Arial;">
		</form>
	</div>
	<!-- 검색 끝 -->
</aside>
<div id="fh5co-work-section">

	<div class="container">
		<div class="row">
			<c:forEach var="dto" items="${list}">
				<div class="col-md-4 text-center project">
					<a href="../maszip/List/read.do?m_code=${dto.m_code}"
						class="grid-project">
							<img src="../storage/${dto.m_img}" alt="Project"
								class="img-responsive" style="margin: 0 auto; height:300px;">
						<div class="desc">
							<h3>${dto.m_name}</h3>
							<span>${dto.m_kind}</span>
						</div>
					</a>
				</div>
			</c:forEach>
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
					<a href="/maszip/List.do?pageNum=${startPage}">[이전]</a>
				</c:if>

				<c:forEach var="i" begin="${startPage+1}" end="${endPage-1}">
					<a href="/maszip/List.do?pageNum=${i}">[${i}]</a>
				</c:forEach>

				<c:if test="${endPage<pageCount}">
					<a href="/maszip/List.do?pageNum=${startPage+11}">[다음]</a>
				</c:if>
			</div>
		</c:if>
	</div>
	
</div>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>
