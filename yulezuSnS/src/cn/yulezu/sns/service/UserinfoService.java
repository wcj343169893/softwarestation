package cn.yulezu.sns.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.yulezu.orm.hibernate.BaseDao;
import cn.yulezu.orm.hibernate.BaseService;
import cn.yulezu.sns.dao.UserinfoDao;
import cn.yulezu.sns.entity.City;
import cn.yulezu.sns.entity.Education;
import cn.yulezu.sns.entity.Userinfo;

@Service
@Transactional
public class UserinfoService extends BaseService<Userinfo>
{	
	@Autowired
	private UserinfoDao userInfoDao;
	@Override
	@Resource(name="userinfoDao")
	public void setBaseDao(BaseDao<Userinfo> baseDao)
	{
		this.baseDao = baseDao;
	}
	
	/**
	 * 取得所有城市
	 * @return
	 */
	public List<City> getCityAll()
	{
 		return userInfoDao.getCityAll();
	}
	
	/**
	 * 取得所有学历
	 * @return
	 */
	public List<Education> getEducationALl()
	{ 
		return userInfoDao.getEducationALl();
	}
	
	/**
	 * 保存城市
	 * @param city
	 */
	public void saveCity(City city)
	{
		userInfoDao.saveCityOrUpdate(city);
	}
	
	/**
	 * 保存学历
	 * @param education
	 */
	public void saveEducation(Education education)
	{
		userInfoDao.saveEducationOrUpdate(education);
	}
	
	/**
	 * 查找一个城市
	 * @param id
	 * @return
	 */
	public City getCityById(Integer id)
	{
		return userInfoDao.getCityById(id);
	}
	
	/**
	 * 查找一个学历
	 * @param id
	 * @return
	 */
	public Education getEducationById(Integer id)
	{
		return userInfoDao.getEducationById(id);
	}	
	
	/**
	 * 删除一个城市
	 * @param id
	 */
	public void deleteCityById(Integer id)
	{
		userInfoDao.deleteCityById(id);
	}
	
	/**
	 * 删除一个学历
	 * @param id
	 */
	public void deleteEducationById(Integer id)
	{
		userInfoDao.deleteEducationById(id);
	}	
	
}
