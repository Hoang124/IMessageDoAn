package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.BEAN.Message;
import Model.BEAN.User;
import Model.BO.GroupBO;
import Model.BO.MessageBO;
import Model.BO.UsersBO;


@WebServlet("/C_deleteMessage")
public class C_deleteMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C_deleteMessage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sendID = request.getParameter("sendID");
		String receiveID = request.getParameter("receiveID");
		GroupBO gpDO = new GroupBO();
		MessageBO mDO = new MessageBO();
		UsersBO uDO = new UsersBO();
		if(gpDO.isGroup(receiveID)) {
			mDO.deleteMessageGP(receiveID);
			gpDO.deleteGroup(receiveID);
			uDO.deleteFriend(uDO.getUserByID(sendID), receiveID);
		}else {
			mDO.deleteMessage(sendID, receiveID);
			uDO.deleteFriend(uDO.getUserByID(sendID), receiveID);
			uDO.deleteFriend(uDO.getUserByID(receiveID), sendID);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
