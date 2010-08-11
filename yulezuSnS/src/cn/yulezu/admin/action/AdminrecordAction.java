package cn.yulezu.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.yulezu.orm.PageData;
import cn.yulezu.sns.entity.Adminrecord;
import cn.yulezu.sns.service.AdminrecordService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin/record")
public class AdminrecordAction {
	@Resource
	private AdminrecordService adminrecordService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request) {
		PageData<Adminrecord> pageData = new PageData<Adminrecord>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = adminrecordService.find(pageData);
		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		adminrecordService.delete(id);
		return "redirect:/admin/record/list";// 重定向
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id) {
		adminrecordService.visible(id);
		return "redirect:/admin/record/list";// 重定向
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id) {
		adminrecordService.unVisible(id);
		return "redirect:/admin/record/list";
	}

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

}
