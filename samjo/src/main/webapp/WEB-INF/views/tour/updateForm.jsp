<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header2.jsp"%>
<!-- 본문 시작 -->

<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">여행지 수정</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="update.do" class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="t_cn">여행지코드</label> 
				<input type="text" class="form-control" id="t_cn" name="t_cn" value="${dto.t_cn}" readonly>
			</div>
			<div class="form-group">
				<label for="t_name">관광지 / 문화행사 이름</label> 
				<input type="text" class="form-control" id="t_name" name="t_name" value="${dto.t_name}">
			</div>
			<div class="form-group">
				<label for="t_addr">주소</label> 
				<input type="text" class="form-control" id="t_addr" name="t_addr" value="${dto.t_addr}">
			</div>
			<div class="form-group">
				<label for="t_dividecn">여행지 구분 코드</label> 
				<input type="number" class="form-control" id="t_dividecn" name="t_dividecn" value="${dto.t_dividecn}">
			</div>
			<div class="form-group">
				<label for="t_tel">전화번호</label> 
				<input type="text" class="form-control" id="t_tel" name="t_tel" value="${dto.t_tel}">
			</div>
			<div class="form-group">
				<label for="t_link">홈페이지 주소</label> 
				<input type="text" class="form-control" id="t_link" name="t_link" value="${dto.t_link}">
			</div>
			<div class="form-group">
				<label for="t_sche">일정</label> 
				<input type="text" class="form-control" id="t_sche" name="t_sche" value="${dto.t_sche}">
			</div>
			<div class="form-group">
				<label for="t_car">주차 가능 여부</label> 
				<input type="text" class="form-control" id="t_car" name="t_car" value="${dto.t_car}">
			</div>
			<div class="form-group">
				<label for="posterMF">현재 대표 이미지</label> 
				<img src='../../storage/${dto.t_img}' width='20%'>
				<input type="file" class="form-control-file" id="posterMF" name="posterMF">
			</div>
			<div class="form-group">
				<label for="t_cont">상세 정보 및 내용</label>
				<textarea class="form-control" id="t_cont" name="t_cont" rows="3">${dto.t_cont}</textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='/admin/tour/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>