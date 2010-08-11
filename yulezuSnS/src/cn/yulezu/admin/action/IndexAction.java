package cn.yulezu.admin.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.yulezu.sns.entity.Administator;
import cn.yulezu.sns.service.AdministatorService;
import cn.yulezu.sns.service.AdminrecordService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin")
public class IndexAction {
	@Resource
	private AdministatorService administatorService;

	@Resource
	private AdminrecordService adminrecordService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		Administator administator = WebUtils.getLoginAdministator(request);
		if (administator != null) {
			return "/admin/index";
		}
		return "/admin/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String account,
			String password) {
		Administator administator = null;
		if (account != null && password != null
				&& !"".trim().equals(account.trim())
				&& !"".trim().equals(password.trim())) {
			administator = administatorService.find("account", account);
			if (administator != null
					&& administator.getPassword().equals(password)) {
				WebUtils.login(request, administator);
				adminrecordService.saveRecord(administator, "登录系统,ip:"
						+ request.getRemoteAddr());
				return "redirect:/admin/index";
			}
		}
		return "/admin/login";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		adminrecordService.saveRecord(WebUtils.getLoginAdministator(request),
				"退出系统,ip:" + request.getRemoteAddr());
		WebUtils.removeLoginAdministator(request);
		return "/admin/login";
	}
}
