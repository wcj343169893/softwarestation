package cn.ss.entity;

// Generated 2010-6-21 8:51:34 by Hibernate Tools 3.2.4.GA

import java.util.Date;

/**
 * Clicklog generated by hbm2java
 */
public class ClickLog implements java.io.Serializable {

	private Integer id;
	private SoftwareInfo softwareInfo;
	private Date clickTime;
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getClickTime() {
		return this.clickTime;
	}

	public SoftwareInfo getSoftwareInfo() {
		return softwareInfo;
	}

	public void setSoftwareInfo(SoftwareInfo softwareInfo) {
		this.softwareInfo = softwareInfo;
	}

	public void setClickTime(Date clickTime) {
		this.clickTime = clickTime;
	}

}
