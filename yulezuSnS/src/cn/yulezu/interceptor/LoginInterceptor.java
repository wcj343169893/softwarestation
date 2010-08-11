package cn.yulezu.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

public class LoginInterceptor implements WebArgumentResolver {
	private String mappingURL;// 利用正则表达式映射到需要拦截的路径

	@Override
	public Object resolveArgument(MethodParameter arg0, NativeWebRequest arg1)
			throws Exception {
//		System.out.println("我来拦截啦~~~");
//		System.out.println(arg0.getMethod().getName());
//		HttpServletRequest request = (HttpServletRequest) arg1
//				.getNativeRequest();
//		System.out.println(request.getRemoteAddr());
//		String url = request.getRequestURL().toString();
//		System.out.println(url);
//		System.out.println(mappingURL);
//		System.out.println(url.matches(mappingURL));
		return UNRESOLVED;
	}

	public String getMappingURL() {
		return mappingURL;
	}

	public void setMappingURL(String mappingURL) {
		this.mappingURL = mappingURL;
	}

}
