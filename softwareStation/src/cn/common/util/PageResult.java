package cn.common.util;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PageResult<E> {
	private String orderBy = "";
	private String sort = "asc";
	private List<E> list = new ArrayList<E>(); // ��ѯ���
	private int pageNo = 1; // ʵ��ҳ��
	private int pageSize = 10; // ÿҳ��¼��
	private int recTotal = 0; // �ܼ�¼��

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
				.getPageTotal() : this.getPageNo());// �ж�ҳ����û�д������ҳ��
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

}
