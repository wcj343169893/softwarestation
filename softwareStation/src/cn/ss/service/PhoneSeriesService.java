package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneSeries;

public class PhoneSeriesService extends BasicService {
	/**
	 * 查询所有
	 * 
	 * @param pageResult
	 * @param phoneSeries
	 */
	public void findAll(PageResult<PhoneSeries> pageResult,
			PhoneSeries phoneSeries) {
		StringBuffer hql = new StringBuffer("from PhoneSeries ps where 1=1");
		if (null != phoneSeries) {
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
	public PhoneSeries findById(int id) {
		return (PhoneSeries) dao.get(PhoneSeries.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(PhoneSeries.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param phoneSeries
	 */
	public void add(PhoneSeries phoneSeries) {
		dao.add(phoneSeries);
	}

	/**
	 * 更新
	 * 
	 * @param phoneSeries
	 */
	public void update(PhoneSeries phoneSeries) {
		dao.update(phoneSeries);
	}
}
