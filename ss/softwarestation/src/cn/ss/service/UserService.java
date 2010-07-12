package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.User;

public class UserService extends BasicService {
	/**
	 * 根据时间分页查询
	 * 
	 * @param pageResult
	 */
	public void findAll(PageResult<User> pageResult) {
		StringBuffer hql = new StringBuffer("from User where 1=1");
		hql.append(" order by id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 根据用户名查询用户
	 * 
	 * @param name
	 * @return
	 */
	public User findByName(String name) {
		name = Tool.filterString(name);
		User user = null;
		String sql = "from User where username= '" + name+"'";
		List list = dao.list(sql);
		if (list != null && list.size() > 0) {
			user = (User) list.get(0);
		}
		return user;
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public User findById(int id) {
		return (User) dao.get(User.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(User.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param User
	 */
	public void add(User user) {
		dao.add(user);
	}

	/**
	 * 更新
	 * 
	 * @param User
	 */
	public void update(User user) {
		dao.update(user);
	}
}
