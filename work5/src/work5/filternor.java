package work5;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class filternor
 */
@WebFilter("/filternor")
public class filternor implements Filter {

    /**
     * Default constructor. 
     */
    public filternor() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest hsr=(HttpServletRequest)request;
		HttpServletResponse rsp=(HttpServletResponse)response;
		rsp.setHeader("Content-Type", "text/html; charset=UTF-8");
		Writer out=rsp.getWriter();
		HttpSession session=hsr.getSession();
		Object log=session.getAttribute("login");
		if(log==null) {
			out.write("用户未登录！");
			rsp.setHeader("refresh","0;/work5/normal-log.jsp"); 
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
