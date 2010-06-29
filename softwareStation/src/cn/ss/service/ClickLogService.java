package cn.ss.service;

import java.util.Date;
import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.ClickLog;

public class ClickLogService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<ClickLog> pageResult, ClickLog clickLog,
			String beginTime, String endTime, int sid) {
		StringBuffer hql = new StringBuffer("from ClickLog c where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and c.clickTime >= '" + beginTime + "'");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and c.clickTime =< '" + endTime + "'");
		}
		if (sid > 0) {
			hql.append(" and c.softwareInfo.id = " + sid);
		}
		if (clickLog != null) {

		}
		hql.append(" order by a.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * �������ڲ�ѯ
	 * 
	 * @param date_str
	 *            ���� yyyy-MM-dd
	 * @param sid
	 *            ���id
	 * @return
	 */
	public List<ClickLog> findByDate(String date_str, int sid) {
		StringBuffer hql = new StringBuffer("from ClickLog c where 1=1");
		if (date_str != null) {
			hql
					.append(" and DATE_FORMAT(c.clickTime ,'%Y %c %e')=DATE_FORMAT('"
							+ date_str + "','%Y %c %e')");
		}
		if (sid > 0) {
			hql.append(" and c.softwareInfo.id=" + sid);
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
	public ClickLog findById(int id) {
		return (ClickLog) dao.get(ClickLog.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(ClickLog.class, id);
	}

	/**
	 * ����
	 * 
	 * @param ClickLog
	 */
	public void add(ClickLog clickLog) {
		// �жϵ����Ƿ��е����¼
		List<ClickLog> clickLogList = findByDate(Tool.dateFormatString(clickLog
				.getClickTime(), "yyyy-MM-dd"), clickLog.getSoftwareInfo()
				.getId());
		//System.out.println(clickLogList);
		if (clickLogList != null && clickLogList.size() > 0) {
			//System.out.println(clickLogList.size());
			// �޸�
			clickLog = clickLogList.get(0);
			clickLog.setNumber(clickLog.getNumber() + 1);
			System.out.println("�޸ĵ��:"+clickLog.getSoftwareInfo().getId());
			dao.update(clickLog);
		} else {
			// ����
			System.out.println("�������:"+clickLog.getSoftwareInfo().getId());
			dao.add(clickLog);
		}
	}

	/**
	 * ����
	 * 
	 * @param ClickLog
	 */
	public void update(ClickLog clickLog) {
		dao.update(clickLog);
	}
}
