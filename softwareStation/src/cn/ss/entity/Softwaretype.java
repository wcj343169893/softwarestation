package cn.ss.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Softwaretype generated by MyEclipse Persistence Tools
 */

public class Softwaretype implements java.io.Serializable {

	// Fields

	private Integer id;

	private String name;

	private Integer zindex;

	private Integer isShow;
	/**
	 * ������Ϣ
	 */
	private List<Softwareinfo> softwareinfoList = new ArrayList<Softwareinfo>();

	// Constructors

	/** default constructor */
	public Softwaretype() {
	}

	/** full constructor */
	public Softwaretype(String name, Integer zindex, Integer isShow) {
		this.name = name;
		this.zindex = zindex;
		this.isShow = isShow;
	}

	// Property accessors

	public List<Softwareinfo> getSoftwareinfoList() {
		return softwareinfoList;
	}

	public void setSoftwareinfoList(List<Softwareinfo> softwareinfoList) {
		this.softwareinfoList = softwareinfoList;
	}

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

	public Integer getZindex() {
		return this.zindex;
	}

	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

	public Integer getIsShow() {
		return this.isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

}