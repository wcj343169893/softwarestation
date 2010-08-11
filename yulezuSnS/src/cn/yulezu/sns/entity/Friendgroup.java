package cn.yulezu.sns.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.yulezu.orm.hibernate.BaseEntity;

@Entity
public class Friendgroup extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private Users users;

	@Column(name = "groupName", length = 10)
	private String groupName;

	@OneToMany(mappedBy = "friendgroup", fetch = FetchType.LAZY)
	private List<Friends> friendsList;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Friends> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<Friends> friendsList) {
		this.friendsList = friendsList;
	}

}
