package cn.ss.action;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.Message;
import cn.ss.service.MessageService;

public class MessageAction extends BasicAction {
	private static final long serialVersionUID = -8962276286156832842L;
	private MessageService messageService;
	private int p;
	private int id;
	private String beginTime;
	private String endTime;
	private Message message;
	private PageResult<Message> pageResult;

	public String delete() throws Exception {
		if (id > 0) {
			messageService.delete(id);
		}
		return list();
	}

	// 增加操作
	@Override
	public String execute() throws Exception {

		return "message";
	}

	/**
	 * 前台成功跳转，防止重复提交信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ok() throws Exception {

		return "ok";
	}

	public String edit() throws Exception {
		return list();
	}

	public String detail() throws Exception {
		init();
		return "detail";
	}

	public String list() throws Exception {
		init();
		pageResult = new PageResult<Message>();
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
		messageService.findAll(pageResult, message, beginTime, endTime);
		return "list";
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
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

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public PageResult<Message> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<Message> pageResult) {
		this.pageResult = pageResult;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
