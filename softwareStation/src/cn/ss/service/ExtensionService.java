package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.Extension;

public class ExtensionService extends BasicService {

	/**
	 * ��ѯ����
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
	 * ��ȡָ����
	 * 
	 * @param id
	 * @return
	 */
	public Extension findById(int id) {
		return (Extension) dao.get(Extension.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Extension.class, id);
	}

	/**
	 * ����
	 * 
	 * @param extension
	 */
	public void add(Extension extension) {
		dao.add(extension);
	}

	/**
	 * ����
	 * 
	 * @param extension
	 */
	public void update(Extension extension) {
		dao.update(extension);
	}
}
