package cn.ss.action;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.Extension;
import cn.ss.service.ExtensionService;

public class ExtensionAction extends BasicAction {
	private ExtensionService extensionService;
	private String name;
	private Integer id;
	private Extension extension;

	public String delete() throws Exception {
		extensionService.delete(id);
		return list();
	}

	public String add() throws Exception {
		extension = new Extension();
		extension.setName(name);
		extensionService.add(extension);
		return list();
	}

	public String edit() throws Exception {
		extension = extensionService.findById(id);
		extension.setName(name);
		extensionService.update(extension);
		return list();
	}

	public String detail() throws Exception {
		init();
		extension = id > 0 ? extensionService.findById(id) : null;
		return "detail";
	}

	public String list() throws Exception {
		init();
		PageResult<Extension> pageResult = new PageResult<Extension>();
		extensionService.findAll(pageResult, extension);
		request.setAttribute("pageResult", pageResult);
		return "list";
	}

	public ExtensionService getExtensionService() {
		return extensionService;
	}

	public void setExtensionService(ExtensionService extensionService) {
		this.extensionService = extensionService;
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

	public Extension getExtension() {
		return extension;
	}

	public void setExtension(Extension extension) {
		this.extension = extension;
	}

}
