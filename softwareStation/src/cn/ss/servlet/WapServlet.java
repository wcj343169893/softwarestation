package cn.ss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.common.util.Tool;
import java.lang.reflect.Method;

public class WapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/*
	 * @see 75f54963ba为请求地址 中间参数用g隔开,允许请求时带param
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		String html = uri.substring(uri.lastIndexOf("/"));
		int index = html.indexOf(".jsp");
		html = index == -1 ? html : html.substring(0, index);// 如果请求中没有.jsp则直接请求
		if (html.length() > 10) {
			String className_md = html.substring(0, 10);
			String[] param = null;
			param = html.substring(9).split("g");
			// 获取类名list detail
			String className = Tool.read(className_md);
			System.out.println("className:" + className);
			try {
				Class clazz = Class.forName(Tool.packages + className);
				Method[] methods = clazz.getDeclaredMethods();
				for (int i = 0; i < methods.length; i++) {
					System.out.println("methodsName:" + methods[i].getName());
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("请求地址有误!");
		}
	}

}
