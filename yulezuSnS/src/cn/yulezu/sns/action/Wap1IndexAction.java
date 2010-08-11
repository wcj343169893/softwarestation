package cn.yulezu.sns.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.yulezu.sns.entity.UserOnline;
import cn.yulezu.sns.entity.Users;
import cn.yulezu.sns.service.UserOnlineService;
import cn.yulezu.sns.service.UsersService;
import cn.yulezu.utils.Config;
import cn.yulezu.utils.EncryptUtils;
import cn.yulezu.utils.SessionUtils;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/sns")
public class Wap1IndexAction {

	@Resource
	private UsersService usersService;
	@Resource
	private UserOnlineService userOnlineService;

	@RequestMapping("/index")
	public String index() {
		// 跳转到主页
		return "/wap1/index";
	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param account
	 *            手机号码或账号
	 * @param password
	 *            登录密码
	 * @return 登录成功，则跳转到成功页面，错误则返回登录
	 * 
	 * @param model
	 *            页面模型
	 * @param t
	 *            登录类型
	 * @param statues
	 *            登录状态(1:在线；0:隐身(VIP))
	 * @return 登录成功：成功页面;失败：登录页面,并显示错误信息
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, String account,
			String password, Model model, String t, String statues) {// 处理登录信息
		String errorMessage = "";
		Users users = null;
		if (account != null && password != null && !"".equals(account.trim())
				&& !"".equals(password.trim())) {
			if (WebUtils.checkPhoneNum(account)) {// 手机号码登录
				users = usersService.find("mobile", account);
			} else { // 账号登录
				users = usersService.find("username", account);
			}
			if (users == null) {
				errorMessage = "您输入的账号或手机号码不存在，请注册!";
			} else if (!users.getPassword().equals(password)) {
				errorMessage = "密码错误!";
			} else {// 登录成功
				users.setLoginTime(new Date());
				usersService.save(users);
				request.getSession().setAttribute(Config.LOGIN_NAME, users);
				boolean isResetSid = false;
				if (t.equals("general")) {// 普通登录
					isResetSid = false;
				} else {
					isResetSid = true;
				}
				UserOnline userOnline = userOnlineService.findUserOnline(users
						.getId());
				String sid = null;
				if (userOnline == null) {
					Date date = new Date();
					sid = enc2string(users);
					userOnline = new UserOnline();
					userOnline.setUsers(users);
					userOnline.setSid(sid);
					userOnline.setLastUpdateTime(date);
					// 保存到数据库
					userOnlineService.save(userOnline);
				} else {
					sid = userOnline.getSid();
					String s = null;
					try {
						s = EncryptUtils.base64Decode(sid);
					} catch (IOException e) {
					}
					String[] as = s.split("\\|");
					if (isResetSid || s == null
							|| !as[0].equals(String.valueOf(users.getId()))) {
						sid = enc2string(users);
						userOnline.setSid(sid);
					}
					// 保存到数据库
					userOnlineService.update(userOnline);
				}
				request.getSession().setAttribute(Config.SID, sid);
				SessionUtils.put(userOnline);
				return "/wap1/reg/loginSuccess";
			}
		} else {
			errorMessage = "请填写正确格式的用户名或密码!";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "/wap1/reg/login";
	}

	/**
	 * 跳转到登录界面
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(Model model, HttpServletRequest request) {// 跳转到登录界面
		return "/wap1/reg/login";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		System.out.println("有用户退出啦!");
		Users users = (Users) request.getSession().getAttribute(
				Config.LOGIN_NAME);
		if (users != null) {
			System.out.println(users.getId() + "退出啦!");
			SessionUtils.remove(users.getId());
		}
		request.getSession().removeAttribute(Config.LOGIN_NAME);
		return "/wap1/index";
	}

	@RequestMapping("/myspace")
	public String myspace(Integer uid, HttpServletRequest request) {
		Users users = null;
		try {
			users = usersService.find(uid);
		} catch (Exception e) {
		}
		request.getSession().setAttribute(Config.LOGIN_NAME, users);
		return "/wap1/users/space";
	}

	/**
	 * 加密
	 * 
	 * @param users
	 * @param date
	 * @return
	 */
	private String enc2string(Users users) {
		Calendar calendar = Calendar.getInstance();
		String sid = EncryptUtils.enc(String.valueOf(users.getId()), users
				.getUsername(), users.getPassword(), String.valueOf(calendar
				.getTimeInMillis()));
		return sid;
	}

	// public static void main(String[] args) {
	// try {
	// String s = EncryptUtils
	// .base64Decode("MzN8YWRtaW5zfDEyMzQ1NnwxMjgxMzQ0MDA4MDkzfA==");
	// String[] ss = s.split("\\|");
	// for (int i = 0; i < ss.length; i++) {
	// System.out.println(ss[i]);
	// }
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
}
