<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>msgView.jsp</title>
    <style> 
      *{ font-family: gulim; font-size: 24px; } 
    </style> 
    <link href="../css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
    <script type="text/javascript">
    	alert('수정되었습니다.');
    	location.href='boardread.do?bbs_idx=${dto.bbs_idx}';
    </script>

</body>
</html> 