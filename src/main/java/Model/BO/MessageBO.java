package Model.BO;

import java.util.Comparator;
import java.util.List;

import Model.BEAN.Message;
import Model.DAO.MessageDAO;

public class MessageBO {
	MessageDAO mess= new MessageDAO();
	public List<Message> getMessages(String sendID, String receiveID){
		List<Message> ListMess = mess.getMessages(sendID, receiveID);
		List<Message> ListRe = mess.getMessages(receiveID, sendID);
		for(Message mesRe:ListRe) {
			if(!ListMess.contains(ListRe)) {
				ListMess.add(mesRe);
			}
		}
		ListMess.sort(Comparator.comparing(Message::getTimeSent));
		return ListMess;
	}
	
	public List<Message> getMessageGP(String receive){
		List<Message> ListMess = mess.getMessageGP(receive);
		ListMess.sort(Comparator.comparing(Message::getTimeSent));
		return ListMess;
	}
	
	public List<Message> getAllMessages(){
		return mess.getAllMessages();
	}
	
	public boolean postMessage(Message message) {
		return mess.postMessage(message);
	}
	
	public boolean deleteMessage(String FromID, String ToID) {
		if(mess.DeleteMessage(FromID, ToID) && mess.DeleteMessage(ToID, FromID)) return true;
		return false;
	}
	public boolean deleteMessageGP(String receive) {
		return mess.DeleteMessageGP(receive);
	}
}
