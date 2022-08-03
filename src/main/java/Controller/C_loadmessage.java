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

import Model.BEAN.Message;
import Model.BO.GroupBO;
import Model.BO.MessageBO;
/**
 * Servlet implementation class loadmessageservlet
 */
@WebServlet("/C_loadmessage")
public class C_loadmessage extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public C_loadmessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		da sua
		String receiveID = request.getParameter("receiveID");
		String sendID = request.getParameter("sendID");
		GroupBO gpDO = new GroupBO();
		boolean isGroup = gpDO.isGroup(receiveID);
		MessageBO mess = new MessageBO();
		List<Message> messages = new ArrayList<Message>();
		if(isGroup) {
			messages = mess.getMessageGP(receiveID);
		}
		else {
			messages = mess.getMessages(sendID, receiveID);
		}
		String data = "";
		if (messages != null) {
			for (Message message : messages){
				if (message.getFrom_ID().equals(sendID)) {
					data += "<div class=\"message__content message__content--send\">\r\n"
							+ "                        <div class=\"message__content-text--send\">"+message.getContent()+"</div>\r\n"
							+ "                    </div>";
				}
				else {
					data += "<div class=\"message__content message__content--recieve\">\r\n"
							+ "                        <div class=\"message__content-text--recieve\">\r\n"
							+ "                            "+message.getContent()+"\r\n"
							+ "                        </div>\r\n"
							+ "                    </div>";
				}
			}
		}
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println(data);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
