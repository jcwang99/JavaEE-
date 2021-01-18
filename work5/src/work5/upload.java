package work5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class upload
 */
@WebServlet("/upload")
public class upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String filepath=null;
    private String id=null;
    private String serpath=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public upload() {
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
		HttpSession session=request.getSession();
		id=String.valueOf(session.getAttribute("login"));
		UserDao ud=new UserDaoimp();
		FileInputStream inputStream = new FileInputStream(filepath);
		serpath="C:\\sql\\"+id+"-"+serpath;
		ud.uploadImg(id,serpath );
		FileOutputStream outputStream=new FileOutputStream(serpath);
		int num = -1;
        while((num=inputStream.read())!=-1) {
            outputStream.write(num);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
		
		File file=new File(filepath);
		boolean isok=file.exists();
		if(isok) {
			file.delete();
			System.out.println("文件已删除");
			out.write("Success");
			response.setHeader("refresh","3;/work5/user/search.jsp"); 
			//response.setHeader("refresh","0;/work4/normal-log.jsp"); 
		}else {
			    System.out.println("文件不存在");
		        out.write("FAIL");
		        response.setHeader("refresh","1;/work5/user/search.jsp"); 
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
		fileItem.write(new File("c:\\tpimg\\" + filename));
		filepath="c:\\tpimg\\" + filename;
		serpath=filename;
		}
		}
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	}

}
