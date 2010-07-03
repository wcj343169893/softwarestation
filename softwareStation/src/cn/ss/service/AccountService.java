package cn.ss.service;

import cn.common.service.BasicService;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Account;

public class AccountService extends BasicService {
	/**
	 * 查询所有
	 * 
	 * @param pageResult
	 * @param account
	 */
	public void findAll(PageResult<Account> pageResult, Account account,
			String beginTime, String endTime, int atId) {
		StringBuffer hql = new StringBuffer("from Account a where 1=1");
		if (beginTime != null
				&& !"".equals(beginTime)
				&& Tool.stringFormatDate(beginTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(a.createTime,'%y %m %d') >= DATE_FORMAT('"+beginTime+"','%y %m %d')");
		}
		if (endTime != null
				&& !"".equals(endTime)
				&& Tool.stringFormatDate(endTime, "yyyy-MM-dd") != null) {
			hql.append(" and DATE_FORMAT(a.createTime,'%y %m %d') <= DATE_FORMAT('"+endTime+"','%y %m %d')");
		}
		if (atId > 0) {
			hql.append(" and a.accType.id = " + atId);
		}
		hql.append(" order by a.id desc");
		dao.listByPage(hql.toString(), pageResult);
	}

	/**
	 * 获取指定数据
	 * 
	 * @param id
	 * @return
	 */
	public Account findById(int id) {
		return (Account) dao.get(Account.class, id);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(int id) {
		dao.del(Account.class, id);
	}

	/**
	 * 新增
	 * 
	 * @param account
	 */
	public void add(Account account) {
		dao.add(account);
	}

	/**
	 * 更新
	 * 
	 * @param account
	 */
	public void update(Account account) {
		dao.update(account);
	}
}
