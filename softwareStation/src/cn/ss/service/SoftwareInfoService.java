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
		return softwareInfoDao.list(mid, plusFine, recommend, 0, 0, null, 0, 0);
	}

	/**
	 * ��ѯ�ö����
	 * 
	 * @return
	 */
	public List<SoftwareInfo> findAll(int mid) {
		return softwareInfoDao.list(mid, -1, -1, 0, 0, null, 0, 1);
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
		return softwareInfoDao.list(mid, -1, recommend, stid, isJava,
				pageResult, 0, 0);
	}

	/**
	 * ��ѯ���¸���
	 * 
	 * @param pageResult
	 * @param mid
	 * @param isJava
	 * @return
	 */
	public List<SoftwareInfo> findAll(PageResult<SoftwareInfo> pageResult,
			int mid, int isJava) {
		return softwareInfoDao.list(mid, -1, 2, -1, isJava, pageResult, 2, 0);
	}

	/**
	 * ����
	 * 
	 * @param pageResult
	 * @param mid
	 * @param isJava
	 * @param ranks
	 * @return
	 */
	public void findByRank(PageResult<SoftwareInfo> pageResult, int mid,
			int isJava, int ranks) {
		softwareInfoDao.list(mid, ranks, isJava, pageResult);
	}

	/**
	 * ��ѯ�Ƽ�
	 * 
	 * @param pageResult
	 * @param mid
	 * @param isJava
	 * @param commend
	 * @return
	 */
	public List<SoftwareInfo> findAll(PageResult<SoftwareInfo> pageResult,
			int mid, int isJava, int commend) {
		return softwareInfoDao.list(mid, -1, commend, -1, isJava, pageResult,
				0, 0);
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
