package org.slave4j.orm.hibernate;

import java.util.List;
import java.util.Map;

import org.slave4j.orm.Compositor;
import org.slave4j.orm.Filtration;
import org.slave4j.orm.PageData;
import org.springframework.transaction.annotation.Transactional;

/**
 * service基类.
 * @author yangzhibin
 *
 * @param <T> 实体类类型
 */
@Transactional
public abstract class BaseService<T extends BaseEntity>
{
	protected BaseDao<T> baseDao;

	public BaseDao<T> getBaseDao()
	{
		return baseDao;
	}

	
	public abstract void setBaseDao(BaseDao<T> baseDao);

	/**
	 * 新增对象.
	 */
	public void save(T entity)
	{
		getBaseDao().save(entity);
	}

	/**
	 * 修改对象.
	 */
	public void update(T entity)
	{
		getBaseDao().update(entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(T entity)
	{
		getBaseDao().delete(entity);
	}

	/**
	 * 删除对象.
	 */
	public void delete(Integer id)
	{
		getBaseDao().delete(id);
	}

	/**
	 * 对象显示.
	 */
	public void visible(Integer id)
	{
		getBaseDao().visible(id);
	}

	/**
	 * 对象不显示.
	 */
	public void unVisible(Integer id)
	{
		getBaseDao().unVisible(id);
	}

	/**
	 * 按id获取对象.
	 */
	@Transactional(readOnly = true)
	public T find(Integer id)
	{
		return getBaseDao().find(id);
	}

	/**
	 * 按属性查找唯一对象,匹配方式为相等.
	 */
	@Transactional(readOnly = true)
	public T find(String fieldName, Object fieldValue)
	{
		return getBaseDao().find(fieldName, fieldValue);
	}

	/**
	 * 按属性查找对象列表,匹配方式为相等.
	 */
	@Transactional(readOnly = true)
	public List<T> findList(String fieldName, Object fieldValue)
	{
		return getBaseDao().findList(fieldName, fieldValue);
	}

	/**
	 * 按照过滤条件对象查找对象列表.
	 */
	@Transactional(readOnly = true)
	public List<T> findList(Filtration... filtrations)
	{
		return getBaseDao().findList(filtrations);
	}

	/**
	 * 按照过滤条件对象查找对象列表.
	 */
	@Transactional(readOnly = true)
	public List<T> findList(List<Filtration> filtrationList)
	{
		return getBaseDao().findList(filtrationList);
	}

	/**
	 * 按照过滤条件对象查找对象列表，支持排序.
	 */
	@Transactional(readOnly = true)
	public List<T> findList(Compositor compositor, Filtration... filtrations)
	{
		return getBaseDao().findList(compositor, filtrations);
	}

	/**
	 * 按照过滤条件对象查找对象列表，支持排序.
	 */
	@Transactional(readOnly = true)
	public List<T> findList(Compositor compositor, List<Filtration> filtrationList)
	{
		return getBaseDao().findList(compositor, filtrationList);
	}

	/**
	 * 获取全部对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findAll()
	{
		return getBaseDao().findAll();
	}

	/**
	 * 获取全部对象,支持排序.
	 */
	@Transactional(readOnly = true)
	public List<T> findAll(Compositor compositor)
	{
		return getBaseDao().findAll(compositor);
	}

	/**
	 * 分页查询.
	 */
	@Transactional(readOnly = true)
	public PageData<T> find(PageData<T> pageData)
	{
		return getBaseDao().find(pageData);
	}

	/**
	 * 按id列表获取对象.
	 */
	@Transactional(readOnly = true)
	public List<T> findListByIds(List<Integer> idList)
	{
		return getBaseDao().findListByIds(idList);
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X find(String hql, Object... values)
	{
		return getBaseDao().find(hql, values);
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> X find(String hql, Map<String, ?> values)
	{
		return getBaseDao().find(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=? and password=?"
	 * @param values 数量可变的参数,按顺序绑定.
	 * @return
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Object... values)
	{
		return getBaseDao().findList(hql, values);
	}

	/**
	 * 按HQL查询对象列表.
	 * @param hql "from Users where name=:name and password=:password"
	 * @param values 命名参数,按名称绑定.
	 * @return 
	 */
	@Transactional(readOnly = true)
	public <X> List<X> findList(String hql, Map<String, ?> values)
	{
		return getBaseDao().findList(hql, values);
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Object... values)
	{
		return getBaseDao().batchExecute(hql, values);
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录数.
	 */
	public int batchExecute(String hql, Map<String, ?> values)
	{
		return getBaseDao().batchExecute(hql, values);
	}

	//--------------------------------------------------------------------------------------------------

	/**
	 * 本地SQL进行修改/删除操作.
	 * @return 更新记录数.
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List find(String sql)
	{
		return getBaseDao().find(sql);
	}
}
