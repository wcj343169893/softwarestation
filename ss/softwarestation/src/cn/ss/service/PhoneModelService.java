package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneModel;

public class PhoneModelService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param phoneModel
	 * @param bid
	 *            Ʒ��id
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
	 * �������Ʋ�ѯ����
	 * 
	 * @param model
	 *            ��������
	 * @param brand
	 *            Ʒ������
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
	 * ����Ʒ��id��ѯ
	 * 
	 * @param bid
	 *            Ʒ��id
	 * @param maxCount
	 *            �������
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
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public PhoneModel findById(int id) {
		return (PhoneModel) dao.get(PhoneModel.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneModel.class, id);
	}

	/**
	 * ����
	 * 
	 * @param phoneModel
	 */
	public void add(PhoneModel phoneModel) {
		dao.add(phoneModel);
	}

	/**
	 * ����
	 * 
	 * @param phoneModel
	 */
	public void update(PhoneModel phoneModel) {
		dao.update(phoneModel);
	}
}
