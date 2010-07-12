package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.User;

public class UserService extends BasicService {
	/**
	 * ����ʱ���ҳ��ѯ
	 * 
	 * @param pageResult
	 */
	public void findAll(PageResult<User> pageResult) {
		StringBuffer hql = new StringBuffer("from User where 1=1");
		hql.append(" order by id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * �����û�����ѯ�û�
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
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public User findById(int id) {
		return (User) dao.get(User.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(User.class, id);
	}

	/**
	 * ����
	 * 
	 * @param User
	 */
	public void add(User user) {
		dao.add(user);
	}

	/**
	 * ����
	 * 
	 * @param User
	 */
	public void update(User user) {
		dao.update(user);
	}
}
