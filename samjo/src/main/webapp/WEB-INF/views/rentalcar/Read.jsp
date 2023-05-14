<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 Read.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">${udto.u_name}</h2>
			</div>
		</div>
	</div>
</aside>

<div class="row-read">
	<div class="col-md-12 text-center project">
		<div class="grid-project">
		
				<div class="image-T">
					<img src="../../storage/${udto.u_img}" class="img-responsive"
						style="width: 40%; float: left;">
					<div class="desc-T">
						<ul>
							<strong>렌트카 기본정보</strong>						
							<hr>
							<li>내용&nbsp;&nbsp;&nbsp;&nbsp;${udto.u_cont}</li>
							<li>업체&nbsp;&nbsp;&nbsp;&nbsp;${udto.u_name}</li>
							<li>전화번호&nbsp;&nbsp;&nbsp;&nbsp;${udto.u_phone}</li>
							<li>주소&nbsp;&nbsp;&nbsp;&nbsp;${udto.u_office}</li>
							</ul>
					</div>
				</div>
			</div>
		</div>
	<div class="col-md-12" id="tab-menu">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
         <li class="col-md-4 nav-item"><a class="nav-link active"
            id="map-tab" data-toggle="tab" href="#map" role="tab"
            aria-controls="map" aria-selected="true">픽업장소 보기</a></li>
         <li class="col-md-4 nav-item"><a class="nav-link" id="info-tab"
            data-toggle="tab" href="#info" role="tab" aria-controls="info"
            aria-selected="false">상세정보</a></li>
         <li class="col-md-4 nav-item"><a class="nav-link"
            id="review-tab" data-toggle="tab" href="#review" role="tab"
            aria-controls="review" aria-selected="false">리뷰보기</a></li>
      </ul>

      <div class="tab-content" id="myTabContent">
         <div class="tab-pane fade active in" id="map" role="tabpanel"
            aria-labelledby="map-tab" style="margin-top:30px;">

            <div id="map"></div>
            <div id="store_address" data-address="${udto.u_office}"></div>
            <div id="store_name" data-name="${udto.u_name}"></div>
			
         </div>

         <div class="tab-pane fade" id="info" role="tabpanel"
            aria-labelledby="info-tab" style="margin-top:30px;">
			
			<form name="frm" method="post" action="/cart/addCart.do" class="create" enctype="multipart/form-data">
            <div class="container">
                           <div>
                              <input type="date" class="" id="sdate" name="sdate"> - 
                              <input type="date" class="" id="fdate" name="fdate">
                           </div>
               <c:forEach var="dto2" items="${list2}">
               <div class="col-md-4 text-center project">
                  <img src="../../storage/${dto2.c_img}" class="img-responsive"
                  style="width: 100%; float: left;">
                     <div class="desc">
                     <input type="hidden" name="s_code" id="s_code" value="${dto2.c_code}">
                        <div class="form-group">
                           <label for="c_name">차량종류</label> 
                           <input type="text" class="form-control" id="c_name" name="c_name" value="${dto2.c_name}" readonly>
                        </div>
                        <div class="form-group">
                           <label for="c_sum">금액(1일)</label> 
                           <input type="number" class="form-control" id="c_sum" name="c_sum" value="${dto2.c_sum}" readonly>
                        </div>
                        <div class="form-group">
                           <label for="c_charge">추가요금(1시간당)</label> 
                           <input type="number" class="form-control" id="c_charge" name="c_charge" value="${dto2.c_charge}" readonly>
                        </div>
                        <div class="form-group">
                           <label for="c_reserve">예약가능 차량수</label> 
                           <input type="number" class="form-control" id="c_reserve" name="c_reserve" value="${dto2.c_reserve}" readonly>
                        </div>                     
                     </div>
                     <button type="submit">장바구니</button>
                     <input type="button" value="수정" onclick="location.href='/admin/rentalcarUpdate.do?c_code=${dto2.c_code}'">
                        <input type="button" value="삭제" onclick="location.href='/admin/rentalcarDelete.do?c_code=${dto2.c_code}'">         
               </div>
            </c:forEach>
            </div>
            </form>
			
		</div>

         <div class="tab-pane fade" id="review" role="tabpanel"
            aria-labelledby="review-tab" style="margin-top:30px;">
            <c:forEach var="dto1" items="${list}">
               <div class="col-md-4 text-center project">
                  <a href="../rentalcar/List/Read.do?c_code=${dto1.c_code}"
                     class="grid-project">
                     <div class="desc">
                        <h3>${dto1.review_content}</h3>
                        <span>${dto1.review_user_id}</span> <br> <span>${dto1.review_date}</span>
                     </div>
                  </a>
               </div>
            </c:forEach>
         </div>


      </div>

   </div>

</div>



<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=276bc655d3f8825cf97ab166b436c29b&libraries=services"></script>
<script>
   $(document)
         .ready(
               function() {

                  var storeAddress = $("#store_address").data("address");

                  var storeName = $("#store_name").data("name");

                  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 

                  mapOption = {
                     center : new kakao.maps.LatLng(33.25110701,
                           126.570667), // 지도의 중심좌표
                     level : 3
                  // 지도의 확대 레벨
                  };

                  // 지도를 생성합니다    
                  var map = new kakao.maps.Map(mapContainer, mapOption);
                  // 주소-좌표 변환 객체를 생성합니다   
                  var geocoder = new kakao.maps.services.Geocoder();

                  // 지도를 표시하는 div 크기를 변경하는 함수입니다
                  function resizeMap() {
                     mapContainer.style.width = '650px';
                     mapContainer.style.height = '650px';
                  }

                  function relayout() {

                     // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
                     // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다 
                     // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
                     map.relayout();
                  }

                  // 주소로 좌표를 검색합니다
                  geocoder
                        .addressSearch(
                              storeAddress,
                              function(result, status) {

                                 // 정상적으로 검색이 완료됐으면 
                                 if (status === kakao.maps.services.Status.OK) {

                                    var coords = new kakao.maps.LatLng(
                                          result[0].y,
                                          result[0].x);

                                    // 결과값으로 받은 위치를 마커로 표시합니다
                                    var marker = new kakao.maps.Marker(
                                          {
                                             map : map,
                                             position : coords
                                          });

                                    // 인포윈도우로 장소에 대한 설명을 표시합니다
                                    var infowindow = new kakao.maps.InfoWindow(
                                          {
                                             content : '<div style="width:200px;text-align:center;padding:3px 0;">'
                                                   + storeName
                                                   + '</div>'
                                          });
                                    infowindow.open(map, marker);

                                    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                                    map.setCenter(coords);

                                    $(".storePosition").click(
                                          function() {
                                             map.panTo(coords);
                                          })

                                 }

                              });

               })
</script>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>