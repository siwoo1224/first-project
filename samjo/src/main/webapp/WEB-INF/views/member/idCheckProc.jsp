<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="ssi.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>idCheckProc.jsp</title>
</head>
<body>

	<div style="text-align: center">
		<h3>* 아이디 중복확인 결과 *</h3>
<%
		String user_id=request.getParameter("user_id").trim();
		int cnt=dao.duplecateID(user_id);
		out.println("입력ID : <strong>" + user_id + "</strong>");
		if(cnt==0){
		    if(!(user_id.length()>=5 && user_id.length()<=10)){
%>
		        <script>alert("아이디 5~20글자이내 입력해 주세요");
		        history.back();</script>
<%
		    }//if end
		    else{
			out.println("<p>사용 가능한 아이디 입니다</p>");
			//사용 가능한 id를 부모창에 전달하기
			out.println("<a href='javascript:apply(\"" + user_id + "\")'>[적용]</a>");
		    }
%>
			<script>
				function apply(user_id) {
					//alert(id);
					//중복 확인된 id를 부모창(opener)
					opener.document.memfrm.user_id.value=user_id;
					window.close();
				}//apply() end
			</script>
<%
		}else{
			out.println("<p style='color:red'>해당 아이디는 사용할 수 없습니다</p>");
		}//if end
%>
		<hr>
		<a href="javascript:history.back()">[다시검색]</a>
		&nbsp;&nbsp;
		<a href="javascript:window.close()">[창닫기]</a>
	</div>
	
	
</body>
</html>

