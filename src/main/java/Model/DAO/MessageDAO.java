package Model.DAO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.jsr310.LocalDateTimeCodec;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;

import Model.BEAN.Message;


public class MessageDAO {
	MongoDatabase database;
	public void connectDatabase() {
		try {
			MongoClient client = MongoClients.create();
			database = client.getDatabase("IMessage");
		} catch (Exception e) {
			System.out.println("Kết nối thất bại");
		}
	}
	
	public List<Message> getMessages(String sendID, String receiveID) {
		connectDatabase();
		try {
			MongoCollection<Document> messages = database.getCollection("Message");
			List<Document> allMessages = messages.find(Filters.and(Filters.eq("From_ID", sendID), Filters.eq("To_ID", receiveID))).into(new ArrayList<Document>());
			List<Message> listMessages = new ArrayList<>();
			for(Document d : allMessages) {
				Message m = new Message();
				m.setGroup(d.getBoolean("isGroup"));
				m.setMessage_ID(d.get("_id").toString());
				m.setFrom_ID(d.getString("From_ID"));
				m.setTo_ID(d.getString("To_ID"));
				SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat Time_Format = new SimpleDateFormat ("HH:mm:ss.S");
				String dat = Date_Format.format(d.getDate("TimeSent"));
				String tim = Time_Format.format(d.getDate("TimeSent"));
				LocalDateTime dt = LocalDateTime.parse(dat+"T"+tim);
				m.setTimeSent(dt.minusHours(7));//trừ đi 7 hours do khi lấy dữ liệu ra bị tăng 7hours
				m.setContent(d.getString("Content"));
				listMessages.add(m);
			}
			return listMessages;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Message> getMessageGP(String receive){
//		da sua
		connectDatabase();
		try {
			MongoCollection<Document> messages = database.getCollection("Message");
			List<Document> allMessages = messages.find(Filters.eq("To_ID", receive)).into(new ArrayList<Document>());
			List<Message> listMessages = new ArrayList<>();
			for(Document d : allMessages) {
				Message m = new Message();
				m.setGroup(d.getBoolean("isGroup"));
				m.setMessage_ID(d.get("_id").toString());
				m.setFrom_ID(d.getString("From_ID"));
				m.setTo_ID(d.getString("To_ID"));
				SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat Time_Format = new SimpleDateFormat ("HH:mm:ss.S");
				String dat = Date_Format.format(d.getDate("TimeSent"));
				String tim = Time_Format.format(d.getDate("TimeSent"));
				LocalDateTime dt = LocalDateTime.parse(dat+"T"+tim);
				m.setTimeSent(dt.minusHours(7));//trừ đi 7 hours do khi lấy dữ liệu ra bị tăng 7hours
				m.setContent(d.getString("Content"));
				listMessages.add(m);
			}
			return listMessages;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Message> getAllMessages() {
		connectDatabase();
		try {
			MongoCollection<Document> messages = database.getCollection("Message");
			List<Document> allMessages = messages.find().into(new ArrayList<Document>());
			List<Message> listMessages = new ArrayList<>();
			for(Document d : allMessages) {
				Message m = new Message();
				m.setGroup(d.getBoolean("isGroup"));
				m.setMessage_ID(d.get("_id").toString());
				m.setFrom_ID(d.getString("From_ID"));
				m.setTo_ID(d.getString("To_ID"));
				SimpleDateFormat Date_Format = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat Time_Format = new SimpleDateFormat ("HH:mm:ss.S");
				String dat = Date_Format.format(d.getDate("TimeSent"));
				String tim = Time_Format.format(d.getDate("TimeSent"));
				LocalDateTime dt = LocalDateTime.parse(dat+"T"+tim);
				m.setTimeSent(dt.minusHours(7));//trừ đi 7 hours do khi lấy dữ liệu ra bị tăng 7hours
				m.setContent(d.getString("Content"));
				listMessages.add(m);
			}
			return listMessages;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean postMessage(Message message) {
		connectDatabase();
		try {
			MongoCollection<Document> messages = database.getCollection("Message");
			messages.insertOne(message.convertToDB());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean DeleteMessage(String FromID, String ToID) {
		connectDatabase();
		try {
			MongoCollection<Document> messages = database.getCollection("Message");
			messages.deleteMany(Filters.and(Filters.eq("From_ID", FromID), Filters.eq("To_ID", ToID)));
			return true;
		} catch (Exception e) {}
		
		return false;
	}
	
	public boolean DeleteMessageGP(String GroupID) {
		connectDatabase();
		try {
			MongoCollection<Document> messages = database.getCollection("Message");
			messages.deleteMany(Filters.eq("To_ID", GroupID));
			return true;
		} catch (Exception e) {}
		
		return false;
	}
}
