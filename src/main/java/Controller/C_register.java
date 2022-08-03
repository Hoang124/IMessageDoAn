package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.UsersBO;

@WebServlet("/C_register")
public class C_register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_register() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String Fullname = request.getParameter("Fullname");
		String Age = request.getParameter("Age");
		String Gender = request.getParameter("Gender");
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		User user = new User();
		UsersBO uBO = new UsersBO();
		user.setFullname(Fullname);
		user.setAge(Integer.parseInt(Age));
		user.setGender(Gender.equals("nam")?true:false);
		user.setUsername(Username);
		user.setPassword(Password);
		uBO.insertUser(user);
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
