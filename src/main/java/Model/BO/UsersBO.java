package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.BEAN.User;
import Model.DAO.LoginDAO;

public class UsersBO {
	LoginDAO u = new LoginDAO();
	public List<User> getAllUser(){
		return u.getAllUser();
	}
	
	public User getUserByID(String ID) {
		List<User> LUser = u.getAllUser();
		for(User user:LUser) {
			if(ID.equals(user.getUser_ID())) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> SearchFriend(String name){
		List<User> lUser = u.getAllUser();
		List<User> lFriend = new ArrayList<User>();
		for(User u:lUser) {
			try {
				if(u.getFullname()!=null && u.getFullname().contains(name))
					lFriend.add(u);
			}catch(Exception e) {}
		}
		return lFriend;
	}
	
	public boolean isFriend(User uA, User uB) {
		try {
			List<String> Group_messA = uA.getGroup_mess();
			if(Group_messA.contains(uB.getUser_ID())) return true;
		}catch (Exception e) {
		}
		return false;
	}
	
	public boolean addID(User user, String ID) {
		List<String> friend = new ArrayList<String>();
		if(user.getGroup_mess() !=null) {
		user.getGroup_mess().forEach(p -> friend.add(p));
		}
		friend.add(ID);
		user.setGroup_mess(friend);
		if(u.UpdateUser(user)) return true;
		return false;
	}
	
	public boolean AddFriend(User uA, User uB) {
		UsersBO userDO = new UsersBO();
		if(userDO.addID(uA, uB.getUser_ID())&&userDO.addID(uB, uA.getUser_ID())) return true;
		return false;
	}
	
	public boolean deleteFriend(User user, String ID) {
		List<String> friend = new ArrayList<String>();
		user.getGroup_mess().forEach(p -> friend.add(p));
		if(friend.remove(ID)) {
			user.setGroup_mess(friend);
			u.UpdateUser(user);
			return true;
		}
		return false;
	}
	public boolean insertUser(User user) {
		return u.insertUser(user);
	}
	
	public boolean updateUser(User user) {
		return u.UpdateUser(user);
	}
}
