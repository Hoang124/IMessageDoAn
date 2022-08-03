package Model.DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import Model.BEAN.Group;
import Model.BEAN.User;

public class GroupDAO {
	MongoDatabase database;
	public void connectDatabase() {
		try {
			MongoClient client = MongoClients.create();
			database = client.getDatabase("IMessage");
		} catch (Exception e) {
			System.out.println("Kết nối thất bại");
		}
	}
	
	public List<Group> getAllGroups() {
		connectDatabase();
		try {
			MongoCollection<Document> groups = database.getCollection("Group");
			List<Document> allGroups = groups.find().into(new ArrayList<Document>());
			List<Group> listGroup = new ArrayList<>();
			for(Document d : allGroups) {
				Group gp = new Group();
				gp.setID_Group(d.get("_id").toString());
				gp.setName(d.getString("Name"));
				if(d.get("ID_Friend") != null) {
					String str = d.get("ID_Friend").toString();
					String string = str.substring(1, str.length()-1);
					List<String> strings = Arrays.asList(string.split(", "));
					gp.setID_Friend(strings);
				}
				else {
					gp.setID_Friend(null);
				}
				
				listGroup.add(gp);
			}
			return listGroup;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean createGroup(Group gp) {
		connectDatabase();
		try {
			MongoCollection<Document> groups = database.getCollection("Group");
			groups.insertOne(gp.convertToDB());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean UpdateGroup(Group gp) {
		connectDatabase();
		try {
			ObjectId id = new ObjectId(gp.getID_Group());
			MongoCollection<Document> Users = database.getCollection("Group");
			Document query = new Document();
	        query.append("_id", id);
	        Document setData = new Document();
	        setData.append("Name", gp.getName()).append("ID_Friend", gp.getID_Friend());
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
	
	public boolean deleteGroup(String ID) {
		connectDatabase();
		try {
			ObjectId id = new ObjectId(ID);
			MongoCollection<Document> Users = database.getCollection("Group");
			Users.deleteOne(Filters.eq("_id", id));
			return true;
		}catch(Exception e) {}
		return false;
	}
}
