package cn.ss.action;

import java.util.Calendar;
import java.util.Date;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneBrand;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.PhoneOs;
import cn.ss.entity.PhoneSeries;
import cn.ss.service.PhoneBrandService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.PhoneOsService;
import cn.ss.service.PhoneSeriesService;

public class PhoneSeriesAction extends BasicAction {
	private static final long serialVersionUID = -5296207928637644036L;
	private PhoneSeriesService phoneSeriesService;
	private PhoneOsService phoneOsService;
	private PhoneBrandService phoneBrandService;
	private PhoneModelService phoneModelService;
	private String name;
	private int id;
	private int p;
	private int phoneOsId;
	private int phoneBrandId;
	private String models;
	private PhoneSeries phoneSeries;

	public String delete() throws Exception {
		phoneSeriesService.delete(id);
		return list();
	}

	public String add() throws Exception {
		phoneSeries = new PhoneSeries();
		PhoneBrand phoneBrand = phoneBrandService.findById(phoneBrandId);
		phoneSeries.setBrand(phoneBrand);
		phoneSeries.setOs(phoneOsService.findById(phoneOsId));
		if (models != null && models.trim().length() > 1) {
			if (models.trim().length()>10) {
				this.setName(models.trim().substring(0, 10));
			}else{
				this.setName(models.trim());
			}
		} else {
			Calendar calendar = Calendar.getInstance();
			this.setName(String.valueOf(calendar.getTimeInMillis()));
		}
		phoneSeries.setName(this.getName());
		phoneSeries.setCreateTime(new Date());
		phoneSeriesService.add(phoneSeries);
		PhoneModel phoneModel = null;
		// ����ϵ��֮�����ӻ���
		if (models != null && models.length() > 0) {
			String[] model_s = models.split(",");
			for (int i = 0; i < model_s.length; i++) {
				if (model_s[i] != null && !"".equals(model_s[i].trim())) {
					phoneModel = new PhoneModel();
					phoneModel.setName(model_s[i]);
					phoneModel.setPhonebrand(phoneBrand);// ����Ʒ��
					phoneModel.setPhoneseries(phoneSeries);// ����ϵ��
					phoneModel.setCreateTime(new Date());
					phoneModelService.add(phoneModel);
				}
			}
		}
		return list();
	}

	public String edit() throws Exception {
		phoneSeries = phoneSeriesService.findById(id);
		PhoneBrand phoneBrand = phoneBrandService.findById(phoneBrandId);
		phoneSeries.setBrand(phoneBrand);
		phoneSeries.setOs(phoneOsService.findById(phoneOsId));
		if (models != null && models.trim().length() > 1) {
			if (models.trim().length()>10) {
				this.setName(models.trim().substring(0, 10));
			}else{
				this.setName(models.trim());
			}
		} else {
			Calendar calendar = Calendar.getInstance();
			this.setName(String.valueOf(calendar.getTimeInMillis()));
		}
		phoneSeries.setName(this.getName());
		phoneSeries.setCreateTime(new Date());
		PhoneModel phoneModel = null;
		// ������л���
		for (PhoneModel phoneModel_old : phoneSeries.getPhoneModelList()) {
			phoneModelService.delete(phoneModel_old.getId());
		}
		phoneSeries.setPhoneModelList(null);
		phoneSeriesService.update(phoneSeries);
		// �޸ı���ϵ��֮���������ӻ���
		if (models != null && models.length() > 0) {
			String[] model_s = models.split(",");
			for (int i = 0; i < model_s.length; i++) {
				if (model_s[i] != null && !"".equals(model_s[i].trim())) {
					phoneModel = new PhoneModel();
					phoneModel.setName(model_s[i].trim());
					phoneModel.setPhonebrand(phoneBrand);// ����Ʒ��
					phoneModel.setPhoneseries(phoneSeries);// ����ϵ��
					phoneModel.setCreateTime(new Date());
					phoneModelService.add(phoneModel);
				}
			}
		}
		return list();
	}

	public String detail() throws Exception {
		init();
		dataInit();
		phoneSeries = phoneSeriesService.findById(id);
		String models = "";
		int size = phoneSeries.getPhoneModelList().size();
		for (int i = 0; i < size; i++) {
			models += phoneSeries.getPhoneModelList().get(i).getName();
			if (i != size - 1) {
				models += ",";
			}
		}
		request.setAttribute("models", models);
		request.setAttribute("phoneSeries", phoneSeries);
		return "detail";
	}

	public String addInit() throws Exception {
		init();
		dataInit();
		phoneSeries = null;
		request.setAttribute("phoneSeries", phoneSeries);
		return "detail";
	}

	public String list() throws Exception {
		init();
		phoneSeries = new PhoneSeries();
		PageResult<PhoneSeries> pageResult = new PageResult<PhoneSeries>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		phoneSeriesService.findAll(pageResult, phoneSeries);
		request.setAttribute("pageResult", pageResult);
		return "list";
	}

	protected void dataInit() {
		PageResult<PhoneBrand> phoneBrandPageResult = new PageResult<PhoneBrand>();
		phoneBrandService.findAll(phoneBrandPageResult, null);
		PageResult<PhoneOs> phoneOsPageResult = new PageResult<PhoneOs>();
		phoneOsService.findAll(phoneOsPageResult, null);

		request.setAttribute("phoneBrandList", phoneBrandPageResult.getList());
		request.setAttribute("phoneOsList", phoneOsPageResult.getList());
	}

	public PhoneSeriesService getPhoneSeriesService() {
		return phoneSeriesService;
	}

	public void setPhoneSeriesService(PhoneSeriesService phoneSeriesService) {
		this.phoneSeriesService = phoneSeriesService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getPhoneOsId() {
		return phoneOsId;
	}

	public void setPhoneOsId(int phoneOsId) {
		this.phoneOsId = phoneOsId;
	}

	public int getPhoneBrandId() {
		return phoneBrandId;
	}

	public void setPhoneBrandId(int phoneBrandId) {
		this.phoneBrandId = phoneBrandId;
	}

	public PhoneSeries getPhoneSeries() {
		return phoneSeries;
	}

	public void setPhoneSeries(PhoneSeries phoneSeries) {
		this.phoneSeries = phoneSeries;
	}

	public PhoneOsService getPhoneOsService() {
		return phoneOsService;
	}

	public void setPhoneOsService(PhoneOsService phoneOsService) {
		this.phoneOsService = phoneOsService;
	}

	public PhoneBrandService getPhoneBrandService() {
		return phoneBrandService;
	}

	public void setPhoneBrandService(PhoneBrandService phoneBrandService) {
		this.phoneBrandService = phoneBrandService;
	}

	public String getModels() {
		return models;
	}

	public void setModels(String models) {
		this.models = models;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

}
