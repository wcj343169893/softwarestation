package cn.ss.action;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.SysLog;
import cn.ss.service.SysLogService;

public class SysLogAction extends BasicAction {
	private static final long serialVersionUID = -3038633850075999469L;
	private int p;
	private int id;
	private String beginTime;
	private String endTime;
	private SysLogService sysLogService;
	private PageResult<SysLog> pageResult = new PageResult<SysLog>();

	public String delete() throws Exception {
		return list();
	}

	public String list() throws Exception {
		init();
		pageResult = new PageResult<SysLog>();
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
		sysLogService.findAll(pageResult, beginTime, endTime);
		request.setAttribute("pageResult", pageResult);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		return "list";
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

	public SysLogService getSysLogService() {
		return sysLogService;
	}

	public void setSysLogService(SysLogService sysLogService) {
		this.sysLogService = sysLogService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PageResult<SysLog> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<SysLog> pageResult) {
		this.pageResult = pageResult;
	}

}
