package cn.ss.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.PhoneSeries;

public class PhoneSeriesDao extends HibernateDaoSupport {
	public void list(PageResult<PhoneSeries> pageResult,
			PhoneSeries phoneSeries, String models, int phoneOsId, int brandId) {
		Criteria criteria = this.getSession().createCriteria(PhoneSeries.class,
				"ps");
		if (phoneOsId > 0) {
			criteria.add(Expression.eq("ps.os.id", phoneOsId));
		}
		if (brandId > 0) {
			criteria.add(Expression.eq("ps.brand.id", brandId));
		}
		if (models != null && !"".equals(models.trim())) {
			models = Tool.filterString(models);
			Criteria pm = criteria.createCriteria("phoneModelList","pm");
			pm.add(Expression.like("name", models+"%"));
			pm.setProjection(Projections.projectionList().add(
					Projections.groupProperty("pm.phoneseries")));
		}
		if (pageResult != null) {
			pageResult.setRecTotal(criteria.list().size());
			criteria.setFirstResult((pageResult.getPageNo() - 1)
					* pageResult.getPageSize());
			criteria.setMaxResults(pageResult.getPageSize());
			pageResult.setList(criteria.list());
		}
	}
}
