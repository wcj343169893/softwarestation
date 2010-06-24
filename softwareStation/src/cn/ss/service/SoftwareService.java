package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.Software;

public class SoftwareService extends BasicService {
	/**
	 * 查询所有
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
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public Software findById(int id) {
		return (Software) dao.get(Software.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Software.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param software
	 */
	public void add(Software software) {
		dao.add(software);
	}

	/**
	 * 更新
	 * 
	 * @param software
	 */
	public void update(Software software) {
		dao.update(software);
	}
}
