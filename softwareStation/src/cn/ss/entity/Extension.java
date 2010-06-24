package cn.ss.entity;

import java.util.ArrayList;
import java.util.List;

public class Extension {
	private int id;
	private String name;
	private List<PhoneOs> phoneOsList = new ArrayList<PhoneOs>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PhoneOs> getPhoneOsList() {
		return phoneOsList;
	}

	public void setPhoneOsList(List<PhoneOs> phoneOsList) {
		this.phoneOsList = phoneOsList;
	}

}
