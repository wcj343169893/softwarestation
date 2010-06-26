package cn.ss.service;

import java.util.List;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.ss.entity.AccountType;

public class AccountTypeService extends BasicService {
	/**
	 * ��ѯ����
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
	 * ��ȡָ������
	 * 
	 * @param id
	 * @return
	 */
	public AccountType findById(int id) {
		return (AccountType) dao.get(AccountType.class, id);
	}

	/**
	 * ɾ��
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(AccountType.class, id);
	}

	/**
	 * ����
	 * 
	 * @param accountType
	 */
	public void add(AccountType accountType) {
		dao.add(accountType);
	}

	/**
	 * ����
	 * 
	 * @param accountType
	 */
	public void update(AccountType accountType) {
		dao.update(accountType);
	}
}
