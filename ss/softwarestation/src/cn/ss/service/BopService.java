package cn.ss.service;

import java.util.Date;
import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Bop;

public class BopService extends BasicService {
	/**
	 * 根据时间分页查询
	 * 
	 * @param pageResult
	 * @param beginTime
	 * @param endTime
	 */
	public void findAll(PageResult<Bop> pageResult, String beginTime,
			String endTime) {
		StringBuffer hql = new StringBuffer("from Bop where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(createtime,'%y %m %d') >= DATE_FORMAT('"
							+ beginTime + "','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(createtime,'%y %m %d') <= DATE_FORMAT('"
							+ endTime + "','%y %m %d')");
		}
		hql.append(" order by id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 根据时间查询清单
	 * 
	 * @param date
	 *            具体日期
	 * @return
	 */
	public Bop findByDate(Date date) {
		Bop bop = null;
		String hql = "from Bop where DATE_FORMAT(createTime,'%y %m %d') = DATE_FORMAT('"
				+ Tool.dateFormatString(date, "yyyy-MM-dd") + "','%y %m %d')";
		List list = dao.list(hql);
		if (list != null && list.size() > 0) {
			bop = (Bop) list.get(0);
		}
		return bop;
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public Bop findById(int id) {
		return (Bop) dao.get(Bop.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Bop.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param bop
	 */
	public void add(Bop bop) {
		dao.add(bop);
	}

	/**
	 * 更新
	 * 
	 * @param bop
	 */
	public void update(Bop bop) {
		dao.update(bop);
	}
}
