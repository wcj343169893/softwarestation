package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.Software;

public class SoftwareService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param software
	 */
	public void findAll(PageResult<Software> pageResult, Software software) {
		StringBuffer hql = new StringBuffer("from Software s where 1=1");
		if (null != software) {
		}
		hql.append(" order by s.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public Software findById(int id) {
		return (Software) dao.get(Software.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Software.class, id);
	}

	/**
	 * ����
	 * 
	 * @param software
	 */
	public void add(Software software) {
		dao.add(software);
	}

	/**
	 * ����
	 * 
	 * @param software
	 */
	public void update(Software software) {
		dao.update(software);
	}
}
