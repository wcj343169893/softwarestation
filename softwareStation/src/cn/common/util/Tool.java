package cn.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class Tool {
	public static String CONFIG = null;
	public static String packages = "cn.ss.wap.";

	/**
	 * ɾ��ָ���ļ�
	 * 
	 * @param path
	 *            ����·��
	 * @param folder
	 *            �ļ���
	 * @param fileName
	 *            ��Ҫɾ�����ļ���
	 * @return
	 */
	public static boolean deleteFile(String path, Folder folder,
			int softwareInfoId, String fileName) {
		boolean flag = false;
		File file = new File(path + folder + "/" + softwareInfoId + "/"
				+ fileName);
		if (file.isFile()) {
			file.delete();
		}
		return flag;
	}

	/**
	 * ɾ���ļ����ļ���
	 * 
	 * @param path
	 *            ��Ҫɾ�����ļ�|�ļ���
	 */
	public static void removeFile(String path) {
		removeFile(new File(path));
	}

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
	 * �����ļ���
	 * 
	 * @param path
	 *            ·��
	 * @param folder
	 *            �ļ��� image/file
	 * @param mkdirName
	 *            �ļ��е�����
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
	 * �õ��ļ��Ĵ�С
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
	 * �ϴ��ļ�
	 * 
	 * @param file
	 *            ��Ҫ�ϴ����ļ�
	 * @param name
	 *            �ϴ��������
	 * @param path
	 *            �ϴ�·��
	 * @param folder
	 *            �ϴ����ļ���
	 * @param softwareId
	 *            �����Ϣ��id������󣬷�����·���У�
	 * @return
	 */
	public static boolean UploadFile(File file, String name, String path,
			Folder folder, int softwareId) {
		boolean flag = false;
		// �õ����ļ��Ķ�����������
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			// �ϴ��ļ�֮ǰ���ȴ���һ���ļ���,�ļ��е�����ΪsoftwareInfo��id
			mkdir(path, folder, String.valueOf(softwareId));
			System.out.println("path:" + path);
			File outFile = new File(path + String.valueOf(folder) + "/"
					+ softwareId + "/" + name);
			boolean isExits = outFile.exists();// �ж��Ƿ����ͬ�ļ������ļ�������У�����ɾ���ļ����ٴ���һ���ļ����ϴ�
			if (isExits) {
				System.out
						.println("file is exists ,I want to delete is ,and create a new one!");
				removeFile(outFile);
				outFile = new File(path + String.valueOf(folder) + "/"
						+ softwareId + "/" + name);
			}
			// �����ϴ�·��ʵ����һ���ļ������
			FileOutputStream fileOutputStream = new FileOutputStream(outFile);
			byte[] buffer = new byte[8192];
			int bufferRead = 0;
			// ÿ�ζ�8192���ֽ�,-1�Ļ���ʾ�����ļ���ĩβ
			while ((bufferRead = inputStream.read(buffer, 0, 8192)) != -1) {
				// ���ļ��������д
				fileOutputStream.write(buffer, 0, bufferRead);
			}
			// ���ιر�������
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
	 * Stringתdate
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
	 * dateתstring
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
	 * ʱ���ǰ���������
	 * 
	 * @param date
	 *            ʱ��
	 * @param d
	 *            ����
	 * @return
	 */
	public static Date beforeOrAfterDate(Date date, int d) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, d);
		return calendar.getTime();
	}

	/**
	 * �ж�����ʱ���Ƿ����
	 * 
	 * @param date1
	 *            ʱ��
	 * @param date2
	 *            ʱ��
	 * @param patten
	 *            ��ʽ
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
	 * -- ��ȡ�����ļ�.��̬����
	 * 
	 * @param baseName
	 *            -- �����ļ�����
	 * @param key
	 *            -- ����Ҫ��ȡ�����ݵļ�
	 * @return -- ���ظ��ݼ���ȡ����ֵ
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
			System.out.println("���ܴ������ļ�[" + baseName + "]�л��ֵ[" + key + "]");
		}
		return value;
	}

	/**
	 * -- ��ȡ�����ļ�.��̬������CONFIG��
	 * 
	 * @param key
	 *            -- ����Ҫ��ȡ�����ݵļ�
	 * @return-- ���ظ��ݼ���ȡ����ֵ
	 */
	public static String read(String key) {
		if (CONFIG != null) {
			return read(CONFIG, key);
		}
		return null;
	}
}
