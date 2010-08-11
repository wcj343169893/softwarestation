package cn.yulezu.sns.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.yulezu.orm.hibernate.BaseEntity;

/**
 * 登录以及加密字符串
 * 
 * @insertTime 创建sid加密字符串的时间
 * 
 * @lastUpdateTime 最后登录时间
 */
@Entity
public class UserOnline extends BaseEntity {
	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users users;
	/**
	 * 用户名和密码，时间的加密字符串,中间用|隔开
	 */
	private String sid;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

}
