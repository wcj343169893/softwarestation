package cn.ss.entity;

// Generated 2010-6-21 8:51:34 by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Accounttype generated by hbm2java
 */
public class AccountType implements java.io.Serializable {

	private Integer id;
	private String name;
	private Date createTime;
	private List<Account> accountList = new ArrayList<Account>();

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
