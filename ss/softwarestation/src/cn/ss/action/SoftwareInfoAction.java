package cn.ss.action;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.xml.internal.bind.v2.runtime.output.Encoded;

import cn.common.action.BasicAction;
import cn.common.util.Config;
import cn.common.util.Folder;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.ClickLog;
import cn.ss.entity.Extension;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.PhoneOs;
import cn.ss.entity.Report;
import cn.ss.entity.Software;
import cn.ss.entity.SoftwareInfo;
import cn.ss.entity.SoftwareType;
import cn.ss.form.SoftwareForm;
import cn.ss.service.ActiveLogService;
import cn.ss.service.ClickLogService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.PhoneOsService;
import cn.ss.service.ReportService;
import cn.ss.service.SoftwareInfoService;
import cn.ss.service.SoftwareService;
import cn.ss.service.SoftwareTypeService;

public class SoftwareInfoAction extends BasicAction {
	private static final long serialVersionUID = -8310769640355327023L;
	private SoftwareInfoService softwareInfoService;
	private SoftwareService softwareService;
	private SoftwareTypeService softwareTypeService;
	private ClickLogService clickLogService;
	private PhoneOsService phoneOsService;
	private ActiveLogService activeLogService;
	private PhoneModelService phoneModelService;
	private ReportService reportService;
	private String name;
	private Integer id;
	private int p;
	private SoftwareInfo softwareInfo;
	private SoftwareForm softwareForm;
	private String beginTime;
	private String endTime;
	private String more;

	/**
	 * ���������0-9
	 * <p>
	 * 1-9 ��� ���� ���� ����(Ԫ) ����(Ԫ) ����/��� ����/���� ����/��� ����/���
	 * </p>
	 */
	private int oi = 0;
	/**
	 * ˳�򣬵��� desc asc
	 */
	private int od = 0;
	/**
	 * �Ƿ���ʾ����
	 */
	private int showData = 0;
	/**
	 * �ϴ����ļ��Ƿ�����������
	 */
	// private int rename;
	/**
	 * ��������id
	 */
	private int softwareTypeId;

	/**
	 * ����������
	 */
	private String producer;

	/**
	 * 0.��ɣ�1.���
	 */
	private int promotionWay;

	/**
	 * �ֻ��ͺ�
	 */
	private int mid;

	/**
	 * ����id
	 */
	private int rid;

	private String ids;

	/**
	 * ��ѯ�Ƿ�¼��
	 */
	private Integer isac;
	private int t = 0;

	public String d() throws Exception {
		softwareInfo = softwareInfoService.findById(id);
		if (softwareInfo.getVote() != null) {
			softwareInfo.setVote(softwareInfo.getVote() + 1);
		}
		softwareInfoService.update(softwareInfo);
		return "dingnidefei";
	}

	public String delete() throws Exception {
		// ��ɾ���ļ��Լ��ļ���Ŀ¼
		init();
		if (ids != null && !"".equals(ids.trim())) {
			String[] idList = ids.split(",");
			for (int i = 0; i < idList.length; i++) {
				if (idList[i] != null && !"".equals(idList[i].trim())) {
					try {
						int id = Integer.parseInt(idList[i].trim());
						// System.out.println("id:"+id);
						softwareInfo = softwareInfoService.findById(id);
						deleteSoftwareInfo();
					} catch (Exception e) {
						// System.out.println("delete softwareInfo error ,beacuse id is String or it is error");
						// e.printStackTrace();
					}
				}
			}
		} else {
			softwareInfo = softwareInfoService.findById(id);
			deleteSoftwareInfo();
		}
		return list();
	}

	private void deleteSoftwareInfo() {
		if (softwareInfo != null && softwareInfo.getId() > 0) {
			Tool.removeFile(uploadPath + Folder.image + "/"
					+ softwareInfo.getId());// ɾ����ͼ
			Tool.removeFile(uploadPath + Folder.file + "/"
					+ softwareInfo.getId());// ɾ������
		}
		// ��ɾ�����ݿ��е���Ϣ
		softwareInfoService.delete(softwareInfo.getId());
		// System.out.println("delete softwareInfo id="+softwareInfo.getId());
	}

