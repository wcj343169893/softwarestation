package cn.ss.entity;

import java.util.Date;

/**
 * 登录日志
 * 
 * @author 文朝军
 * 
 */
public class SysLog implements java.io.Serializable {

	private static final long serialVersionUID = -2789309261012076573L;
	private int id;
	private User user;
	private String ip;
	private Date logTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
