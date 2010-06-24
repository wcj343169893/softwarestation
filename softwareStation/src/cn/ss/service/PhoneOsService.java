package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneOs;

public class PhoneOsService extends BasicService {

	/**
	 * ��ѯ���в���ϵͳ
	 * 
	 * @param pageResult
	 * @param phoneOs
	 */
	public void findAll(PageResult<PhoneOs> pageResult, PhoneOs phoneOs) {
		StringBuffer hql = new StringBuffer("from PhoneOs po where 1=1");
		if (null != phoneOs) {
		}
		hql.append(" order by po.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ȡָ���Ĳ���ϵͳ
	 * 
	 * @param id
	 * @return
	 */
	public PhoneOs findById(int id) {
		return (PhoneOs) dao.get(PhoneOs.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneOs.class, id);
	}

	/**
	 * ����
	 * 
	 * @param phoneOs
	 */
	public void add(PhoneOs phoneos) {
		dao.add(phoneos);
	}

	/**
	 * ����
	 * 
	 * @param phoneOs
	 */
	public void update(PhoneOs phoneos) {
		dao.update(phoneos);
	}
}
