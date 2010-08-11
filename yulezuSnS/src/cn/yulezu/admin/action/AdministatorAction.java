package cn.yulezu.admin.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.yulezu.orm.PageData;
import cn.yulezu.sns.entity.Administator;
import cn.yulezu.sns.entity.Adminrecord;
import cn.yulezu.sns.entity.Module;
import cn.yulezu.sns.service.AdministatorService;
import cn.yulezu.sns.service.AdminrecordService;
import cn.yulezu.sns.service.ModuleService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin/administator")
public class AdministatorAction {
	@Resource
	private AdministatorService administatorService;
	@Resource
	private ModuleService moduleService;
	@Resource
	private AdminrecordService adminrecordService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request) {
		PageData<Administator> pageData = new PageData<Administator>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = administatorService.find(pageData);
		return new ModelMap(pageData); 
	}

	@RequestMapping(value = "/new")
	public String addForm(Model model) {
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute(moduleList);
		return "/admin/administator/input";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Administator administator = administatorService.find(id);
		model.addAttribute(administator);
		List<Module> moduleList = moduleService.findAll();
		model.addAttribute(moduleList);
		return "/admin/administator/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Administator administator, String[] module,
			HttpServletRequest request) {
		Administator logAdmin = WebUtils.getLoginAdministator(request);
		if (logAdmin != null) {// 执行操作.否则跳转到登录界面

		}
		List<Module> moduleList = null;
		if (module != null && module.length > 0) {
			moduleList = new ArrayList<Module>();
			for (int i = 0; i < module.length; i++) {
				moduleList.add(moduleService.find(Integer.parseInt(module[i])));
			}
		}
		administator.setModules(moduleList);
		if (administator.isNew()) {
			administatorService.save(administator);
			adminrecordService.saveRecord(logAdmin, "新增管理员,account:"
					+ administator.getAccount() + "\t id:"
					+ administator.getId());
		} else {
			administatorService.update(administator);
			adminrecordService.saveRecord(logAdmin, "修改管理员,account:"
					+ administator.getAccount() + "\t id:"
					+ administator.getId());
		}
		return "redirect:/admin/administator/list";
	}

	/**
	 * 删除管理员
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
			HttpServletRequest request) {
		Administator logAdmin = WebUtils.getLoginAdministator(request);
		if (logAdmin != null) {// 执行操作.否则跳转到登录界面

		}
		administatorService.delete(id);
		adminrecordService.saveRecord(logAdmin, "删除管理员,id:" + id);
		return "redirect:/admin/administator/list";// 重定向
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id) {
		administatorService.visible(id);
		return "redirect:/admin/administator/list";// 重定向
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id) {
		administatorService.unVisible(id);
		return "redirect:/admin/administator/list";
	}
	@RequestMapping(value = "/editpassword/{id}/{pwd}")
	public String editpassword(@PathVariable("id") Integer id,@PathVariable("pwd") String pwd) {
		Administator administator =administatorService.find(id);
		administator.setPassword(pwd.trim());
		administatorService.save(administator);
		return "修改成功";
	}
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}
}
