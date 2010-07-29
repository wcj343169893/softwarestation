package org.slave4j.demo.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.slave4j.orm.hibernate.BaseEntity;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {
	private String name;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "roleList", fetch = FetchType.LAZY)
	private Set<Right> rightList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Right> getRightList() {
		return rightList;
	}

	public void setRightList(Set<Right> rightList) {
		this.rightList = rightList;
	}

}
