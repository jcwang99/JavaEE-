package work5;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class measure
 */
@WebServlet("/admin/measure")
public class measure extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String tel;
	private String QQ;
	private String email;
	private String choose;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public measure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		Writer out=response.getWriter();
		int i=Integer.valueOf(choose);
		User user=new User();
		UserDao ud=new UserDaoimp();
		user.setId(id);
		user.setName(name);
		user.setQQ(QQ);
		user.setEmail(email);
		switch(i) {
			case 1 :
				int i1=ud.addUser(user);
				if(i1!=-1) {
				out.write("添加成功！共增加"+i1+"行");
				response.setHeader("refresh","1;/work5/admin/admin.jsp"); 
				}
				else {
					out.write("添加失败！请重试");
					response.setHeader("refresh","1;/work5/admin/admin.jsp"); 
				}
				break;
			case 2:
				int i2=
				ud.deleteUserById(id);
				if(i2!=-1) {
				out.write("删除成功！共增加"+i2+"行");
				response.setHeader("refresh","1;/work5/admin/admin.jsp"); 
				}
				else {
					out.write("删除失败！请重试");
					response.setHeader("refresh","1;/work5/admin/admin.jsp"); 
				}
				break;
			case 3:
				int i3=
				ud.updateUser(user);
				if(i3!=-1) {
				out.write("更新成功！共更新"+i3+"行");
				response.setHeader("refresh","1;/work5/admin/admin.jsp"); 
				}
				else {
					out.write("更新失败！请重试");
					response.setHeader("refresh","1;/work5/admin/admin.jsp"); 
				}
				break;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Writer out=response.getWriter();
		id=request.getParameter("id");
		if(id=="") {
			id="-1";
			out.write("学号不能为空！");
			response.setHeader("refresh","2;/work5/admin/admin.jsp");
		}
			
		name=request.getParameter("name");
		if(name=="") {
			name="-1";
			out.write("姓名不能为空！");
			response.setHeader("refresh","2;/work5/admin/admin.jsp");
		}

		tel=request.getParameter("tel");
		if(tel=="")
			tel="-1";
		QQ=request.getParameter("QQ");
		if(QQ=="")
			QQ="-1";
		email=request.getParameter("email");
		if(email=="")
			email="-1";
		choose=request.getParameter("choose");
		//System.out.println(choose);
	}

}
