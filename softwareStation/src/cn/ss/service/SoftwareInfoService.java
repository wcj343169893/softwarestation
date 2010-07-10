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
		softwareInfoDao.list(pageResult, softwareInfo, beginTime, endTime,
				name, oi, od, softwareTypeId, producer, promotionWay);
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
		return softwareInfoDao.list(mid, -1, 2, -1, isJava, pageResult, 1, 0);
	}

	public List<SoftwareInfo> findByName(String name) {
		name = Tool.filterString(name);
		String hql = "from SoftwareInfo where name='" + name + "'";
		return dao.list(hql);
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
