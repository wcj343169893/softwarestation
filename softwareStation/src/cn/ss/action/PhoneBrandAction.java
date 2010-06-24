package cn.ss.action;

import java.util.Date;

import cn.common.action.BasicAction;
import cn.common.util.PageResult;
import cn.ss.entity.PhoneBrand;
import cn.ss.service.PhoneBrandService;

public class PhoneBrandAction extends BasicAction {
	private static final long serialVersionUID = 6449115596039487609L;
	private PhoneBrandService phoneBrandService;
	private int id;
	private String name;
	private int p;
	private PhoneBrand phoneBrand;

	/**
	 * 根据id删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		phoneBrandService.delete(id);
		return list();
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		init();
		phoneBrand = new PhoneBrand();
		PageResult<PhoneBrand> pageResult = new PageResult<PhoneBrand>();
		if (p != 0) {
			pageResult.setPageNo(p);
		}
		phoneBrandService.findAll(pageResult, phoneBrand);
		request.setAttribute("pageResult", pageResult);
		return "list";
	}

	/**
	 * 查询详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detail() throws Exception {
		init();
		phoneBrand = phoneBrandService.findById(id);
		request.setAttribute("phoneBrand", phoneBrand);
		return "detail";
	}

	/**
	 * 新增
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		phoneBrand = new PhoneBrand();
		phoneBrand.setName(this.getName());
		phoneBrand.setCreateTime(new Date());
		phoneBrandService.add(phoneBrand);
		return list();
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		phoneBrand = phoneBrandService.findById(id);
		phoneBrand.setName(name);
		phoneBrand.setCreateTime(new Date());
		phoneBrandService.update(phoneBrand);
		return list();
	}

	public PhoneBrandService getPhoneBrandService() {
		return phoneBrandService;
	}

	public void setPhoneBrandService(PhoneBrandService phoneBrandService) {
		this.phoneBrandService = phoneBrandService;
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

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

}
