package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.SoftwareInfo;

public class SoftwareInfoService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param softwareInfo
	 */
	public void findAll(PageResult<SoftwareInfo> pageResult,
			SoftwareInfo softwareInfo, String beginTime, String endTime,
			String name, int oi, int od, int softwareTypeId, String producer,
			int promotionWay) {
		StringBuffer hql = new StringBuffer("FROM SoftwareInfo si where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(si.createTime ,'%Y %c %e')>=DATE_FORMAT('"
							+ beginTime + "','%Y %c %e')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(si.createTime ,'%Y %c %e') <= DATE_FORMAT('"
							+ endTime + "','%Y %c %e')");
		}
		if (name != null && !"".equals(name)) {
			name = name.replace("'", "");
			hql.append(" and si.name like '%" + name + "%'");
		}
		if (softwareTypeId > 0) {
			hql.append(" and si.softwareType.id =" + softwareTypeId + "");
		}
		if (producer != null && !"".equals(producer)) {
			producer = producer.replace("'", "");
			hql.append(" and si.producer like '%" + producer + "%'");
		}
		if (promotionWay ==3) {
			hql.append(" and si.promotionWay = 0");
		}

		// ����softwareinfo����ĵ�������أ�����ܼ� ��ʱδʵ��

		hql.append(" " + getOrderString(oi));
		hql.append(od == 0 ? " desc" : " asc");
		dao.listByPage(hql.toString(), pageResult);
	}

	public String getOrderString(int oi) {
		String str = "";
		// ����һ�����������
		if (oi > -1) {
			switch (oi) {
			case 0:
				str = "ORDER BY si.id";
				break;
			case 1:// ���
				str = "ORDER BY cl.number";
				break;
			case 2:// ����
				str = "ORDER BY dl.number";
				break;
			case 3:// ����
				str = "ORDER BY al.number";
				break;
			case 4:// ����(Ԫ)
				str = "ORDER BY al.price";
				break;
			case 5:// ����(Ԫ)
				str = "ORDER BY al.number*al.price";
				break;
			case 6:// ����/���
				str = "ORDER BY dl.number/cl.number";
				break;
			case 7:// ����/����
				str = "ORDER BY al.number/dl.number";
				break;
			case 8:// ����/���
				str = "ORDER BY al.number/cl.number";
				break;
			case 9:// ����/���
				str = "ORDER BY al.number*al.price/cl.number";
				break;
			default:
				break;
			}
		}
		return str;
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public SoftwareInfo findById(int id) {
		return (SoftwareInfo) dao.get(SoftwareInfo.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(SoftwareInfo.class, id);
	}

	/**
	 * ����
	 * 
	 * @param softwareInfo
	 */
	public void add(SoftwareInfo softwareInfo) {
		dao.add(softwareInfo);
	}

	/**
	 * ����
	 * 
	 * @param softwareInfo
	 */
	public void update(SoftwareInfo softwareInfo) {
		dao.update(softwareInfo);
	}
}
