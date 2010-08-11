package cn.yulezu.sns.dao;

import org.springframework.stereotype.Repository;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.sns.entity.UserOnline;

@Repository
public class UserOnlineDao extends BaseDao<UserOnline> {
	/**
	 * 根据用户id查询
	 * 
	 * @param userId
	 * @return
	 */
	public UserOnline findByUsersId(Integer userId) {
		return super
				.find("from UserOnline where 1=? and users.id=?", 1, userId);
	}
}
