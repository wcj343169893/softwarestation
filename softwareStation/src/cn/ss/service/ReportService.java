package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Report;

public class ReportService extends BasicService {
	/**
	 * ��ѯ����
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<Report> pageResult, Report report,
			String beginTime, String endTime, int sid, String ps, int rid) {
		StringBuffer hql = new StringBuffer("from Report r where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(r.reportTime,'%y %m %d') >= DATE_FORMAT('"+beginTime+"','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(r.reportTime,'%y %m %d') <= DATE_FORMAT('"+endTime+"','%y %m %d')");
		}
		if (sid > 0) {
			hql.append(" and r.softwareInfo = " + sid);
		}
		if (ps != null && !"".equals(ps.trim())) {
			ps = ps.replace("'", "");
			hql.append(" and r.ps like '%" + ps + "%'");
		}
		if (rid > 0) {
			hql.append(" and r.rid =" + rid);
		}
		if (report != null) {

		}
		hql.append(" order by r.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public Report findById(int id) {
		return (Report) dao.get(Report.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Report.class, id);
	}

	/**
	 * ����
	 * 
	 * @param report
	 */
	public void add(Report report) {
		dao.add(report);
	}

	/**
	 * ����
	 * 
	 * @param Report
	 */
	public void update(Report report) {
		dao.update(report);
	}
}
