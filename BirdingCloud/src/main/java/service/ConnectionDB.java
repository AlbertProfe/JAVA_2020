package service;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ConnectionDB {

	public static MongoCollection<Document> connectionBirdingDB() {

		MongoClientURI connectionString = new MongoClientURI(
				"mongodb+srv://cifo:1234@clustertest.0h8fd.mongodb.net/test");
		// create object mongoClient: object to connect, manage, get DBs, handle, ...
		// CRUD, clean, etc ...
		MongoClient mongoClient = new MongoClient(connectionString);

		// getter from mongo to get db, particularly CITY
		MongoDatabase database = mongoClient.getDatabase("birding");
		// collection CARS
		MongoCollection<Document> birds = database.getCollection("birds");
		
		

		return birds;

	}

}
