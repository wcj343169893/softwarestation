package cn.yulezu.admin.action;

import java.text.SimpleDateFormat;
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
import cn.yulezu.sns.entity.Module;
import cn.yulezu.sns.service.AdminrecordService;
import cn.yulezu.sns.service.ModuleService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin/module")
public class ModuleAction {
	@Resource
	private ModuleService moduleService;
	@Resource
	private AdminrecordService adminrecordService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request) {
		PageData<Module> pageData = new PageData<Module>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = moduleService.find(pageData);
		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new")
	public String addForm(Model model) {
		List<Module> moduleList = moduleService.findParentModule();
		model.addAttribute(moduleList);
		return "/admin/module/input";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Module module = moduleService.find(id);
		model.addAttribute(module);
		List<Module> moduleList = moduleService.findParentModule(id);
		model.addAttribute(moduleList);
		return "/admin/module/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Module module, HttpServletRequest request) {
		Administator logAdmin = WebUtils.getLoginAdministator(request);
		if (module.isNew()) {
			moduleService.save(module);
			adminrecordService.saveRecord(logAdmin, "新增模块,id:" + module.getId()
					+ "title:" + module.getTitle() + "\t path:"
					+ module.getPath());
		} else {
			moduleService.update(module);
			adminrecordService.saveRecord(logAdmin, "修改模块,id:" + module.getId()
					+ "title:" + module.getTitle() + "\t path:"
					+ module.getPath());
		}

		return "redirect:/admin/module/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
			HttpServletRequest request) {
		moduleService.delete(id);
		Administator logAdmin = WebUtils.getLoginAdministator(request);
		adminrecordService.saveRecord(logAdmin, "删除模块,id:" + id);
		return "redirect:/admin/module/list";// 重定向
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id) {
		moduleService.visible(id);
		return "redirect:/admin/module/list";// 重定向
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id) {
		moduleService.unVisible(id);
		return "redirect:/admin/module/list";
	}

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

}
