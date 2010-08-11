package cn.yulezu.sns.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Message;

@Service
@Transactional
public class MessageService extends BaseService<Message> {
	@Override
	@Resource(name = "messageDao")
	public void setBaseDao(BaseDao<Message> baseDao) {
		this.baseDao = baseDao;
	}

}
