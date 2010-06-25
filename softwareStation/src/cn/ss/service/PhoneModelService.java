package cn.ss.service;

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
