package work5;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class normalLog
 */
@WebServlet("/normalLog")
public class normalLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String id;
	String password;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public normalLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=response.getWriter();
		doPost(request, response);
		//adminDaoimp ad=new adminDaoimp();
		//admin a=new admin();
		UserDao ud=new UserDaoimp();
		User u=new User();
		u=ud.logUser(id, password);
		if(u==null) {
			out.write("用户名/密码错误！请重新登录！");
			response.setHeader("refresh","2;/work5/normal-log.jsp"); 
			//request.getRequestDispatcher("/log.jsp").forward(request, response);
		}
		else {
			out.write("登陆成功！");
			HttpSession session=request.getSession();
			session.setAttribute("login", id);
			session.setAttribute("nalogin", u.getName());
		    response.setHeader("refresh","2;/work5/user/search.jsp");
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
		id=request.getParameter("id");
		password=request.getParameter("password");
	}

}
