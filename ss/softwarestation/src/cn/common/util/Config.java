package cn.common.util;

import java.util.HashMap;
import java.util.Map;

public class Config {
	/**
	 * �ϴ��ļ���·��
	 */
	// public static final String uploadPath = "d:/";
	/**
	 * ��������
	 */
	public static Map<Integer, String> reports = new HashMap<Integer, String>();
	static {
		reports.put(1, "�޷�����");
		reports.put(2, "�ļ���");
		reports.put(3, "�汾����");
		reports.put(4, "�۷����");
		reports.put(5, "���ֲ���");
		reports.put(6, "��������");
	}

	public static Map<Integer, String> getReports() {
		return reports;
	}

	public static void setReports(Map<Integer, String> reports) {
		Config.reports = reports;
	}

}
