<%@page import="kr.co.samjo.board2.boardDTO"%>
<%@page import="kr.co.samjo.board2.boardDAO"%>
<%@page import="kr.co.samjo.board2.CmtDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<%
boardDAO dao = new boardDAO();
ArrayList<CmtDTO> list = new ArrayList<CmtDTO>();
list = dao.cmtList(Integer.parseInt(request.getParameter("bbs_idx")));
%>
<jsp:useBean id="dto1" class="kr.co.samjo.board2.CmtDTO" scope="page"></jsp:useBean>
<jsp:useBean id="dto3" class="kr.co.samjo.board2.boardDTO" scope="page"></jsp:useBean>
<!-- 본문 시작 list.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">자유게시판</h2>
			</div>
		</div>
	</div>
	<div class="row-notice">
		<div class="col-md-12 text-center project">
			<div class="grid-project">
				<table class="table table-sm">
					<thead>
						<tr>
							<th colspan="4" scope="col" style="font-size: 25px;">${dto.bbs_title}</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" style="text-align: left">아이디 : ${dto.bbs_id}</td>
							<td>등록일 : ${dto.bbs_date}</td>
							<td>조회수 : ${dto.bbs_count}</td>
						</tr>
						<tr>
							<td colspan="4" rowspan="4" style="text-align: left">
								<div>
									<div style="font-size: 20px;">${dto.bbs_content}</div>
									<c:if test="${dto.bbs_img != null}">
										<img src="../../storage/${dto.bbs_img}">
									</c:if>
								</div>
							</td>
						</tr>
					</tbody>
				</table>



			</div>
			<div class='bottom'>
				<%
				if (s_mlevel.equals("2")) {
				%>
				<c:if test="${dto.bbs_id eq s_id }">
					<div style="text-align: right;">
						<input type="button" value="수정" class="btn btn-secondary"
							onclick="location.href='/board/update.do?bbs_idx=${dto.bbs_idx}'">
						<input type="button" value="삭제" class="btn btn-secondary"
							onclick="location.href='/board/delete.do?bbs_idx=${dto.bbs_idx}'">
					</div>
				</c:if>
				<%
				}
				%>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='/board/List.do'">목록</button>
			</div>
		</div>
		<div class="col-md-12" id="tab-menu">

			<div>
				<h4>댓글</h4>
				<hr>
				<form name="frm" method="post" action="/board/cmt.do"
					enctype="multipart/form-data">
					<input type="hidden" name="cmt_bbs_idx" id="cmt_bbs_idx"
						value="${dto.bbs_idx}">
					<div class="form-group">
						<label for="cmt_content">내용</label>
						<textarea class="form-control" id="cmt_content" name="cmt_content"
							rows="3"></textarea>
					</div>
					<%
					if (s_mlevel.equals("2")) {
					%>
					<button type="submit" class="btn btn-primary">등록</button>
					<%
					}
					%>

				</form>
				<table class="table table-hover cmt">
					<thead>
						<tr>
							<th class="board_no">내용</th>
							<th class="board_title">작성자</th>
							<th class="board_date">작성일</th>
							<th class="board_readcnt"></th>
						</tr>
					</thead>
					<thead>
						<%
						if (list != null) {
							for (int i = 0; i < list.size(); i++) {
								CmtDTO dto = list.get(i);
						%>
						<tr>
							<td>
								<%
								for (int j = 0; j < dto.getCmt_re_setp(); j++) {
								%> <img src='../storage/reply.gif'> <%
 }
 %> <%=dto.getCmt_content()%>
							</td>
							<td><%=dto.getCmt_id()%></td>
							<td><%=dto.getCmt_date()%></td>
							<td>
								<%
								if (s_mlevel.equals("2")) {
								%> <input type="button" value="답글"
								onclick="location.href='/cmt/replyproc.do?cmt_idx=<%=dto.getCmt_idx()%>&bbs_idx=<%=dto.getCmt_bbs_idx()%>'">

								<c:if test="${dto.bbs_id eq s_id }">
									<input type="button" value="수정"
										onclick="location.href='/cmt/update.do?cmt_idx=<%=dto.getCmt_idx()%>&bbs_idx=<%=dto.getCmt_bbs_idx()%>'">
									<input type="button" value="삭제"
										onclick="location.href='/cmt/delete.do?cmt_idx=<%=dto.getCmt_idx()%>&bbs_idx=<%=dto.getCmt_bbs_idx()%>'">
								</c:if> <%
 }
 %>

							</td>
						</tr>
						<%
						}
						}
						%>
					</thead>
				</table>
			</div>
		</div>
	</div>


</aside>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>