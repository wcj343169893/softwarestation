package cn.ss.action;

import java.util.Date;
import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.SoftwareInfo;
import cn.ss.entity.SoftwareType;
import cn.ss.service.PhoneModelService;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareTypeService;

public class SoftwareTypeAction extends BasicAction {
	private static final long serialVersionUID = -487891704302083458L;
	private SoftwareTypeService softwareTypeService;
	private SoftwareInfoService softwareInfoService;
	private PhoneModelService phoneModelService;
	private String name;
	private Integer id;
	private int p;
	private int zindex;
	private int isShow;
	private int isWrap;
	private SoftwareType softwareType;
	private int mid;

	public String delete() throws Exception {
		softwareTypeService.delete(id);
		return list();
	}

	public String add() throws Exception {
		softwareType = new SoftwareType();
		softwareType.setIsShow(this.getIsShow());
		softwareType.setName(this.getName());
		softwareType.setZindex(this.getZindex());
		softwareType.setCreateTime(new Date());
		softwareType.setIsWrap(isWrap);
		softwareTypeService.add(softwareType);
		return list();
	}

	public String edit() throws Exception {
		softwareType = softwareTypeService.findById(id);
		softwareType.setName(name);
		softwareType.setZindex(this.getZindex());
		softwareType.setIsShow(this.getIsShow());
		softwareType.setCreateTime(new Date());
		softwareType.setIsWrap(isWrap);
		softwareTypeService.update(softwareType);
		return list();
	}

	public String detail() throws Exception {
		init();
		softwareType = softwareTypeService.findById(id);
		request.setAttribute("softwareType", softwareType);
		return "detail";
	}

	public String list() throws Exception {
		init();
		softwareType = new SoftwareType();
		PageResult<SoftwareType> pageResult = new PageResult<SoftwareType>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareTypeService.findAll(pageResult, softwareType);
		request.setAttribute("pageResult", pageResult);
		return "list";
	}

	/**
	 * 前台显示软件类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ls() throws Exception {
		init();
		// mid 查询手机型号
		request.setAttribute("model", phoneModelService.findById(mid));
		request.setAttribute("softwareTypeList", softwareTypeService
				.findAll(null));
		return "ls";
	}

	/**
	 * 前台显示单个类型的软件
	 * 
	 * @return
	 * @throws Exception
	 */
	public String show() throws Exception {
		init();
		request.setAttribute("model", phoneModelService.findById(mid));
		softwareType = softwareTypeService.findById(id);
		request.setAttribute("softwareType", softwareType);
		// mid 根据手机型号显示软件
		PageResult<SoftwareInfo> pageResult=new PageResult<SoftwareInfo>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		softwareInfoService.findAll(pageResult, mid, id, 0,1);
		request.setAttribute("pageResult", pageResult);
		return "show";
	}

	public SoftwareTypeService getSoftwareTypeService() {
		return softwareTypeService;
	}

	public void setSoftwareTypeService(SoftwareTypeService softwareTypeService) {
		this.softwareTypeService = softwareTypeService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public SoftwareType getSoftwareType() {
		return softwareType;
	}

	public void setSoftwareType(SoftwareType softwareType) {
		this.softwareType = softwareType;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getZindex() {
		return zindex;
	}

	public void setZindex(int zindex) {
		this.zindex = zindex;
	}

	public int getIsWrap() {
		return isWrap;
	}

	public void setIsWrap(int isWrap) {
		this.isWrap = isWrap;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

}
