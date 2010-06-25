package cn.ss.dto;

import java.util.ArrayList;
import java.util.List;

import cn.ss.entity.PhoneModel;
import cn.ss.entity.SoftwareInfo;
import cn.ss.entity.SoftwareType;

/**
 * 主页显示DTO
 * <p>
 * 最新更新
 * </p>
 * <p>
 * 热点推荐
 * </p>
 * <p>
 * 加精
 * </p>
 * <p>
 * 人气
 * </p>
 * <p>
 * 软件分类
 * </p>
 * 
 * @author 文朝军
 * 
 */
public class IndexDTO {
	private PhoneModel model = new PhoneModel();
	/**
	 * 最新更新
	 */
	private List<SoftwareInfo> software_newList = new ArrayList<SoftwareInfo>();

	/**
	 * 热点推荐
	 */
	private List<SoftwareInfo> software_recommendList = new ArrayList<SoftwareInfo>();
	/**
	 * 加精
	 */
	private List<SoftwareInfo> software_plusFineList = new ArrayList<SoftwareInfo>();
	/**
	 * 人气
	 */
	private List<SoftwareInfo> software_hotList = new ArrayList<SoftwareInfo>();

	/**
	 * 软件分类
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
