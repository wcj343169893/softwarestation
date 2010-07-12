package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Message;

public class MessageService extends BasicService {
	/**
	 * 分页查询所有
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<Message> pageResult, Message message,
			String beginTime, String endTime) {
		StringBuffer hql = new StringBuffer("from Message r where 1=1");
		if (beginTime != null && !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(r.createTime,'%y %m %d') >= DATE_FORMAT('"
							+ beginTime + "','%y %m %d')");
		}
		if (endTime != null && !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql
					.append(" and DATE_FORMAT(r.createTime,'%y %m %d') <= DATE_FORMAT('"
							+ endTime + "','%y %m %d')");
		}
		if (message != null) {

		}
		hql.append(" order by r.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public Message findById(int id) {
		return (Message) dao.get(Message.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Message.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param message
	 */
	public void add(Message message) {
		dao.add(message);
	}

	/**
	 * 更新
	 * 
	 * @param Message
	 */
	public void update(Message message) {
		dao.update(message);
	}
}
