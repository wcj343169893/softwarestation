package cn.common.dao.hibImpl;


import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.common.dao.CommonDAO;
import cn.common.util.PageResult;


@SuppressWarnings("unchecked")
public class CommonDAOHibImpl extends HibernateDaoSupport implements CommonDAO {

	public Serializable add(Object o){
		Serializable ret = super.getHibernateTemplate().save(o);
		return ret;
	}

	public Object get(Class clazz, Serializable id){
		Object ret = super.getHibernateTemplate().get(clazz, id);
		return ret;
	}

	public void del(Class clazz,Serializable id){
		super.getHibernateTemplate().delete(this.get(clazz, id));
	}

	public void update(Object o){
		super.getHibernateTemplate().update(o);		
	}

	public List list(String hql){
		List ret = super.getHibernateTemplate().find(hql);
		return ret;
	}

	
	public void listByPage(String hql, PageResult pageResult) {
		if (null==hql){
			return ;
		}
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(pageResult.getFirstRec());
		query.setMaxResults(pageResult.getPageSize());
		List ret = query.list();
		pageResult.setList(ret);

		String queryString = "";
		if (hql.toUpperCase().indexOf("SELECT") != -1) {
			int i = query.getQueryString().toUpperCase().indexOf("FROM");
			queryString = "Select count(*) " + hql.substring(i,hql.length());
		} else {
			queryString = "Select count(*) " + hql;
		}
		// 去掉ORDER BY 的部分
		int j = queryString.toUpperCase().lastIndexOf("ORDER");
		if (j!=-1){
			queryString = queryString.substring(0, j);
		}
		Query cquery = this.getSession().createQuery(queryString);
		cquery.setCacheable(true);
		int recTotal = Integer.parseInt(cquery.iterate().next().toString());		
		pageResult.setRecTotal(recTotal);
	}
	
	//执行存储过程
	@SuppressWarnings("deprecation")
	public Object execProc(String procName) {
		Object ret = null;
		//Transaction tx = this.getSession().beginTransaction();
				String procedure = "{call "+procName+"() }";
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
