<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- ssi.jsp 공통코드--%>

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<%@ page import="kr.co.samjo.utility.*"%>
<%@ page import="kr.co.samjo.member.*"%>

<jsp:useBean id="dao" class="kr.co.samjo.member.MemberDAO" scope="page"></jsp:useBean>
<jsp:useBean id="dto" class="kr.co.samjo.member.MemberDTO" scope="page"></jsp:useBean>

<%request.setCharacterEncoding("UTF-8");%>
