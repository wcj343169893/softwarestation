package cn.yulezu.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import cn.yulezu.orm.Compositor;
import cn.yulezu.orm.Filtration;
import cn.yulezu.orm.PageData;
import cn.yulezu.orm.Compositor.CompositorType;
import cn.yulezu.sns.entity.Administator;

/**
 * web工具类
 * 
 * @author yangzhibin
 * 
 */
public class WebUtils {
	@SuppressWarnings("unchecked")
	public static Map getParametersStartingWith(HttpServletRequest request,
			String prefix) {
		Enumeration paramNames = request.getParameterNames();
		Map params = new TreeMap();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	@SuppressWarnings("unchecked")
	public static List<Filtration> creatFiltrationList(
			HttpServletRequest request, String filterPrefix) {
		List<Filtration> filtrationList = new ArrayList<Filtration>();

		// 从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
		Map<String, String> filterParamMap = getParametersStartingWith(request,
				filterPrefix);

		// 分析参数Map,构造PropertyFilter列表
		for (Map.Entry<String, String> entry : filterParamMap.entrySet()) {
			String filterName = entry.getKey();
			String value = MyStringUtils.iso2utf(entry.getValue());
			// 如果value值为空,则忽略此filter.
			if (StringUtils.isNotBlank(value)) {
				Filtration filter = new Filtration(filterName, value);
				filtrationList.add(filter);
			}
		}
		return filtrationList;
	}

	public static void setPageDataParameter(HttpServletRequest request,
			PageData<?> pageData) {
		// 第一步：设置过滤条件
		List<Filtration> filtrationList = creatFiltrationList(request,
				"filter_");
		pageData.setFiltrations(filtrationList);
		// 第二不：设置排序条件
		String fieldName = request.getParameter("fieldName");
		String compositorType = request.getParameter("compositorType");
		if (StringUtils.isNotBlank(fieldName)
				&& StringUtils.isNotBlank(compositorType)) {
			Compositor compositor = new Compositor(fieldName, Enum.valueOf(
					CompositorType.class, compositorType));
			pageData.setCompositor(compositor);
		}
		// 第三步：设置当前页
		String pageNoStr = request.getParameter("pageNo");
		if (StringUtils.isNotBlank(pageNoStr)) {
			Integer pageNo = Integer.parseInt(pageNoStr);
			pageData.setPageNo(pageNo);
		}
	}

	/**
	 * 获取登录用户信息
	 * 
	 * @param request
	 *            请求
	 * @return 登录的用户
	 */
	public static Administator getLoginAdministator(HttpServletRequest request) {
		Administator loginAdmin = null;
		if (request != null) {
			loginAdmin = (Administator) request.getSession().getAttribute(
					"administators");
		}
		return loginAdmin;
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param administator
	 * @return
	 */
	public static boolean login(HttpServletRequest request,
			Administator administator) {
		boolean flag = false;
		if (request != null) {
			request.getSession().setAttribute("administators", administator);
			flag = true;
		}
		return flag;
	}

	/**
	 * 移除session里面的administator
	 * 
	 * @param request
	 * @return
	 */
	public static boolean removeLoginAdministator(HttpServletRequest request) {
		boolean flag = false;
		if (request != null) {
			request.getSession().removeAttribute("administators");
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断phoneNum是否为合法的手机
	 * 
	 * @param phoneNum
	 *            电话号码
	 * @return
	 */
	public static boolean checkPhoneNum(String phoneNum) {
		String maxg = "(\\+?86)?1(3|8|5)\\d{9}";
		if (phoneNum.matches(maxg)) {
			return true;
		} else {
			return false;
		}
	}
}