	public String add_name() throws Exception {
		init();
		// request.setCharacterEncoding("UTF-8");
		// �ж��Ƿ����ͬ�����ֵ�����
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println(name);
		if (name != null && !"".equals(name.trim())) {
			try {
				name = new String(name.getBytes("ISO-8859-1"), "GB2312");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("name:" + name);
			List<SoftwareInfo> softwareInfoList = softwareInfoService
					.findByName(name);
			boolean flag = false;
			if (softwareInfoList != null && softwareInfoList.size() > 0) {
				flag = true;
			}

			if (flag) {
				out.print(name + "�Ѵ���!");
			} else {
				out.print("�����ύ!");
			}
		} else {
			out.print("����Ϊ��");
		}
		return null;
	}

	public String add() throws Exception {
		init();

		Calendar calendar = Calendar.getInstance();
		softwareInfo = new SoftwareInfo();
		softwareInfo.setSoftwareType(softwareTypeService.findById(this
				.getSoftwareTypeId()));// ������������
		softwareInfo.setClick(0);// ���õ��
		softwareInfo.setCreateTime(calendar.getTime());// �����޸�ʱ��
		softwareInfo.setName(softwareForm.getName());
		softwareInfo.setNumber(softwareForm.getNumber());
		softwareInfo.setTraffic((double) softwareForm.getTraffic());
		softwareInfo.setSafety(softwareForm.getSafety());
		softwareInfo.setPrompt(softwareForm.getPrompt());
		softwareInfo.setProducer(softwareForm.getProducer());
		softwareInfo.setRecommend(softwareForm.getRecommend());
		softwareInfo.setIsShow(softwareForm.getIsShow());
		softwareInfo.setPlusFine(softwareForm.getPlusFine());
		softwareInfo.setPromotionWay(softwareForm.getPromotionWay());
		softwareInfo.setDescription(softwareForm.getDescription());
		softwareInfo.setTops(softwareForm.getTops());
		softwareInfo.setShortName(softwareForm.getShortName());
		softwareInfo.setIsRename(softwareForm.getIsRename());
		softwareInfo.setVote(0);

		// �ж��Ƿ��н�ͼ
		if (softwareForm.getImage() != null) {
			// �ϴ���ͼ
			// ��������ͼ����
			softwareForm.setImageFileName(rename(calendar, softwareForm
					.getImageFileName()));
			softwareInfo.setImgPath(softwareForm.getImageFileName());// ���ý�ͼ����
		}
		softwareInfoService.add(softwareInfo);// ����������Ϣ
		if (softwareInfo.getImgPath() != null
				&& !"".equals(softwareInfo.getImgPath().trim())) {
			Tool.UploadFile(softwareForm.getImage(), softwareForm
					.getImageFileName(), uploadPath, Folder.image, softwareInfo
					.getId());// �ϴ�
		}
		// ��ȡ�������ļ�
		List<File> upload = softwareForm.getUpload();
		List<String> uploadFileName = softwareForm.getUploadFileName();
		Software software = null;
		if (upload != null && softwareForm.getPhoneOs() != null
				&& upload.size() > 0) {// �ж��Ƿ����ļ��ϴ�
			// ��ѯ���е�ƽ̨,��ŵ�Map��
			Map<Integer, List<PhoneOs>> maps = new HashMap<Integer, List<PhoneOs>>();
			List<PhoneOs> os_int = null;
			for (String string : softwareForm.getPhoneOs()) {
				String[] oss = string.split("_");
				if (!maps.containsKey(Integer.parseInt(oss[0]))) {
					os_int = new ArrayList<PhoneOs>();
				}
				os_int.add(phoneOsService.findById(Integer.parseInt(oss[1])));
				maps.put(Integer.parseInt(oss[0]), os_int);
			}
			List<Integer> ids = new ArrayList<Integer>();
			for (Integer integer : maps.keySet()) {
				ids.add(integer);
			}
			for (int i = 0; i < softwareInfo.getNumber(); i++) {
				calendar = Calendar.getInstance();
				String fileName = uploadFileName.get(i);
				if (extenIsTrue(maps.get(ids.get(i)), fileName
						.substring(fileName.lastIndexOf(".")))) {
					System.out.println(fileName);
					if (softwareInfo.getIsRename() != null
							&& softwareInfo.getIsRename() == 1) {
						fileName = rename(fileName);
					}
					Tool.UploadFile(upload.get(i), fileName, uploadPath,
							Folder.file, softwareInfo.getId());// �ϴ�
					// ��������
					software = new Software();
					software.setSoftwareInfo(softwareInfo);
					software.setDownloadPath(fileName);
					software.setCreateTime(calendar.getTime());
					software.setClick(0);
					software.setSize(Tool.fileSize(upload.get(i)));
					software.setDownload(0);
					software.setPhoneOsList(maps.get(ids.get(i)));
					softwareService.add(software);
				}
			}
		}

		return list();
	}

	/**
	 * �������ļ���
	 * 
	 * @param calendar
	 * @param fileName
	 * @return
	 */
	private String rename(Calendar calendar, String fileName) {
		fileName = "[361rj.com]"
				+ String.valueOf(calendar.getTimeInMillis())
				+ fileName.substring(fileName.lastIndexOf("."), fileName
						.length());
		return fileName;
	}

	/**
	 * �������ļ���ֻԭ�ļ���ǰ�����[361rj.com]
	 * 
	 * @param fileName
	 * @return
	 */
	private String rename(String fileName) {
		fileName = "[361rj.com]" + fileName;
		return fileName;
	}

	public String addInit() throws Exception {
		init();
		softwareInfo = null;
		dataInit();
		request.setAttribute("softwareInfo", softwareInfo);
		return "detail";
	}

	public String edit() throws Exception {
		init();
		Calendar calendar = Calendar.getInstance();
		softwareInfo = softwareInfoService.findById(softwareForm.getId());
		softwareInfo.setSoftwareType(softwareTypeService.findById(this
				.getSoftwareTypeId()));// ������������
		softwareInfo.setClick(0);// ���õ��
		softwareInfo.setCreateTime(calendar.getTime());// �����޸�ʱ��
		softwareInfo.setName(softwareForm.getName());
		softwareInfo.setTraffic((double) softwareForm.getTraffic());
		softwareInfo.setSafety(softwareForm.getSafety());
		softwareInfo.setPrompt(softwareForm.getPrompt());
		softwareInfo.setProducer(softwareForm.getProducer());
		softwareInfo.setRecommend(softwareForm.getRecommend());
		softwareInfo.setIsShow(softwareForm.getIsShow());
		softwareInfo.setPlusFine(softwareForm.getPlusFine());
		softwareInfo.setPromotionWay(softwareForm.getPromotionWay());
		softwareInfo.setDescription(softwareForm.getDescription());
		softwareInfo.setTops(softwareForm.getTops());
		softwareInfo.setShortName(softwareForm.getShortName());
		softwareInfo.setIsRename(softwareForm.getIsRename());
		// �޸Ľ�ͼ
		if (softwareForm.getImage() != null) {
			// ���֮ǰ�н�ͼ����ֻ�����ļ������û�У�������һ��
			if (softwareInfo.getImgPath() != null
					&& !"".equals(softwareInfo.getImgPath().trim())) {
				softwareForm.setImageFileName(softwareInfo.getImgPath());
			} else {
				// ��������ͼ����
				softwareForm.setImageFileName(rename(calendar, softwareForm
						.getImageFileName()));
				softwareInfo.setImgPath(softwareForm.getImageFileName());// ���ý�ͼ����
			}
			Tool.UploadFile(softwareForm.getImage(), softwareForm
					.getImageFileName(), uploadPath, Folder.image, softwareInfo
					.getId());// �ϴ�
		}
		softwareInfoService.update(softwareInfo);
		// ��ѯ���е�ƽ̨,��ŵ�Map��
		// �޸�������ƽ̨
		Map<Integer, List<PhoneOs>> maps_update = new HashMap<Integer, List<PhoneOs>>();
		List<PhoneOs> os_int = null;
		List<String> phoneOs_old = softwareForm.getPhoneOs_update();
		if (phoneOs_old != null) {// �ж��Ƿ����޸ĵ�����
			for (String string : softwareForm.getPhoneOs_update()) {
				String[] oss = string.split("_");
				if (!maps_update.containsKey(Integer.parseInt(oss[0]))) {
					os_int = new ArrayList<PhoneOs>();
				}
				os_int.add(phoneOsService.findById(Integer.parseInt(oss[1])));
				maps_update.put(Integer.parseInt(oss[0]), os_int);
			}
		}
		// ����������ƽ̨
		Map<Integer, List<PhoneOs>> maps = new HashMap<Integer, List<PhoneOs>>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> phoneOs_new = softwareForm.getPhoneOs();
		if (phoneOs_new != null) {// �ж��Ƿ�������������
			for (String string : softwareForm.getPhoneOs()) {
				String[] oss = string.split("_");
				if (!maps.containsKey(Integer.parseInt(oss[0]))) {
					os_int = new ArrayList<PhoneOs>();
				}
				os_int.add(phoneOsService.findById(Integer.parseInt(oss[1])));
				maps.put(Integer.parseInt(oss[0]), os_int);
			}
			for (Integer integer : maps.keySet()) {
				ids.add(integer);
			}
		}
		int softwaresize = 0;
		List<File> softwares = softwareForm.getUpload_update();
		List<String> softwares_FileName = softwareForm
				.getUpload_updateFileName();
		// �޸�����
		List<String> softwareId = softwareForm.getSoftwareId();
		List<String> sufns = softwareForm.getSufn();// ֻ������������
		if (phoneOs_old != null && softwareId != null && softwareId.size() > 0) {// ֮ǰ������
			Software software = null;
			for (String string : softwareId) {
				String[] sid_str = string.split("_");
				int index = Integer.parseInt(sid_str[0].trim());
				int sid = Integer.parseInt(sid_str[1].trim());
				software = softwareService.findById(sid);
				software.setSoftwareInfo(softwareInfo);
				// ����ƽ̨
				software.setPhoneOsList(maps_update.get(index));
				// ��������
				if (softwares != null && softwares.size() > 0) {// ����������
					// �ж�������û�������ϴ�
					if (!software.getDownloadPath().equals(sufns.get(index))) {
						String fileName = softwares_FileName.get(0);// ���»�ȡ�ϴ����ļ���
						if (extenIsTrue(maps_update.get(index), fileName
								.substring(fileName.lastIndexOf(".")))) {
							// ��ɾ��ԭ���������ļ�
							Tool.deleteFile(uploadPath, Folder.file,
									softwareInfo.getId(), software
											.getDownloadPath());
							if (softwareInfo.getIsRename() != null
									&& softwareInfo.getIsRename() == 1) {
								fileName = rename(fileName);
							}
							software.setDownloadPath(fileName);
							File softwareFile = softwares.get(0);
							Tool.UploadFile(softwareFile, fileName, uploadPath,
									Folder.file, softwareInfo.getId());// �ϴ���һ���ļ�
							software.setSize(Tool.fileSize(softwareFile));
						}
						softwares.remove(0);
						softwares_FileName.remove(0);
					}
				}
				softwaresize++;
				softwareService.update(software);// ����������Ϣ
			}
		}
		// ��������������
		// ��ȡ�������ļ�
		List<File> upload = softwareForm.getUpload();
		List<String> uploadFileName = softwareForm.getUploadFileName();
		Software software = null;
		if (phoneOs_new != null && upload != null && upload.size() > 0
				&& maps.size() > 0) {// �ж��Ƿ����ļ��ϴ�
			for (int i = 0; i < upload.size(); i++) {
				calendar = Calendar.getInstance();
				String fileName = uploadFileName.get(i);
				if (extenIsTrue(maps.get(ids.get(i)), fileName
						.substring(fileName.lastIndexOf(".")))) {
					File f = upload.get(i);
					if (softwareInfo.getIsRename() != null
							&& softwareInfo.getIsRename() == 1) {
						fileName = rename(fileName);
					}
					System.out.println("fileName:" + fileName + "\t length:"
							+ f.length());
					Tool.UploadFile(f, fileName, uploadPath, Folder.file,
							softwareInfo.getId());// �ϴ�
					// ��������
					software = new Software();
					software.setSoftwareInfo(softwareInfo);
					software.setDownloadPath(fileName);
					software.setCreateTime(calendar.getTime());
					software.setClick(0);
					software.setDownload(0);
					software.setSize(Tool.fileSize(f));
					software.setPhoneOsList(maps.get(ids.get(i)));
					softwareService.add(software);
					softwaresize++;
				}
			}
		}
		// ���¸����������ļ�����
		softwareInfo.setNumber(softwaresize);
		softwareInfoService.update(softwareInfo);
		return list();
	}

	/**
	 * ��ʼ������
	 */
	protected void dataInit() {
		// PageResult<PhoneOs> phoneOsPageResult = new PageResult<PhoneOs>();
		// PageResult<SoftwareType> softwareTypePageResult = new
		// PageResult<SoftwareType>();
		// softwareTypeService.findAll( null);
		init();
		String phoneOs_s = "";
		List<PhoneOs> phoneOsList = (List<PhoneOs>) phoneOsService.findAll();
		int size = phoneOsList.size();
		for (int i = 0; i < phoneOsList.size(); i++) {
			phoneOs_s += phoneOsList.get(i).getId() + ","
					+ phoneOsList.get(i).getName();
			if (i != size - 1) {
				phoneOs_s += ",";
			}
		}
		request.setAttribute("phoneOs_s", phoneOs_s);
		request.setAttribute("phoneOsList", phoneOsList);
		request.setAttribute("softwareTypeList", softwareTypeService
				.findAll(null));
	}

	public String detail() throws Exception {
		init();
		dataInit();
		softwareInfo = softwareInfoService.findById(id);
		return "detail";
	}

	public String search() throws Exception {
		init();
		PageResult<SoftwareInfo> pageResult = new PageResult<SoftwareInfo>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		if (name != null && !"".equals(name.trim())) {
			softwareInfoService.findAll(pageResult, null, null, null, name, 11,
					1, 0, null, 4);
			request.setAttribute("pageResult", pageResult);
		}
		return "search";
	}

	public String show() throws Exception {
		init();
		// dataInit();
		softwareInfo = softwareInfoService.findById(id);
		if (softwareInfo == null) {
			return "error";
		}

		// ��ѯ�����������ĳ������֧��mid
		PhoneOs phoneOs = null;
		PhoneModel model = null;
		int osId = 0;
		List<Extension> extension = null;
		if (mid > 0) {
			model = phoneModelService.findById(mid);
			phoneOs = model.getPhoneseries().getOs();// �ֻ��Ĳ���ϵͳ
			extension = phoneOs.getExtensionList();
			osId = phoneOs.getId();
		} else {
			String from = "/showsoftwareInfo.php?id=" + id;
			response.sendRedirect("setmodel.php?from=" + from);
			return null;
		}
		// List<Software> softwareList = new ArrayList<Software>();// ֧�ִ˲���ϵͳ������
		Software software_p = null;// ������Ͱ�
		Software software_java = null;// ͨ�ð�
		boolean flag = false;
		String name = "";
		frist: for (Software software : softwareInfo.getSoftwareList()) {// �ж��Ƿ����ʺϻ��͵�����
			name = software.getDownloadPath();
			name = name.substring(name.lastIndexOf("."));// ��ȡ�ļ��ĺ�׺
			List<PhoneOs> osList = software.getPhoneOsList();
			for (int i = 0; i < osList.size(); i++) {
				int osId2 = osList.get(i).getId();
				if (phoneOs != null && osId == osId2) {// ƥ�����ϵͳ
					software_p = software;
					flag = true;
					request.setAttribute("name", name.substring(1));
					break frist;
				}
			}
		}
		if (!flag) {// ���û���ʺϴ˲���ϵͳ����������Ѱ��֧�ֵĺ�׺
			secend: for (Software software : softwareInfo.getSoftwareList()) {// �ж��Ƿ����ʺϻ��͵�����
				name = software.getDownloadPath();
				name = name.substring(name.lastIndexOf("."));// ��ȡ�ļ��ĺ�׺
				// System.out.println("extension��" + name);
				for (int i = 0; i < extension.size(); i++) {
					if (extension.get(i).getName().toLowerCase().equals(
							name.toLowerCase())) {
						software_p = software;
						flag = true;
						request.setAttribute("name", name.substring(1));
						break secend;
					}
				}
			}
		}
		// System.out.println("name:" + name);
		if (phoneOs != null && !phoneOs.getName().equals("java") && !flag) {
			for (Software software : softwareInfo.getSoftwareList()) {
				name = software.getDownloadPath();
				name = name.substring(name.lastIndexOf("."));// ��ȡ�ļ��ĺ�׺
				// System.out.println("��׺2��" + name);
				if (name.toLowerCase().equals(".jar")
						|| name.toLowerCase().equals(".jad")) {
					software_java = software;
					request.setAttribute("software_java_name", name
							.substring(1));
					break;
				}
			}
		}
		// ��¼�����ĵ��
		ClickLog clickLog = new ClickLog();
		clickLog.setClickTime(new Date());
		clickLog.setNumber(1);
		clickLog.setSoftwareInfo(softwareInfo);
		clickLogService.add(clickLog);
		int download = 0;
		for (int i = 0; i < softwareInfo.getDownloadLogList().size(); i++) {
			download += softwareInfo.getDownloadLogList().get(i).getNumber();
		}
		request.setAttribute("download", download);
		request.setAttribute("model", model);
		request.setAttribute("software_p", software_p);
		request.setAttribute("software_java", software_java);
		return "show";
	}

	public String list() throws Exception {
		init();
		softwareInfo = new SoftwareInfo();
		PageResult<SoftwareInfo> pageResult = new PageResult<SoftwareInfo>();
		if (beginTime != null && !"".equals(beginTime) && endTime != null
				&& !"".equals(endTime)) {
			if (beginTime.compareTo(endTime) > 0) {
				String tmp = beginTime;
				beginTime = endTime;
				endTime = tmp;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}

		softwareInfoService.findAll(pageResult, softwareInfo, beginTime,
				endTime, name, oi, od, softwareTypeId, producer, promotionWay);
		// List<SoftwareInfo> softwareInfoList = pageResult.getList();
		// pageResult.setList(softwareInfoList);
		request.setAttribute("pageResult", pageResult);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("yesterday", Tool
				.beforeOrAfterDate(new Date(), -1));// ��������
		request.setAttribute("endTime", endTime);
		dataInit();
		return "list";
	}

	/**
	 * �жϺ�׺���Ƿ�֧��ƽ̨
	 * 
	 * @param phoneOsList
	 *            ƽ̨
	 * @param exten
	 *            ��׺�� ���磺 .jar
	 * @return
	 */
	public boolean extenIsTrue(List<PhoneOs> phoneOsList, String exten) {
		boolean flag = false;
		if (phoneOsList != null && exten != null && exten.length() > 0) {
			List<Extension> extesionList = null;
			for (PhoneOs phoneOs : phoneOsList) {
				extesionList = phoneOs.getExtensionList();
				for (Extension extension : extesionList) {
					if (extension.getName().trim().toLowerCase().equals(
							exten.trim().toLowerCase())) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public SoftwareService getSoftwareService() {
		return softwareService;
	}

	public void setSoftwareService(SoftwareService softwareService) {
		this.softwareService = softwareService;
	}

	public SoftwareTypeService getSoftwareTypeService() {
		return softwareTypeService;
	}

	public void setSoftwareTypeService(SoftwareTypeService softwareTypeService) {
		this.softwareTypeService = softwareTypeService;
	}

	public PhoneOsService getPhoneOsService() {
		return phoneOsService;
	}

	public void setPhoneOsService(PhoneOsService phoneOsService) {
		this.phoneOsService = phoneOsService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public SoftwareInfo getSoftwareInfo() {
		return softwareInfo;
	}

	public void setSoftwareInfo(SoftwareInfo softwareInfo) {
		this.softwareInfo = softwareInfo;
	}

	public SoftwareForm getSoftwareForm() {
		return softwareForm;
	}

	public void setSoftwareForm(SoftwareForm softwareForm) {
		this.softwareForm = softwareForm;
	}

	public int getSoftwareTypeId() {
		return softwareTypeId;
	}

	public void setSoftwareTypeId(int softwareTypeId) {
		this.softwareTypeId = softwareTypeId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getShowData() {
		return showData;
	}

	public void setShowData(int showData) {
		this.showData = showData;
	}

	public ActiveLogService getActiveLogService() {
		return activeLogService;
	}

	public void setActiveLogService(ActiveLogService activeLogService) {
		this.activeLogService = activeLogService;
	}

	public int getOi() {
		return oi;
	}

	public void setOi(int oi) {
		this.oi = oi;
	}

	public int getOd() {
		return od;
	}

	public void setOd(int od) {
		this.od = od;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public int getPromotionWay() {
		return promotionWay;
	}

	public void setPromotionWay(int promotionWay) {
		this.promotionWay = promotionWay;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

	public ClickLogService getClickLogService() {
		return clickLogService;
	}

	public void setClickLogService(ClickLogService clickLogService) {
		this.clickLogService = clickLogService;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getIsac() {
		return isac;
	}

	public void setIsac(Integer isac) {
		this.isac = isac;
	}

	public int getT() {
		return t;
	}

	public void setT(int t) {
		this.t = t;
	}

}