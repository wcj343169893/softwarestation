package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.SoftwareType;

public class SoftwareTypeService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param softwareType
	 */
	public void findAll(PageResult<SoftwareType> pageResult,
			SoftwareType softwareType) {
		StringBuffer hql = new StringBuffer("from SoftwareType st where 1=1");
		if (null != softwareType) {
		}
		hql.append(" order by st.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public SoftwareType findById(int id) {
		return (SoftwareType) dao.get(SoftwareType.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(SoftwareType.class, id);
	}

	/**
	 * ����
	 * 
	 * @param softwareType
	 */
	public void add(SoftwareType softwareType) {
		dao.add(softwareType);
	}

	/**
	 * ����
	 * 
	 * @param softwareType
	 */
	public void update(SoftwareType softwareType) {
		dao.update(softwareType);
	}
}
