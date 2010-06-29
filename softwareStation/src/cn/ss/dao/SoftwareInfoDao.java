package cn.ss.dao;

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
 * ���������Ϣ
 * 
 * @author �ĳ���
 * 
 */
public class SoftwareInfoDao extends HibernateDaoSupport {
	public List<SoftwareInfo> list() {// �漰���߼���ѯCriteria

		return null;
	}

	/**
	 * 
	 * 
	 * @param mid
	 * 
	 * @param plusFine
	 *            �Ӿ�
	 * @param recommend
	 *            �Ƽ�
	 * @param pageResult
	 * @return
	 */
	/**
	 * ��ѯ
	 * 
	 * @param mid
	 *            ����id
	 * @param plusFine
	 *            �Ӿ�
	 * @param recommend
	 *            �Ƽ�
	 * @param softwareTypeId
	 *            �������id
	 * @param show_java
	 *            �Ƿ����ͨ�ð�0����ʾ��1������
	 * @param pageResult
	 *            ��ҳ
	 * @return
	 */
	public List<SoftwareInfo> list(int mid, int plusFine, int recommend,
			int softwareTypeId, int show_java,
			PageResult<SoftwareInfo> pageResult) {
		Criteria criteria = this.getSession().createCriteria(
				SoftwareInfo.class, "si");
		criteria.add(Expression.eq("isShow", new Integer(1)));
		if (plusFine == 1 || plusFine == 0) {
			criteria.add(Expression.eq("si.plusFine", plusFine));
		}
		if (plusFine == 2) {
			criteria.addOrder(Order.desc("si.plusFine"));
		}
		if (recommend == 0 || recommend == 1) {
			criteria.add(Expression.eq("si.recommend", recommend));
		}
		if (recommend == 2) {
			criteria.addOrder(Order.desc("si.recommend"));
		}
		if (softwareTypeId > 0) {
			criteria.createCriteria("softwareType", "st").add(
					Expression.eq("st.id", softwareTypeId));
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
			} else {
				pm.add(Expression.eq("pm.id", mid));
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
}
