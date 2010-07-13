package cn.ss.form;

import java.io.File;
import java.util.List;

public class SoftwareForm {
	/**
	 * �ϴ����ļ�
	 */
	private List<File> upload;
	/**
	 * �ϴ��ļ����ļ���
	 */
	private List<String> uploadFileName;

	/**
	 * �޸ĺ������
	 */
	private List<File> upload_update;
	/**
	 * �޸ĺ���������
	 */
	private List<String> upload_updateFileName;
	/**
	 * ѡ��Ĳ���ϵͳid 0_1 ҳ��id+ѡ����
	 */
	private List<String> phoneOs;

	/**
	 * �޸�������֧��ƽ̨ 0_1
	 */
	private List<String> phoneOs_update;

	/**
	 * ������� 0_25 ҳ��id+�������
	 */
	private List<String> softwareId;
	/**
	 * sufn=softwareUpdateFileName ���������֣��޸ĺ�Ϊ·����
	 */
	private List<String> sufn;

	/**
	 * ������ͼ
	 */
	private File image;

	/**
	 * ��ͼ������
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
	private int isRename;
	/**
	 * ���
	 */
	private String shortName;

	/**
	 * ��һ��
	 */
	private Integer vote;
	/**
	 * �Ƿ��ö�0��1��
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public int getIsRename() {
		return isRename;
	}

	public void setIsRename(int isRename) {
		this.isRename = isRename;
	}

}