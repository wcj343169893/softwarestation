package cn.ss.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.common.util.PageResult;
import cn.common.util.Tool;
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
	 * @param byDate
	 *            1���Ȱ�ʱ������2,����ʱ������,�����򲻰���ʱ������
	 * @return
	 */
	public List<SoftwareInfo> list(int mid, int plusFine, int recommend,
			int softwareTypeId, int show_java,
			PageResult<SoftwareInfo> pageResult, int byDate, int tops) {
		Criteria criteria = this.getSession().createCriteria(
				SoftwareInfo.class, "si");
		criteria.add(Expression.eq("isShow", new Integer(1)));
		if (tops == 1) {
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
			} else {// ����ͨ�ð�
				pm.add(Expression.eq("pm.id", mid));
			}
		} else {
			if (show_java > 0) {// ����ͨ�ð�
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

	public void list(PageResult<SoftwareInfo> pageResult,
			SoftwareInfo softwareInfo, String beginTime, String endTime,
			String name, int oi, int od, int softwareTypeId, String producer,
			int promotionWay) {
		Criteria criteria = this.getSession().createCriteria(
				SoftwareInfo.class, "si");
		Criteria al = null;
		if (oi == 6
				|| (promotionWay == 3 && ((beginTime != null
						&& !"".equals(beginTime) && Tool.stringFormatDate(
						beginTime, "yyyy-MM-dd") != null) || (endTime != null
						&& !"".equals(endTime) && Tool.stringFormatDate(
						endTime, "yyyy-MM-dd") != null)))) {
			al = criteria.createCriteria("activeLogList", "al").setProjection(
					Projections.projectionList().add(
							Projections.groupProperty("al.softwareInfo")));
		}
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			if (promotionWay == 3) {
				al
						.add(Expression
								.sql("DATE_FORMAT(activeTime ,'%Y %c %e') >= DATE_FORMAT('"
										+ beginTime + "','%Y %c %e')"));
				// criteria.add(Restrictions.ge("al.activeTime", Tool
				// .stringFormatDate(beginTime, "yyyy-MM-dd")));// ���ڵ���
			} else {
				criteria
						.add(Expression
								.sql("DATE_FORMAT(createTime ,'%Y %c %e')>=DATE_FORMAT('"
										+ beginTime + "','%Y %c %e')"));
			}
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			if (promotionWay == 3) {
				al
						.add(Expression
								.sql("DATE_FORMAT(activeTime ,'%Y %c %e') <= DATE_FORMAT('"
										+ endTime + "','%Y %c %e')"));
				// criteria.add(Restrictions.le("al.activeTime", Tool
				// .stringFormatDate(endTime, "yyyy-MM-dd")));// С�ڵ���
			} else {
				criteria
						.add(Expression
								.sql(" DATE_FORMAT(createTime ,'%Y %c %e') <= DATE_FORMAT('"
										+ endTime + "','%Y %c %e')"));
			}
		}
		if (name != null && !"".equals(name)) {
			name = name.replace("'", "");
			criteria.add(Expression.like("si.name", name + "%"));
		}
		if (softwareTypeId > 0) {
			criteria.add(Expression.eq("si.softwareType.id", softwareTypeId));
		}
		if (producer != null && !"".equals(producer)) {
			producer = producer.replace("'", "");
			criteria.add(Expression.like("si.producer", producer + "%"));
		}
		if (promotionWay == 3) {
			criteria.add(Expression.eq("si.promotionWay", new Integer(0)));
		}
		switch (od) {
		case 0:// asc
			switch (oi) {
			case 1:// id
				criteria.addOrder(Order.asc("si.id"));
				break;
			case 2:// ����
				criteria.addOrder(Order.asc("si.name"));
				break;
			case 3:// �������
				criteria.addOrder(Order.asc("si.number"));
				break;
			case 4:// ���
				criteria.createCriteria("clickLogList", "cl").setProjection(
						Projections.projectionList().add(
								Projections.sum("cl.number"), "clNumber").add(
								Projections.groupProperty("cl.softwareInfo")));
				criteria.addOrder(Order.asc("clNumber"));
				break;
			case 5:// ����
				criteria.createCriteria("downloadLogList", "dl").setProjection(
						Projections.projectionList().add(
								Projections.sum("dl.number"), "dlNumber").add(
								Projections.groupProperty("dl.softwareInfo")));
				criteria.addOrder(Order.asc("dlNumber"));
				break;
			case 6:// ����
				criteria.setProjection(Projections.projectionList().add(
						Projections.sum("al.number"), "alNumber").add(
						Projections.groupProperty("al.softwareInfo")));
				criteria.addOrder(Order.asc("alNumber"));
				break;
			case 7:// ʱ��
				criteria.addOrder(Order.asc("si.createTime"));
				break;
			default:
				criteria.addOrder(Order.asc("si.id"));
				break;
			}
			break;
		case 1:// desc
			switch (oi) {
			case 1:// id
				criteria.addOrder(Order.desc("si.id"));
				break;
			case 2:// ����
				criteria.addOrder(Order.desc("si.name"));
				break;
			case 3:// �������
				criteria.addOrder(Order.desc("si.number"));
				break;
			case 4:// ���
				criteria.createCriteria("clickLogList", "cl").setProjection(
						Projections.projectionList().add(
								Projections.sum("cl.number"), "clNumber").add(
								Projections.groupProperty("cl.softwareInfo")));
				criteria.addOrder(Order.desc("clNumber"));
				break;
			case 5:// ����
				criteria.createCriteria("downloadLogList", "dl").setProjection(
						Projections.projectionList().add(
								Projections.sum("dl.number"), "dlNumber").add(
								Projections.groupProperty("dl.softwareInfo")));
				criteria.addOrder(Order.desc("dlNumber"));
				break;
			case 6:// ����
				criteria.setProjection(Projections.projectionList().add(
						Projections.sum("al.number"), "alNumber").add(
						Projections.groupProperty("al.softwareInfo")));
				criteria.addOrder(Order.desc("alNumber"));
				break;
			case 7:// ʱ��
				criteria.addOrder(Order.desc("si.createTime"));
				break;
			default:
				criteria.addOrder(Order.desc("si.id"));
				break;
			}
			break;
		default:
			break;
		}
		if (pageResult != null) {
			pageResult.setRecTotal(criteria.list().size());
			criteria.setFirstResult((pageResult.getPageNo() - 1)
					* pageResult.getPageSize());
			criteria.setMaxResults(pageResult.getPageSize());
			if (oi == 4 || oi == 5 || oi == 6) {
				List list = criteria.list();
				List<SoftwareInfo> softwareInfoList = new ArrayList<SoftwareInfo>();
				// Map<Object, Object> maps = new HashMap<Object, Object>();
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					// maps.put(((SoftwareInfo) obj[1]).getId(), Integer
					// .valueOf(obj[0].toString()));
					softwareInfoList.add((SoftwareInfo) obj[1]);
				}
				// pageResult.setTemp2(maps);
				pageResult.setList(softwareInfoList);
			} else {
				pageResult.setList(criteria.list());
			}
		}
	}

	/**
	 * ר�Ŵ�������
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
		// si.addOrder(Order.desc("recommend"));
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
					"phoneModelList", "pm").add(
					Restrictions.or(Expression.eq("pm.id", mid), Expression.eq(
							"ext.name", ".jar")));
		}
		if (pageResult != null) {
			List list = si.list();
			pageResult.setRecTotal(list.size());
			si.setFirstResult((pageResult.getPageNo() - 1)
					* pageResult.getPageSize());
			si.setMaxResults(pageResult.getPageSize());
			pageResult.setList(si.list());
		}
		// List<SoftwareInfo> list = criteria.list();
	}
}
