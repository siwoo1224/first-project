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
					class="heading-section">패키지투어 등록</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="packagetourIns.do" class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="pack_no">패키지코드</label> 
				<input type="text" class="form-control" id="pack_no" name="pack_no" placeholder="P000">
			</div>
			<div class="form-group">
				<label for="pack_name">패키지이름</label> 
				<input type="text" class="form-control" id="pack_name" name="pack_name">
			</div>
			<div class="form-group">
				<label for="pack_cose">여행코스</label> 
				<input type="text" class="form-control" id="pack_cose" name="pack_cose">
			</div>
			<div class="form-group">
				<label for="pack_plan_start">모집일정(출발)</label> 
				<input type="date" class="form-control" id="pack_plan_start" name="pack_plan_start">
			</div>
			<div class="form-group">
				<label for="pack_plan_end">모집일정(도착)</label> 
				<input type="date" class="form-control" id="pack_plan_end" name="pack_plan_end">
			</div>
			<div class="form-group">
				<label for="pack_price">비용</label> 
				<input type="text" class="form-control" id="pack_price" name="pack_price">
			</div>
			<div class="form-group">
				<label for="pack_people">모집 인원</label> 
				<input type="text" class="form-control" id="pack_people" name="pack_people">
			</div>
			<div class="form-group">
				<label for="posterMF">대표 이미지</label> 
				<input type="file" class="form-control-file" id="posterMF" name="posterMF">
			</div>
			<div class="form-group">
				<label for="pack_cont">상세 정보 및 내용</label>
				<textarea class="form-control" id="pack_cont" name="pack_cont" rows="3"></textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='/packagetour/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>