package app;

import com.mongodb.client.MongoDatabase;

import controller.Controller;
import dao.AuthorDAO;

public class Main {

	public static void main(String[] args) {

		
		MongoDatabase database = Controller.init();

		AuthorDAO authorDAO = Controller.setSource(database);

		Controller.add(authorDAO);

		Controller.printAll(authorDAO);

	}

}
