package cn.yulezu.sns.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.sns.entity.Honor;
import cn.yulezu.sns.entity.Rank;
import cn.yulezu.sns.entity.Ranklevel;
import cn.yulezu.sns.entity.Users;

@Repository
public class UsersDao extends BaseDao<Users>
{
	
	/**
	 * 保存头衔类型
	 * @param honor
	 */
	public void saveOrUpdateHonor(Honor honor)
	{
		this.getSession().saveOrUpdate(honor);
	}
	
	/**
	 * 取得所有头衔类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Honor> getHonorAll()
	{
		String hql = "from Honor";
	 	return this.getSession().createQuery(hql).list();
	}
	
	/**
	  * 取得一个头衔类型
	 * @return
	 */
	public Honor getHonorById(Integer id)
	{
		return (Honor)this.getSession().createQuery("from Honor where id=?").setInteger(0,id).uniqueResult();
	}
	
	/**
	 * 保存头衔
	 * @param rank
	 */
	public void saveOrUpdateRank(Rank rank)
	{
		this.getSession().saveOrUpdate(rank);
	}
	
	/**
	 * 查找头衔分类对应的所有头衔
	 * @param honorId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Rank> getRankByHonor(Integer honorId)
	{
		String hql = "from Rank as r where r.honor.id=?";
		return this.getSession().createQuery(hql).setInteger(0, honorId).list();
	}	
	
	/**
	 * 取得一个头衔
	 * @param id
	 * @return
	 */
	public Rank getRankById(int id)
	{
		String hql = "from Rank where id=?";
		return (Rank) this.getSession().createQuery(hql).setInteger(0,id).uniqueResult();
	}
	
	
	/**
	 * 删除头衔
	 * @param rank
	 */
	public void deleteRank(Rank rank)
	{
		this.getSession().delete(rank);
	}
	
	/**
	 * 取得全部头衔等级
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ranklevel> getRanklevelAll()
	{
		return  this.getSession().createQuery("from Ranklevel as rl order by rl.level").list();
	}
	
	/**
	 * 取得一个头衔等级
	 * @param id
	 * @return
	 */
	public Ranklevel getRanklevelById(Integer id)
	{
		return (Ranklevel)this.getSession().createQuery("from Ranklevel where level=?").setInteger(0, id).uniqueResult();
	}
	
	/**
	 * 保存头衔等级
	 * @param ranklevel
	 */
	public void saveOrUpdateRanklevel(Ranklevel ranklevel)
	{
		this.getSession().saveOrUpdate(ranklevel);
	}
	
	/**
	 * 删除头衔等级(级联删除相应的头衔)
	 * @param rankleavel
	 */
	public void deleteRanklevel(Ranklevel rankleavel)
	{
		this.getSession().delete(rankleavel);
	}
	
}