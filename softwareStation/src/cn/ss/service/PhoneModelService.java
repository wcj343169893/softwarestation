package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneModel;

public class PhoneModelService extends BasicService {
	/**
	 * 查询所有
	 * 
	 * @param pageResult
	 * @param phoneModel
	 * @param bid
	 *            品牌id
	 */
	public void findAll(PageResult<PhoneModel> pageResult,
			PhoneModel phoneModel, int bid, String keyword) {
		StringBuffer hql = new StringBuffer("from PhoneModel ps where 1=1");
		if (null != phoneModel) {
		}
		if (bid > 0) {
			hql.append(" and ps.phonebrand.id=" + bid);
		}
		if (keyword != null) {
			keyword = keyword.replace("'", "");
			hql.append(" and ps.name like '" + keyword + "%'");
		}
		hql.append(" order by ps.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public PhoneModel findById(int id) {
		return (PhoneModel) dao.get(PhoneModel.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneModel.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param phoneModel
	 */
	public void add(PhoneModel phoneModel) {
		dao.add(phoneModel);
	}

	/**
	 * 更新
	 * 
	 * @param phoneModel
	 */
	public void update(PhoneModel phoneModel) {
		dao.update(phoneModel);
	}
}
