package cn.yulezu.sns.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.yulezu.sns.entity.Userinfo;
import cn.yulezu.sns.entity.Users;
import cn.yulezu.sns.service.UsersService;
import cn.yulezu.utils.WebUtils;

@Controller
@RequestMapping("/sns/reg")
public class RegAction {
	@Resource
	private UsersService usersService;
	private String errorMessage;

	/**
	 * 注册用户名、密码 、性别
	 * 
	 * @param request
	 * @param account
	 * @param password
	 * @param rPassword
	 * @param sex
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(HttpServletRequest request, String account,
			String password, String rPassword, String sex, Model model) {
		boolean flag = false;
		errorMessage = null;
		if (account != null && !"".equals(account.trim()) && password != null
				&& !"".equals(password.trim()) && rPassword != null
				&& !"".equals(rPassword.trim())) {
			if (isOK(account, password, rPassword)) {
				Users users = new Users();
				users.setUsername(account);
				users.setPassword(password);

				Userinfo userinfo = new Userinfo();// 详细信息
				userinfo.setSexy(Integer.parseInt(sex));
				userinfo.setInsertTime(new Date());
				userinfo.setVisible(true);
				userinfo.setUsers(users);

				users.setUserinfo(userinfo);
				usersService.save(users);
				request.setAttribute("users", users);
				flag = true;
			}
		}
		if (flag) {
			return "/wap1/reg/regist2n";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "/wap1/reg/regist";
	}

	/**
	 * 初始化注册
	 * 
	 * @return
	 */
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public String regist() {

		return "/wap1/reg/regist";
	}

	/**
	 * 注册时 填入昵称和生日
	 * 
	 * @param request
	 * @param uid
	 * @param nickname
	 * @param birthday
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regist2n", method = RequestMethod.POST)
	public String regist(HttpServletRequest request, Integer uid,
			String nickname, String birthday, Model model) {
		Users users = usersService.find(uid);
		boolean flag = false;
		errorMessage = null;
		if (users != null) {
			if (nickname != null && !"".equals(nickname.trim())
					&& birthday != null && !"".equals(birthday.trim())) {
				users.setNickname(nickname);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				try {
					users.getUserinfo().setBirthday(sdf.parse(birthday));
					flag = true;
				} catch (ParseException e) {
					flag = false;
					errorMessage = "生日格式不正确，请重新输入";
				}
				if (flag) {
					usersService.save(users);
					request.setAttribute("users", users);
					return "/wap1/reg/regist2m";
				}
			} else {
				errorMessage = "昵称或者生日不能为空,请输入";
			}
			request.setAttribute("users", users);
		} else {
			errorMessage = "操作失败！！";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "/wap1/reg/regist2n";
	}

	/**
	 * 验证手机号码
	 * 
	 * @param request
	 * @param uid
	 * @param mobile
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/regist2m", method = RequestMethod.POST)
	public String regist(HttpServletRequest request, Integer uid,
			String mobile, Model model) {
		Users users = usersService.find(uid);
		errorMessage = null;
		if (users != null) {
			if (mobile != null) {
				if (WebUtils.checkPhoneNum(mobile)) {
					// 判断手机号码是否被绑定
					if (usersService.find("mobile", mobile) != null) {
						errorMessage = "手机号码已经被绑定，请重新输入";
					} else {
						users.setMobile(mobile);
						usersService.save(users);
						request.setAttribute("users", users);
						return "/wap1/reg/regist2b";
					}
				} else {
					errorMessage = "手机号码输入有误";
				}
			} else {
				errorMessage = "手机号码不能为空,请输入";
			}
			request.setAttribute("users", users);
		} else {
			errorMessage = "操作失败！！";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "/wap1/reg/regist2m";
	}

	/**
	 * 加载手机认证
	 * 
	 * @param request
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "/regist2m", method = RequestMethod.GET)
	public String regist(HttpServletRequest request, Integer uid) {
		request.setAttribute("users", usersService.find(uid));
		return "/wap1/reg/regist2m";
	}

	/**
	 * 加载手机绑定
	 * 
	 * @param request
	 * @param uid
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/regist2b", method = RequestMethod.GET)
	public String regist(HttpServletRequest request, Integer uid, String mobile) {
		return "/wap1/reg/regist2b";
	}

	/**
	 * 验证注册 信息是否合法
	 * 
	 * @param account
	 * @param password
	 * @param rPassword
	 * @return
	 */
	public boolean isOK(String account, String password, String rPassword) {
		if (account.length() < 6) {
			errorMessage = "用户名必须大于6位";
			return false;
		} else {
			// 判断用户名是否为数字和字母
			if (!account.matches("^\\w*$")) {
				errorMessage = "请输入正确的用户名";
				return false;
			}
			if (!password.matches("^\\w*$")) {
				errorMessage = "请输入正确的密码";
				return false;
			}
			Users u = usersService.find("username", account);
			if (u != null) {
				errorMessage = "此用户已经存在";
				return false;
			} else if (password.length() < 6) {
				errorMessage = "密码必须大于6位";
				return false;
			} else if (rPassword.length() < 6) {
				errorMessage = "确认密码必须大于6位";
				return false;
			} else if (!rPassword.equals(password)) {
				errorMessage = "确认密码和密码必须一致";
				return false;
			} else {
				return true;
			}
		}
	}

	public static void main(String[] args) {
		String s = "sdf23dsf3";
		System.out.println(s.matches("^\\w*$"));
	}
}
