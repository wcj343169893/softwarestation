package org.slave4j.orm;

import java.util.List;

/**
 * 页面数据
 * @author yangzhibin
 *
 */
public class PageData<T>
{
	private List<T> result;//页面数据列表
	private Pagination pagination = new Pagination();//分页
	private Compositor compositor;//排序
	private List<Filtration> filtrations;//过滤条件

	/**
	 * 获取页面数据列表.
	 */
	public List<T> getResult()
	{
		return result;
	}

	/**
	 * 设置页面数据列表.
	 */
	public void setResult(List<T> result)
	{
		this.result = result;
	}

	/**
	 * 获取分页信息.
	 */
	public Pagination getPagination()
	{
		return pagination;
	}

	/**
	 * 设置分页信息.
	 */
	public void setPagination(Pagination pagination)
	{
		this.pagination = pagination;
	}

	/**
	 * 获取排序信息.
	 */
	public Compositor getCompositor()
	{
		return compositor;
	}

	/**
	 * 设置排序信息.
	 */
	public void setCompositor(Compositor compositor)
	{
		this.compositor = compositor;
	}

	/**
	 * 获取过滤条件.
	 */
	public List<Filtration> getFiltrations()
	{
		return filtrations;
	}

	/**
	 * 设置过滤条件.
	 */
	public void setFiltrations(List<Filtration> filtrations)
	{
		this.filtrations = filtrations;
	}

	/**
	 * 添加过滤条件(该方法自定义过滤一些数据).
	 */
	public void addFiltrations(Filtration... filtrations)
	{
		for (Filtration filtration : filtrations)
		{
			this.filtrations.add(filtration);
		}
	}

	/**
	 * 设置当前页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(int pageNo)
	{
		pagination.setPageNo(pageNo);
	}

	/**
	 * 设置每页的记录数量,低于2时自动调整为20.
	 */
	public void setPageSize(int pageSize)
	{
		pagination.setPageSize(pageSize);
	}
}
