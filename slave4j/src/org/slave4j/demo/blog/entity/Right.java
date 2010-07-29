package org.slave4j.demo.blog.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.slave4j.orm.hibernate.BaseEntity;

@Entity
@Table(name = "right")
public class Right extends BaseEntity {
	private String name;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "right_role", joinColumns = { @JoinColumn(name = "right_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roleList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(Set<Role> roleList) {
		this.roleList = roleList;
	}

}
