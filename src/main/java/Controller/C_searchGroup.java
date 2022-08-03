package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.UsersBO;

/**
 * Servlet implementation class C_searchGroup
 */
@WebServlet("/C_searchGroup")
public class C_searchGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public C_searchGroup() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("Name");
		UsersBO userdo = new UsersBO();
		List<User> lFriend = userdo.SearchFriend(name);
		String data="";
		for(User user:lFriend) {
					data += "                        <li class=\"item__friend\">\r\n"
						+ "                            <form action=\"C_insertFriend\">\r\n"
						+ "                                <i class=\"item__friend-img font__img-account fas fa-user\"></i>\r\n"
						+ "                                <span class=\"item__friend-name\">"+user.getFullname()+"</span>\r\n"
						+ "                                <input type=\"hidden\" name=\"Friend_ID\" value=\""+user.getUser_ID()+"\">\r\n"
						+ "                                <input class=\"item__friend-btn\" type=\"button\" value=\"Add\" onclick=\"insertID(\'"+user.getUser_ID()+"\')\"></input>\r\n"
						+ "                            </form>\r\n"
						+ "                        </li>";
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
