package cn.ss.service;

import java.util.Date;
import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.ActiveLog;

public class ActiveLogService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<ActiveLog> pageResult, ActiveLog activeLog,
			String beginTime, String endTime, int sid) {
		StringBuffer hql = new StringBuffer("from ActiveLog a where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and a.activeTime >= '" + beginTime + "'");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and a.activeTime =< '" + endTime + "'");
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
	 * �������ڲ�ѯ
	 * 
	 * @param date_str
	 *            ���� yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public List<ActiveLog> findByDate(String date_str, Date date, int sid) {
		StringBuffer hql = new StringBuffer("from ActiveLog a where 1=1");
		if (date_str != null) {
			hql.append(" and a.activeTime like '" + date_str + "%'");
		}
		if (sid > 0) {
			hql.append(" and a.softwareInfo.id=" + sid);
		}
		List list = dao.list(hql.toString());
		return list;
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public ActiveLog findById(int id) {
		return (ActiveLog) dao.get(ActiveLog.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(ActiveLog.class, id);
	}

	/**
	 * ����
	 * 
	 * @param ActiveLog
	 */
	public void add(ActiveLog activeLog) {
		dao.add(activeLog);
	}

	/**
	 * ����
	 * 
	 * @param activeLog
	 */
	public void update(ActiveLog activeLog) {
		dao.update(activeLog);
	}
}
