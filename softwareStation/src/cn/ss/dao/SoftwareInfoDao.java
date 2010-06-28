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
	 * ��ѯ
	 * 
	 * @param osId
	 *            ����ƽ̨id
	 * @param plusFine
	 *            �Ӿ�
	 * @param recommend
	 *            �Ƽ�
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
