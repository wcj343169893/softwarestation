package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.Phoneos;

public class PhoneosService extends BasicService {

	/**
	 * 查询所有操作系统
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
	 * 获取指定的操作系统
	 * 
	 * @param id
	 * @return
	 */
	public Phoneos findById(int id) {
		return (Phoneos) dao.get(Phoneos.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Phoneos.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param dict
	 */
	public void add(Phoneos phoneos) {
		dao.add(phoneos);
	}

	/**
	 * 更新
	 * 
	 * @param dict
	 */
	public void update(Phoneos phoneos) {
		dao.update(phoneos);
	}
}
