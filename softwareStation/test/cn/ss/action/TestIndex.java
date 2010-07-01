package cn.ss.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

public class TestIndex extends TestCase {
	public void testSort() {
		List<String> list = new ArrayList<String>();
		list.add("手机浏览器UC");
		list.add("手机浏");
		list.add("览器UC");
		list.add("手机UC");
		list.add("手机浏");
		list.add("手览器UC");
		list.add("器UC");
		list.add("UC");

		// List<Map<Integer, String>> ls = new ArrayList<Map<Integer,
		// String>>();
		Map<Integer, List<String>> maps = new HashMap<Integer, List<String>>();
		List<String> ls = null;
		for (int i = 0; i < list.size(); i++) {
			if (!maps.containsKey(list.get(i).length())) {
				ls = new ArrayList<String>();
			} else {
				ls = maps.get(list.get(i).length());
			}
			ls.add(list.get(i));
			maps.put(list.get(i).length(), ls);
		}
		int maxLength = 12;
		int mixLength = 10;
		for (int i = 0; i < 12; i++) {
			if (maps.containsKey(i)) {// 如果存在这个长度的信息
				List<String> stringList = maps.get(i);
				if (stringList != null && stringList.size() > 0) {// 判断map中i是否存在数据
					String s = stringList.get(0);
					if (maps.containsKey(maxLength - i)) {

					}

				} else {//

				}
			}
		}
	}

	public void testSort2() {
		Map<Integer, String> maps = new HashMap<Integer, String>();
		maps.put(1, "手机浏览器UC");
		maps.put(2, "手机浏");
		maps.put(3, "览器UC");
		maps.put(4, "手机UC");
		maps.put(5, "手机浏");
		maps.put(6, "手览器UC");
		maps.put(7, "器UC");
		maps.put(8, "UC");
		for (Integer keys : maps.keySet()) {
			
		}
	}
}
