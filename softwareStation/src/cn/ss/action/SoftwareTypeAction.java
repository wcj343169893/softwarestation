package cn.ss.action;

import java.util.Date;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.SoftwareType;
import cn.ss.service.SoftwareTypeService;

public class SoftwareTypeAction extends BasicAction {
	private static final long serialVersionUID = -487891704302083458L;
	private SoftwareTypeService softwareTypeService;
	private String name;
	private Integer id;
	private int p;
	private int zindex;
	private int isShow;
	private int isWrap;
	private SoftwareType softwareType;

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

}
