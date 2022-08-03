package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class C_insertFriend
 */
@WebServlet("/C_insertFriend")
public class C_insertFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_insertFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID_Friend");
		List<String> LFriend = (List<String>) request.getSession().getAttribute("LFriend");
		if(LFriend != null) {
			if(!LFriend.contains(id)) LFriend.add(id);
		}
		else {
			LFriend = new ArrayList<String>();
			LFriend.add(id);
		}
		request.getSession().setAttribute("LFriend", LFriend);
		LFriend.forEach(p -> System.out.println(p));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
