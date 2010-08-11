package cn.yulezu.sns.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.entity.Friendgroup;

@Service
@Transactional
public class FriendgroupService extends BaseService<Friendgroup> {

	@Override
	@Resource(name = "friendgroupDao")
	public void setBaseDao(BaseDao<Friendgroup> baseDao) {
		this.baseDao = baseDao;
	}

	/**
	 * 根据用户id查询分组
	 * 
	 * @param uid
	 * @return
	 */
	public List<Friendgroup> findByUid(Integer uid) {
		return baseDao.findList("from Friendgroup where users.id=? and 1=?",
				uid, 1);
	}
}