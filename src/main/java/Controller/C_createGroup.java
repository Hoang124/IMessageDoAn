package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Group;
import Model.BEAN.User;
import Model.BO.GroupBO;
import Model.BO.UsersBO;

/**
 * Servlet implementation class C_createGroup
 */
@WebServlet("/C_createGroup")
public class C_createGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_createGroup() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String NameGroup = request.getParameter("NameGroup");
		List<String> LFriend = (List<String>) request.getSession().getAttribute("LFriend");
		User user = (User) request.getSession().getAttribute("User");
		LFriend.add(user.getUser_ID());
		UsersBO userDO = new UsersBO();
		String nameCreate = "-1";
		Group gp = new Group();
		gp.setName(nameCreate);
		GroupBO gpDO = new GroupBO();
		gpDO.createGroup(gp);
		List<Group> LGroup = gpDO.getAllGroups();
		for(Group g:LGroup) {
			if(g.getName().equals(nameCreate)) {
				gp = g;
			}
		}
		gp.setName(NameGroup);
		gp.setID_Friend(LFriend);
		gpDO.UpdateGroup(gp);
		for(String S:LFriend) {
			userDO.addID(userDO.getUserByID(S), gp.getID_Group());
		}
		request.getSession().removeAttribute("LFriend");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
