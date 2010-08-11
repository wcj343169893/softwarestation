package cn.yulezu.sns.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import cn.yulezu.orm.hibernate.BaseEntity;

@Entity
public class Module extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 菜单标题
	 */
	@Column(name = "title", length = 50)
	private String title;
	/**
	 * 菜单描述（暂时还未用到）
	 */
	@Column(name = "descn", length = 200)
	private String descn;
	/**
	 * 菜单图标
	 */
	@Column(name = "icon", length = 10)
	private String icon;
	/**
	 * 连接
	 */
	@Column(name = "path", length = 200)
	private String path;
	@ManyToMany(mappedBy = "modules")
	private List<Administator> administators;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Module parent;// 父节点

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("id")
	private List<Module> children;// 子节点

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public List<Module> getChildren() {
		return children;
	}

	public void setChildren(List<Module> children) {
		this.children = children;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescn() {
		return descn;
	}

	public void setDescn(String descn) {
		this.descn = descn;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Administator> getAdministators() {
		return administators;
	}

	public void setAdministators(List<Administator> administators) {
		this.administators = administators;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}
