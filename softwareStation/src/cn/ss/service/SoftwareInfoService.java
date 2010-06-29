package cn.ss.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.dao.SoftwareInfoDao;
import cn.ss.entity.SoftwareInfo;

public class SoftwareInfoService extends BasicService {
	private SoftwareInfoDao softwareInfoDao;

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
		StringBuffer hql = new StringBuffer("FROM SoftwareInfo si where 1=1 ");
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
		if (promotionWay == 3) {
			hql.append(" and si.promotionWay = 0");
		}

		// ����softwareinfo����ĵ�������أ�����ܼ� ��ʱδʵ��

		hql.append(" ORDER BY " + getOrderString(oi));
		hql.append(od == 0 ? " desc" : " asc");
		dao.listByPage(hql.toString(), pageResult);
	}

	public String getOrderString(int oi) {
		String str = "";
		// ����һ�����������
		if (oi > -1) {
			switch (oi) {
			case 0:
				str = "si.id";
				break;
			case 1:// ���
				str = "cl.number";
				break;
			case 2:// ����
				str = "dl.number";
				break;
			case 3:// ����
				str = "al.number";
				break;
			case 4:// ����(Ԫ)
				str = "al.price";
				break;
			case 5:// ����(Ԫ)
				str = "al.number*al.price";
				break;
			case 6:// ����/���
				str = "dl.number/cl.number";
				break;
			case 7:// ����/����
				str = "al.number/dl.number";
				break;
			case 8:// ����/���
				str = "al.number/cl.number";
				break;
			case 9:// ����/���
				str = "al.number*al.price/cl.number";
				break;
			case 10:// �Ƿ����
				str = "si.promotionWay";
				break;
			case 11:// �Ƿ�Ӿ�(���� ǰ̨��ѯ)
				str = "si.recommend desc , si.promotionWay";
				break;
			default:
				break;
			}
		}
		return str;
	}

	/**
	 * ��ѯ(��������wap)
	 * 
	 * @param mid
	 *            ����id ����Ϊ0:û�����û���
	 * @param plusFine
	 *            �Ӿ�0:��,1:��
	 * @param recommend
	 *            �Ƽ�0:��,1:��
	 * @return
	 */
	public List<SoftwareInfo> findAll(int mid, int plusFine, int recommend) {
		// List<SoftwareInfo> softwareInfoList = new ArrayList<SoftwareInfo>();
		// StringBuffer hql = new StringBuffer(
		// "FROM SoftwareInfo si where 1=1 and si.isShow=1 ");
		// if (mid > 0) {// ����id-->ƽ̨
		// hql.append("");
		// }
		// if (plusFine > 0) {
		// hql.append("and si.plusFine=" + plusFine);
		// }
		// if (recommend > 0) {
		// hql.append("and si.recommend=" + recommend);
		// }
		// if (date != null) {
		// hql
		// .append("and DATE_FORMAT(si.createTime ,'%Y %c %e') = DATE_FORMAT('"
		// + Tool.dateFormatString(date, "yyyy-MM-dd")
		// + "','%Y %c %e')");
		// }
		// softwareInfoDao.list(osId, plusFine, recommend, pageResult);
		// softwareInfoList = dao.list(hql.toString());
		return softwareInfoDao.list(mid, plusFine, recommend, 0, 0, null);
		// return softwareInfoList;
	}

	/**
	 * ǰ̨�����ҳ��ʾ
	 * 
	 * @param pageResult
	 * @param mid
	 *            �ֻ�����
	 * @param stid
	 *            ����id
	 * @param ty
	 *            �Ƿ����ͨ�ð�0����ʾ��1������
	 */
	public List<SoftwareInfo> findAll(PageResult<SoftwareInfo> pageResult,
			int mid, int stid, int isJava, int recommend) {
		// StringBuffer hql = new StringBuffer(
		// "FROM SoftwareInfo si where 1=1 and si.isShow=1 ");
		// if (stid > 0) {
		// hql.append(" and si.softwareType.id=" + stid);
		// }
		// if (mid > 0) {
		// // hql.append(" and si.softwareType.id=" + stid);
		// }
		// if (ty > 0) {
		// // hql.append(" and si.softwareType.id=" + stid);
		// }
		// if (commend > 0) {
		// hql.append(" order by si.recommend desc");
		// }
		// dao.listByPage(hql.toString(), pageResult);
		return softwareInfoDao.list(mid, -1, recommend, stid, isJava, pageResult);
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

	public SoftwareInfoDao getSoftwareInfoDao() {
		return softwareInfoDao;
	}

	public void setSoftwareInfoDao(SoftwareInfoDao softwareInfoDao) {
		this.softwareInfoDao = softwareInfoDao;
	}

}
