package cn.ss.form;

import java.util.Date;

public class PhoneosForm {
	private Integer id;

	/**
	 *操作系统名称
	 */
	private String name;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
