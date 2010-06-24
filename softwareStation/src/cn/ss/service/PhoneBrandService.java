package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneBrand;

public class PhoneBrandService extends BasicService {
	/**
	 * 查询所有操作品牌
	 * 
	 * @param pageResult
	 * @param phoneBrand
	 */
	public void findAll(PageResult<PhoneBrand> pageResult, PhoneBrand phoneBrand) {
		StringBuffer hql = new StringBuffer("from PhoneBrand pb where 1=1");
		if (null != phoneBrand) {
		}
		hql.append(" order by pb.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 获取指定的品牌
	 * 
	 * @param id
	 * @return
	 */
	public PhoneBrand findById(int id) {
		return (PhoneBrand) dao.get(PhoneBrand.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneBrand.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param phoneBrand
	 */
	public void add(PhoneBrand phoneBrand) {
		dao.add(phoneBrand);
	}

	/**
	 * 更新
	 * 
	 * @param phoneBrand
	 */
	public void update(PhoneBrand phoneBrand) {
		dao.update(phoneBrand);
	}
}
