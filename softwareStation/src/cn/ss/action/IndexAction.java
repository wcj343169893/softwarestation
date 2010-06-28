package cn.ss.action;

import java.util.Date;
import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.dto.IndexDTO;
import cn.ss.entity.SoftwareInfo;
import cn.ss.entity.SoftwareType;
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

	@Override
	public String execute() throws Exception {
		init();
		// 1.查询置顶软件加精 plusFine
		List<SoftwareInfo> sipList = softwareInfoService.findAll(mid, 1, 0,
				null);
		indexDTO.setSoftware_plusFineList(sipList);
		// 2.推荐 recommend
		List<SoftwareInfo> sirList = softwareInfoService.findAll(mid, 0, 1,
				null);
		indexDTO.setSoftware_recommendList(sirList);
		// 3.最新更新
		indexDTO.setSoftware_newList(softwareInfoService.findAll(mid, 0, 0,
				new Date()));
		// 4.软件类型列表
		indexDTO.setSoftwareTypeList(softwareTypeService.findAll(null));
		if (mid > 0) {
			indexDTO.setModel(phoneModelService.findById(mid));
		} else {
			indexDTO.setModel(null);// 重置机型
		}
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

}
