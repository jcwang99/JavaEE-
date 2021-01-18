package work5;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class find
 */
@WebServlet("/find")
public class find extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String tel;
	private String QQ;
	private String email;
	private int startIndex;
	private int userPerpage;
	private int pageNum;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find() {
        super();
        // TODO Auto-generated constructor stub
        //Map<String,String> map=new HashMap<String,String>();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		UserDao ud=new UserDaoimp();
		int judge=0;
		int totalUsers=0;
		int totalPages=0;
		List<User>users=new ArrayList<User>();
		List<User>users2=new ArrayList<User>();
		List<User>users3=new ArrayList<User>();

		userPerpage=5;
		pageNum=1;
		startIndex=userPerpage*(pageNum-1);
		


		getServletContext().setAttribute("id", id);
		getServletContext().setAttribute("name", name);
		getServletContext().setAttribute("tel", tel);
		getServletContext().setAttribute("QQ", QQ);
		getServletContext().setAttribute("email", email);
		getServletContext().setAttribute("userPerpage",userPerpage);
		users=ud.queryUsers(id, name, tel, QQ, email,startIndex,userPerpage);
		users2=ud.queryUsers(id, name, tel, QQ, email,startIndex,userPerpage+1);
		users3=ud.queryAll(id, name, tel, QQ, email);
		if(users!=null) {
			if(users.size()==users2.size()) {
				judge=1;
			}
		}
		if(users3!=null) {
		      totalPages = users3.size() % userPerpage == 0 ? users3.size() / userPerpage : users3.size() / userPerpage + 1;
		      totalUsers=users3.size();
		     if(pageNum>totalPages)
					pageNum=totalPages;
		}

		
		request.setAttribute("users", users);
		request.setAttribute("pagenum", pageNum);
		request.setAttribute("judge", judge);
		getServletContext().setAttribute("totalPages",totalPages);
		getServletContext().setAttribute("totalUsers", totalUsers);
		request.setAttribute("totalPages", totalPages);
		request.getRequestDispatcher("/view.jsp").forward(request, response);
		/*for(int i=0;i<users.size();i++){
			u=users.get(i);
			System.out.println(u.getId());
		}*/

		
		
		
		
		
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
		if(id=="")
			id="-1";
		name=request.getParameter("name");
		if(name=="")
			name="-1";
		tel=request.getParameter("tel");
		if(tel=="")
			tel="-1";
		QQ=request.getParameter("QQ");
		if(QQ=="")
			QQ="-1";
		email=request.getParameter("email");
		if(email=="")
			email="-1";
	}

}
