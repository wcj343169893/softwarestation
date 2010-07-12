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

	/**
	 * 每页条数
	 */
	private int pageSize;

	private String from;

	/**
	 * @return
	 * @throws Exception
	 */
	public String set() throws Exception {
		init();
		keyword = null;
		PageResult<PhoneModel> modelPageResult = new PageResult<PhoneModel>();
		if (p != 0) {
			modelPageResult.setPageNo(p);
		}
		if (bid != 0) {// 如果没有选择品牌，则查询所有的品牌
			phoneModelService.findAll(modelPageResult, phoneModel, bid, null);
			request.setAttribute("pageResult", modelPageResult);
			request.setAttribute("brand", phoneBrandService.findById(bid));
			request.setAttribute("bid", bid);
			bid = 0;
			return "detail";
		} else {// 
			PageResult<PhoneModel> pageResult = new PageResult<PhoneModel>();
			pageResult.setPageSize(25);
			phoneModelService.findAll(pageResult, null, 1, null);
			brandDTO.setPhoneModelList(pageResult.getList());
			brandDTO.setPhoneBrandList(phoneBrandService.findAll());
			// request.setAttribute("phoneBrandList",
			// phoneBrandService.findAll());
		}
		bid = 0;
		return "list";
	}

	public String sure() throws Exception {
		init();
		phoneModel = phoneModelService.findById(mid);
		if (from != null) {
			System.out.println(from);
			if (from.indexOf("?") != -1) {
				int index = from.indexOf("mid=");
				System.out.println("from1:" + from);
				if (index != -1) {
					from = from.substring(0, index);
					from += "mid=" + mid;
				} else {
					from += "&amp;mid=" + mid;// http://sj.361rj.com/index.php?mid=26&mid=20&mid=32&mid=40&mid=34&mid=31&mid=31&mid=33
				}
				System.out.println("from2:" + from);
			} else {
				from += "?mid=" + mid;
			}
		} else {
			from = "/index.php?mid=" + mid;
		}
		return "sure";
	}

	/**
	 * 搜索
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		init();
		PageResult<PhoneModel> modelPageResult = new PageResult<PhoneModel>();
		if (p != 0) {
			modelPageResult.setPageNo(p);
		}
		if (keyword != null && !"".equals(keyword.trim())) {
			phoneModelService.findAll(modelPageResult, phoneModel, 0, keyword);
			request.setAttribute("pageResult", modelPageResult);
		}
		return "search";
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

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
