package cn.ss.entity;

import java.util.Date;

public class Report {
	private int id;
	/**
	 * 错误id
	 */
	private int rid;
	/**
	 * 软件
	 */
	private SoftwareInfo softwareInfo;
	/**
	 * 机型
	 */
	private PhoneModel phoneModel;
	/**
	 * 报错日期
	 */
	private Date reportTime;
	/**
	 * 附言
	 */
	private String ps;

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public SoftwareInfo getSoftwareInfo() {
		return softwareInfo;
	}

	public void setSoftwareInfo(SoftwareInfo softwareInfo) {
		this.softwareInfo = softwareInfo;
	}

	public PhoneModel getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(PhoneModel phoneModel) {
		this.phoneModel = phoneModel;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

}
