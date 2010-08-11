package cn.yulezu.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * 处理properties文件
 * 
 * @author 文朝军
 * 
 */
public class Configuration {
	private Properties properties;
	private FileInputStream inputFile;
	private FileOutputStream outputfile;

	/**
	 * 初始化configuration类
	 */
	public Configuration() {
		properties = new Properties();
	}

	/**
	 * 初始化configuration类
	 * 
	 * @param fileName
	 *            要读取的配置文件的路径+名字
	 */
	public Configuration(String fileName) {
		properties = new Properties();
		try {
			inputFile = new FileInputStream(fileName);
			properties.load(inputFile);
			inputFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param key
	 *            取得其值的键
	 * @return key 的值
	 */
	public String getValue(String key) {
		if (properties.containsKey(key)) {
			return properties.getProperty(key);// 得到某一属性的值
		} else {
			return "";
		}
	}

	/**
	 * 重载函数，得到key的值
	 * 
	 * @param fileName
	 *            文件的路径+文件名
	 * @param key
	 *            键
	 * @return key值
	 */
	public String getValue(String fileName, String key) {
		try {
			inputFile = new FileInputStream(fileName);
			properties.load(inputFile);
			inputFile.close();
			if (properties.containsKey(key)) {
				return properties.getProperty(key);// 得到某一属性的值
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 清除properties的key和其值
	 */
	public void clear() {
		properties.clear();
	}

	/**
	 * 改变或添加一个key的值，当key存在于文件中是，该key的值被value说代替 当key不存在时，该key的值就是value
	 * 
	 * @param key
	 *            要存入的键
	 * @param value
	 *            要存入的值
	 */
	public void setValue(String key, String value) {
		properties.setProperty(key, value);
	}

	/**
	 * 将更改后的文件数据存入指定的文件中，该文件可以不存在
	 * 
	 * @param fileName
	 *            文件路径+名称
	 * @param description
	 *            对该文件的描述
	 */
	public void saveFile(String fileName, String description) {
		try {
			outputfile = new FileOutputStream(fileName);
			properties.store(outputfile, description);
			outputfile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.setValue("a", "aaaa");
		configuration.setValue("b", "bbbb");
		configuration.setValue("v", "vvvv");
		configuration.setValue("tianqi", "今天天气好晴朗，处处百花香");
		configuration.saveFile("./test.properties", "我是来打酱油的");
	}
}
