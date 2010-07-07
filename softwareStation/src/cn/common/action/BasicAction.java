package cn.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BasicAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected String uploadPath;
	protected String path;

	public void init() {
		request = ServletActionContext.getRequest();
		response = ServletActionContext.getResponse();
		uploadPath = ServletActionContext.getServletContext().getRealPath("/")
				+ "upload/";
		path = ServletActionContext.getServletContext().getRealPath("/");
	}

}
