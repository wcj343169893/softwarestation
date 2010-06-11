package cn.ss.action;

import cn.common.action.BasicAction;
import cn.ss.form.PhoneosForm;
import cn.ss.service.PhoneosService;

public class PhoneosAction extends BasicAction {
	private PhoneosForm phoneosForm;
	private PhoneosService phoneosService;

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public String add() throws Exception {

		return "add";
	}

	public String edit() throws Exception {

		return "edit";
	}

	public String detail() throws Exception {

		return "detail";
	}

	public String list() throws Exception {
		return "list";
	}

	public PhoneosForm getPhoneosForm() {
		return phoneosForm;
	}

	public void setPhoneosForm(PhoneosForm phoneosForm) {
		this.phoneosForm = phoneosForm;
	}

	public PhoneosService getPhoneosService() {
		return phoneosService;
	}

	public void setPhoneosService(PhoneosService phoneosService) {
		this.phoneosService = phoneosService;
	}

}
