package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.User;
import Model.BO.UsersBO;

@WebServlet("/C_updateUser")
public class C_updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_updateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String ID = request.getParameter("idUpdate");
		String Fullname = request.getParameter("nameUpdate");
		String Age = request.getParameter("ageUpdate");
		String Gender = request.getParameter("genderUpdate");
		String Password = request.getParameter("passwordUpdate");
		UsersBO uBO = new UsersBO();
		User user = uBO.getUserByID(ID);
		user.setFullname(Fullname);
		user.setGender(Gender.equals("nam")?true:false);
		user.setAge(Integer.parseInt(Age));
		if(Password.length()>=6)user.setPassword(Password);
		uBO.updateUser(user);
		request.getSession().removeAttribute("User");
		request.getSession().setAttribute("User", user);
		response.sendRedirect("messaging.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
