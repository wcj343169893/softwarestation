package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneBrand;

public class PhoneBrandService extends BasicService {
	/**
	 * ��ѯ���в���Ʒ��
	 * 
	 * @param pageResult
	 * @param phoneBrand
	 */
	public void findAll(PageResult<PhoneBrand> pageResult, PhoneBrand phoneBrand) {
		StringBuffer hql = new StringBuffer("from PhoneBrand pb where 1=1");
		if (null != phoneBrand) {
			// hql.append(" and id="+phoneBrand.getId());
		}
		hql.append(" order by pb.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ѯ����Ʒ��
	 * 
	 * @return
	 */
	public List<PhoneBrand> findAll() {
		String sql = "from PhoneBrand";
		return dao.list(sql);
	}

	/**
	 * ��ȡָ����Ʒ��
	 * 
	 * @param id
	 * @return
	 */
	public PhoneBrand findById(int id) {
		return (PhoneBrand) dao.get(PhoneBrand.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneBrand.class, id);
	}

	/**
	 * ����
	 * 
	 * @param phoneBrand
	 */
	public void add(PhoneBrand phoneBrand) {
		dao.add(phoneBrand);
	}

	/**
	 * ����
	 * 
	 * @param phoneBrand
	 */
	public void update(PhoneBrand phoneBrand) {
		dao.update(phoneBrand);
	}
}
