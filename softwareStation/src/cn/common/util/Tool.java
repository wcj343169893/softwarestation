package cn.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Tool {
	public static String CONFIG = null;
	public static String packages = "cn.ss.wap.";

	/**
	 * 删除指定文件
	 * 
	 * @param path
	 *            工程路径
	 * @param folder
	 *            文件夹
	 * @param fileName
	 *            需要删除的文件名
	 * @return
	 */
	public static boolean deleteFile(String path, Folder folder,
			int softwareInfoId, String fileName) {
		boolean flag = false;
		File file = new File(path + folder + "/" + softwareInfoId + "/"
				+ fileName);
		if (file.isFile()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean deleteFile(String path, Folder folder, String fileName) {
		boolean flag = false;
		File file = new File(path + folder + "/" + fileName);
		if (file.isFile()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除文件、文件夹
	 * 
	 * @param path
	 *            需要删除的文件|文件夹
	 */
	public static void removeFile(String path) {
		removeFile(new File(path));
	}

	/**
	 * 删除文件和文件夹
	 * 
	 * @param path
	 */
	public static void removeFile(File path) {
		System.out.println("removing file " + path.getPath());
		if (path.isDirectory()) {
			File[] child = path.listFiles();
			if (child != null && child.length != 0) {
				for (int i = 0; i < child.length; i++) {
					removeFile(child[i]);
					child[i].delete();
				}
			}
		}
		path.delete();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 *            路径
	 * @param folder
	 *            文件夹 image/file
	 * @param mkdirName
	 *            文件夹的名称
	 */
	public static void mkdir(String path, Folder folder, String mkdirName) {
		try {
			path += !"".equals(String.valueOf(folder).trim()) ? (folder + "/")
					: "";
			File dirFile = new File(path + mkdirName);
			boolean bFile = dirFile.exists();
			if (bFile == true) {
				System.out.println("The folder exists.");
			} else {
				System.out
						.println("The folder do not exist,now trying to create a one...");
				bFile = dirFile.mkdir();
				if (bFile == true) {
					System.out.println("Create successfully!");
				} else {
					System.out
							.println("Disable to make the folder,please check the disk is full or not.");
					System.exit(1);
				}
			}
		} catch (Exception err) {
			System.err.println("ELS - Chart: " + err.getMessage());
			err.printStackTrace();
		}
	}

	/**
	 * 得到文件的大小
	 * 
	 * @param file
	 * @return
	 */
	public static int fileSize(File file) {
		long length = file.length();
		double size = (length) / 1024;
		DecimalFormat df = new DecimalFormat("0");
		String s = df.format(size);
		if (!"".equals(s.trim())) {
			return Integer.parseInt(s);
		}
		return 0;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            需要上传的文件
	 * @param name
	 *            上传后的名字
	 * @param path
	 *            上传路径
	 * @param folder
	 *            上传的文件夹
	 * @param softwareId
	 *            软件信息的id（处理后，放入了路径中）
	 * @return
	 */
	public static boolean UploadFile(File file, String name, String path,
			Folder folder, int softwareId) {
		boolean flag = false;
		// 得到该文件的二进制输入流
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			// 上传文件之前，先创建一个文件夹,文件夹的名称为softwareInfo的id
			mkdir(path, folder, String.valueOf(softwareId));
			System.out.println("path:" + path);
			File outFile = new File(path + String.valueOf(folder) + "/"
					+ softwareId + "/" + name);
			boolean isExits = outFile.exists();// 判断是否存在同文件名的文件，如果有，则先删除文件，再创建一个文件，上传
			if (isExits) {
				System.out
						.println("file is exists ,I want to delete is ,and create a new one!");
				removeFile(outFile);
				outFile = new File(path + String.valueOf(folder) + "/"
						+ softwareId + "/" + name);
			}
			// 根据上传路径实例化一个文件输出流
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			byte[] buffer = new byte[8192];
			int bufferRead = 0;
			// 每次读8192个字节,-1的话表示读到文件的末尾
			while ((bufferRead = inputStream.read(buffer, 0, 8192)) != -1) {
				// 往文件输出流中写
				fileOutputStream.write(buffer, 0, bufferRead);
			}
			// 依次关闭流对象
			fileOutputStream.close();
			inputStream.close();
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 写入文件
	 * 
	 * @param arg
	 * @param path
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(String arg, String path, Folder folder,
			String fileName) {
		boolean flag = false;
		OutputStream out = null;
		try {
			File file = new File(path + folder.index + "/" + fileName);
			out = new FileOutputStream(file);
			byte[] buffer = new byte[8192];
			buffer = arg.getBytes();
			out.write(buffer);
			out.close();
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 读取文件内容
	 * 
	 * @param path
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static String readFile(String path, Folder folder, String fileName) {
		StringBuffer sb = new StringBuffer();
		try {
			File file = new File(path + folder.index + "/" + fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				if (!"".equals(tmp.trim())) {
					sb.append(tmp);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 读取指定文件
	 * 
	 * @param file
	 * @return
	 */
	public static String readFile(File file) {
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				if (!"".equals(tmp.trim())) {
					sb.append(tmp);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 读取指定文件夹下的所有文件内容
	 * 
	 * @param path
	 * @param folder
	 * @return
	 */
	public static Map<String, String> readFile(String path, Folder folder) {
		Map<String, String> maps = new HashMap<String, String>();
		File file = new File(path + folder);
		if (file.isDirectory()) {
			File[] child = file.listFiles();
			for (int i = 0; i < child.length; i++) {
				String s = readFile(child[i]);
				if (s != null && !"".equals(s.trim())) {
					maps.put(child[i].getName(), s);
				}
			}
		}
		return maps;
	}

	/**
	 * String转date
	 * 
	 * @param date
	 * @param patten
	 * @return
	 */
	public static Date stringFormatDate(String date, String patten) {
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		try {
			d = sdf.parse(date);
		} catch (Exception e) {
		}
		return d;
	}

	/**
	 * date转string
	 * 
	 * @param date
	 * @param patten
	 * @return
	 */
	public static String dateFormatString(Date date, String patten) {
		String d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		try {
			d = sdf.format(date);
		} catch (Exception e) {
		}
		return d;
	}

	/**
	 * 时间的前后几天的日期
	 * 
	 * @param date
	 *            时间
	 * @param d
	 *            天数
	 * @return
	 */
	public static Date beforeOrAfterDate(Date date, int d) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, d);
		return calendar.getTime();
	}

	/**
	 * 判断两个时间是否相等
	 * 
	 * @param date1
	 *            时间
	 * @param date2
	 *            时间
	 * @param patten
	 *            格式
	 * @return
	 */
	public static boolean dateIsEque(Date date1, Date date2, String patten) {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat(patten);
		if (sdf.format(date1).equals(sdf.format(date2))) {
			flag = true;
		}
		return flag;
	}

	/**
	 * -- 读取属性文件.静态方法
	 * 
	 * @param baseName
	 *            -- 属性文件名称
	 * @param key
	 *            -- 键，要读取的内容的键
	 * @return -- 返回根据键读取到的值
	 */
	public static String read(String baseName, String key) {
		if (baseName == null || baseName.equals(""))
			return null;
		ResourceBundle bundle = ResourceBundle.getBundle(baseName);
		String value = null;
		try {
			value = bundle.getString(key);
		} catch (Exception e) {
			value = null;
			System.out.println("不能从属性文件[" + baseName + "]中获得值[" + key + "]");
		}
		return value;
	}

	/**
	 * -- 读取属性文件.静态方法（CONFIG）
	 * 
	 * @param key
	 *            -- 键，要读取的内容的键
	 * @return-- 返回根据键读取到的值
	 */
	public static String read(String key) {
		if (CONFIG != null) {
			return read(CONFIG, key);
		}
		return null;
	}

	/**
	 * 过滤特殊字符
	 * 
	 * @param string
	 * @return
	 */
	public static String filterString(String string) {
		String s = string;
		if (s != null && !"".equals(s.trim())) {
			s = s.replace("<", "&lt;");
			s = s.replace(">", "&gt;");
			s = s.replace("&", "&amp;");
			s = s.replace("'", "&apos;");
			s = s.replace("\"", "&quot;");
		}
		return s;
	}
}
