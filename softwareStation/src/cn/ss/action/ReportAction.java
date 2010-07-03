package cn.ss.action;

import java.util.Date;
import java.util.Map;

import cn.common.action.BasicAction;
import cn.common.util.Config;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Account;
import cn.ss.entity.Report;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.PhoneModelService;
import cn.ss.service.ReportService;
import cn.ss.service.SoftwareInfoService;

public class ReportAction extends BasicAction {
	private static final long serialVersionUID = 3344331806990985706L;
	private ReportService reportService;
	private PhoneModelService phoneModelService;
	private SoftwareInfoService softwareInfoService;
	private int p;
	private int id;
	private Report report;
	private String beginTime;
	private String endTime;
	private int mid;
	private int sid;
	private int rid;
	private String ps;
	private SoftwareInfo softwareInfo;
	private Map<Integer, String> reports;

	public String delete() throws Exception {
		if (id > 0) {
			reportService.delete(id);
		}
		return list();
	}

	public String add() throws Exception {
		// 需要设置防重复提交
		if (sid > 0 && mid > 0) {
			softwareInfo = softwareInfoService.findById(sid);
			report = new Report();
			report.setRid(rid);
			report.setSoftwareInfo(softwareInfo);
			report.setPhoneModel(phoneModelService.findById(mid));
			report.setReportTime(new Date());
			report.setPs(Tool.filterString(ps));
			reportService.add(report);
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		initData();
		return "report";
	}

	/**
	 * 前台成功跳转，防止重复提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ok() throws Exception {
		return "ok";
	}

	public String edit() throws Exception {
		return list();
	}

	public String detail() throws Exception {
		init();
		initData();
		return "detail";
	}

	public String list() throws Exception {
		init();
		initData();
		PageResult<Report> pageResult = new PageResult<Report>();
		if (beginTime != null && !"".equals(beginTime) && endTime != null
				&& !"".equals(endTime)) {
			if (beginTime.compareTo(endTime) > 0) {
				String tmp = beginTime;
				beginTime = endTime;
				endTime = tmp;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		reportService.findAll(pageResult, report, beginTime, endTime, 0, ps,
				rid);
		request.setAttribute("pageResult", pageResult);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		return "list";
	}

	private void initData() {
		softwareInfo = softwareInfoService.findById(sid);
		reports = Config.reports;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public SoftwareInfo getSoftwareInfo() {
		return softwareInfo;
	}

	public void setSoftwareInfo(SoftwareInfo softwareInfo) {
		this.softwareInfo = softwareInfo;
	}

	public Map<Integer, String> getReports() {
		return reports;
	}

	public void setReports(Map<Integer, String> reports) {
		this.reports = reports;
	}

	public String getPs() {
		return ps;
	}

	public void setPs(String ps) {
		this.ps = ps;
	}

}
