package cn.ss.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Map<Integer, String> getModelMaps(PhoneBrand phoneBrand,
			PhoneSeries phoneSeries) {
		Map<Integer, String> maps = new HashMap<Integer, String>();
		List<PhoneModel> models = null;
		List<PhoneSeries> series = null;
		if (phoneBrand != null) {
			series = phoneBrand.getPhoneseriesList();
			if (series != null) {
				for (int i = 0; i < series.size(); i++) {
					if (phoneSeries != null
							&& phoneSeries.getId() == series.get(i).getId()) {
					} else {
						models = series.get(i).getPhoneModelList();
						for (int j = 0; j < models.size(); j++) {
							maps.put(models.get(j).getId(), models.get(j)
									.getName().toLowerCase().trim());
						}
					}
				}
			}
		}
		return maps;
	}

	public String add() throws Exception {
		phoneSeries = new PhoneSeries();
		PhoneBrand phoneBrand = phoneBrandService.findById(phoneBrandId);// 查询品牌
		if (phoneBrand != null) {// 判断页面是否选择品牌
			Map<Integer, String> maps = getModelMaps(phoneBrand, null);
			phoneSeries.setBrand(phoneBrand);
			phoneSeries.setOs(phoneOsService.findById(phoneOsId));
			if (models != null && models.trim().length() > 1) {// 给我认为的系列添加名称
				if (models.trim().length() > 10) {
					this.setName(models.trim().substring(0, 10));
				} else {
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
			// 保存系列之后，增加机型
			if (models != null && models.length() > 0) {
				String[] model_s = models.split(",");
				for (int i = 0; i < model_s.length; i++) {
					if (model_s[i] != null && !"".equals(model_s[i].trim())) {
						if (!maps
								.containsValue(model_s[i].trim().toLowerCase())) {
							phoneModel = new PhoneModel();
							phoneModel.setName(model_s[i]);
							// phoneModel.setPhonebrand(phoneBrand);// 设置品牌
							phoneModel.setPhoneseries(phoneSeries);// 设置系列
							phoneModel.setCreateTime(new Date());
							phoneModelService.add(phoneModel);
						}
					}
				}
			}
		}
		return list();
	}

	public String edit() throws Exception {
		phoneSeries = phoneSeriesService.findById(id);
		PhoneBrand phoneBrand = phoneBrandService.findById(phoneBrandId);
		if (phoneBrand != null) {
			Map<Integer, String> maps = getModelMaps(phoneBrand, phoneSeries);
			phoneSeries.setBrand(phoneBrand);
			phoneSeries.setOs(phoneOsService.findById(phoneOsId));
			if (models != null && models.trim().length() > 1) {
				if (models.trim().length() > 10) {
					this.setName(models.trim().substring(0, 10));
				} else {
					this.setName(models.trim());
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				this.setName(String.valueOf(calendar.getTimeInMillis()));
			}
			phoneSeries.setName(this.getName());
			phoneSeries.setCreateTime(new Date());
			PhoneModel phoneModel = null;
			// 清除所有机型
			List<PhoneModel> phoneModel_old = phoneSeries.getPhoneModelList();
			// phoneSeries.setPhoneModelList(null);
			phoneSeriesService.update(phoneSeries);
			int size = phoneModel_old.size();
			// 修改保存系列之后，重新增加机型
			if (models != null && models.length() > 0) {
				String[] model_s = models.split(",");
				List<String> modelList = new ArrayList<String>();
				for (int i = 0; i < model_s.length; i++) {
					if (model_s[i] != null
							&& !"".equals(model_s[i].trim())
							&& !maps.containsValue(model_s[i].trim()
									.toLowerCase())) {
						boolean flag=false;
						for (int j = 0; j < modelList.size(); j++) {
							if (modelList.get(j).toLowerCase().trim().equals(
									model_s[i].trim().toLowerCase())) {
								flag=true;
							}
						}
						if (!flag) {
							modelList.add(model_s[i].trim());// 以前不存在的手机机型
						}
					}
				}
				if (modelList.size() < size) {// 判断新增机型，与以前的机型的数量
					phoneSeries.setPhoneModelList(null);// 解除关系
					for (int i = modelList.size(); i < size; i++) {
						phoneModelService.delete(phoneModel_old.get(i).getId());// 删除多余的机型
					}
				}
				for (int i = 0; i < modelList.size(); i++) {
					if (i < size) {
						phoneModel = phoneModel_old.get(i);// 修改原有的机型
						phoneModel.setName(modelList.get(i).trim());
						phoneModel.setPhoneseries(phoneSeries);// 设置系列
						phoneModel.setCreateTime(new Date());
						phoneModelService.update(phoneModel);
					} else {
						phoneModel = new PhoneModel();// 新增机型
						phoneModel.setName(modelList.get(i).trim());
						phoneModel.setPhoneseries(phoneSeries);// 设置系列
						phoneModel.setCreateTime(new Date());
						phoneModelService.add(phoneModel);
					}
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
		phoneSeriesService.findAll(pageResult, phoneSeries,models,phoneOsId,phoneBrandId);
		request.setAttribute("pageResult", pageResult);
		dataInit();
		return "list";
	}

	protected void dataInit() {
		request.setAttribute("phoneBrandList", phoneBrandService.findAll());
		request.setAttribute("phoneOsList", phoneOsService.findAll());
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
