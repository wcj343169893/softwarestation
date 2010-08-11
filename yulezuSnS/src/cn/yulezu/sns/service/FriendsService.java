package cn.yulezu.sns.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Friends;

@Service
@Transactional
public class FriendsService extends BaseService<Friends> {

	@Override
	@Resource(name = "friendsDao")
	public void setBaseDao(BaseDao<Friends> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 根据好友分组查询好友
	 * 
	 * @param gid
	 * @return
	 */
	public List<Friends> findByGid(Integer gid) {
		return baseDao.findList("from Friends where 1=? and friendgroup.id=?",
				1, gid);
	}
}