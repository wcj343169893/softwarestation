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
	 * �޸ĺ�����
	 */
	private List<File> upload_update;
	/**
	 * �޸ĺ��������
	 */
	private List<String> upload_updateFileName;
	/**
	 * ѡ��Ĳ���ϵͳid 0_1 ҳ��id+ѡ����
	 */
	private List<String> phoneOs;

	/**
	 * �޸������֧��ƽ̨ 0_1
	 */
	private List<String> phoneOs_update;

	/**
	 * ������ 0_25 ҳ��id+������
	 */
	private List<String> softwareId;
	/**
	 * sufn=softwareUpdateFileName ��������֣��޸ĺ�Ϊ·����
	 */
	private List<String> sufn;

	/**
	 * �����ͼ
	 */
	private File image;

	/**
	 * ��ͼ������
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
