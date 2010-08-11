package cn.yulezu.utils;

import java.util.ArrayList;
import java.util.List;

import cn.yulezu.sns.entity.UserOnline;

public class SessionUtils {

	private static final int maxSize = 500;
	/**
	 * 在线用户列表
	 */
	private static List<UserOnline> onlineList = new ArrayList<UserOnline>();

	/**
	 * 获取在线用户列表
	 * 
	 * @return
	 */
	public static List<UserOnline> getOnlineList() {
		return onlineList;
	}

	/**
	 * 获取在线人数
	 * 
	 * @return
	 */
	public static int getOnlineSize() {
		return onlineList.size();
	}

	/**
	 * 加入在线列表中
	 * 
	 * @param online
	 *            在线信息
	 */
	public static boolean put(UserOnline online) {
		if (getOnlineSize() < maxSize) {
			for (int i = 0; i < onlineList.size(); i++) {
				UserOnline o = onlineList.get(i);
				if (online.getId().equals(o.getId())) {
					onlineList.set(i, online);
					return true;
				}
			}
			onlineList.add(online);
			return true;
		}
		return false;
	}

	/**
	 * 移除在线用户
	 * 
	 * @param usersId
	 *            用户id
	 */
	public static void remove(int usersId) {
		for (int i = 0; i < onlineList.size(); i++) {
			UserOnline online = onlineList.get(i);
			if (online.getUsers().getId() == usersId) {
				onlineList.remove(i);
				break;
			}
		}
	}

	/**
	 * 判断用户是否已经登录
	 * 
	 * @param id
	 * @return
	 */
	private static boolean isOnline(int id) {
		for (int i = 0; i < onlineList.size(); i++) {
			UserOnline online = onlineList.get(i);
			if (online.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
