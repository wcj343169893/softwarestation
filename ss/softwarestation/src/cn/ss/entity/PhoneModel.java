package cn.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Phonemodel generated by MyEclipse Persistence Tools
 */

public class PhoneModel implements java.io.Serializable {

	// Fields

	/**
	 *id
	 */
	private Integer id;

	/**
	 *���� ����
	 */
	private String name;

	/**
	 * ϵ��
	 */
	private PhoneSeries phoneseries;
	/**
	 * Ʒ��
	private PhoneBrand phonebrand;
	 */
	/**
	 * ����ʱ��
	 */
	private Date createTime;

	// Constructors

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

	public PhoneSeries getPhoneseries() {
		return phoneseries;
	}

	public void setPhoneseries(PhoneSeries phoneseries) {
		this.phoneseries = phoneseries;
	}


	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}