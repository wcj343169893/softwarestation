package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.Extension;

public class ExtensionService extends BasicService {

	/**
	 * 分页查询所有
	 * 
	 * @param pageResult
	 * @param phoneOs
	 */
	public void findAll(PageResult<Extension> pageResult, Extension extension) {
		StringBuffer hql = new StringBuffer("from Extension e where 1=1");
		if (null != extension) {
		}
		hql.append(" order by e.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 查询所有
	 * 
	 * @param extension
	 */
	public List<Extension> findAll(Extension extension) {
		StringBuffer hql = new StringBuffer("from Extension e where 1=1");
		if (null != extension) {
		}
		hql.append(" order by e.id desc");
		return dao.list(hql.toString());
	}

	/**
	 * 获取指定的
	 * 
	 * @param id
	 * @return
	 */
	public Extension findById(int id) {
		return (Extension) dao.get(Extension.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Extension.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param extension
	 */
	public void add(Extension extension) {
		dao.add(extension);
	}

	/**
	 * 更新
	 * 
	 * @param extension
	 */
	public void update(Extension extension) {
		dao.update(extension);
	}
}
