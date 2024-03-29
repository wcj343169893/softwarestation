package cn.yulezu.sns.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.yulezu.orm.hibernate.BaseEntity;

// Generated 2010-7-26 16:49:25 by Hibernate Tools 3.2.4.GA

/**
 * Rank generated by hbm2java
 */
@Entity
public class Rank  implements  java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name",length=10)
	private String name;
	private String img;
	
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE,CascadeType.MERGE })
	@JoinColumn(name="grade")
	private Ranklevel ranklevel;//头衔等级
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hid")
	private Honor honor;//头衔分类
	
	public Rank() {
	}

	public Rank(String name) {
		this.name = name;

	}
	
	
	public Ranklevel getRanklevel() {
		return ranklevel;
	}

	public void setRanklevel(Ranklevel ranklevel) {
		this.ranklevel = ranklevel;
	}

	public Honor getHonor() {
		return honor;
	}

	public void setHonor(Honor honor) {
		this.honor = honor;
	}


	public Integer getId() {
		return id;
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


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}
