package cn.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Phonebrand generated by MyEclipse Persistence Tools
 */

public class PhoneBrand implements java.io.Serializable {

	// Fields

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 品牌名称
	 */
	private String name;

	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 *系列
	 */
	private List<PhoneSeries> phoneseriesList = new ArrayList<PhoneSeries>();

	// Constructors

	public List<PhoneSeries> getPhoneseriesList() {
		return phoneseriesList;
	}

	public void setPhoneseriesList(List<PhoneSeries> phoneseriesList) {
		this.phoneseriesList = phoneseriesList;
	}

	/** default constructor */
	public PhoneBrand() {
	}

	/** full constructor */
	public PhoneBrand(String name, Date createTime) {
		this.name = name;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}