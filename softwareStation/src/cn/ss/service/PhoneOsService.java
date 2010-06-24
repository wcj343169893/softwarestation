package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneOs;

public class PhoneOsService extends BasicService {

	/**
	 * 查询所有操作系统
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
	 * 获取指定的操作系统
	 * 
	 * @param id
	 * @return
	 */
	public PhoneOs findById(int id) {
		return (PhoneOs) dao.get(PhoneOs.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneOs.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param phoneOs
	 */
	public void add(PhoneOs phoneos) {
		dao.add(phoneos);
	}

	/**
	 * 更新
	 * 
	 * @param phoneOs
	 */
	public void update(PhoneOs phoneos) {
		dao.update(phoneos);
	}
}
