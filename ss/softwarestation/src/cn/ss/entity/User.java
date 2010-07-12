package cn.ss.entity;

/**
 * 用户
 * 
 * @author 文朝军
 * 
 */
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 5348503757218191113L;
	private int id;
	private String username;
	private String password;

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}

}
