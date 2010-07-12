package cn.ss.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ����ļ�
 * 
 * @author �ĳ���
 * 
 */
public class Software implements java.io.Serializable {

	private Integer id;

	/**
	 * �����Ϣ
	 */
	private SoftwareInfo softwareInfo;
	/**
	 * ֧��ƽ̨
	 */
	private List<PhoneOs> phoneOsList = new ArrayList<PhoneOs>();

	private Integer click;

	private Integer active;

	private Integer download;

	private String downloadPath;

	private Date createTime;

	private Double price;

	private Integer size;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SoftwareInfo getSoftwareInfo() {
		return softwareInfo;
	}

	public void setSoftwareInfo(SoftwareInfo softwareInfo) {
		this.softwareInfo = softwareInfo;
	}

	public List<PhoneOs> getPhoneOsList() {
		return phoneOsList;
	}

	public void setPhoneOsList(List<PhoneOs> phoneOsList) {
		this.phoneOsList = phoneOsList;
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

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	

}