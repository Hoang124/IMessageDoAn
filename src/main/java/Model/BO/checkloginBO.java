package Model.BO;

import Model.BEAN.User;
import Model.DAO.LoginDAO;

public class checkloginBO {
	public User checkLogin(String username,String password) {
		LoginDAO log = new LoginDAO();
		return log.checkLogin(username, password);
	}
}
