package cn.ss.dto;

import java.util.ArrayList;
import java.util.List;

import cn.ss.entity.PhoneModel;
import cn.ss.entity.SoftwareInfo;
import cn.ss.entity.SoftwareType;

/**
 * ��ҳ��ʾDTO
 * <p>
 * ���¸���
 * </p>
 * <p>
 * �ȵ��Ƽ�
 * </p>
 * <p>
 * �Ӿ�
 * </p>
 * <p>
 * ����
 * </p>
 * <p>
 * �������
 * </p>
 * 
 * @author �ĳ���
 * 
 */
public class IndexDTO {
	private PhoneModel model = new PhoneModel();
	/**
	 * ���¸���
	 */
	private List<SoftwareInfo> software_newList = new ArrayList<SoftwareInfo>();

	/**
	 * �ȵ��Ƽ�
	 */
	private List<SoftwareInfo> software_recommendList = new ArrayList<SoftwareInfo>();
	/**
	 * �Ӿ�
	 */
	private List<SoftwareInfo> software_plusFineList = new ArrayList<SoftwareInfo>();
	/**
	 * ����
	 */
	private List<SoftwareInfo> software_hotList = new ArrayList<SoftwareInfo>();

	/**
	 * �������
	 */
	private List<SoftwareType> softwareTypeList = new ArrayList<SoftwareType>();

	public List<SoftwareType> getSoftwareTypeList() {
		return softwareTypeList;
	}

	public void setSoftwareTypeList(List<SoftwareType> softwareTypeList) {
		this.softwareTypeList = softwareTypeList;
	}

	public List<SoftwareInfo> getSoftware_newList() {
		return software_newList;
	}

	public void setSoftware_newList(List<SoftwareInfo> softwareNewList) {
		software_newList = softwareNewList;
	}

	public List<SoftwareInfo> getSoftware_recommendList() {
		return software_recommendList;
	}

	public void setSoftware_recommendList(
			List<SoftwareInfo> softwareRecommendList) {
		software_recommendList = softwareRecommendList;
	}

	public List<SoftwareInfo> getSoftware_plusFineList() {
		return software_plusFineList;
	}

	public void setSoftware_plusFineList(List<SoftwareInfo> softwarePlusFineList) {
		software_plusFineList = softwarePlusFineList;
	}

	public List<SoftwareInfo> getSoftware_hotList() {
		return software_hotList;
	}

	public void setSoftware_hotList(List<SoftwareInfo> softwareHotList) {
		software_hotList = softwareHotList;
	}

	public PhoneModel getModel() {
		return model;
	}

	public void setModel(PhoneModel model) {
		this.model = model;
	}

}
