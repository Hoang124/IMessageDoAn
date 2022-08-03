package Model.BEAN;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;


public class Message {
	private boolean isGroup;
	private String Message_ID;
	private String From_ID;
	private String To_ID;
	private LocalDateTime TimeSent;
	private String Content;
	
	public Message() {}
	
	public Message(String from_ID, String to_ID, LocalDateTime timeSent, String content,boolean isgroup) {
		From_ID = from_ID;
		To_ID = to_ID;
		TimeSent = timeSent;
		Content = content;
		isGroup = isgroup;
	}
	
	public Document convertToDB() {
		return new Document("isGroup", isGroup).append("From_ID", From_ID).append("To_ID", To_ID).append("TimeSent", TimeSent).append("Content", Content);
	}
	
	public boolean isGroup() {
		return isGroup;
	}

	public void setGroup(boolean isGroup) {
		this.isGroup = isGroup;
	}

	public String getFrom_ID() {
		return From_ID;
	}
	public void setFrom_ID(String from_ID) {
		From_ID = from_ID;
	}
	
	public String getTo_ID() {
		return To_ID;
	}

	public void setTo_ID(String to_ID) {
		To_ID = to_ID;
	}

	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMessage_ID() {
		return Message_ID;
	}
	public void setMessage_ID(String message_ID) {
		Message_ID = message_ID;
	}
	public LocalDateTime getTimeSent() {
		return TimeSent;
	}
	public void setTimeSent(LocalDateTime timeSent) {
		TimeSent = timeSent;
	}
}
