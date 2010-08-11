package cn.yulezu.sns.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.dao.UsersDao;
import cn.yulezu.sns.entity.Honor;
import cn.yulezu.sns.entity.Rank;
import cn.yulezu.sns.entity.Ranklevel;
import cn.yulezu.sns.entity.Users;

@Service
@Transactional
public class UsersService extends BaseService<Users>
{
	@Autowired
	private UsersDao usersDao;
	@Override
	@Resource(name="usersDao")
	public void setBaseDao(BaseDao<Users> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	/**
	 * 保存头衔类型
	 * @param honor
	 */
	public void saveOrUpdateHonor(Honor honor)
	{
		usersDao.saveOrUpdateHonor(honor);
	}
	
	/**
	 * 取得所有头衔类型
	 * @return
	 */
	public List<Honor> getHonorAll()
	{
		return usersDao.getHonorAll();
	}
	
	/**
	  * 取得一个头衔类型
	 * @return
	 */
	public Honor getHonorById(Integer id)
	{
		return usersDao.getHonorById(id);
	}	
	
	/**
	 * 保存头衔
	 * @param rank
	 */
	public void saveOrUpdateRank(Rank rank)
	{
		
		usersDao.saveOrUpdateRank(rank);
	}
	
	/**
	 * 查找头衔分类对应的所有头衔
	 * @param honorId
	 * @return
	 */
	public List<Rank> getRankByHonor(Integer honorId)
	{
		return usersDao.getRankByHonor(honorId);
	}	
	
	/**
	 * 取得一个头衔
	 * @param id
	 * @return
	 */
	public Rank getRankById(int id)
	{
		return usersDao.getRankById(id);
	}	
	
	/**
	 * 删除头衔
	 * @param rank
	 */
	public void deleteRank(Rank rank)
	{
		usersDao.deleteRank(rank);
	}
	
	/**
	 * 取得全部头衔等级
	 * @return
	 */
	public List<Ranklevel> getRanklevelAll()
	{
		return  usersDao.getRanklevelAll();
	}
	
	/**
	 * 取得一个头衔等级
	 * @param id
	 * @return
	 */
	public Ranklevel getRanklevelById(Integer id)
	{
		return usersDao.getRanklevelById(id);
	}
	
	/**
	 * 保存头衔等级
	 * @param ranklevel
	 */
	public void saveOrUpdateRanklevel(Ranklevel ranklevel)
	{
		usersDao.saveOrUpdateRanklevel(ranklevel);
	}
	
	/**
	 * 删除头衔等级(级联删除相应的头衔)
	 * @param rankleavel
	 */
	public void deleteRanklevel(Ranklevel rankleavel)
	{
		usersDao.deleteRanklevel(rankleavel);
	}	
	
	
}