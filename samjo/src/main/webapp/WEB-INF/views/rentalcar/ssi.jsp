<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- ssi.jsp 공통코드--%>

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="net.utility.*"%>
<%@ page import="kr.co.samjo.product.rentalcar.*"%>
<%@ page import="kr.co.samjo.product.rental.*"%>

<jsp:useBean id="dao" class="kr.co.samjo.product.rentalcar.rentalcarDAO" scope="page"></jsp:useBean>    
<jsp:useBean id="dto" class="kr.co.samjo.product.rentalcar.rentalcarDTO" scope="page"></jsp:useBean>


<%request.setCharacterEncoding("UTF-8");%>


<%
	//검색(if문으로 쓰면 계속 넣어줘야되서 고정)-----------------
	String word=request.getParameter("word");	//검색어
	String col=request.getParameter("col");		//검색칼럼
	//word=Utility.checkNull(word);	
	//문자열값이 null이면 빈문자열로 치환
	//col =Utility.checkNull(col);	
	
	//현재페이지--------------------------------------------
	int nowPage=1;
	if(request.getParameter("nowPage")!=null){
		nowPage=Integer.parseInt(request.getParameter("nowPage"));
	}//if end
%>