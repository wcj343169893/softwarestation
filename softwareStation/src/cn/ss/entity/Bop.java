package cn.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 每日账单
 * 
 * @author 文朝军
 * 
 */
public class Bop implements java.io.Serializable {

	private Integer id;
	private Date createtime;
	private Date modifyTime;
	private Integer isvisible;
	private List<Account> accountList = new ArrayList<Account>();
	private List<ActiveLog> activeLogList = new ArrayList<ActiveLog>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getIsvisible() {
		return isvisible;
	}

	public void setIsvisible(Integer isvisible) {
		this.isvisible = isvisible;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public List<ActiveLog> getActiveLogList() {
		return activeLogList;
	}

	public void setActiveLogList(List<ActiveLog> activeLogList) {
		this.activeLogList = activeLogList;
	}

}
