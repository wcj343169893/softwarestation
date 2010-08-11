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

import cn.yulezu.orm.PageData;
import cn.yulezu.sns.entity.Friendgroup;
import cn.yulezu.sns.entity.Friends;
import cn.yulezu.sns.service.FriendgroupService;
import cn.yulezu.sns.service.FriendsService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/admin/friends")
public class FriendsAction {
	@Resource
	private FriendsService friendsService;

	@Resource
	private FriendgroupService friendgroupService;

	@RequestMapping("/list")
	public ModelMap list(HttpServletRequest request) {
		PageData<Friends> pageData = new PageData<Friends>();
		// 给pageData设置参数
		WebUtils.setPageDataParameter(request, pageData);
		pageData = friendsService.find(pageData);
		return new ModelMap(pageData);
	}

	/**
	 * 好友分组列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/glist/{uid}")
	public String glist(@PathVariable("uid") Integer uid, Model model) {
		List<Friendgroup> friendgroupList = friendgroupService.findByUid(uid);
		model.addAttribute(friendgroupList);
		model.addAttribute("uid",uid);
		return "/admin/friends/friendGroupList";
	}

	/**
	 * 好友列表
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/flist/{uid}/{gid}")
	public String flist(@PathVariable("gid") Integer gid, Model model,@PathVariable("uid") Integer uid) {
		List<Friends> friendsList = friendsService.findByGid(gid);
		model.addAttribute(friendsList);
		model.addAttribute("uid",uid);
		return "/admin/friends/list";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		friendsService.delete(id);
		return "redirect:/admin/friends/list";// 重定向
	}

	@RequestMapping(value = "/visible/{id}")
	public String visible(@PathVariable("id") Integer id) {
		friendsService.visible(id);
		return "redirect:/admin/friends/list";// 重定向
	}

	@RequestMapping(value = "/unVisible/{id}")
	public String unVisible(@PathVariable("id") Integer id) {
		friendsService.unVisible(id);
		return "redirect:/admin/friends/list";
	}

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), false));
	}

}
