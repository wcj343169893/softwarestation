package cn.ss.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.SysLog;
import cn.ss.entity.User;
import cn.ss.service.SysLogService;
import cn.ss.service.UserService;

public class UserAction extends BasicAction {
	private static final long serialVersionUID = -3038633850075999469L;
	private int p;
	private int id;
	private UserService userService;
	private String username;
	private String password;
	private User user;
	private PageResult<User> pageResult = new PageResult<User>();
	private SysLogService sysLogService;

	public String out() throws Exception {
		init();
		user = null;
		request.getSession().removeAttribute("users");
		return "error";
	}

	/*
	 * 登录
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {
		init();
		String error = null;
		if (username != null && !"".equals(username.trim()) && password != null
				&& !"".equals(password.trim())) {
			user = userService.findByName(username.trim());
			if (user != null) {
				if (user.getPassword().equals(password.trim())) {
					SysLog sysLog = new SysLog();
					sysLog.setUser(user);
					sysLog.setLogTime(new Date());
					sysLog.setIp(getIpAddr(request));// 获取ip
					sysLogService.add(sysLog);
					request.getSession().setAttribute("users", user);
					return "success";
				} else {
					error = "用户密码错误";
				}
			} else {
				error = "用户不存在";
			}
		}
		request.setAttribute("error", error);
		return "error";
	}

	/**
	 * 获取客户端ip
	 * 
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public String list() throws Exception {
		init();
		pageResult = new PageResult<User>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		userService.findAll(pageResult);
		return "list";
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PageResult<User> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<User> pageResult) {
		this.pageResult = pageResult;
	}

	public SysLogService getSysLogService() {
		return sysLogService;
	}

	public void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

}
