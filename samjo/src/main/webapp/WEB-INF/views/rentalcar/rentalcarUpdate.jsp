<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header2.jsp"%>

<!-- 본문 시작 -->

<aside id="fh5co-hero-T" class="js-fullheight">
	<div class="flexslider js-fullheight">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">렌트카 수정</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="rentalcarUpdate.do" class="Ins" enctype="multipart/form-data" >
			<div class="form-group">
				<label for="u_code">업체코드</label> 
				<input type="text" class="form-control" id="u_code" name="u_code" placeholder="C000_A00" value="${dto.u_code}">
			</div>
			<div class="form-group">
				<label for="c_code">차량코드</label> 
				<input type="text" class="form-control" id="c_code" name="c_code" value="${dto.c_code}">
			</div>
			<div class="form-group">
				<label for="c_kind">차종</label> 
				<input type="text" class="form-control" id="c_kind" name="c_kind" value="${dto.c_kind}">
			</div>
			<div class="form-group">
				<label for="c_name">차량명</label> 
				<input type="number" class="form-control" id="c_name" name="c_name" value="${dto.c_name}">
			</div>
			<div class="form-group">
				<label for="c_sum">금액(1일)</label> 
				<input type="text" class="form-control" id="c_sum" name="c_sum" value="${dto.c_sum}">
			</div>
			<div class="form-group">
				<label for="c_charge">추가요금(1시간당)</label> 
				<input type="text" class="form-control" id="c_charge" name="c_charge" value="${dto.c_charge}">
			</div>
			<div class="form-group">
				<label for="c_reserve">예약가능 차량수</label> 
				<input type="number" class="form-control" id="c_reserve" name="c_reserve" value="${dto.c_reserve}">
			</div>
			<div class="form-group">
				<label for="posterMF2">이미지</label> 
				<img src='../storage/${dto.c_img}' width='20%'>
				<input type="file" class="form-control-file" id="posterMF2" name="posterMF2" value="${dto.c_img}">
			</div>
			<div class="form-group">
				<label for="c_cont">상세 정보 및 내용</label>
				<textarea class="form-control" id="c_cont" name="c_cont" rows="3" value="${dto.c_cont}"></textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='/rentalcar/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>