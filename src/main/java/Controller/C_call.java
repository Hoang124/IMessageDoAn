package Controller;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/call/{id}")
public class C_call {

	public Session session;
//private static Set<CallRoomEP> CREP = new CopyOnWriteArraySet<>();
	private static HashMap<String, Set<C_call>> rooms = new HashMap<String, Set<C_call>>();

	@OnOpen
	public void onOpen(Session session, @PathParam("id") String Id) throws IOException {
		if (rooms.get(Id) == null) {
			rooms.put(Id, new CopyOnWriteArraySet<C_call>());
		}
		this.session = session;
//CREP.add(this);
		rooms.get(Id).add(this);
		System.out.println(session.getId());
	}

	@OnMessage
	public void onMessage(Session session, ByteBuffer message, @PathParam("id") String Id) throws IOException {
		broadcast(message, Id);
	}

	@OnError
	public void error(Session session, Throwable throwable, @PathParam("id") String Id) {
		rooms.get(Id).remove(this);
		System.out.println(throwable.getMessage());
//CREP.remove(this);
	}

	@OnClose
	public void onClose(Session session, @PathParam("id") String Id) throws IOException {
		rooms.get(Id).remove(this);
//CREP.remove(this);
	}

	private synchronized void broadcast(ByteBuffer message, String Id) throws IOException {
		for (C_call i : rooms.get(Id)) {
			if (!i.session.getId().equals(this.session.getId()))
				synchronized (i.session.getBasicRemote()) {
					i.session.getBasicRemote().sendBinary(message);
				}
		}
		/*
		 * for (Session s : session.getOpenSessions()) {
		 * s.getBasicRemote().sendText(message); }
		 */
	}
}
