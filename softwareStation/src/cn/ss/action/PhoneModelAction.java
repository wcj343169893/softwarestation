package cn.ss.action;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.dto.BrandDTO;
import cn.ss.entity.PhoneBrand;
import cn.ss.entity.PhoneModel;
import cn.ss.service.PhoneBrandService;
import cn.ss.service.PhoneModelService;

public class PhoneModelAction extends BasicAction {
	private PhoneBrandService phoneBrandService;
	private PhoneModelService phoneModelService;
	private PhoneBrand phoneBrand;
	private PhoneModel phoneModel;
	/**
	 * 品牌列表
	 */
	private BrandDTO brandDTO;
	private int p;
	/**
	 * 品牌id
	 */
	private int bid;
	/**
	 * 机型id
	 */
	private int mid;

	/**
	 * 搜索关键字
	 */
	private String keyword;

	public String set() throws Exception {
		init();
		PageResult<PhoneModel> modelPageResult = new PageResult<PhoneModel>();
		if (p != 0) {
			modelPageResult.setPageNo(p);
		}
		if (bid != 0) {// 如果没有选择品牌，则查询所有的品牌
			phoneModelService.findAll(modelPageResult, phoneModel, bid, null);
			request.setAttribute("pageResult", modelPageResult);
			request.setAttribute("brand", phoneBrandService.findById(bid));
			return "detail";
		} else {// 
			PageResult<PhoneModel> pageResult =new PageResult<PhoneModel>();
			pageResult.setPageSize(25);
			phoneModelService.findAll(pageResult, null, 1, null);
			brandDTO.setPhoneModelList(pageResult.getList());
			brandDTO.setPhoneBrandList(phoneBrandService.findAll());
			//request.setAttribute("phoneBrandList", phoneBrandService.findAll());
		}
		return "list";
	}

	// 暂时未用
	public String search() throws Exception {
		init();
		PageResult<PhoneModel> modelPageResult = new PageResult<PhoneModel>();
		if (p != 0) {
			modelPageResult.setPageNo(p);
		}
		if (bid != 0) {// 如果没有选择品牌，则查询所有的品牌
			phoneModelService
					.findAll(modelPageResult, phoneModel, bid, keyword);
			request.setAttribute("pageResult", modelPageResult);
			request.setAttribute("brand", phoneBrandService.findById(bid));
			return "detail";
		} else {// 所有品牌
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BrandDTO getBrandDTO() {
		return brandDTO;
	}

	public void setBrandDTO(BrandDTO brandDTO) {
		this.brandDTO = brandDTO;
	}

}
