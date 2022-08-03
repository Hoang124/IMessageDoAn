package Model.BEAN;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Group {
	String ID_Group;
	String Name;
	List<String> ID_Friend;
	
	public Group() {}
	public Group(String name, List<String> id_friend) {
		Name = name;
		ID_Friend = id_friend;
	}
	
	public Document convertToDB() {
		return new Document("Name", Name).append("ID_Friend", ID_Friend);
	}
	
	public String getID_Group() {
		return ID_Group;
	}
	public void setID_Group(String iD_Group) {
		ID_Group = iD_Group;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public List<String> getID_Friend() {
		return ID_Friend;
	}
	public void setID_Friend(List<String> iD_Friend) {
		ID_Friend = iD_Friend;
	}
}
