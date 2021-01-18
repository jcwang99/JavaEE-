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
 * Servlet implementation class find4
 */
@WebServlet("/find4")
public class find4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pagenum;
	private int startIndex;
	private int userPerpage=5;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		int judge=0;
		int totalPages=0;
		int totalUsers=0;
		List<User>users=new ArrayList<User>();
		List<User>users2=new ArrayList<User>();
		UserDao ud=new UserDaoimp();
		totalUsers=(int) getServletContext().getAttribute("totalUsers");
		totalPages = totalUsers % userPerpage == 0 ? totalUsers / userPerpage : totalUsers / userPerpage + 1;
		
		startIndex=userPerpage*(pagenum-1);
		
		users=ud.querysomeTempUsers(startIndex, userPerpage);
		users2=ud.querysomeTempUsers(startIndex, userPerpage+1);
		if(users!=null) {
			if(users.size()==users2.size()) {
				judge=1;
			}
		}
		request.setAttribute("users", users);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("judge", judge);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("/view2.jsp").forward(request, response);
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
