package cn.yulezu.sns.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.yulezu.orm.hibernate.BaseEntity;

@Entity
public class Friends extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "users_friends_id")
	private Users friend;
	@Column(name = "nameTemp", length = 10)
	private String nameTemp;
	//
	// @ManyToOne
	// @JoinColumn(name = "users_id")
	// private Users users;

	@ManyToOne
	@JoinColumn(name = "friendgroup_id")
	private Friendgroup friendgroup;

	public Users getFriend() {
		return friend;
	}

	public void setFriend(Users friend) {
		this.friend = friend;
	}

	public String getNameTemp() {
		return nameTemp;
	}

	public void setNameTemp(String nameTemp) {
		this.nameTemp = nameTemp;
	}

	public Friendgroup getFriendgroup() {
		return friendgroup;
	}

	public void setFriendgroup(Friendgroup friendgroup) {
		this.friendgroup = friendgroup;
	}

}
