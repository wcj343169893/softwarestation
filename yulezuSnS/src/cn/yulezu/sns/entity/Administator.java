package cn.yulezu.sns.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import cn.yulezu.orm.hibernate.BaseEntity;

@Entity
public class Administator extends BaseEntity {

	/**
	 * 用户名
	 */
	@Column(name = "account", length = 16)
	private String account;
	/**
	 * 密码
	 */
	@Column(name = "password", length = 16)
	private String password;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "Administator_Module", joinColumns = { @JoinColumn(name = "Administator_ID", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "Module_ID", referencedColumnName = "id") })
	private List<Module> modules;

	@OneToMany(mappedBy = "administator")
	private List<Adminrecord> adminrecords;

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Adminrecord> getAdminrecords() {
		return adminrecords;
	}

	public void setAdminrecords(List<Adminrecord> adminrecords) {
		this.adminrecords = adminrecords;
	}

	// private Users users;

}
