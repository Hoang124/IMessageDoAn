package Controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
import Model.BO.MessageBO;
import Model.BO.UsersBO;

/**
 * Servlet implementation class sendmessageservlet
 */
@WebServlet("/C_sendmessage")
public class C_sendmessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C_sendmessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		đã sửa
		String receiveID = request.getParameter("receiveID");
		String sendID = request.getParameter("sendID");
		String content = request.getParameter("messContent");
		UsersBO userdo = new UsersBO();
		GroupBO gpDo = new GroupBO();
		boolean isGroup = gpDo.isGroup(receiveID);
		if(isGroup == false) {
			User usend = userdo.getUserByID(sendID);
			User ureceive = userdo.getUserByID(receiveID);
			if(!userdo.isFriend(usend, ureceive)) userdo.AddFriend(usend, ureceive);
		}
		MessageBO mess = new MessageBO();
		Message message = new Message(sendID, receiveID, LocalDateTime.now(), content, isGroup);
		mess.postMessage(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
