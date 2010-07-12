package cn.ss.dto;

import java.util.List;

import cn.ss.entity.PhoneBrand;
import cn.ss.entity.PhoneModel;

public class BrandDTO {
	/**
	 * ŵ����25������
	 */
	private List<PhoneModel> phoneModelList;
	/**
	 * ����ŵ���ǵ����л���
	 */
	private List<PhoneBrand> phoneBrandList;

	public List<PhoneModel> getPhoneModelList() {
		return phoneModelList;
	}

	public void setPhoneModelList(List<PhoneModel> phoneModelList) {
		this.phoneModelList = phoneModelList;
	}

	public List<PhoneBrand> getPhoneBrandList() {
		return phoneBrandList;
	}

	public void setPhoneBrandList(List<PhoneBrand> phoneBrandList) {
		this.phoneBrandList = phoneBrandList;
	}

}
