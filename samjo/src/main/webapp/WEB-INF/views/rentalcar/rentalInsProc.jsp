<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="ssi.jsp" %> 
<%@ include file="../header.jsp" %>    
<!-- rentalInsProc.jsp 본문시작 -->
<%
SimpleDateFormat sdf = new SimpleDateFormat("yyyy. MM. dd. a HH:mm:ss");	
	//사용자가 입력 요청한 정보를 가져오기
	String c_code		=request.getParameter("c_code").trim();
	String c_kind		=request.getParameter("c_kind").trim();
	String c_name		=request.getParameter("c_name").trim();
	int c_sum			=Integer.parseInt(request.getParameter("c_sum").trim());
	int c_charge		=Integer.parseInt(request.getParameter("c_charge").trim());
	int c_reserve		=Integer.parseInt(request.getParameter("c_reserve").trim());
	String c_img		=request.getParameter("c_img").trim();
	String c_cont		=request.getParameter("c_cont").trim();
	String review_user_id	=request.getParameter("review_user_id").trim();
	String review_content 	=request.getParameter("review_content").trim();
	String review_date		=request.getParameter("review_date").trim();
	

	//dto객체에 담기
	dto.setC_code(c_code);
	dto.setC_kind(c_kind);
	dto.setC_name(c_name);
	dto.setC_sum(c_sum);
	dto.setC_charge(c_charge);
	dto.setC_reserve(c_reserve);
	dto.setC_img(c_img);
	dto.setC_cont(c_cont);
	dto.setReview_user_id(review_user_id);
	dto.setReview_content(review_content);
	dto.setReview_date(review_date);
	
	int cnt=dao.create(dto); 
    if(cnt==0){
        out.println("<p>렌트카 업체 추가 실패했습니다</p>");
        out.println("<p><a href='javascript:history.back()'>[다시시도]</a></p>");
    }else{
        out.println("<script>");
        out.println("    alert('렌트카 업체가 추가되었습니다');");
        out.println("    location.href='rentalcar/List.do';");//목록페이지 이동
        out.println("</script>");
    }//if end
%>
<!-- 본문끝 -->
<%@ include file="../footer.jsp" %>    