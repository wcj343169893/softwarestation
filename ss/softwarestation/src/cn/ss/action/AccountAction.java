package cn.ss.action;

import java.util.List;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.common.util.Tool;
import cn.ss.entity.Account;
import cn.ss.entity.AccountType;
import cn.ss.entity.Bop;
import cn.ss.service.AccountService;
import cn.ss.service.AccountTypeService;
import cn.ss.service.BopService;

public class AccountAction extends BasicAction {
	private static final long serialVersionUID = 7896831671543589893L;
	private AccountService accountService;
	private AccountTypeService accountTypeService;
	private BopService bopService;
	private Account account;
	private int p;
	private int id;
	private String name;
	private String explanation;
	private double price;
	private int atId;
	private String beginTime;
	private String endTime;
	private Bop bop;

	public String delete() throws Exception {
		accountService.delete(id);
		return list();
	}

	public String add() throws Exception {
		AccountType accountType = accountTypeService.findById(atId);
		if (accountType != null) {
			isExistBop();
			account = new Account();
			account.setExplanation(explanation);
			account.setPrice(price);
			account.setCreateTime(Tool
					.stringFormatDate(beginTime, "yyyy-MM-dd"));
			account.setAccType(accountType);
			account.setBop(bop);
			accountService.add(account);
		}
		beginTime = "";
		atId = -1;
		return list();
	}

	public String edit() throws Exception {
		AccountType accountType = accountTypeService.findById(atId);
		account = accountService.findById(id);
		if (accountType != null && account != null) {
			// 判断账单是否存在
			isExistBop();
			account.setBop(bop);
			account.setExplanation(explanation);
			account.setPrice(price);
			account.setCreateTime(Tool
					.stringFormatDate(beginTime, "yyyy-MM-dd"));
			account.setAccType(accountType);
			accountService.update(account);
		}
		beginTime = "";
		atId = -1;
		return list();
	}

	/**
	 * 判断账单是否存在，不存在，则新建一个，存在，则修改时间
	 */
	private void isExistBop() {
		bop = bopService.findByDate(Tool.stringFormatDate(beginTime,
				"yyyy-MM-dd"));
		if (bop == null) {
			bop = new Bop();
			bop.setCreatetime(Tool.stringFormatDate(beginTime, "yyyy-MM-dd"));// 创建日期
			bop.setIsvisible(1);
			bopService.add(bop);
		} else {
			bop.setModifyTime(Tool.stringFormatDate(beginTime, "yyyy-MM-dd"));// 修改日期
			bopService.update(bop);
		}
	}

	public String detail() throws Exception {
		init();
		initData();
		account = id == -1 ? null : accountService.findById(id);
		request.setAttribute("account", account);
		return "detail";
	}

	public String list() throws Exception {
		init();
		initData();
		PageResult<Account> pageResult = new PageResult<Account>();
		if (beginTime != null && !"".equals(beginTime) && endTime != null
				&& !"".equals(endTime)) {
			if (beginTime.compareTo(endTime) > 0) {
				String tmp = beginTime;
				beginTime = endTime;
				endTime = tmp;
			}
		}
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		accountService.findAll(pageResult, account, beginTime, endTime, atId);
		request.setAttribute("pageResult", pageResult);
		request.setAttribute("beginTime", beginTime);
		request.setAttribute("endTime", endTime);
		return "list";
	}

	private void initData() {
		List<AccountType> accountTypeList = (List<AccountType>) accountTypeService
				.findAll();
		request.setAttribute("accountTypeList", accountTypeList);
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public AccountTypeService getAccountTypeService() {
		return accountTypeService;
	}

	public void setAccountTypeService(AccountTypeService accountTypeService) {
		this.accountTypeService = accountTypeService;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAtId() {
		return atId;
	}

	public void setAtId(int atId) {
		this.atId = atId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public BopService getBopService() {
		return bopService;
	}

	public void setBopService(BopService bopService) {
		this.bopService = bopService;
	}

	public Bop getBop() {
		return bop;
	}

	public void setBop(Bop bop) {
		this.bop = bop;
	}

}
