/*
 * 用户信息
 * */

package model;

public class User {
	private int id;
	private String UserName;
	private String UserPassword;
	private String UserType;
	
	
	public User() {
		super();
	}
	
	public User(String userName, String userPassword, String userType) {
		super();
		UserName = userName;
		UserPassword = userPassword;
		UserType = userType;
	}
	
	
	public User(int id, String userName, String userPassword, String userType) {
		super();
		this.id = id;
		UserName = userName;
		UserPassword = userPassword;
		UserType = userType;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}
	
	
}
