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
    <div class="title">알림</div>
    <div class="content">
    	<dl>
    		<dd>${msg != null ? img : ""} ${msg}</dd>
    	</dl>
    	<p>
    		${link1}
    		${link2}
    	</p>
    </div>

</body>
</html> 