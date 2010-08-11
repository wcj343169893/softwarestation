package cn.yulezu.sns.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.dao.UserOnlineDao;
import cn.yulezu.sns.entity.UserOnline;

@Service
@Transactional
public class UserOnlineService extends BaseService<UserOnline> {

	@Resource
	private UserOnlineDao userOnlineDao;

	@Override
	@Resource(name = "userOnlineDao")
	public void setBaseDao(BaseDao<UserOnline> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 根据用户id查询用户的登录信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserOnline findUserOnline(int userId) {
		return userOnlineDao.findByUsersId(userId);
	}
}
