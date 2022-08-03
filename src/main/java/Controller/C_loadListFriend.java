package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Group;
import Model.BEAN.Message;
import Model.BEAN.User;
import Model.BO.GroupBO;
import Model.BO.UsersBO;

@WebServlet("/C_loadListFriend")
public class C_loadListFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_loadListFriend() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserID = request.getParameter("UserID");
		UsersBO userdo = new UsersBO(); 
		GroupBO gpDo = new GroupBO();
		List<Group> lgroup = gpDo.getAllGroups();
		List<User> ListUser = userdo.getAllUser();
		User u = userdo.getUserByID(UserID);
		String data = "";
		if (u.getGroup_mess() != null)
		for (String ID : u.getGroup_mess()){
			for(User user:ListUser) {
				if(ID.equals(user.getUser_ID())) {
					data += "               <li class=\"item__friend\">\r\n"
							+ "                            <div class=\"item__friend-detail\">\r\n"
							+ "                                <form class=\"item__friend-form\" action=\"messaging.jsp\">\r\n"
							+ "	                                <i class=\"item__friend-img font__img-account fas fa-user\"></i>\r\n"
							+ "	                                <span class=\"item__friend-name\">"+user.getFullname()+"</span>\r\n"
							+ "	                                <input type=\"hidden\" name=\"targetID\" value=\""+user.getUser_ID()+"\">\r\n"
							+ "	                                <input class=\"item__friend-btn\" type=\"submit\" value=\"Select\">\r\n"
							+ "                                </form>\r\n"
							+ "                                <div class=\"item__friend-more\" onclick=\"ShowDelete(this,'item__friend-deleteshow')\">\r\n"
							+ "                                    <i class=\"item__more fas fa-ellipsis-v\"></i>\r\n"
							+ "                                    <div class=\"item__friend-delete\" onclick=\"deleteMessage('"+user.getUser_ID()+"')\">\r\n"
							+ "                                        <span class=\"item__delete-span\">Delete</span>\r\n"
							+ "                                    </div>\r\n"
							+ "                                </div>\r\n"
							+ "                            </div>    \r\n"
							+ "	                    </li>";
				}
			}
			
			for(Group gp:lgroup) {
				if(ID.equals(gp.getID_Group())) {
					data += "               <li class=\"item__friend\">\r\n"
							+ "                            <div class=\"item__friend-detail\">\r\n"
							+ "                                <form class=\"item__friend-form\" action=\"messaging.jsp\">\r\n"
							+ "	                                <i class=\"item__friend-img font__img-account fas fa-user\"></i>\r\n"
							+ "	                                <span class=\"item__friend-name\">"+gp.getName()+"</span>\r\n"
							+ "	                                <input type=\"hidden\" name=\"targetID\" value=\""+gp.getID_Group()+"\">\r\n"
							+ "	                                <input class=\"item__friend-btn\" type=\"submit\" value=\"Select\">\r\n"
							+ "                                </form>\r\n"
							+ "                                <div class=\"item__friend-more\" onclick=\"ShowDelete(this,'item__friend-deleteshow')\">\r\n"
							+ "                                    <i class=\"item__more fas fa-ellipsis-v\"></i>\r\n"
							+ "                                    <div class=\"item__friend-delete\" onclick=\"deleteMessage('"+gp.getID_Group()+"')\" >\r\n"
							+ "                                        <span class=\"item__delete-span\">Delete</span>\r\n"
							+ "                                    </div>\r\n"
							+ "                                </div>\r\n"
							+ "                            </div>    \r\n"
							+ "	                    </li>";
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
