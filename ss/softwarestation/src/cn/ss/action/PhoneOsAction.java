package cn.ss.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.Extension;
import cn.ss.entity.PhoneOs;
import cn.ss.service.ExtensionService;
import cn.ss.service.PhoneOsService;

public class PhoneOsAction extends BasicAction {
	private static final long serialVersionUID = -2834444107359435356L;
	private PhoneOsService phoneOsService;
	private ExtensionService extensionService;
	private String name;
	private Integer id;
	private int p;
	private PhoneOs phoneOs;
	private String extensions = "";

	public String delete() throws Exception {
		phoneOsService.delete(id);
		return list();
	}

	public String add() throws Exception {
		// 软件扩展名
		List<Extension> extensionList = getExtensionsList();
		phoneOs = new PhoneOs();
		phoneOs.setName(this.getName());
		phoneOs.setCreateTime(new Date());
		phoneOs.setExtensionList(extensionList);
		phoneOsService.add(phoneOs);
		return list();
	}

	public String edit() throws Exception {
		// 软件扩展名
		List<Extension> extensionList = getExtensionsList();
		phoneOs = phoneOsService.findById(id);
		phoneOs.setName(name);
		phoneOs.setCreateTime(new Date());
		phoneOs.setExtensionList(extensionList);
		phoneOsService.update(phoneOs);
		return list();
	}

	/**
	 * 根据id数组查询扩展List
	 * 
	 * @return
	 */
	private List<Extension> getExtensionsList() {
		String[] exs = extensions.split(",");
		List<Extension> extensionList = new ArrayList<Extension>();
		for (int i = 0; i < exs.length; i++) {
			if (!exs[i].trim().equals("")) {
				extensionList.add(extensionService.findById(Integer
						.parseInt(exs[i].trim())));
			}
		}
		return extensionList;
	}

	public String detail() throws Exception {
		init();
		initData();
		phoneOs = id > 0 ? phoneOsService.findById(id) : null;
		request.setAttribute("phoneOs", phoneOs);
		return "detail";
	}

	public String list() throws Exception {
		init();
		phoneOs = new PhoneOs();
		PageResult<PhoneOs> pageResult = new PageResult<PhoneOs>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		phoneOsService.findAll(pageResult, phoneOs);
		request.setAttribute("pageResult", pageResult);
		return "list";
	}

	private void initData() {
		request.setAttribute("extensionList", extensionService.findAll(null));
	}

	public PhoneOs getPhoneOs() {
		return phoneOs;
	}

	public void setPhoneOs(PhoneOs phoneOs) {
		this.phoneOs = phoneOs;
	}

	public PhoneOsService getPhoneOsService() {
		return phoneOsService;
	}

	public void setPhoneOsService(PhoneOsService phoneOsService) {
		this.phoneOsService = phoneOsService;
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

	public ExtensionService getExtensionService() {
		return extensionService;
	}

	public void setExtensionService(ExtensionService extensionService) {
		this.extensionService = extensionService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExtensions() {
		return extensions;
	}

	public void setExtensions(String extensions) {
		this.extensions = extensions;
	}

}
