package work5;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class find3
 */
@WebServlet("/find3")
public class find3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filepath=null;
	private int startIndex;
	private int userPerpage;
	private int pageNum;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public find3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		List<User> userlist=new ArrayList();
		List<User> users=new ArrayList<User>();
		List<User>users2=new ArrayList<User>();
		List<User>users3=new ArrayList<User>();
		int judge=0;
		int totalUsers=0;
		int totalPages=0;
		UserDao ud=new UserDaoimp();
		ud.deletetemp();
		userlist=ud.queryAllUsers();
		
		for(User user:userlist) {
			String orpath=user.getImg();
			if(engineST.compare(orpath, filepath)) {
				ud.addtempUser(user);
				System.out.println(user.getId());
			}
		}
		
		File file=new File(filepath);
		boolean isok=file.exists();
		if(isok) {
			file.delete();
			System.out.println("文件已删除");
			//response.setHeader("refresh","3;/work4/user/search.jsp"); 
		}else {
			    System.out.println("文件不存在");
		        //response.setHeader("refresh","1;/work4/user/search.jsp"); 
		}
		
		userPerpage=5;
		pageNum=1;
		startIndex=userPerpage*(pageNum-1);
		
		users=ud.querysomeTempUsers(startIndex, userPerpage);
		users2=ud.querysomeTempUsers(startIndex, userPerpage+1);
		users3=ud.queryTempUsers();
		
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

		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//doGet(request, response);
		//1 先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传的）
		if (ServletFileUpload.isMultipartContent(request)) {
		// 创建 FileItemFactory 工厂实现类
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		// 创建用于解析上传数据的工具类 ServletFileUpload 类
		ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
		try {
		// 解析上传的数据，得到每一个表单项 FileItem
		List<FileItem> list = servletFileUpload.parseRequest(request);
		// 循环判断，每一个表单项，是普通类型，还是上传的文件
		for (FileItem fileItem : list) {
		if (fileItem.isFormField()) {
		// 普通表单项
		System.out.println(" 表单项的 name  属性值：" + fileItem.getFieldName());
		// 参数 UTF-8. 解决乱码问题
		System.out.println(" 表单项的 value  属性值：" + fileItem.getString("UTF-8"));
		//id=fileItem.getString("UTF-8");
		
		} else {
		// 上传的文件
		System.out.println(" 表单项的 name  属性值：" + fileItem.getFieldName());
		String filename=fileItem.getName();
		int index=filename.lastIndexOf("\\");
		if(index!=-1) {
		filename=filename.substring(index+1);
		}
		System.out.println(" 上传的文件名：" + filename);
		fileItem.write(new File("c:\\comp1\\" + filename));
		filepath="c:\\comp1\\" + filename;
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	
	}

}
