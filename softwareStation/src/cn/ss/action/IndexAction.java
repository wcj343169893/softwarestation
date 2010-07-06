package cn.ss.action;

import java.util.List;
import java.util.Random;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.dto.IndexDTO;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.PhoneBrandService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareTypeService;

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
	private SoftwareTypeService softwareTypeService;
	private IndexDTO indexDTO;
	/**
	 * 机型id
	 */
	private int mid;
	private int c;
	private int isJava;
	private int p;
	private PageResult<SoftwareInfo> pageResult = new PageResult<SoftwareInfo>();
	private PhoneModel phoneModel;
	private int commend;
	/**
	 * 0：日榜.1：月榜.2：总榜
	 */
	private int ranks;

	@Override
	public String execute() throws Exception {
		init();
		// 1.查询软件加精 plusFine
		List<SoftwareInfo> sipList = softwareInfoService.findAll(mid, 1, -1);
		indexDTO.setSoftware_plusFineList(sipList);
		// 2.推荐 recommend
		List<SoftwareInfo> sirList = softwareInfoService.findAll(mid, -1, 1);
		SoftwareInfo s_recommend = null;
		Random ran = new Random();
		int size = sirList.size();
		for (int i = 0; i < size; i++) {
			int o = ran.nextInt(sirList.size() - 1);
			if (sirList.get(o) != null) {
				s_recommend = sirList.get(o);
				break;
			}
		}
		request.setAttribute("s_recommend", s_recommend);
		indexDTO.setSoftware_recommendList(sirList);
		// 3.置顶软件20条
		indexDTO.setSoftware_topsList(softwareInfoService.findAll(mid));

		// 4.软件类型列表
		indexDTO.setSoftwareTypeList(softwareTypeService.findAll(null));
		if (mid > 0) {
			indexDTO.setModel(phoneModelService.findById(mid));
		} else {
			indexDTO.setModel(null);// 重置机型
		}
		return "success";
	}

	/**
	 * 排行
	 * 
	 * @return
	 * @throws Exception
	 */
	public String rank() throws Exception {
		phoneModel = null;
		if (mid != 0) {
			phoneModel = phoneModelService.findById(mid);
			if (phoneModel == null
					|| phoneModel.getPhoneseries().getOs().getName()
							.toLowerCase().equals("java")) {
				isJava = 2;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findByRank(pageResult, mid, isJava, ranks);
		return "rank";
	}

	/**
	 * 最新
	 * 
	 * @return
	 * @throws Exception
	 */
	public String news() throws Exception {
		phoneModel = null;
		if (mid != 0) {
			phoneModel = phoneModelService.findById(mid);
			if (phoneModel == null
					|| phoneModel.getPhoneseries().getOs().getName()
							.toLowerCase().equals("java")) {
				isJava = 2;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findAll(pageResult, mid, isJava);
		return "news";
	}

	/**
	 * 推荐
	 * 
	 * @return
	 * @throws Exception
	 */
	public String commend() throws Exception {
		phoneModel = null;
		if (mid != 0) {
			phoneModel = phoneModelService.findById(mid);
			if (phoneModel == null
					|| phoneModel.getPhoneseries().getOs().getName()
							.toLowerCase().equals("java")) {
				isJava = 2;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findAll(pageResult, mid, isJava, commend);
		return "commend";
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

	public IndexDTO getIndexDTO() {
		return indexDTO;
	}

	public void setIndexDTO(IndexDTO indexDTO) {
		this.indexDTO = indexDTO;
	}

	public SoftwareTypeService getSoftwareTypeService() {
		return softwareTypeService;
	}

	public void setSoftwareTypeService(SoftwareTypeService softwareTypeService) {
		this.softwareTypeService = softwareTypeService;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getIsJava() {
		return isJava;
	}

	public void setIsJava(int isJava) {
		this.isJava = isJava;
	}

	public PageResult<SoftwareInfo> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<SoftwareInfo> pageResult) {
		this.pageResult = pageResult;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public PhoneModel getPhoneModel() {
		return phoneModel;
	}

	public void setPhoneModel(PhoneModel phoneModel) {
		this.phoneModel = phoneModel;
	}

	public int getCommend() {
		return commend;
	}

	public void setCommend(int commend) {
		this.commend = commend;
	}

	public int getRanks() {
		return ranks;
	}

	public void setRanks(int ranks) {
		this.ranks = ranks;
	}

}
