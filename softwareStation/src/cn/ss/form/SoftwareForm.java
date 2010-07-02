package cn.ss.form;

import java.io.File;
import java.util.List;

public class SoftwareForm {
	/**
	 * 上传的文件
	 */
	private List<File> upload;
	/**
	 * 上传文件的文件名
	 */
	private List<String> uploadFileName;

	/**
	 * 修改后的软件
	 */
	private List<File> upload_update;
	/**
	 * 修改后软件名称
	 */
	private List<String> upload_updateFileName;
	/**
	 * 选择的操作系统id 0_1 页面id+选择编号
	 */
	private List<String> phoneOs;

	/**
	 * 修改软件的支持平台 0_1
	 */
	private List<String> phoneOs_update;

	/**
	 * 软件编号 0_25 页面id+软件编号
	 */
	private List<String> softwareId;
	/**
	 * sufn=softwareUpdateFileName 软件的名字（修改后为路径）
	 */
	private List<String> sufn;

	/**
	 * 软件截图
	 */
	private File image;

	/**
	 * 截图的名称
	 */
	private String imageFileName;
	private int id;
	private int number;
	private String name;
	private String safety;
	private int traffic;
	private String prompt;
	private String producer;
	private int recommend;
	private int isShow;
	private int plusFine;
	private int promotionWay;
	private String description;
	private String imgPath;
	/**
	 * 顶一下
	 */
	private Integer vote;
	/**
	 * 是否置顶0否，1是
	 */
	private Integer tops;

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public List<String> getSufn() {
		return sufn;
	}

	public void setSufn(List<String> sufn) {
		this.sufn = sufn;
	}

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getPhoneOs() {
		return phoneOs;
	}

	public void setPhoneOs(List<String> phoneOs) {
		this.phoneOs = phoneOs;
	}

	public List<String> getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(List<String> softwareId) {
		this.softwareId = softwareId;
	}

	public List<File> getUpload_update() {
		return upload_update;
	}

	public void setUpload_update(List<File> uploadUpdate) {
		upload_update = uploadUpdate;
	}

	public List<String> getUpload_updateFileName() {
		return upload_updateFileName;
	}

	public void setUpload_updateFileName(List<String> uploadUpdateFileName) {
		upload_updateFileName = uploadUpdateFileName;
	}

	public List<String> getPhoneOs_update() {
		return phoneOs_update;
	}

	public void setPhoneOs_update(List<String> phoneOsUpdate) {
		phoneOs_update = phoneOsUpdate;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	public int getTraffic() {
		return traffic;
	}

	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getIsShow() {
		return isShow;
	}

	public void setIsShow(int isShow) {
		this.isShow = isShow;
	}

	public int getPlusFine() {
		return plusFine;
	}

	public void setPlusFine(int plusFine) {
		this.plusFine = plusFine;
	}

	public int getPromotionWay() {
		return promotionWay;
	}

	public void setPromotionWay(int promotionWay) {
		this.promotionWay = promotionWay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}

	public Integer getTops() {
		return tops;
	}

	public void setTops(Integer tops) {
		this.tops = tops;
	}

}
