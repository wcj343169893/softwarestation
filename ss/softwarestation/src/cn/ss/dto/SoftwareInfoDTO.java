package cn.ss.dto;

import java.util.Date;

public class SoftwareInfoDTO {
	private Integer id;

	private String name;

	/**
	 * �������
	 */
	private String softwareTypeName;

	private String description;

	private String imgPath;

	private Integer click;

	/**
	 * ��ѯ����ܼ�����
	 */
	private Integer active;
	/**
	 * ��ѯ��������ش���
	 */
	private Integer download;
	/**
	 * ��ѯ����ܽ��
	 */
	private Double allPrice;
	/**
	 * ����_�l��
	 */
	private String producer;

	private String safety;

	private Double traffic;

	private Date createTime;

	private String prompt;

	private Integer recommend = 0;

	private Integer isShow = 1;

	private Integer plusFine = 0;

	/**
	 * �������
	 */
	private Integer number = 0;

	/**
	 * �ϴ��ļ��Ƿ�����������
	 */
	private Integer isRename = 0;

	/**
	 * �ƹ㷽ʽ0.��ɣ�1.���
	 */
	private Integer promotionWay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoftwareTypeName() {
		return softwareTypeName;
	}

	public void setSoftwareTypeName(String softwareTypeName) {
		this.softwareTypeName = softwareTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Integer getClick() {
		return click;
	}

	public void setClick(Integer click) {
		this.click = click;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getSafety() {
		return safety;
	}

	public void setSafety(String safety) {
		this.safety = safety;
	}

	public Double getTraffic() {
		return traffic;
	}

	public void setTraffic(Double traffic) {
		this.traffic = traffic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public Integer getRecommend() {
		return recommend;
	}

	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}

	public Integer getPlusFine() {
		return plusFine;
	}

	public void setPlusFine(Integer plusFine) {
		this.plusFine = plusFine;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getIsRename() {
		return isRename;
	}

	public void setIsRename(Integer isRename) {
		this.isRename = isRename;
	}

	public Integer getPromotionWay() {
		return promotionWay;
	}

	public void setPromotionWay(Integer promotionWay) {
		this.promotionWay = promotionWay;
	}

}
