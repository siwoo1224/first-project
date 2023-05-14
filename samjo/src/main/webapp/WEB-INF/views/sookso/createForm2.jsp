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
					class="heading-section">방 등록</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="roomcreate.do" class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="s_cn">해당 숙소 코드</label> 
				<input type="text" class="form-control" id="s_cn" name="s_cn" value="${dto.s_cn}" readonly>
			</div>
			<div class="form-group">
				<label for="room_cn">방코드</label> 
				<input type="text" class="form-control" id="room_cn" name="room_cn" placeholder="S000_1">
			</div>
			<div class="form-group">
				<label for="room_name">방이름</label> 
				<input type="text" class="form-control" id="room_name" name="room_name">
			</div>
			<div class="form-group">
				<label for="posterMF2">대표 이미지</label> 
				<input type="file" class="form-control-file" id="posterMF2" name="posterMF2">
			</div>
			<div class="form-group">
				<label for="room_mp">최대인원수</label> 
				<input type="text" class="form-control" id="room_mp" name="room_mp">
			</div>
			<div class="form-group">
				<label for="room_dp">평일 가격</label>
				<input type="text" class="form-control" id="room_dp" name="room_dp">
			</div>
			<div class="form-group">
				<label for="room_ep">주말 가격</label>
				<input type="text" class="form-control" id="room_ep" name="room_ep">
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='/sookso/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>