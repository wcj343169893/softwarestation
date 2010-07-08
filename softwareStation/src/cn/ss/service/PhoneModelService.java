package cn.ss.service;

import java.util.List;

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
			hql.append(" and ps.phoneseries.brand.id=" + bid);
		}
		if (keyword != null) {
			keyword = keyword.replace("'", "");
			hql.append(" and ps.name like '" + keyword + "%'");
		}
		hql.append(" order by ps.id");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 根据名称查询机型
	 * 
	 * @param model
	 *            机型名称
	 * @param brand
	 *            品牌名称
	 * @return
	 */
	public List<PhoneModel> findALl(String model, String brand) {
		String sql = "from PhoneModel pm where 1=1 ";
		if (model != null && !"".equals(model.trim())) {
			sql += " and pm.phoneseries.brand.name='" + brand + "'";
		}
		if (brand != null && !"".equals(brand.trim())) {
			sql += " and pm.name='" + model + "'";
		}
		return dao.list(sql);
	}

	/**
	 * 根据品牌id查询
	 * 
	 * @param bid
	 *            品牌id
	 * @param maxCount
	 *            最多条数
	 * @return
	 */
	public List<PhoneModel> findAll(int bid, int maxCount) {
		StringBuffer hql = new StringBuffer("from PhoneModel ps where 1=1");
		if (bid > 0) {
			hql.append(" and ps.phoneseries.brand.id=" + bid);
		}
		hql.append(" order by ps.id limit 0," + maxCount);
		return dao.list(hql.toString());
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
