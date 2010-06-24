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

}
