package cn.common.util;

import java.util.HashMap;
import java.util.Map;

public class Config {
	/**
	 * 上传文件的路径
	 */
	// public static final String uploadPath = "d:/";
	/**
	 * 报错内容
	 */
	public static Map<Integer, String> reports = new HashMap<Integer, String>();
	static {
		reports.put(1, "无法下载");
		reports.put(2, "文件损坏");
		reports.put(3, "版本过旧");
		reports.put(4, "扣费软件");
		reports.put(5, "发现病毒");
		reports.put(6, "其他错误");
	}

	public static Map<Integer, String> getReports() {
		return reports;
	}

	public static void setReports(Map<Integer, String> reports) {
		Config.reports = reports;
	}

}
