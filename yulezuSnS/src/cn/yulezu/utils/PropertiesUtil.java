package cn.yulezu.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtil {

	/**
	 * 取得配置文件 
	 * 
	 * @param path
	 *            配置文件的路径，从根目录下得到格式如下:/com/../.././xx.properties
	 * @return 配置文件对象
	 */
	public Properties getProperties(String path) {
		String filePath = getClass().getResource(path).getPath();
		// System.out.println(filePath);
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(
					filePath));
			Properties p = new Properties();
			p.load(in);
			in.close();
			return p;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		PropertiesUtil p = new PropertiesUtil();
		// p.test();
		Properties o = p.getProperties("/config.properties");
			

		Set set = o.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			System.out.println(o.getProperty(key));
		}
		// System.out.println(o.getProperty("current_day_water"));
	}

	public void test() {
		PropertiesUtil p = new PropertiesUtil();
		Properties o = p.getProperties("/config.properties");
		o.setProperty("name" + (o.size() + 1), "开心校园");
		String filePath = getClass().getResource("/public_room.properties")
				.getPath();
		// System.out.println(filePath);
		try {
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					filePath));
			o.store(out, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得所有key
	 * 
	 * @return
	 */
	public List getLoverPropertiesKeies() {
		PropertiesUtil p = new PropertiesUtil();
		List data = new ArrayList();
		Properties o = p.getProperties("/lover.properties");
		Set set = o.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			data.add(key);
		}
		return data;
	}

	public void setLoverProperties(String key, String value) {
		PropertiesUtil p = new PropertiesUtil();
		Properties o = p.getProperties("/lover.properties");
		o.setProperty(key, value);
		String filePath = getClass().getResource("/lover.properties").getPath();
		try {
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					filePath));
			o.store(out, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String getLoverProperties(String key) {
		PropertiesUtil p = new PropertiesUtil();
		Properties o = p.getProperties("/lover.properties");
		return o.getProperty(key);
	}

	/**
	 * 获取config.properties里面的值
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfigProperties(String key) {
		PropertiesUtil p = new PropertiesUtil();
		Properties pro = p.getProperties("/config.properties");
		return pro.getProperty(key);
	}

}
