package org.slave4j.demo.blog.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.slave4j.orm.hibernate.BaseEntity;

@Entity
public class Grade extends BaseEntity {
	private String name;

	@OneToMany(mappedBy = "grade")
	private Set<User> users;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
