package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.SoftwareType;

public class SoftwareTypeService extends BasicService {
	/**
	 * 查询所有
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
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public SoftwareType findById(int id) {
		return (SoftwareType) dao.get(SoftwareType.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(SoftwareType.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param softwareType
	 */
	public void add(SoftwareType softwareType) {
		dao.add(softwareType);
	}

	/**
	 * 更新
	 * 
	 * @param softwareType
	 */
	public void update(SoftwareType softwareType) {
		dao.update(softwareType);
	}
}
