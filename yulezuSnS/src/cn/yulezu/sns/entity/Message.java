package cn.yulezu.sns.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.yulezu.orm.hibernate.BaseEntity;

/**
 * 消息
 * 
 * @author 文朝军
 * 
 */
@Entity
public class Message extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "title", length = 50)
	private String title;

	@Column(name = "content", length = 200)
	private String content;

	/**
	 *发送者
	 */
	@ManyToOne
	@JoinColumn(name = "from_user")
	private Users fromUser;

	/**
	 *接收者
	 */
	@ManyToOne
	@JoinColumn(name = "to_user")
	private Users toUser;
	/**
	 * 阅读状态
	 */
	private int readed;

	/**
	 * 消息类型
	 */
	private int type;
	/**
	 * 是否优先阅读
	 */
	private int priority;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Users getFromUser() {
		return fromUser;
	}

	public void setFromUser(Users fromUser) {
		this.fromUser = fromUser;
	}

	public Users getToUser() {
		return toUser;
	}

	public void setToUser(Users toUser) {
		this.toUser = toUser;
	}

	public int getReaded() {
		return readed;
	}

	public void setReaded(int readed) {
		this.readed = readed;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
