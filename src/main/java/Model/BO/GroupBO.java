package Model.BO;

import java.util.ArrayList;
import java.util.List;

import Model.BEAN.Group;
import Model.BEAN.Message;
import Model.BEAN.User;
import Model.DAO.GroupDAO;

public class GroupBO {
// đã sửa
	GroupDAO gpDao = new GroupDAO();
	public List<Group> getAllGroups(){
		return gpDao.getAllGroups();
	}
	
	public boolean createGroup(Group gp) {
		return gpDao.createGroup(gp);
	}
	
	public boolean UpdateGroup(Group gp) {
		return gpDao.UpdateGroup(gp);
	}
	
	public Group getGroupById(String id) {
		for(Group gp:gpDao.getAllGroups()) {
			if(gp.getID_Group().equals(id)) return gp;
		}
		return null;
	}
	
	public boolean isGroup(String ID) {
		GroupBO gpDO = new GroupBO();
		Group gp = gpDO.getGroupById(ID);
		if(gp == null) return false;
		else {
			for(Group g:gpDao.getAllGroups()) {
				if(g.getID_Group().equals(gp.getID_Group())) return true;
			}
		}
		return false;
	}
	
	public boolean deleteFriend(Group gp, String ID) {
		List<String> lFriend = gp.getID_Friend();
		lFriend.forEach(p -> lFriend.add(p));
		if(lFriend.remove(ID)) {
			gp.setID_Friend(lFriend);
			gpDao.UpdateGroup(gp);
			return true;
		}
		return false;
	}
	
	public boolean deleteGroup(String ID) {
		GroupBO gpDO = new GroupBO();
		UsersBO uDO = new UsersBO();
		try {
			Group gp = gpDO.getGroupById(ID);
			List<String> lFriend = gp.getID_Friend();
			gpDao.deleteGroup(ID);
			for(String f:lFriend) {
				uDO.deleteFriend(uDO.getUserByID(f), ID);
			}
			return true;
		} catch(Exception e) {}
		return false;
	}
}
