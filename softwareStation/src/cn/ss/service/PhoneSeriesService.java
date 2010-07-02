package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneSeries;

public class PhoneSeriesService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param phoneSeries
	 */
	public void findAll(PageResult<PhoneSeries> pageResult,
			PhoneSeries phoneSeries, String name, int phoneOsId, int brandId) {
		StringBuffer hql = new StringBuffer("from PhoneSeries ps where 1=1");
		if (null != phoneSeries) {
		}
		if (phoneOsId > 0) {
			hql.append(" and ps.os.id=" + phoneOsId);
		}
		if (brandId > 0) {
			hql.append(" and ps.brand.id=" + brandId);
		}
		if (name != null && !"".equals(name.trim())) {
			name = name.replace("'", "");
			hql.append(" and ps.name like '%" + name + "'");
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
	public PhoneSeries findById(int id) {
		return (PhoneSeries) dao.get(PhoneSeries.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneSeries.class, id);
	}

	/**
	 * ����
	 * 
	 * @param phoneSeries
	 */
	public void add(PhoneSeries phoneSeries) {
		dao.add(phoneSeries);
	}

	/**
	 * ����
	 * 
	 * @param phoneSeries
	 */
	public void update(PhoneSeries phoneSeries) {
		dao.update(phoneSeries);
	}
}
