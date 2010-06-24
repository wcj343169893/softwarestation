package cn.ss.action;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneBrand;
import cn.ss.entity.PhoneModel;
import cn.ss.service.PhoneBrandService;
import cn.ss.service.PhoneModelService;

public class PhoneModelAction extends BasicAction {
	private PhoneBrandService phoneBrandService;
	private PhoneModelService phoneModelService;
	private PhoneBrand phoneBrand;
	private PhoneModel phoneModel;
	private int p;
	/**
	 * Ʒ��id
	 */
	private int bid;
	/**
	 * ����id
	 */
	private int mid;

	public String set() throws Exception {
		init();
		PageResult<PhoneModel> modelPageResult = new PageResult<PhoneModel>();
		if (p != 0) {
			modelPageResult.setPageNo(p);
		}
		if (bid != 0) {// ���û��ѡ��Ʒ�ƣ����ѯ���е�Ʒ��
			phoneModelService.findAll(modelPageResult, phoneModel, bid);
			request.setAttribute("pageResult", modelPageResult);
			request.setAttribute("brand", phoneBrandService.findById(bid));
			return "detail";
		} else {// ����Ʒ��
			request.setAttribute("phoneBrandList", phoneBrandService.findAll());
		}
		return "list";
	}

	public PhoneBrandService getPhoneBrandService() {
		return phoneBrandService;
	}

	public void setPhoneBrandService(PhoneBrandService phoneBrandService) {
		this.phoneBrandService = phoneBrandService;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public PhoneBrand getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(PhoneBrand phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

	public PhoneModel getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(PhoneModel phoneModel) {
		this.phoneModel = phoneModel;
	}

}
