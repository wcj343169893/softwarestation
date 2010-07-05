package cn.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SoftwareInfo implements java.io.Serializable {

	private Integer id;

	private String name;

	/**
	 * 软件分类
	 */
	private SoftwareType softwareType;

	private String description;

	private String imgPath;

	private Integer click;

	/**
	 * 查询后的总激活数
	 */
	private Integer active;
	/**
	 * 查询后的总下载次数
	 */
	private Integer download;
	/**
	 * 查询后的总金额
	 */
	private Double allPrice;
	/**
	 * 软件開發商
	 */
	private String producer;

	private String safety;

	private Double traffic;

	private Date createTime;

	private String prompt;

	private Integer recommend = 0;

	private Integer isShow = 1;

	private Integer plusFine = 0;
	private String shortName;

	/**
	 * 顶一下
	 */
	private Integer vote;
	/**
	 * 是否置顶0否，1是
	 */
	private Integer tops;

	/**
	 * 软件个数
	 */
	private Integer number = 0;

	/**
	 * 上传文件是否允许重命名
	 */
	private Integer isRename = 0;

	/**
	 * 推广方式0.提成，1.免费
	 */
	private int promotionWay;

	/**
	 * 软件信息
	 */
	private List<Software> softwareList = new ArrayList<Software>();

	/**
	 * 激活日志（每日一篇）
	 */
	private List<ActiveLog> activeLogList = new ArrayList<ActiveLog>();

	/**
	 * 点击日志 （每日一篇）
	 */
	private List<ClickLog> clickLogList = new ArrayList<ClickLog>();

	/**
	 * 下载日志（每日一篇）
	 */
	private List<DownloadLog> downloadLogList = new ArrayList<DownloadLog>();

	/**
	 * 软件评论
	 */
	private List<Commentary> commentaryList = new ArrayList<Commentary>();

	public List<Software> getSoftwareList() {
		return softwareList;
	}

	public void setSoftwareList(List<Software> softwareList) {
		this.softwareList = softwareList;
	}

	public int getPromotionWay() {
		return promotionWay;
	}

	public void setPromotionWay(int promotionWay) {
		this.promotionWay = promotionWay;
	}

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

	public SoftwareType getSoftwareType() {
		return softwareType;
	}

	public void setSoftwareType(SoftwareType softwareType) {
		this.softwareType = softwareType;
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

	public List<ActiveLog> getActiveLogList() {
		return activeLogList;
	}

	public void setActiveLogList(List<ActiveLog> activeLogList) {
		this.activeLogList = activeLogList;
	}

	public List<ClickLog> getClickLogList() {
		return clickLogList;
	}

	public void setClickLogList(List<ClickLog> clickLogList) {
		this.clickLogList = clickLogList;
	}

	public List<DownloadLog> getDownloadLogList() {
		return downloadLogList;
	}

	public void setDownloadLogList(List<DownloadLog> downloadLogList) {
		this.downloadLogList = downloadLogList;
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

	public List<Commentary> getCommentaryList() {
		return commentaryList;
	}

	public void setCommentaryList(List<Commentary> commentaryList) {
		this.commentaryList = commentaryList;
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

}