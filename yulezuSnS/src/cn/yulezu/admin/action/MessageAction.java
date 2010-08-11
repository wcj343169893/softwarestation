package cn.yulezu.admin.action;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import cn.yulezu.sns.entity.Message;
import cn.yulezu.sns.service.MessageService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin/message")
public class MessageAction {

	@Resource
	private MessageService messageService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request) {
		PageData<Message> pageData = new PageData<Message>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = messageService.find(pageData);
		return new ModelMap(pageData);
	}

	@RequestMapping(value = "/new")
	public String addForm(Model model) {
		return "/admin/message/input";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editForm(@PathVariable("id") Integer id, Model model) {
		Message message = messageService.find(id);
		model.addAttribute(message);
		return "/admin/message/input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Message message, HttpServletRequest request) {
		if (message.isNew()) {
			messageService.save(message);
		} else {
			messageService.update(message);
		}
		return "redirect:/admin/message/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id,
			HttpServletRequest request) {
		messageService.delete(id);
		return "redirect:/admin/message/list";// 重定向
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id) {
		messageService.visible(id);
		return "redirect:/admin/message/list";// 重定向
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id) {
		messageService.unVisible(id);
		return "redirect:/admin/message/list";
	}

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

}
