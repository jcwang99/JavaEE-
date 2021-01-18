<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="work5.User" %>
<%@ page import="work5.UserDao" %>
<%@ page import="work5.UserDaoimp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台管理</title>
</head>
<body>

    <div style="width:100%;text-align:center">
	<h3>    添加用户</h3>
	<hr>
	<form action="measure" method="post">
		学号:<input type="text" name="id" /><br> 姓名:<input type="text"
			name="name" /><br> 电话:<input type="text" name="tel" /><br>
		QQ:<input type="text" name="QQ" /><br> email:<input type="text"
			name="email" /><br> 
			<input type="hidden" name="choose" value="1">
			<input type="submit" />
	</form>
	</div>

        <div style="width:100%;text-align:center">
	<h3>        删除用户</h3>
	<hr>
	<form action="measure" method="post">
		学号:<input type="text" name="id" /><br> 
			<input type="hidden" name="choose" value="2">
			<input type="submit" />
	</form>
	</div>

            <div style="width:100%;text-align:center">
	<h3>           更新用户</h3>
	<hr>
	<form action="measure" method="post">
		学号:<input type="text" name="id" /><br> 姓名:<input type="text"
			name="name" /><br> 电话:<input type="text" name="tel" /><br>
		QQ:<input type="text" name="QQ" /><br> email:<input type="text"
			name="email" /><br> 
			<input type="hidden" name="choose" value="3">
			<input type="submit" />
	</form>
	</div>
    
</body>
</html>