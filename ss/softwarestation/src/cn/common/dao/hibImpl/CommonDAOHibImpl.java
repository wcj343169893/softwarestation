package cn.common.dao.hibImpl;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.common.dao.CommonDAO;
import cn.common.util.PageResult;
import cn.ss.dto.SoftwareInfoDTO;

@SuppressWarnings("unchecked")
public class CommonDAOHibImpl extends HibernateDaoSupport implements CommonDAO {

	public Serializable add(Object o) {
		Serializable ret = super.getHibernateTemplate().save(o);
		return ret;
	}

	public Object get(Class clazz, Serializable id) {
		Object ret = super.getHibernateTemplate().get(clazz, id);
		return ret;
	}

	public void del(Class clazz, Serializable id) {
		super.getHibernateTemplate().delete(this.get(clazz, id));
	}

	public void update(Object o) {
		super.getHibernateTemplate().update(o);
	}

	public List list(String hql) {
		List ret = super.getHibernateTemplate().find(hql);
		return ret;
	}

	public void list(String sql, PageResult pageResult) {
		if (null == sql) {
			return;
		}
		String queryString = "";
		if (sql.toUpperCase().indexOf("SELECT") != -1) {
			int i = sql.toUpperCase().indexOf("FROM");
			queryString = "Select count(*) " + sql.substring(i, sql.length());
		} else {
			queryString = "Select count(*) " + sql;
		}
		// 去掉ORDER BY 的部分
		int j = queryString.toUpperCase().lastIndexOf("ORDER");
		if (j != -1) {
			queryString = queryString.substring(0, j);
		}
		Query cquery = this.getSession().createSQLQuery(queryString);
		// cquery.setCacheable(true);
		List list = cquery.list();
		Object o=null;
		if (list!=null&&list.size()>0) {
			o= list.get(0);
		}
		//int recTotal = Integer.parseInt(cquery.iterate().next().toString());
		int recTotal=Integer.parseInt(o.toString());
		pageResult.setRecTotal(recTotal);

		Query query = this.getSession().createSQLQuery(sql);
		query.setFirstResult(pageResult.getFirstRec());
		query.setMaxResults(pageResult.getPageSize());
		query.setCacheable(true);
		pageResult.setList(query.list());
	}

	public void listByPage(String hql, PageResult pageResult) {
		if (null == hql) {
			return;
		}
		String queryString = "";
		if (hql.toUpperCase().indexOf("SELECT") != -1) {
			int i = hql.toUpperCase().indexOf("FROM");
			queryString = "Select count(*) " + hql.substring(i, hql.length());
		} else {
			queryString = "Select count(*) " + hql;
		}
		// 去掉ORDER BY 的部分
		int j = queryString.toUpperCase().lastIndexOf("ORDER");
		if (j != -1) {
			queryString = queryString.substring(0, j);
		}
		Query cquery = this.getSession().createQuery(queryString);
		// cquery.setCacheable(true);
		int recTotal = Integer.parseInt(cquery.iterate().next().toString());
		pageResult.setRecTotal(recTotal);

		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(pageResult.getFirstRec());
		query.setMaxResults(pageResult.getPageSize());
		query.setCacheable(true);
		List ret = query.list();
		pageResult.setList(ret);
	}

	// 执行存储过程
	@SuppressWarnings("deprecation")
	public Object execProc(String procName) {
		Object ret = null;
		// Transaction tx = this.getSession().beginTransaction();
		String procedure = "{call " + procName + "() }";
		CallableStatement cstmt;
		try {
			Connection con = this.getSession().connection();
			cstmt = con.prepareCall(procedure);
			ret = cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
