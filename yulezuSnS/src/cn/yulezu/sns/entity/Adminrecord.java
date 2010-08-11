package cn.yulezu.sns.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.yulezu.orm.hibernate.BaseEntity;

@Entity
public class Adminrecord extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 记录内容
	 */
	@Column(name = "body", length = 1000)
	private String body;
	@ManyToOne
	@JoinColumn(name = "administator_id")
	private Administator administator;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Administator getAdministator() {
		return administator;
	}

	public void setAdministator(Administator administator) {
		this.administator = administator;
	}

}
