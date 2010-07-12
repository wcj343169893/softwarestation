package cn.ss.action;

import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.Account;
import cn.ss.entity.AccountType;
import cn.ss.entity.Bop;
import cn.ss.service.AccountService;
import cn.ss.service.AccountTypeService;
import cn.ss.service.ActiveLogService;
import cn.ss.service.BopService;

public class BopAction extends BasicAction {
	private static final long serialVersionUID = -480925603091510208L;
	private int p;
	private int id;
	private String beginTime;
	private String endTime;
	private BopService bopService;

	public String delete() throws Exception {
		return list();
	}

	public String list() throws Exception {
		init();
		PageResult<Bop> pageResult = new PageResult<Bop>();
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
		bopService.findAll(pageResult, beginTime, endTime);
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

	public BopService getBopService() {
		return bopService;
	}

	public void setBopService(BopService bopService) {
		this.bopService = bopService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
