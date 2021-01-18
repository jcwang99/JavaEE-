<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询页面</title>
</head>
<body>
    <div style="width:100%;text-align:center">
	<h3>查询系统 （ 支持姓名，邮箱的模糊查询）</h3>
	<hr>
	<form action="../find" method="post">
		学号:<input type="text" name="id" /><br> 姓名:<input type="text"
			name="name" /><br> 电话:<input type="text" name="tel" /><br>
		QQ:<input type="text" name="QQ" /><br> email:<input type="text"
			name="email" /><br> <input type="submit" />
	</form>
	</div>
	<div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	
	</div>
	 <div style="width:100%;text-align:center">
	<a href="../log.jsp">管理员登录</a>
	</div>
	
		</div>
	<div>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	
	
	</div>
	
		 <div style="width:100%;text-align:center">
	<a href="/work5/user/send.jsp">进入聊天室</a>
	</div>
	
	 <div style="width:100%;text-align:right">
	<a href="/work5/user/upload.jsp">照片上传</a>
	
		 <div style="width:100%;text-align:left">
	<a href="/work5/user/phsearch.jsp">照片查询</a>
	</div>
</body>
</html>