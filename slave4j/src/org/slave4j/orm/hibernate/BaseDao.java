package org.slave4j.orm.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slave4j.orm.Compositor;
import org.slave4j.orm.Filtration;
import org.slave4j.orm.PageData;
import org.slave4j.utils.HibernateUtils;
import org.slave4j.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * dao基类.
 * 		1：该类封装了最常见数据库操作的方法，你可以继承该类，添加自己喜欢的方法
 * 		2：当你有多个sessionFactory时，你也可以在你的子类中重写setSessionFactory()方法
 * @author yangzhibin
 *
 * @param <T> 实体类类型
 */
@SuppressWarnings("unchecked")
public class BaseDao<T extends BaseEntity>
{
	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	/**
	 * 构造方法
	 */
	public BaseDao()
	{
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 采用@Resource(name="xxx")按名称注入SessionFactory, 当有多个SesionFactory的时候Override本函数.
	 */
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 取得Session.
	 */
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 新增对象.
	 */
	public void save(T entity)
	{
		Assert.notNull(entity, "entity不能为空");
		entity.setInsertTime(new Date());//插入时间
		getSession().save(entity);
		logger.debug("save entity: {}", entity);
	}

	/**
	 * 修改对象.
	 */
	public void update(T entity)
	{
		Assert.notNull(entity, "entity不能为空");
		entity.setLastUpdateTime(new Date());//最后一次修改时间
		getSession().update(entity);
		logger.debug("update entity: {}", entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(T entity)
	{
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(Integer id)
	{
		delete(find(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	/**
	 * 对象显示.
	 */
	public void visible(Integer id)
	{
		T entity = find(id);
		Assert.notNull(entity, "entity不能为空");
		if(!entity.isVisible())
		{
			entity.setVisible(true);
			update(entity);
			logger.debug("visible entity {},id is {}", entityClass.getSimpleName(), id);
		}
	}

	/**
	 * 对象不显示.
	 */
	public void unVisible(Integer id)
	{
		T entity = find(id);
		Assert.notNull(entity, "entity不能为空");
		if(entity.isVisible())
		{
			entity.setVisible(false);
			update(entity);
			logger.debug("unVisible entity {},id is {}", entityClass.getSimpleName(), id);
		}
		
	}

	/**
	 * 按id获取对象.
	 */
	public T find(Integer id)
	{
		Assert.notNull(id, "id不能为空");
		return (T) getSession().load(entityClass, id);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	public T find(String fieldName, Object fieldValue)
	{
		Assert.hasText(fieldName, "fieldName不能为空");
		Criterion criterion = Restrictions.eq(fieldName, fieldValue);
		return (T) HibernateUtils.createCriteria(getSession(), entityClass, criterion).uniqueResult();
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	public List<T> findList(String fieldName, Object fieldValue)
	{
		Assert.hasText(fieldName, "fieldName不能为空");
		Criterion criterion = Restrictions.eq(fieldName, fieldValue);
		return HibernateUtils.createCriteria(getSession(), entityClass, criterion).list();
	}

	/**
	 * 按照过滤条件对象查找对象列表.
	 */
	public List<T> findList(Filtration... filtrations)
	{
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrations);
		return criteria.list();
	}

	/**
	 * 按照过滤条件对象查找对象列表.
	 */
	public List<T> findList(List<Filtration> filtrationList)
	{
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrationList);
		return criteria.list();
	}

	/**
	 * 按照过滤条件对象查找对象列表，支持排序.
	 */
	public List<T> findList(Compositor compositor, Filtration... filtrations)
	{
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrations);
		//设置排序
		criteria = HibernateUtils.setCompositorParameter(criteria, compositor);
		return criteria.list();
	}

	/**
	 * 按照过滤条件对象查找对象列表，支持排序.
	 */
	public List<T> findList(Compositor compositor, List<Filtration> filtrationList)
	{
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		//设置过滤条件
		criteria = HibernateUtils.setFiltrationParameter(criteria, filtrationList);
		//设置排序
		criteria = HibernateUtils.setCompositorParameter(criteria, compositor);
		return criteria.list();
	}

	/**
	 * 获取全部对象.
	 */
	public List<T> findAll()
	{
		return findList();
	}

	/**
	 * 获取全部对象,支持排序.
	 */
	public List<T> findAll(Compositor compositor)
	{
		return findList(compositor);
	}

	/**
	 * 分页查询.
	 */
	public PageData<T> find(PageData<T> pageData)
	{
		Assert.notNull(pageData, "pageData不能为空");
		Criteria criteria = HibernateUtils.createCriteria(getSession(), entityClass);
		HibernateUtils.setParameter(criteria, pageData);
		pageData.setResult(criteria.list());
		return pageData;
	}

	/**
	 * 按id列表获取对象.
	 */
	public List<T> findListByIds(List<Integer> idList)
	{
		if (idList != null && idList.size() >= 1)
		{
			Criterion criterion = Restrictions.in("id", idList);
			return HibernateUtils.createCriteria(getSession(), entityClass, criterion).list();
		} else
		{
			return null;
		}
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> X find(String hql, Object... values)
	{
		return (X) HibernateUtils.createQuery(getSession(), hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	public <X> X find(String hql, Map<String, ?> values)
	{
		return (X) HibernateUtils.createQuery(getSession(), hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	public <X> List<X> findList(String hql, Object... values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	public <X> List<X> findList(String hql, Map<String, ?> values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).list();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Object... values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Map<String, ?> values)
	{
		return HibernateUtils.createQuery(getSession(), hql, values).executeUpdate();
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 本地SQL进行修改/删除操作.
	 * @return 更新记录数.
	 */
	public List find(String sql)
	{
		Assert.hasText(sql, "sql不能为空");
		return getSession().createSQLQuery(sql).list();
	}

}
