package cn.common.dao;

import java.io.Serializable;
import java.util.List;

import cn.common.util.PageResult;

@SuppressWarnings("unchecked")
public interface CommonDAO {
	public Object get(Class clazz,Serializable id);
	public Serializable add(Object o);
	public void del(Class clazz,Serializable id);
	public void update(Object o);
	public List list(String sql);
	public void listByPage(String hql, PageResult pageResult);
	public Object execProc(String procName);
}
