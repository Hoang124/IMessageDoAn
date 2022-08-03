package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.json.JsonObject;

import Model.BEAN.User;
import Model.BO.UsersBO;

/**
 * Servlet implementation class C_getUserById
 */
@WebServlet("/C_getUserById")
public class C_getUserById extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_getUserById() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ID = request.getParameter("ID");
		UsersBO uBO = new UsersBO();
		User user = uBO.getUserByID(ID);
		String ObjectUser = user.convertDB().toJson();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter().write(ObjectUser);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
