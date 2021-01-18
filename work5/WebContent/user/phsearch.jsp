<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>照片查询</title>
</head>
<body>
<%//String s=String.valueOf(session.getAttribute("login"));%>
<div style="width:100%;text-align:center">
	<h3>照片查询 </h3>
	<hr>
	<form action="http://localhost:8080/work5/find3" method="post" enctype="multipart/form-data">
		             选择 照片:<input type="file"
			name="photo" /><br> <input type="submit" value="上传"/>
	</form>
	</div>
</body>
</html>