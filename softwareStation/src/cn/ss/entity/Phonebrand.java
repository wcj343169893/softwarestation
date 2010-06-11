package cn.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Phonebrand generated by MyEclipse Persistence Tools
 */

public class Phonebrand implements java.io.Serializable {

	// Fields

	/**
	 * id
	 */
	private Integer id;

	/**
	 * Ʒ������
	 */
	private String name;

	/**
	 * ����ʱ��
	 */
	private Date createTime;
	/**
	 *ϵ��
	 */
	private List<Phoneseries> phoneseriesList = new ArrayList<Phoneseries>();

	// Constructors

	public List<Phoneseries> getPhoneseriesList() {
		return phoneseriesList;
	}

	public void setPhoneseriesList(List<Phoneseries> phoneseriesList) {
		this.phoneseriesList = phoneseriesList;
	}

	/** default constructor */
	public Phonebrand() {
	}

	/** full constructor */
	public Phonebrand(String name, Date createTime) {
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