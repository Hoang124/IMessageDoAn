package Model.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import Model.BEAN.Message;
import Model.BEAN.User;

public class LoginDAO {
	MongoDatabase database;
	public void connectDatabase() {
		try {
			MongoClient client = MongoClients.create();
			database = client.getDatabase("IMessage");
		} catch (Exception e) {
			System.out.println("Kết nối thất bại");
		}
	}
	public User checkLogin(String username, String password) {
		connectDatabase();
		try {
			MongoCollection<Document> Users = database.getCollection("User");
			Document userdoc = Users.find(Filters.and(Filters.eq("Username", username), Filters.eq("Password", password))).first();
			if(userdoc != null) {
				User user = new User();
				user.setUser_ID(userdoc.get("_id").toString());
				user.setFullname(userdoc.getString("Fullname"));
				user.setGender(userdoc.getBoolean("Gender"));
				user.setAge(userdoc.getInteger("Age"));
				user.setUsername(userdoc.getString("Username"));
				user.setPassword(userdoc.getString("Password"));
				if(userdoc.get("Group_mess") != null) {
					String str = userdoc.get("Group_mess").toString();
					String string = str.substring(1, str.length()-1);
					List<String> strings = Arrays.asList(string.split(", "));
					user.setGroup_mess(strings);
				}
				else {
					user.setGroup_mess(null);
				}
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<User> getAllUser(){
		List<User> listUser = new ArrayList<User>();
		connectDatabase();
		try {
			MongoCollection<Document> Users = database.getCollection("User");
			List<Document> u = Users.find().into(new ArrayList<Document>());
			for(Document userdoc : u) {
				User user = new User();
				user.setUser_ID(userdoc.get("_id").toString());
				user.setFullname(userdoc.getString("Fullname"));
				user.setGender(userdoc.getBoolean("Gender"));
				user.setAge(userdoc.getInteger("Age"));
				user.setUsername(userdoc.getString("Username"));
				user.setPassword(userdoc.getString("Password"));
				if(userdoc.get("Group_mess") != null) {
					String str = userdoc.get("Group_mess").toString();
					String string = str.substring(1, str.length()-1);
					List<String> strings = Arrays.asList(string.split(", "));
					user.setGroup_mess(strings);
				}
				else {
					user.setGroup_mess(null);
				}
				listUser.add(user);
			}
			return listUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertUser(User u) {
		connectDatabase();
		try {
			MongoCollection<Document> usersCollection = database.getCollection("User");
			usersCollection.insertOne(u.convertDB());
			return true;
		} catch(Exception e) {
			
		}
		return false;
	}
	
	public boolean UpdateUser(User user) {
		connectDatabase();
		try {
			ObjectId id = new ObjectId(user.getUser_ID());
			MongoCollection<Document> Users = database.getCollection("User");
			Document query = new Document();
	        query.append("_id", id);
	        Document setData = new Document();
	        setData.append("Fullname", user.getFullname()).append("Gender", user.isGender()).append("Age", user.getAge()).
	        append("Username", user.getUsername()).append("Password", user.getPassword()).append("Group_mess", user.getGroup_mess());
	        Document update = new Document();
	        update.append("$set", setData);
	        //To update single Document  
			Users.updateOne(query, update);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean DeleteUser(String ID) {
		connectDatabase();
		try {
			ObjectId id = new ObjectId(ID);
			MongoCollection<Document> Users = database.getCollection("User");
			Users.deleteOne(Filters.eq("_id", id));
			return true;
		}catch(Exception e) {}
		return false;
	}
}
