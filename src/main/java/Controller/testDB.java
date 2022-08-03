package Controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoIterable;

public class testDB {

	public static void main(String[] args) {
		MongoClient client = MongoClients.create();
		MongoIterable<String> list = client.listDatabaseNames();
		list.forEach(name -> System.out.println(name));
	}

}
