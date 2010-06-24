package cn.ss.action;

import java.util.List;

import cn.common.action.BasicAction;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.PhoneBrandService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.SoftwareInfoService;

/**
 * 加载主页信息
 * 
 * @author 文朝军
 * 
 */
public class IndexAction extends BasicAction {
	private SoftwareInfoService softwareInfoService;
	private PhoneBrandService phoneBrandService;
	private PhoneModelService phoneModelService;
	/**
	 * 机型id
	 */
	private int mid;

	@Override
	public String execute() throws Exception {
		init();
		// 如果选择机型，则查询相应机型的信息
		// 如果没有选择，则查询全部(必须为显示软件)
		// 1.查询置顶软件加精 plusFine
		List<SoftwareInfo> sipList = softwareInfoService.findAll(mid, 1, 0);
		// 2.推荐 recommend
		List<SoftwareInfo> sirList = softwareInfoService.findAll(mid, 0,1);
		request.setAttribute("sipList", sipList);
		request.setAttribute("sirList", sirList);
		return "success";
	}

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public PhoneBrandService getPhoneBrandService() {
		return phoneBrandService;
	}

	public void setPhoneBrandService(PhoneBrandService phoneBrandService) {
		this.phoneBrandService = phoneBrandService;
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

}
