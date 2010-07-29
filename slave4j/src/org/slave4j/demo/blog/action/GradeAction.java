package org.slave4j.demo.blog.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slave4j.demo.blog.entity.Grade;
import org.slave4j.demo.blog.service.GradeService;
import org.slave4j.orm.PageData;
import org.slave4j.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog/grade")
public class GradeAction {
	@Resource
	private GradeService gradeService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request) {
		PageData<Grade> pageData = new PageData<Grade>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = gradeService.find(pageData);
		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new")
	public String addForm() {
		return "/blog/grade/input";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Grade grade = gradeService.find(id);
		model.addAttribute(grade);
		return "/blog/grade/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Grade grade) {
		if (grade.isNew()) {
			gradeService.save(grade);
		} else {
			gradeService.update(grade);
		}

		return "redirect:/blog/grade/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		gradeService.delete(id);
		return "redirect:/blog/grade/list";
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id) {
		gradeService.visible(id);
		return "redirect:/blog/grade/list";
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id) {
		gradeService.unVisible(id);
		return "redirect:/blog/grade/list";
	}
}
