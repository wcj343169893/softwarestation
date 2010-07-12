package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.SysLog;

public class SysLogService extends BasicService {
	/**
	 * ����ʱ���ҳ��ѯ
	 * 
	 * @param pageResult
	 * @param beginTime
	 * @param endTime
	 */
	public void findAll(PageResult<SysLog> pageResult, String beginTime,
			String endTime) {
		StringBuffer hql = new StringBuffer("from SysLog where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(logTime,'%y %m %d') >= DATE_FORMAT('"
					+ beginTime + "','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(logTime,'%y %m %d') <= DATE_FORMAT('"
					+ endTime + "','%y %m %d')");
		}
		hql.append(" order by id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public SysLog findById(int id) {
		return (SysLog) dao.get(SysLog.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(SysLog.class, id);
	}

	/**
	 * ����
	 * 
	 * @param SysLog
	 */
	public void add(SysLog sysLog) {
		dao.add(sysLog);
	}

	/**
	 * ����
	 * 
	 * @param SysLog
	 */
	public void update(SysLog sysLog) {
		dao.update(sysLog);
	}
}
