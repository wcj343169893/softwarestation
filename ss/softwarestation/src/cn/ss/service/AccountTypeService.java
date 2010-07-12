package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.AccountType;

public class AccountTypeService extends BasicService {
	/**
	 * 查询所有
	 * 
	 * @param pageResult
	 * @param accountType
	 */
	public void findAll(PageResult<AccountType> pageResult,
			AccountType accountType) {
		StringBuffer hql = new StringBuffer("from AccountType at where 1=1");
		if (null != accountType) {
		}
		hql.append(" order by at.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	public List<AccountType> findAll() {
		StringBuffer hql = new StringBuffer("from AccountType at where 1=1");
		hql.append(" order by at.id");
		return dao.list(hql.toString());
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public AccountType findById(int id) {
		return (AccountType) dao.get(AccountType.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(AccountType.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param accountType
	 */
	public void add(AccountType accountType) {
		dao.add(accountType);
	}

	/**
	 * 更新
	 * 
	 * @param accountType
	 */
	public void update(AccountType accountType) {
		dao.update(accountType);
	}
}
