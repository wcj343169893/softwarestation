package cn.ss.action;

import java.util.Date;
import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.Tool;
import cn.ss.entity.ActiveLog;
import cn.ss.entity.DownloadLog;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.ActiveLogService;
import cn.ss.service.SoftwareInfoService;

public class ActiveLogAction extends BasicAction {
	private static final long serialVersionUID = 7896831671543589893L;
	private ActiveLogService activeLogService;
	private SoftwareInfoService softwareInfoService;
	private ActiveLog activeLog;
	private int p;
	private int id;
	private int sid;
	private double price;
	private int number;
	private String beginTime;
	private String endTime;

	public String delete() throws Exception {
		activeLogService.delete(id);
		return list();
	}

	public String add() throws Exception {
		SoftwareInfo softwareInfo = softwareInfoService.findById(sid);
		if (softwareInfo != null) {// 如果提交的日期已存在，则修改
			List<ActiveLog> list = activeLogService.findByDate(Tool
					.stringFormatDate(beginTime, "yyyy-MM-dd"), null, sid);
			if (list != null && list.size() > 0) {
				activeLog = list.get(0);
				editActiveLog(softwareInfo);
			} else {
				addActiveLog(softwareInfo);
			}
		}
		return "add";
	}

	/**
	 * 新增
	 * 
	 * @param softwareInfo
	 */
	private void addActiveLog(SoftwareInfo softwareInfo) {
		List<DownloadLog> dowloadLogList = softwareInfo.getDownloadLogList();
		// int yesterdayNumber = 0;
		// for (int i = 0; i < dowloadLogList.size(); i++) {//判断更新日期是否有数据
		// if
		// (Tool.dateIsEque(Tool.beforeOrAfterDate(Tool.stringFormatDate(beginTime,
		// "yyyy-MM-dd"), -1),
		// dowloadLogList.get(i).getDownloadTime(), "yyyy-MM-dd")) {
		// yesterdayNumber = dowloadLogList.get(i).getNumber();
		// }
		// }
		activeLog = new ActiveLog();
		activeLog.setActiveTime(new Date());
		// activeLog
		// .setNumber(number > yesterdayNumber ? yesterdayNumber : number);//
		// 判断激活数与下载数量
		activeLog.setNumber(number);
		activeLog.setPrice(price);
		activeLog.setActiveTime(Tool.stringFormatDate(beginTime, "yyyy-MM-dd"));
		activeLog.setSoftwareInfo(softwareInfo);
		activeLogService.add(activeLog);
	}

	public String edit() throws Exception {
		// SoftwareInfo softwareInfo = softwareInfoService.findById(sid);
		// if (softwareInfo != null) {
		// activeLog = activeLogService.findById(id);
		// editActiveLog(softwareInfo);
		// }
		add();
		return list();
	}

	/**
	 * 编辑信息
	 * 
	 * @param softwareInfo
	 */
	private void editActiveLog(SoftwareInfo softwareInfo) {
		List<DownloadLog> dowloadLogList = softwareInfo.getDownloadLogList();
		// int yesterdayNumber = 0;
		// for (int i = 0; i < dowloadLogList.size(); i++) {
		// if (Tool.dateIsEque(Tool.beforeOrAfterDate(new Date(), -1),
		// dowloadLogList.get(i).getDownloadTime(), "yyyy-MM-dd")) {
		// yesterdayNumber = dowloadLogList.get(i).getNumber();
		// }
		// }
		activeLog.setActiveTime(new Date());
		// activeLog
		// .setNumber(number > yesterdayNumber ? yesterdayNumber : number);//
		// 判断激活数与下载数量
		activeLog.setNumber(number);
		activeLog.setPrice(price);
		activeLog.setActiveTime(Tool.stringFormatDate(beginTime, "yyyy-MM-dd"));
		activeLog.setSoftwareInfo(softwareInfo);
		activeLogService.update(activeLog);
	}

	public String detail() throws Exception {
		init();
		initData();
		if (id != 0) {
			activeLog = activeLogService.findById(id);
		}
		return "detail";
	}

	public String list() throws Exception {
		init();
		initData();
		// PageResult<ActiveLog> pageResult = new PageResult<ActiveLog>();
		// if (beginTime != null && !"".equals(beginTime) && endTime != null
		// && !"".equals(endTime)) {
		// if (beginTime.compareTo(endTime) > 0) {
		// String tmp = beginTime;
		// beginTime = endTime;
		// endTime = tmp;
		// }
		// }
		// if (p != 0) {
		// pageResult.setPageNo(p);
		// }
		// activeLogService
		// .findAll(pageResult, activeLog, beginTime, endTime, sid);
		// request.setAttribute("pageResult", pageResult);
		// request.setAttribute("beginTime", beginTime);
		// request.setAttribute("endTime", endTime);
		return "list";
	}

	private void initData() {
		SoftwareInfo softwareInfo = softwareInfoService.findById(sid);
		request.setAttribute("softwareInfo", softwareInfo);
	}

	public ActiveLogService getActiveLogService() {
		return activeLogService;
	}

	public void setActiveLogService(ActiveLogService activeLogService) {
		this.activeLogService = activeLogService;
	}

	public ActiveLog getActiveLog() {
		return activeLog;
	}

	public void setActiveLog(ActiveLog activeLog) {
		this.activeLog = activeLog;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
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

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

}
