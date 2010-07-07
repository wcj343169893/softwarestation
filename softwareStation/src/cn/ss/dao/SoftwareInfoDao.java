package cn.ss.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.common.util.PageResult;
import cn.ss.entity.SoftwareInfo;

/**
 * 处理软件信息
 * 
 * @author 文朝军
 * 
 */
public class SoftwareInfoDao extends HibernateDaoSupport {
	public List<SoftwareInfo> list() {// 涉及到高级查询Criteria

		return null;
	}

	/**
	 * 查询
	 * 
	 * @param mid
	 *            机型id
	 * @param plusFine
	 *            加精
	 * @param recommend
	 *            推荐
	 * @param softwareTypeId
	 *            软件类型id
	 * @param show_java
	 *            是否过滤通用版0：显示，1：过滤
	 * @param pageResult
	 *            分页
	 * @param byDate
	 *            1：先按时间排序，2,后按照时间排序,其他则不按照时间排序
	 * @return
	 */
	public List<SoftwareInfo> list(int mid, int plusFine, int recommend,
			int softwareTypeId, int show_java,
			PageResult<SoftwareInfo> pageResult, int byDate,int tops) {
		Criteria criteria = this.getSession().createCriteria(
				SoftwareInfo.class, "si");
		criteria.add(Expression.eq("isShow", new Integer(1)));
		if (tops==1) {
			criteria.add(Expression.eq("tops", tops));
			criteria.setMaxResults(20);
		}
		if (byDate == 1) {
			criteria.addOrder(Order.desc("si.createTime"));
		}
		if (plusFine == 1 || plusFine == 0) {
			criteria.add(Expression.eq("si.plusFine", plusFine));
		}
		if (plusFine == 2) {
			criteria.addOrder(Order.desc("si.plusFine"));
		}
		if (recommend == 0 || recommend == 1) {
			criteria.add(Expression.eq("si.recommend", recommend));
			criteria.addOrder(Order.desc("si.createTime"));
		}
		if (recommend == 2) {
			criteria.addOrder(Order.desc("si.recommend"));
		}
		if (softwareTypeId > 0) {
			criteria.createCriteria("softwareType", "st").add(
					Expression.eq("st.id", softwareTypeId));
		}
		if (byDate == 2) {
			criteria.addOrder(Order.desc("si.createTime"));
		}
		if (mid > 0) {
			Criteria cSoftware = criteria.createCriteria("softwareList", "s");
			cSoftware.setProjection(Projections.projectionList().add(
					Projections.groupProperty("s.softwareInfo")));
			Criteria cOs = cSoftware.createCriteria("phoneOsList");
			Criteria ps = cOs.createCriteria("phoneseriesList", "ps");
			Criteria pm = ps.createCriteria("phoneModelList", "pm");
			if (show_java == 0) {
				Criteria extenssions = cOs.createCriteria("extensionList",
						"ext");
				pm.add(Restrictions.or(Expression.eq("pm.id", mid), Expression
						.eq("ext.name", ".jar")));
			} else {// 过滤通用版
				pm.add(Expression.eq("pm.id", mid));
			}
		} else {
			if (show_java > 0) {// 过滤通用版
				Criteria cSoftware = criteria.createCriteria("softwareList",
						"s");
				cSoftware.setProjection(Projections.projectionList().add(
						Projections.groupProperty("s.softwareInfo")));
				Criteria cOs = cSoftware.createCriteria("phoneOsList");
				Criteria extenssions = cOs.createCriteria("extensionList",
						"ext");
				cSoftware
						.add(Expression.not(Expression.eq("ext.name", ".jar")));
			}
		}
		if (pageResult != null) {
			pageResult.setRecTotal(criteria.list().size());
			criteria.setFirstResult((pageResult.getPageNo() - 1)
					* pageResult.getPageSize());
			criteria.setMaxResults(pageResult.getPageSize());
			pageResult.setList(criteria.list());
		}
		List<SoftwareInfo> list = criteria.list();
		return list;
	}

	/**
	 * 专门处理排行
	 * 
	 * @param mid
	 * @param ranks
	 * @param show_java
	 * @param pageResult
	 * @return
	 */
	public void list(int mid, int ranks, int show_java,
			PageResult<SoftwareInfo> pageResult) {
		SimpleDateFormat sdf_moth = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Criteria si = this.getSession().createCriteria(SoftwareInfo.class);
		Criteria dl = si.createCriteria("downloadLogList", "dl");
		si.addOrder(Order.desc("recommend"));
		si.add(Expression.eq("isShow", new Integer(1)));
		dl.setProjection(Projections.projectionList().add(
				Projections.sum("number"), "downloads").add(
				Projections.groupProperty("dl.softwareInfo")));
		dl.addOrder(Order.desc("downloads"));
		if (ranks == 1) {
			dl
					.add(Expression
							.sql("DATE_FORMAT(downloadTime,'%y %m') BETWEEN  DATE_FORMAT('"
									+ sdf_moth.format(date)
									+ "','%y %m') AND   DATE_FORMAT('"
									+ sdf_moth.format(date) + "','%y %m')"));
		} else if (ranks == 0) {
			dl
					.add(Expression
							.sql("DATE_FORMAT(downloadTime,'%y %m %d') =  DATE_FORMAT(now(),'%y %m %d')"));
		}
		// System.out.println("list:" + si.list());
		if (mid > 0) {
			Criteria cOs = si.createCriteria("softwareList").createCriteria(
					"phoneOsList");
			Criteria extenssions = cOs.createCriteria("extensionList", "ext");
			cOs.createCriteria("phoneseriesList").createCriteria(
					"phoneModelList","pm").add(
					Restrictions.or(Expression.eq("pm.id", mid), Expression.eq(
							"ext.name", ".jar")));
		}
		if (pageResult != null) {
			List list = si.list();
			pageResult.setRecTotal(list.size());
			si.setFirstResult((pageResult.getPageNo() - 1)
					* pageResult.getPageSize());
			si.setMaxResults(pageResult.getPageSize());
			pageResult.setList(list);
		}
		// List<SoftwareInfo> list = criteria.list();
	}
}
