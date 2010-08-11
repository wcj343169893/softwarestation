package cn.yulezu.utils;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.yulezu.sns.entity.Users;

public class RequestUtil {

	private static boolean debug = false;
	public static final String URLPatternMask = "####";

	/**
	 * 访问url
	 * 
	 * @param rURL
	 * @return
	 * @throws IOException
	 */
	public static String accessInterface(String urlPattern,
			ArrayList parameterArr, HashMap headerMap) throws Exception {
		String result = "";

		InputStream content = null;
		BufferedReader in = null;
		URL tmUrl = null;
		String line = null;
		String statusCode = null;
		String rURL = urlPattern;

		if (parameterArr != null) {
			rURL = fillURLPattern(urlPattern, parameterArr);
		}

		if (debug) {
			System.out.println("************************************");
			System.out.println("accessing interface: " + rURL);
		}
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		tmUrl = new URL(rURL);

		HttpURLConnection urlConn = (HttpURLConnection) tmUrl.openConnection();

		if (headerMap != null) {
			Iterator it = headerMap.keySet().iterator();
			String headerName = "", headerVal = "";
			while (it.hasNext()) {
				headerName = (String) it.next();
				headerVal = (String) headerMap.get(headerName);
				urlConn.setRequestProperty(headerName, headerVal);
			}
			headerMap.clear();
		}
		String cntEncoding = urlConn.getContentEncoding();
		String contentType = urlConn.getContentType();
		// statusCode=urlConn.getHeaderField(0);
		String encoding;
		if (cntEncoding != null || contentType == null)
			encoding = cntEncoding;
		else if (contentType.toLowerCase().indexOf("charset") >= 0) {
			int pos = contentType.toLowerCase().indexOf("charset");
			encoding = contentType.substring(pos + 8).trim();
		} else
			encoding = "gb2312";

		if (headerMap != null) {
			Iterator it = urlConn.getHeaderFields().keySet().iterator();
			while (it.hasNext()) {
				Object o = it.next();
				if (o == null)
					continue;
				headerMap.put(o.toString(), urlConn.getHeaderFields().get(
						o.toString()));
			}
		}
		content = (InputStream) urlConn.getContent();

		in = new BufferedReader(new InputStreamReader(content, encoding));
		while ((line = in.readLine()) != null) {
			if (debug)
				System.out.println(line);
			if (line.trim().compareTo("") == 0)
				continue;
			result = result + line + "\n";
		}
		if (debug)
			System.out.println("************************************");

		content.close();
		in.close();
		return result.trim();
	}

	/**
	 * 装填生成查询接口url
	 * 
	 * @param pattern
	 * @param arr
	 * @return
	 */
	public static String fillURLPattern(String pattern, ArrayList arr) {
		String urlstr = pattern;
		int vIndex = 0;
		int sI = 0;
		// System.out.println("arr is null?"+(arr==null));
		while ((sI = urlstr.indexOf(URLPatternMask)) >= 0) {
			// System.out.println("parameter is null?"+(arr.get(vIndex)==null));
			urlstr = urlstr.substring(0, sI)
					+ URLEncoder.encode(arr.get(vIndex).toString())
					+ urlstr.substring(sI + URLPatternMask.length());
			vIndex++;
		}
		return urlstr;
	}

	public static String[] decodeJson2Arr(String str, String deviceCode) {
		try {
			if (str.startsWith("["))
				str = str.substring(1);
			if (str.endsWith("]"))
				str = str.substring(0, str.length() - 1);
			String[] strarr = str.split(deviceCode);
			for (int i = 0; i < strarr.length; i++)
				strarr[i] = strarr[i].replaceAll("'", "").trim();
			return strarr;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 处理请求URL
	 * 
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 */
	public static String encodeURL(HttpServletRequest request,
			HttpServletResponse response, String url) {
		if (url.indexOf(Config.SID + "=") > 0) {
			return response.encodeURL(url);
		}
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute(Config.SID);
		if (url.indexOf("?") > 0) {
			url = response.encodeURL(url.substring(0, url.indexOf("?")))
					+ "?"
					+ Config.SID
					+ "="
					+ sid
					+ (!url.endsWith("?") ? "&amp;"
							+ url.substring(url.indexOf("?") + 1) : "");

		} else {
			url = response.encodeURL(url) + "?" + Config.SID + "=" + sid;
		}
		return url;
	}
}