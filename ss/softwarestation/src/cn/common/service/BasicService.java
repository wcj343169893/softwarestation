package cn.common.service;

import cn.common.dao.CommonDAO;

public class BasicService {
	protected CommonDAO dao;

	public CommonDAO getDao() {
		return dao;
	}

	public void setDao(CommonDAO dao) {
		this.dao = dao;
	}
	
	
	/**
	 * 
	 * isNullOrEmpty��������
	 * �ж��ַ����Ƿ�Ϊ��
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str){
		if(null==str){
			return true;
		}else if(str.trim().equals("")){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * parseInt��������
	 * �ַ���ת��Ϊ����
	 * @param str
	 * @return
	 */
	public static int parseInt(String str){
		int i=-1;
		try {
			i=Integer.parseInt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
}
