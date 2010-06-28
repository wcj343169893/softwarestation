package cn.ss.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
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
	 * @param osId
	 *            操作平台id
	 * @param plusFine
	 *            加精
	 * @param recommend
	 *            推荐
	 * @param pageResult
	 * @return
	 */
	public List<SoftwareInfo> list(int osId, int plusFine, int recommend,
			PageResult<SoftwareInfo> pageResult) {
		Criteria criteria = this.getSession().createCriteria(
				SoftwareInfo.class, "si");
		if (plusFine > 0) {
			criteria.add(Expression.eq("si.plusFine", plusFine));
		}
		if (recommend > 0) {
			criteria.add(Expression.eq("si.recommend", recommend));
		}
		if (osId > 0) {
			Criteria cSoftware = criteria.createCriteria("softwareList", "s");
			cSoftware.setProjection(Projections.projectionList().add(
					Projections.groupProperty("s.softwareInfo")));
			Criteria cOs = cSoftware.createCriteria("phoneOsList");
			cOs.add(Restrictions.eq("id", osId));
		}
		List<SoftwareInfo> list = criteria.list();
		return list;
	}
}
