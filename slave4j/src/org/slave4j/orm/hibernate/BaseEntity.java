package org.slave4j.orm.hibernate;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * entity基类.
 * @author yangzhibin
 *
 */
@MappedSuperclass
public class BaseEntity
{
	@Id
	//主键自动生成策略：数据库自动增长
	@GeneratedValue
	//主键
	protected Integer id;
	//是否显示
	@Column(nullable=false)
	protected boolean visible = true;
	//插入时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,updatable=false)
	protected Date insertTime;
	//最后一次修改时间
	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastUpdateTime;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}

	public Date getInsertTime()
	{
		return insertTime;
	}

	public void setInsertTime(Date insertTime)
	{
		this.insertTime = insertTime;
	}

	public Date getLastUpdateTime()
	{
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 是否是新对象
	 * @return
	 */
	public boolean isNew()
	{
		return (this.id == null);
	}

}
