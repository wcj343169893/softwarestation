package cn.ss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.ss.entity.User;

/**
 * Servlet Filter implementation class RightFilter
 */
public class RightFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public RightFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest r = (HttpServletRequest) request;
		HttpServletResponse rs = (HttpServletResponse) response;
		String request_uri = r.getRequestURI();
		String ctx_path = r.getContextPath();
		User user = (User) r.getSession().getAttribute("users");
		System.out.println("过滤一下...");
		if (request_uri.substring(ctx_path.length()).equals("login.jsp ")) {
			return;
		}
		if (user == null) {
			System.out.println("用户没有登录");
			rs.sendRedirect("user!out.action");
		}
		System.out.println("用户已登录");
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
