package cn.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class PageResult<E> {
	private String orderBy = "";
	private String sort = "asc";
	private List<E> list = new ArrayList<E>(); // 查询结果
	private int pageNo = 1; // 实际页号
	private int pageSize = 10; // 每页记录数
	private int recTotal = 0; // 总记录数
	private List<Object> tmp = new ArrayList<Object>();// 备用数组
	private Map<Object, Object> temp2 = new HashMap<Object, Object>();

	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return (0 == pageSize) ? 10 : pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRecTotal() {
		return recTotal;
	}

	public void setRecTotal(int recTotal) {
		this.recTotal = recTotal;
	}

	public int getPageTotal() {
		int ret = (this.getRecTotal() - 1) / this.getPageSize() + 1;
		ret = (ret < 1) ? 1 : ret;
		return ret;
	}

	public int getFirstRec() {
		this.setPageNo(this.getPageNo() >= this.getPageTotal() ? this
				.getPageTotal() : this.getPageNo());// 判断页码有没有大于最大页数
		int ret = (this.getPageNo() - 1) * this.getPageSize();// + 1;
		ret = (ret < 1) ? 0 : ret;
		return ret;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public List<Object> getTmp() {
		return tmp;
	}

	public void setTmp(List<Object> tmp) {
		this.tmp = tmp;
	}

	public Map<Object, Object> getTemp2() {
		return temp2;
	}

	public void setTemp2(Map<Object, Object> temp2) {
		this.temp2 = temp2;
	}

}
