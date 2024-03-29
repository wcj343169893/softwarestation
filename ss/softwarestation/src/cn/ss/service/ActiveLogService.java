package cn.ss.service;

import java.util.Date;
import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.ActiveLog;

public class ActiveLogService extends BasicService {
	/**
	 * 查询所有
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<ActiveLog> pageResult, ActiveLog activeLog,
			String beginTime, String endTime, int sid) {
		StringBuffer hql = new StringBuffer("from ActiveLog a where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(a.activeTime,'%y %m %d') >= DATE_FORMAT('"
							+ beginTime + "','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(a.activeTime,'%y %m %d') <= DATE_FORMAT('"
							+ endTime + "','%y %m %d')");
		}
		if (sid > 0) {
			hql.append(" and a.softwareInfo.id = " + sid);
		}
		if (activeLog != null) {

		}
		hql.append(" order by a.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 根据日期查询
	 * 
	 * @param date_str
	 *            日期 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public List<ActiveLog> findByDate(Date beginTime, Date endTime, int sid) {
		StringBuffer hql = new StringBuffer("from ActiveLog a where 1=1");
		if (beginTime != null) {
			
			hql
					.append(" and DATE_FORMAT(a.activeTime,'%y %m %d') = DATE_FORMAT('"
							+ Tool.dateFormatString(beginTime,"yyyy-MM-dd") + "','%y %m %d')");
		}
		if (sid > 0) {
			hql.append(" and a.softwareInfo.id=" + sid);
		}
		List list = dao.list(hql.toString());
		return list;
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public ActiveLog findById(int id) {
		return (ActiveLog) dao.get(ActiveLog.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(ActiveLog.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param ActiveLog
	 */
	public void add(ActiveLog activeLog) {
		dao.add(activeLog);
	}

	/**
	 * 更新
	 * 
	 * @param activeLog
	 */
	public void update(ActiveLog activeLog) {
		dao.update(activeLog);
	}
}
