<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 -->

<aside id="fh5co-hero-R">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">리뷰 등록</h2>
			</div>
		</div>
	</div>
</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="reviewcreate.do"
			class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="res_no">예약번호</label> <input type="text"
					class="form-control" id="review_res_no" name="review_res_no"
					value="${res_no }" readonly>
			</div>
			<input type="hidden" id="review_code" name="review_code" value="${s_code }">
			<div class="form-group">
				<label for="s_code">예약 상품</label> <input type="text"
					class="form-control" id="s_name" name="s_name" value="${s_name }"
					readonly> 
			</div>
			<div class="form-group">
				<label for="review_user_id">아이디</label> <input type="text"
					class="form-control" id="review_user_id" name="review_user_id"
					value="${user_id }" readonly>
			</div>
			<style>
			.star-rating {
			  display: flex;
			  flex-direction: row-reverse;
			  font-size: 2.25rem;
			  line-height: 2.5rem;
			  justify-content: space-around;
			  padding: 0 0.2em;
			  text-align: center;
			  width: 5em;
			}
			 
			.star-rating input {
			  display: none;
			}
			 
			.star-rating label {
			  -webkit-text-fill-color: transparent; /* Will override color (regardless of order) */
			  -webkit-text-stroke-width: 2.3px;
			  -webkit-text-stroke-color: #2b2a29;
			  cursor: pointer;
			}
			 
			.star-rating :checked ~ label {
			  -webkit-text-fill-color: gold;
			}
			 
			.star-rating label:hover,
			.star-rating label:hover ~ label {
			  -webkit-text-fill-color: #fff58c;
			}
			</style>
			<div class="star-rating space-x-4 mx-auto">
				<input type="radio" id="5-stars" name="review_asterion" value="5" v-model="ratings"/>
				<label for="5-stars" class="star pr-4">★</label>
				<input type="radio" id="4-stars" name="review_asterion" value="4" v-model="ratings"/>
				<label for="4-stars" class="star">★</label>
				<input type="radio" id="3-stars" name="review_asterion" value="3" v-model="ratings"/>
				<label for="3-stars" class="star">★</label>
				<input type="radio" id="2-stars" name="review_asterion" value="2" v-model="ratings"/>
				<label for="2-stars" class="star">★</label>
				<input type="radio" id="1-star" name="review_asterion" value="1" v-model="ratings" />
				<label for="1-star" class="star">★</label>
			</div>
			<div class="form-group">
				<label for="review_content">리뷰 내용</label>
				<textarea class="form-control" id="review_content"
					name="review_content" rows="3"></textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="button" class="btn btn-secondary"
					onclick="location.href='index.do'">홈 화면</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>