package cn.yulezu.sns.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.sns.entity.City;
import cn.yulezu.sns.entity.Education;
import cn.yulezu.sns.entity.Userinfo;

@Repository
public class UserinfoDao extends BaseDao<Userinfo>
{
	/**
	 * 取得所有城市
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<City> getCityAll()
	{
		return this.getSession().createQuery("from City").list();
	}
	
	/**
	 * 取得所有学历
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Education> getEducationALl()
	{
		return this.getSession().createQuery("from Education").list();
	}
	
	/**
	 * 保存或者更新城市
	 * @param city
	 */
	public void saveCityOrUpdate(City city)
	{
		this.getSession().saveOrUpdate(city);
	}
	
	/**
	 * 保存或者更新学历
	 * @param city
	 */
	public void saveEducationOrUpdate(Education education)
	{
		this.getSession().saveOrUpdate(education);
	}
	/**
	 * 查找一个城市
	 * @param id
	 * @return
	 */
	public City getCityById(Integer id)
	{
		return (City)this.getSession().createQuery("from City where id=?").setInteger(0, id).uniqueResult();
	}
	/**
	 * 查找一个学历
	 * @param id
	 * @return
	 */
	public Education getEducationById(Integer id)
	{
		return (Education)this.getSession().createQuery("from Education where id=?").setInteger(0, id).uniqueResult();
	}
	
	/**
	 * 删除城市
	 * @param id
	 */
	public void deleteCityById(Integer id)
	{
		this.getSession().delete(this.getCityById(id));
	}
	
	/**
	 * 删除学历
	 * @param id
	 */
	public void deleteEducationById(Integer id)
	{
		this.getSession().delete(this.getEducationById(id));
	}	
	
}