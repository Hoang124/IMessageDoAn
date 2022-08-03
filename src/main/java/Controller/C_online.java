package Controller;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import Model.BEAN.Group;
import Model.BO.GroupBO;

@ServerEndpoint(value = "/online/{id}")
public class C_online {
	private Session session;
	private static HashMap<String, C_online> users = new HashMap<String, C_online>();

	@OnOpen
	public void onOpen(Session session, @PathParam("id") String Id) throws IOException {
		this.session = session;
		users.put(Id, this);
	}

	@OnMessage
	public void onMessage(Session session, String message, @PathParam("id") String Id) throws Exception {
		if (users.get(message) != null) {
			users.get(message).session.getBasicRemote().sendText(Id);
		}
		else {
			GroupBO groupBO = new GroupBO();
			Group group = groupBO.getGroupById(message);
			if (group != null && group.getID_Friend().size() > 0)
			for (String userID: group.getID_Friend()) {
				if (users.get(userID) != null && (userID.compareTo(Id) != 0))
					users.get(userID).session.getBasicRemote().sendText(Id);
			}
		}
	}

	@OnError
	public void error(Session session, Throwable throwable, @PathParam("id") String Id) {
		users.remove(Id);
		System.out.println(throwable.getMessage());
	}

	@OnClose
	public void onClose(Session session, @PathParam("id") String Id) throws IOException {
		users.remove(Id);
	}

}
