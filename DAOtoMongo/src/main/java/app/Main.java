package app;

import com.mongodb.client.MongoDatabase;

import controller.Controller;
import dao.AuthorDAO;

public class Main {

	public static void main(String[] args) {

		
		
		//connection to db and creation of database MONGO object
		MongoDatabase database = Controller.init();

		//create the DAO object to manage, handle, access to our Author collection and every document
		AuthorDAO authorDAO = Controller.setSource(database);

		//add authorDAO to collection
		Controller.add(authorDAO);

		//print all documents in authors collection thanks to authorDAO
		Controller.printAll(authorDAO);

	}

}
