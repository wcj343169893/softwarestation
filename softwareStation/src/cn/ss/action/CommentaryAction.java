package cn.ss.action;

import java.util.Date;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Commentary;
import cn.ss.entity.PhoneModel;
import cn.ss.entity.SoftwareInfo;
import cn.ss.service.CommentaryService;
import cn.ss.service.PhoneModelService;
import cn.ss.service.SoftwareInfoService;

public class CommentaryAction extends BasicAction {
	private static final long serialVersionUID = 7896831671543589893L;
	private SoftwareInfoService softwareInfoService;
	private PageResult<Commentary> pageResult = new PageResult<Commentary>();
	private PhoneModelService phoneModelService;
	private int p;
	/**
	 * ����id
	 */
	private int id;
	/**
	 * ���۵����
	 */
	private int sid;
	/**
	 * ��̨��ѯ��ʼʱ��
	 */
	private String beginTime;
	/**
	 * ��̨��ѯ����ʱ��
	 */
	private String endTime;
	/**
	 * ����id
	 */
	private int mid;
	/**
	 * ��������
	 */
	private String content;
	private CommentaryService commentaryService;
	private Commentary commentary;

	public String delete() throws Exception {
		if (id > 0) {
			commentaryService.delete(id);
		}
		return ls();
	}

	public String add() throws Exception {
		if (sid > 0 && mid > 0) {
			SoftwareInfo softwareInfo = softwareInfoService.findById(sid);
			PhoneModel phoneModel = phoneModelService.findById(mid);
			commentary = new Commentary();
			commentary.setContent(Tool.filterString(content));
			commentary.setCommentTime(new Date());
			commentary.setSoftwareInfo(softwareInfo);
			commentary.setPhoneModel(phoneModel);
			commentaryService.add(commentary);
		}
		return "add";
	}

	public String detail() throws Exception {
		init();
		initData();
		if (id != 0) {
		}
		return "detail";
	}

	public String ls() throws Exception {
		pageResult = new PageResult<Commentary>();
		pageResult.setPageSize(10);// ÿҳ10����¼
		pageResult.setSort("Desc");
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		if (beginTime != null && !"".equals(beginTime) && endTime != null
				&& !"".equals(endTime)) {
			if (beginTime.compareTo(endTime) > 0) {
				String tmp = beginTime;
				beginTime = endTime;
				endTime = tmp;
			}
		}
		commentaryService.findAll(pageResult, commentary, beginTime, endTime,
				0, content);
		return "ls";
	}

	// ǰ̨Ҳ��Ҫ��ҳ��ѯ
	public String list() throws Exception {
		init();
		initData();
		pageResult = new PageResult<Commentary>();
		pageResult.setPageSize(5);// ÿҳ5����¼
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		if (sid > 0) {
			commentaryService.findAll(pageResult, null, null, null, sid, null);
		}
		return "list";
	}

	private void initData() {
		SoftwareInfo softwareInfo = softwareInfoService.findById(sid);
		request.setAttribute("softwareInfo", softwareInfo);
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
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

	public SoftwareInfoService getSoftwareInfoService() {
		return softwareInfoService;
	}

	public void setSoftwareInfoService(SoftwareInfoService softwareInfoService) {
		this.softwareInfoService = softwareInfoService;
	}

	public CommentaryService getCommentaryService() {
		return commentaryService;
	}

	public void setCommentaryService(CommentaryService commentaryService) {
		this.commentaryService = commentaryService;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public PageResult<Commentary> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<Commentary> pageResult) {
		this.pageResult = pageResult;
	}

	public PhoneModelService getPhoneModelService() {
		return phoneModelService;
	}

	public void setPhoneModelService(PhoneModelService phoneModelService) {
		this.phoneModelService = phoneModelService;
	}

}
