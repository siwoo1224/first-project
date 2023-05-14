<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>idCheckForm.jsp</title>
</head>
<body>

	<div style="text-align: center">
		<h3>* 아이디 중복확인 *</h3>
		<form action="idCheckProc.do" onsubmit="return blankCheck()">
			아이디 : <input type="text" name="user_id" id="user_id" maxlength="10" autofocus>
				   <input type="submit" value="중복확인">
		</form>
	</div>
	
	<script>
		function blankCheck() {
			var user_id=document.getElementById("user_id").value;
			user_id=user_id.trim();
			if(user_id.lengyh<5){
				alert("아이디는 5~10글자 이내로 입력해 주세요");
				return false;
			}//if end
			return true;
		}//blankCheck() end
	</script>

</body>
</html>

