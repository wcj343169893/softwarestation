package cn.ss.action;

import cn.common.action.BasicAction;
import cn.common.util.Folder;
import cn.common.util.Tool;
import cn.ss.entity.Software;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareService;

public class SoftwareAction extends BasicAction {
	private static final long serialVersionUID = -5649579764754613826L;
	private SoftwareService softwareService;
	private SoftwareInfoService softwareInfoService;
	private Software software;
	/**
	 * ���id
	 */
	private int id;
	/**
	 * �����Ϣid
	 */
	private int id2;

	public String delete() throws Exception {
		init();
		software = softwareService.findById(id);
		// ��softwareinfo���¼����������
		SoftwareInfo softwareInfo = software.getSoftwareInfo();
		System.out.println("id2:" + id2);
		if (softwareInfo != null && softwareInfo.getId() == id2) {// �ж����������������Ϣid�봫������id2�Ƿ�һ��
			System.out.println("softwareInfo.getId:" + softwareInfo.getId());
			// ��ɾ���������ϵ��ļ�
			if (software != null
					&& !"".equals(software.getDownloadPath().trim())) {
				Tool.deleteFile(uploadPath, Folder.file, software
						.getSoftwareInfo().getId(), software.getDownloadPath());
			}
			// ɾ�����ݿ��еļ�¼
			softwareService.delete(id);
			int number = softwareInfo.getSoftwareList() != null ? softwareInfo
					.getSoftwareList().size() : 0;
			softwareInfo.setNumber(number);
			softwareInfoService.update(softwareInfo);
		}
		return "delete";
	}

	public SoftwareService getSoftwareService() {
		return softwareService;
	}

	public void setSoftwareService(SoftwareService softwareService) {
		this.softwareService = softwareService;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

}
