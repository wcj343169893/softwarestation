package cn.ss.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.common.action.BasicAction;
import cn.common.util.Folder;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.ClickLog;
import cn.ss.entity.Extension;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.PhoneOs;
import cn.ss.entity.Software;
import cn.ss.entity.SoftwareInfo;
import cn.ss.entity.SoftwareType;
import cn.ss.form.SoftwareForm;
import cn.ss.service.ActiveLogService;
import cn.ss.service.ClickLogService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.PhoneOsService;
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
	private String name;
	private Integer id;
	private int p;
	private SoftwareInfo softwareInfo;
	private SoftwareForm softwareForm;
	private String beginTime;
	private String endTime;

	/**
	 * 无语的排序0-9
	 * <p>
	 * 1-9 点击 下载 激活 单价(元) 收入(元) 下载/点击 激活/下载 激活/点击 收入/点击
	 * </p>
	 */
	private int oi = 0;
	/**
	 * 顺序，倒序 desc asc
	 */
	private int od = 0;
	/**
	 * 是否显示数据
	 */
	private int showData = 0;
	/**
	 * 上传的文件是否允许重命名
	 */
	// private int rename;
	/**
	 * 软件分类id
	 */
	private int softwareTypeId;

	/**
	 * 软件开发商
	 */
	private String producer;

	/**
	 * 0.提成，1.免费
	 */
	private int promotionWay;

	/**
	 * 手机型号
	 */
	private int mid;

	public String delete() throws Exception {
		// 先删除文件以及文件的目录
		init();
		softwareInfo = softwareInfoService.findById(id);
		if (softwareInfo != null && softwareInfo.getId() > 0) {
			Tool.removeFile(uploadPath + Folder.image + "/"
					+ softwareInfo.getId());// 删除截图
			Tool.removeFile(uploadPath + Folder.file + "/"
					+ softwareInfo.getId());// 删除软件
		}
		// 再删除数据库中的信息
		softwareInfoService.delete(id);
		return list();
	}

	public String add() throws Exception {
		init();
		Calendar calendar = Calendar.getInstance();
		softwareInfo = new SoftwareInfo();
		softwareInfo.setSoftwareType(softwareTypeService.findById(this
				.getSoftwareTypeId()));// 设置软件分类
		softwareInfo.setClick(0);// 设置点击
		softwareInfo.setCreateTime(calendar.getTime());// 设置修改时间
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

		// 判断是否有截图
		if (softwareForm.getImage() != null) {
			// 上传截图
			// 重命名截图名称
			softwareForm.setImageFileName(rename(calendar, softwareForm
					.getImageFileName()));
			softwareInfo.setImgPath(softwareForm.getImageFileName());// 设置截图名称
		}
		softwareInfoService.add(softwareInfo);// 保存软件信息
		if (softwareInfo.getImgPath() != null
				&& !"".equals(softwareInfo.getImgPath().trim())) {
			Tool.UploadFile(softwareForm.getImage(), softwareForm
					.getImageFileName(), uploadPath, Folder.image, softwareInfo
					.getId());// 上传
		}
		// 获取到所有文件
		List<File> upload = softwareForm.getUpload();
		List<String> uploadFileName = softwareForm.getUploadFileName();
		Software software = null;
		if (upload != null && upload.size() > 0) {// 判断是否有文件上传
			// 查询所有的平台,存放到Map中
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
					// fileName = rename(calendar, fileName);
					Tool.UploadFile(upload.get(i), fileName, uploadPath,
							Folder.file, softwareInfo.getId());// 上传
					// 新增软件
					software = new Software();
					software.setSoftwareInfo(softwareInfo);
					software.setDownloadPath(fileName);
					software.setCreateTime(calendar.getTime());
					software.setClick(0);
					software.setDownload(0);
					software.setPhoneOsList(maps.get(ids.get(i)));
					softwareService.add(software);
				}
			}
		}

		return list();
	}

	/**
	 * 重命名文件名
	 * 
	 * @param calendar
	 * @param fileName
	 * @return
	 */
	private String rename(Calendar calendar, String fileName) {
		fileName = "[yulezu]"
				+ String.valueOf(calendar.getTimeInMillis())
				+ fileName.substring(fileName.lastIndexOf("."), fileName
						.length());
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
				.getSoftwareTypeId()));// 设置软件分类
		softwareInfo.setClick(0);// 设置点击
		softwareInfo.setCreateTime(calendar.getTime());// 设置修改时间
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
		// 修改截图
		if (softwareForm.getImage() != null) {
			// 如果之前有截图，则只覆盖文件，如果没有，则新增一个
			if (softwareInfo.getImgPath() != null
					&& !"".equals(softwareInfo.getImgPath().trim())) {
				softwareForm.setImageFileName(softwareInfo.getImgPath());
			} else {
				// 重命名截图名称
				softwareForm.setImageFileName(rename(calendar, softwareForm
						.getImageFileName()));
				softwareInfo.setImgPath(softwareForm.getImageFileName());// 设置截图名称
			}
			Tool.UploadFile(softwareForm.getImage(), softwareForm
					.getImageFileName(), uploadPath, Folder.image, softwareInfo
					.getId());// 上传
		}
		softwareInfoService.update(softwareInfo);
		// 查询所有的平台,存放到Map中
		// 修改软件的平台
		Map<Integer, List<PhoneOs>> maps_update = new HashMap<Integer, List<PhoneOs>>();
		List<PhoneOs> os_int = null;
		List<String> phoneOs_old = softwareForm.getPhoneOs_update();
		if (phoneOs_old != null) {// 判断是否有修改的软件
			for (String string : softwareForm.getPhoneOs_update()) {
				String[] oss = string.split("_");
				if (!maps_update.containsKey(Integer.parseInt(oss[0]))) {
					os_int = new ArrayList<PhoneOs>();
				}
				os_int.add(phoneOsService.findById(Integer.parseInt(oss[1])));
				maps_update.put(Integer.parseInt(oss[0]), os_int);
			}
		}
		// 新增软件的平台
		Map<Integer, List<PhoneOs>> maps = new HashMap<Integer, List<PhoneOs>>();
		List<Integer> ids = new ArrayList<Integer>();
		List<String> phoneOs_new = softwareForm.getPhoneOs();
		if (phoneOs_new != null) {// 判断是否有新增的软件
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
		// 修改软件
		List<String> softwareId = softwareForm.getSoftwareId();
		List<String> sufns = softwareForm.getSufn();// 只在这里起作用
		if (phoneOs_old != null && softwareId != null && softwareId.size() > 0) {// 之前有软件
			Software software = null;
			for (String string : softwareId) {
				String[] sid_str = string.split("_");
				int index = Integer.parseInt(sid_str[0].trim());
				int sid = Integer.parseInt(sid_str[1].trim());
				software = softwareService.findById(sid);
				software.setSoftwareInfo(softwareInfo);
				// 更新平台
				software.setPhoneOsList(maps_update.get(index));
				// 更新软件
				if (softwares != null && softwares.size() > 0) {// 有软件更新
					// 判断软件有没有重新上传
					if (!software.getDownloadPath().equals(sufns.get(index))) {
						String fileName = softwares_FileName.get(0);// 重新获取上传的文件名
						if (extenIsTrue(maps_update.get(index), fileName
								.substring(fileName.lastIndexOf(".")))) {
							// 先删除原来的软件文件
							Tool.deleteFile(uploadPath, Folder.file,
									softwareInfo.getId(), software
											.getDownloadPath());
							software.setDownloadPath(fileName);
							System.out.println(fileName);
							File softwareFile = softwares.get(0);
							Tool.UploadFile(softwareFile, fileName, uploadPath,
									Folder.file, softwareInfo.getId());// 上传第一个文件
						}
						softwares.remove(0);
						softwares_FileName.remove(0);
					}
				}
				softwaresize++;
				softwareService.update(software);// 更新软件信息
			}
		}
		// 保存新增的软件
		// 获取到所有文件
		List<File> upload = softwareForm.getUpload();
		List<String> uploadFileName = softwareForm.getUploadFileName();
		Software software = null;
		if (phoneOs_new != null && upload != null && upload.size() > 0
				&& maps.size() > 0) {// 判断是否有文件上传
			for (int i = 0; i < upload.size(); i++) {
				calendar = Calendar.getInstance();
				String fileName = uploadFileName.get(i);
				if (extenIsTrue(maps.get(ids.get(i)), fileName
						.substring(fileName.lastIndexOf(".")))) {
					System.out.println(fileName);
					Tool.UploadFile(upload.get(i), fileName, uploadPath,
							Folder.file, softwareInfo.getId());// 上传
					// 新增软件
					software = new Software();
					software.setSoftwareInfo(softwareInfo);
					software.setDownloadPath(fileName);
					software.setCreateTime(calendar.getTime());
					software.setClick(0);
					software.setDownload(0);
					software.setPhoneOsList(maps.get(ids.get(i)));
					softwareService.add(software);
					softwaresize++;
				}
			}
		}
		// 重新更新软件的文件数量
		softwareInfo.setNumber(softwaresize);
		softwareInfoService.update(softwareInfo);
		return list();
	}

	/**
	 * 初始化数据
	 */
	protected void dataInit() {
		PageResult<PhoneOs> phoneOsPageResult = new PageResult<PhoneOs>();
		phoneOsService.findAll(phoneOsPageResult, null);
		PageResult<SoftwareType> softwareTypePageResult = new PageResult<SoftwareType>();
		softwareTypeService.findAll(softwareTypePageResult, null);

		String phoneOs_s = "";
		List<PhoneOs> phoneOsList = (List<PhoneOs>) phoneOsPageResult.getList();
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
		request.setAttribute("softwareTypeList", softwareTypePageResult
				.getList());
	}

	public String detail() throws Exception {
		init();
		dataInit();
		softwareInfo = softwareInfoService.findById(id);
		return "detail";
	}

	public String show() throws Exception {
		init();
		// dataInit();
		softwareInfo = softwareInfoService.findById(id);
		// 查询该软件下面的某个包，支持mid
		PhoneOs phoneOs = null;
		PhoneModel model = null;
		List<Extension> extension = null;
		if (mid > 0) {
			model = phoneModelService.findById(mid);
			phoneOs = model.getPhoneseries().getOs();// 手机的操作系统
			extension = phoneOs.getExtensionList();
		}
		// List<Software> softwareList = new ArrayList<Software>();// 支持此操作系统的软件
		Software software_p = null;// 适配机型版
		Software software_java = null;// 通用版
		boolean flag = false;
		for (Software software : softwareInfo.getSoftwareList()) {// 判断是否有适合机型的软件
			String name = software.getDownloadPath();
			name = name.substring(name.lastIndexOf("."));// 获取文件的后缀
			System.out.println("后缀：" + name);
			for (int i = 0; i < extension.size(); i++) {
				System.out.println("extension" + extension.get(i).getName());
				if (extension.get(i).getName().equals(name)) {
					software_p = software;
					flag = true;
					System.out.println("匹配成功！" + name);
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		for (Software software : softwareInfo.getSoftwareList()) {
			String name = software.getDownloadPath();
			name = name.substring(name.lastIndexOf("."));// 获取文件的后缀
			System.out.println("后缀：" + name);
			if (name.equals(".jar")) {
				software_java = software;
				break;
			}
		}
		// 记录软件的点击
		ClickLog clickLog = new ClickLog();
		clickLog.setClickTime(new Date());
		clickLog.setNumber(1);
		clickLog.setSoftwareInfo(softwareInfo);
		clickLogService.add(clickLog);

		// request.setAttribute("softwareList", softwareList);
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
				.beforeOrAfterDate(new Date(), -1));// 昨天日期
		request.setAttribute("endTime", endTime);
		dataInit();
		return "list";
	}

	/**
	 * 判断后缀名是否支持平台
	 * 
	 * @param phoneOsList
	 *            平台
	 * @param exten
	 *            后缀名 例如： .jar
	 * @return
	 */
	public boolean extenIsTrue(List<PhoneOs> phoneOsList, String exten) {
		boolean flag = false;
		if (phoneOsList != null && exten != null && exten.length() > 0) {
			List<Extension> extesionList = null;
			for (PhoneOs phoneOs : phoneOsList) {
				extesionList = phoneOs.getExtensionList();
				for (Extension extension : extesionList) {
					if (extension.getName().equals(exten)) {
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

}
