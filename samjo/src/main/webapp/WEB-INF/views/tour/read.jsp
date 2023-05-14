<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 list.jsp -->
<aside id="fh5co-hero-T">
	<div class="flexslider">
		<div class="container">
			<div class="slider-text-inner desc">
				<h2
					style="margin-top: 300px; text-align: center; font-weight: bold;"
					class="heading-section">${dto.t_name}</h2>
			</div>
		</div>
		<hr>
	</div>
</aside>


<div class="row-read">
	<div class="col-md-12 text-center project">
		<div class="grid-project">
			<div class="image-T">
				<img src="../../storage/${dto.t_img}" class="img-responsive"
					style="width: 40%; float: left;">
				<div class="desc-T">
					<ul>
						<strong>기본정보</strong>
						<hr>
						<li>주소&emsp;&emsp;&emsp;${dto.t_addr}</li>
						<c:if test="${dto.t_tel != null}">
							<li>전화번호&nbsp;&nbsp;&nbsp;&nbsp;${dto.t_tel}</li>
						</c:if>
						<c:if test="${dto.t_link != null}">
							<li>홈페이지&nbsp;&nbsp;&nbsp;&nbsp;
							<a class="textLink" href="${dto.t_link}" target="_blank">${dto.t_link}</a>
							</li>
						</c:if>
						<c:if test="${dto.t_car != null}">
							<li>주차&emsp;&emsp;&emsp;${dto.t_car}</li>
						</c:if>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class="col-md-12" id="tab-menu">
		<ul class="nav nav-tabs" id="myTab" role="tablist">
			<li class="col-md-6 nav-item"><a class="nav-link active"
				id="map-tab" data-toggle="tab" href="#map" role="tab"
				aria-controls="map" aria-selected="true">지도에서 보기</a></li>
			<li class="col-md-6 nav-item"><a class="nav-link" id="info-tab"
				data-toggle="tab" href="#info" role="tab" aria-controls="info"
				aria-selected="false">상세정보</a></li>
		</ul>

		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade active in" id="map" role="tabpanel"
				aria-labelledby="map-tab" style="margin-top: 30px;">

				<div id="map"></div>
				<div id="store_address" data-address="${dto.t_addr}"></div>
				<div id="store_name" data-name="${dto.t_name}"></div>

			</div>

			<div class="tab-pane fade" id="info" role="tabpanel"
				aria-labelledby="info-tab" style="margin-top: 30px;">
				<div style="white-space: pre; overflow: auto;">${dto.t_cont}</div>
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
						// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
						var mapTypeControl = new kakao.maps.MapTypeControl();

						// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
						// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
						map.addControl(mapTypeControl,
								kakao.maps.ControlPosition.TOPRIGHT);

						// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
						var zoomControl = new kakao.maps.ZoomControl();
						map.addControl(zoomControl,
								kakao.maps.ControlPosition.RIGHT);

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