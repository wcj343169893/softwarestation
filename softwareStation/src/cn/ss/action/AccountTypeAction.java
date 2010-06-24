package cn.ss.action;

import java.util.Date;
import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.AccountType;
import cn.ss.service.AccountTypeService;

public class AccountTypeAction extends BasicAction {

	private static final long serialVersionUID = 5826227066549132818L;
	private AccountTypeService accountTypeService;
	private AccountType accountType;
	private int p;
	private int id;
	private String name;

	public String delete() throws Exception {
		accountTypeService.delete(id);
		return list();
	}

	public String add() throws Exception {
		accountType = new AccountType();
		accountType.setName(name);
		accountType.setCreateTime(new Date());
		accountTypeService.add(accountType);
		return list();
	}

	public String edit() throws Exception {
		accountType = accountTypeService.findById(id);
		accountType.setName(name);
		accountType.setCreateTime(new Date());
		accountTypeService.update(accountType);
		return list();
	}

	public String detail() throws Exception {
		init();
		accountType = id == -1 ? null : accountTypeService.findById(id);
		request.setAttribute("accountType", accountType);
		return "detail";
	}

	public String list() throws Exception {
		init();
		PageResult<AccountType> pageResult = new PageResult<AccountType>();
		accountTypeService.findAll(pageResult, accountType);
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		request.setAttribute("pageResult", pageResult);
		return "list";
	}

	public AccountTypeService getAccountTypeService() {
		return accountTypeService;
	}

	public void setAccountTypeService(AccountTypeService accountTypeService) {
		this.accountTypeService = accountTypeService;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
