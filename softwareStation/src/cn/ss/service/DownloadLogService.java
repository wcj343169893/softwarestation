package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.DownloadLog;

public class DownloadLogService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<DownloadLog> pageResult,
			DownloadLog downloadLog, String beginTime, String endTime, int sid) {
		StringBuffer hql = new StringBuffer("from DownloadLog a where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and a.downloadTime >= '" + beginTime + "'");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and a.downloadTime =< '" + endTime + "'");
		}
		if (sid > 0) {
			hql.append(" and a.softwareInfo.id = " + sid);
		}
		if (downloadLog != null) {

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
	public List<DownloadLog> findByDate(String date_str, int sid) {
		StringBuffer hql = new StringBuffer("from DownloadLog d where 1=1");
		if (date_str != null) {
			hql
					.append(" and DATE_FORMAT(d.downloadTime ,'%Y %c %e')=DATE_FORMAT('"
							+ date_str + "','%Y %c %e')");
		}
		if (sid > 0) {
			hql.append(" and d.softwareInfo.id=" + sid);
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
	public DownloadLog findById(int id) {
		return (DownloadLog) dao.get(DownloadLog.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(DownloadLog.class, id);
	}

	/**
	 * ����
	 * 
	 * @param DownloadLog
	 */
	public void add(DownloadLog downloadLog) {
		// �жϵ����Ƿ��е����¼
		List<DownloadLog> DownloadLogList = findByDate(Tool.dateFormatString(
				downloadLog.getDownloadTime(), "yyyy-MM-dd"), downloadLog
				.getSoftwareInfo().getId());
		System.out.println(DownloadLogList);
		if (DownloadLogList != null && DownloadLogList.size() > 0) {
			System.out.println(DownloadLogList.size());
			// �޸�
			downloadLog = DownloadLogList.get(0);
			downloadLog.setNumber(downloadLog.getNumber() + 1);
			dao.update(downloadLog);
		} else {
			// ����
			dao.add(downloadLog);
		}
	}

	/**
	 * ����
	 * 
	 * @param DownloadLog
	 */
	public void update(DownloadLog downloadLog) {
		dao.update(downloadLog);
	}
}
