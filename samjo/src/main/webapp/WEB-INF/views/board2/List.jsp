<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<h2 style="margin-top: 300px; text-align: center; font-weight: bold;"
				class="heading-section">자유게시판</h2>
		</div>
	</div>

		<!-- 검색 시작 -->
		<div style='text-align: right; height: 50px; margin-right: 50px;'>
			<form action="List.do">
				<select class="custom-select" name="col" style="width: 150px; height: 35px; border: 2px solid gray; border-radius: 5px 5px 5px 5px">
					<option value="subject_content">제목 + 내용 
					<option value="subject">제목
					<option value="content">내용
					<option value="wname">작성자
				</select>
				<input type="text" name="word" id="word" style="border: 2px solid gray; border-radius: 5px 5px 5px 5px">
				&nbsp;&nbsp; 
				<input type="submit" value="검색" class="btn btn-secondary" style="font-weight: bold; font-family: Arial;">
			
			</form>
		</div>
		<!-- 검색 끝 -->
	<div id="fh5co-work-section">
		<div class="container">
			<div class="row">
				<div class="col-md-12 text-center project">
					<div class="grid-project">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>글번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>등록일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<!-- MediagroupCont의 list()함수에서 mav.addObject("list")를 가리킴 -->
							<c:forEach var="dto" items="${list}">
								<tr>
									<td>${dto.bbs_idx}</td>
									<td><a href="boardread.do?bbs_idx=${dto.bbs_idx}">${dto.bbs_title}</a></td>
									<td>${dto.bbs_id}</td>
									<td>${dto.bbs_date}</td>
									<td>${dto.bbs_count}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>



		<div class="paging">
			<div class="content">
			<% if (s_mlevel.equals("2")) {%>
            <input type="button" class="btn btn-primary" value="글쓰기" onclick="location.href='/board/create.do'">
        	<% }%>
				
			</div>
			
			<br>
			
			<c:if test="${requestScope.count>0 }">
				<c:set var="pageCount" value="${requestScope.totalPage}" />
				<c:set var="startPage" value="${requestScope.startPage}" />
				<c:set var="endPage" value="${requestScope.endPage}" />

				<div class="content">
					<c:if test="${endPage>pageCount}">
						<c:set var="endPage" value="${pageCount+1}" />
					</c:if>

					<c:if test="${startPage>0}">
						<a href="/board2/List.do?pageNum=${startPage}">[이전]</a>
					</c:if>

					<c:forEach var="i" begin="${startPage+1}" end="${endPage-1}">
						<a href="/board2/List.do?pageNum=${i}">[${i}]</a>
					</c:forEach>

					<c:if test="${endPage<pageCount}">
						<a href="/board2/List.do?pageNum=${startPage+11}">[다음]</a>
					</c:if>
				</div>
			</c:if>
		</div>
	</div>
</aside>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>