package Model.BEAN;

import java.util.List;

import org.bson.Document;

public class User {

	private String User_ID;
	private String Fullname;
	private boolean Gender;
	private int Age;
	private String Username;
	private String Password;
	private List<String> Group_mess;
	
	public User() {}
	public User(String fullname, boolean gender, int age, String username, String password) {
		Fullname = fullname;
		Gender = gender;
		Age = age;
		Username = username;
		Password = password;
	}
	
	public Document convertDB() {
		return new Document("Fullname", Fullname).append("Gender", Gender).append("Age", Age).append("Username", Username).append("Password", Password).append("Group_mess", Group_mess);
	}

	public String getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(String user_ID) {
		User_ID = user_ID;
	}

	public String getFullname() {
		return Fullname;
	}

	public void setFullname(String fullname) {
		Fullname = fullname;
	}

	public boolean isGender() {
		return Gender;
	}

	public void setGender(boolean gender) {
		Gender = gender;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	public List<String> getGroup_mess() {
		return Group_mess;
	}

	public void setGroup_mess(List<String> group_mess) {
		Group_mess = group_mess;
	}
}
