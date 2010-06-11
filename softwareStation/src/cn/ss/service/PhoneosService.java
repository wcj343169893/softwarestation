package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.Phoneos;

public class PhoneosService extends BasicService {

	/**
	 * ��ѯ���в���ϵͳ
	 * 
	 * @param pageResult
	 * @param condition
	 */
	public void findAll(PageResult<Phoneos> pageResult, Phoneos phoneos) {
		StringBuffer hql = new StringBuffer("from phoneOs po where 1=1");
		if (null != phoneos) {
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
	public Phoneos findById(int id) {
		return (Phoneos) dao.get(Phoneos.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Phoneos.class, id);
	}

	/**
	 * ����
	 * 
	 * @param dict
	 */
	public void add(Phoneos phoneos) {
		dao.add(phoneos);
	}

	/**
	 * ����
	 * 
	 * @param dict
	 */
	public void update(Phoneos phoneos) {
		dao.update(phoneos);
	}
}
