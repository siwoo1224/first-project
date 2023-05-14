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
					class="heading-section">숙소 수정</h2>
			</div>
		</div>
	</div>

</aside>

<div class="row">
	<div class="col-md-12">
		<form name="frm" method="post" action="update.do" class="tourcreate" enctype="multipart/form-data">
			<div class="form-group">
				<label for="s_cn"></label> 
				<input type="text" class="form-control" id="s_cn" name="s_cn" value="${dto.s_cn}" readonly>
			</div>
			<div class="form-group">
				<label for="s_name">숙소 명</label> 
				<input type="text" class="form-control" id="s_name" name="s_name" value="${dto.s_name}">
			</div>
			<div class="form-group">
				<label for="s_addr">주소</label> 
				<input type="text" class="form-control" id="s_addr" name="s_addr" value="${dto.s_addr}">
			</div>
			<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
				  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
				</div>
				
				<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
				<script>
				    // 우편번호 찾기 화면을 넣을 element
				    var element_wrap = document.getElementById('wrap');
				
				    function foldDaumPostcode() {
				        // iframe을 넣은 element를 안보이게 한다.
				        element_wrap.style.display = 'none';
				    }
				
				    function DaumPostcode() {
				        // 현재 scroll 위치를 저장해놓는다.
				        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
				        new daum.Postcode({
				            oncomplete: function(data) {
				                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
				
				                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
				                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				                var fullAddr = data.address; // 최종 주소 변수
				                var extraAddr = ''; // 조합형 주소 변수
				
				                // 기본 주소가 도로명 타입일때 조합한다.
				                if(data.addressType === 'R'){
				                    //법정동명이 있을 경우 추가한다.
				                    if(data.bname !== ''){
				                        extraAddr += data.bname;
				                    }
				                    // 건물명이 있을 경우 추가한다.
				                    if(data.buildingName !== ''){
				                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
				                    }
				                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
				                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
				                }
				
				                // 우편번호와 주소 정보를 해당 필드에 넣는다.
				                //document.getElementById('user_zipcode').value = data.zonecode; //5자리 새우편번호 사용
				                document.getElementById('s_addr').value = fullAddr;
				
				                // iframe을 넣은 element를 안보이게 한다.
				                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
				                element_wrap.style.display = 'none';
				
				                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
				                document.body.scrollTop = currentScroll;
				                
				                $('#s_addr2').focus();
				            },
				            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
				            onresize : function(size) {
				                element_wrap.style.height = size.height+'px';
				            },
				            width : '100%',
				            height : '100%'
				        }).embed(element_wrap);
				
				        // iframe을 넣은 element를 보이게 한다.
				        element_wrap.style.display = 'block';
				    }
				</script>
			<div class="form-group">
				<label for="s_addr2">나머지주소</label> 
				<input type="text" class="form-control" id="s_addr2" name="s_addr2" value="${dto.s_addr2}">
			</div>
			<div class="form-group">
				<label for="s_tel">전화번호</label> 
				<input type="text" class="form-control" id="s_tel" name="s_tel" value="${dto.s_tel}">
			</div>
			<div class="form-group">
				<label for="s_link">홈페이지 주소</label> 
				<input type="text" class="form-control" id="s_link" name="s_link" value="${dto.s_link}">
			</div>
			<div class="form-group">
				<label for="posterMF">현재 대표 이미지</label>
				<img src='../../storage/${dto.s_img}' width='20%'>
				<input type="file" class="form-control-file" id="posterMF" name="posterMF">
			</div>
			<div class="form-group">
				<label for="t_cont">상세 정보 및 내용</label>
				<textarea class="form-control" id="s_cont" name="s_cont" rows="3">${dto.s_cont}</textarea>
			</div>
			<div class='bottom'>
				<button type="submit" class="btn btn-primary">수정</button>
				<button type="button" class="btn btn-secondary" onclick="location.href='/admin/sookso/List.do'">목록</button>
			</div>
		</form>
	</div>
</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>