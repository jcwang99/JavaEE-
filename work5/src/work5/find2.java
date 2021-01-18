package work5;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class find2
 */
@WebServlet("/find2")
public class find2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pagenum;
	private String id;
	private String name;
	private String tel;
	private String QQ;
	private String email;
	private int startIndex;
	private int userPerpage=5;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=response.getWriter();
		int judge=0;
		int totalPages=0;
		int totalUsers=0;
		List<User>users=new ArrayList<User>();
		List<User>users2=new ArrayList<User>();
		UserDao ud=new UserDaoimp();
		id=(String) getServletContext().getAttribute("id");
		name=(String) getServletContext().getAttribute("name");
		tel=(String) getServletContext().getAttribute("tel");
		QQ=(String) getServletContext().getAttribute("QQ");
		email=(String) getServletContext().getAttribute("email");
		totalUsers=(int) getServletContext().getAttribute("totalUsers");
		totalPages = totalUsers % userPerpage == 0 ? totalUsers / userPerpage : totalUsers / userPerpage + 1;
		
		startIndex=userPerpage*(pagenum-1);
		
		users=ud.queryUsers(id, name, tel, QQ, email,startIndex,userPerpage);
		users2=ud.queryUsers(id, name, tel, QQ, email,startIndex,userPerpage+1);
		if(users!=null) {
			if(users.size()==users2.size()) {
				judge=1;
			}
		}
		request.setAttribute("users", users);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("judge", judge);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("/view.jsp").forward(request, response);
		
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
		String s=request.getParameter("pagenum");
		pagenum=Integer.valueOf(s);
		s=request.getParameter("userPerpage");
		if(s!=null) {
		     userPerpage=Integer.valueOf(s);
		     pagenum=1;
		}
	}

}
