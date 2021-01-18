<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="work5.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>查询结果</title>
</head>
<body>
	<% List<User>users =new ArrayList<User>();
	
	User u=new User();
	users=(List<User>)request.getAttribute("users");
	String t= String.valueOf(request.getAttribute("pagenum"));
	int pagenum=Integer.valueOf(t);
	t=String.valueOf(request.getAttribute("judge"));
	int judge=Integer.valueOf(t);
	t=String.valueOf(request.getAttribute("totalPages"));
	int totalPages=Integer.valueOf(t);
	
	    %>

	    <table border="1" >
            <tr>
            <th width="5%">学号 </th>
            <th width="10%">姓名</th>
            <th width="30%">电话</th>
            <th width="30%">QQ</th>
            <th width="50%">email</th>
            </tr>
          </table>
		<% 
		for(int i=0;i<users.size();i++){
			u=users.get(i);
			%>
             <table border="1" >
             <tr>
             <td width="5%"><%=u.getId() %></td>
             <td width="10%"><%=u.getName() %></td>
             <td width="30%"><%=u.getTel() %></td>
             <td width="30%"><%=u.getQQ() %></td>
             <td width="50%"><%=u.getEmail() %></td>
             </tr>
             </table>
			<% 
		}
		%>

		<div style="width:100%;text-align:center">
		<% if(pagenum==1){
		       if(judge==0){%>
		       	             共<%=totalPages %>页
			<a href="find2?pagenum=<%= pagenum+1 %>" >下一页</a>
			<% }
			}else{
				if(judge==0){
					%>
					<a href="find2?pagenum=<%= pagenum-1 %>" >上一页</a>
					第<%=pagenum %>页
					<a href="find2?pagenum=<%= pagenum+1 %>" >下一页</a><br>
					共<%=totalPages %>页
			<%		
				}
				else{
					%>
					<a href="find2?pagenum=<%= pagenum-1 %>" >上一页</a>  
					第<%=pagenum %>页<br>
					共<%=totalPages %>页
				<% 
				}
				
			 }%>
			 
		</div>
		
		<form action="find2" method="post">
                                查询页数:<input type="text" name="pagenum" /><br>
              <input type="submit" />
	</form>
	     <form action="find2" method="post">
                                每页显示个数:<input type="text" name="userPerpage" /><br>
                 <input type="hidden" name="pagenum" value=<%= pagenum %>> <input type="submit" />
	</form>
		
</body>
</html>