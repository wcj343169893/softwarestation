package cn.ss.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.ReportModel;
import cn.ss.service.PhoneModelService;
import cn.ss.service.ReportModelService;

public class ReportModelAction extends BasicAction {
	private static final long serialVersionUID = 1964860166698476385L;
	private ReportModelService reportModelService;
	private PhoneModelService phoneModelService;
	private int p;
	private int id;
	private String beginTime;
	private String endTime;
	private ReportModel reportModel;
	private String model;
	private String brand;
	private int mid;
	private Integer deal;
	private PageResult<ReportModel> pageResult;

	public String delete() throws Exception {
		if (id > 0) {
			reportModelService.delete(id);
		}
		return list();
	}

	// 增加操作
	public String add() throws Exception {
		init();
		List<PhoneModel> phoneModelList = new ArrayList<PhoneModel>();
		if (model != null && brand != null && !"".equals(model.trim())
				&& !"".equals(brand.trim())) {
			phoneModelList = phoneModelService.findALl(model, brand);
			if (phoneModelList != null && phoneModelList.size() > 0) {
				request.setAttribute("t", 1);
				request.setAttribute("error", "已存在此机型");
				request.setAttribute("phoneModelList", phoneModelList);
				return "error";
			} else {
				List list = reportModelService.findAll(model, brand);
				if (list != null && list.size() > 0) {
					request.setAttribute("t", 1);
					request.setAttribute("error", "此机型已经提交，等待加入中...");
					return "error";
				}
				reportModel = new ReportModel();
				reportModel.setBrandName(Tool.filterString(brand));
				reportModel.setModelName(Tool.filterString(model));
				reportModel.setReportTime(new Date());
				reportModel.setDeal(0);
				reportModelService.add(reportModel);
				return "rm";
			}
		} else {
			request.setAttribute("t", 1);
			request.setAttribute("error", "请输入正确的信息");
			return "error";
		}
	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	/**
	 * 前台成功跳转，防止重复提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ok() throws Exception {
		init();
		request.setAttribute("t", 1);
		return "ok";
	}

	public String deal() throws Exception {
		reportModel = reportModelService.findById(id);
		reportModel.setDeal(1);
		reportModelService.update(reportModel);
		return list();
	}
	public String list() throws Exception {
		init();
		pageResult = new PageResult<ReportModel>();
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
		reportModel = new ReportModel();
		reportModel.setBrandName(brand);
		reportModel.setModelName(model);
		reportModel.setDeal(deal);
		reportModelService.findAll(pageResult, reportModel, beginTime, endTime);
		return "list";
	}

	public ReportModelService getReportModelService() {
		return reportModelService;
	}

	public void setReportModelService(ReportModelService reportModelService) {
		this.reportModelService = reportModelService;
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

	public ReportModel getReportModel() {
		return reportModel;
	}

	public void setReportModel(ReportModel reportModel) {
		this.reportModel = reportModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

	public PageResult<ReportModel> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<ReportModel> pageResult) {
		this.pageResult = pageResult;
	}

	public Integer getDeal() {
		return deal;
	}

	public void setDeal(Integer deal) {
		this.deal = deal;
	}

}
