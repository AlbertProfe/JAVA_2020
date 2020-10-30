package controller;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import dao.AuthorDAO;
import model.Author;
import model.Book;

public class Controller {

	//create MongoClientURI and MongoClient and MongoDatabase
	//that is connecting our project to MongoDB Atlas,
	//particularly database city
	public static MongoDatabase init() {

		MongoClientURI connectionString = new MongoClientURI(
				"mongodb+srv://xxxxx:1234@clustertest.0h8fd.mongodb.net/test");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("city");

		return database;

	}

	//create authorDAO java object and set DATA SOURCE, that is
	//authorDAO is working with MONGO CITY database (MongoDatabase object) created at init()
	public static AuthorDAO setSource(MongoDatabase database) {

		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.setDataSource(database);
		
		return authorDAO;

	}
	
	//get authorDAO and create 
	//books, one author and CALL to authorDAO.saveAurthor
	public static void add(AuthorDAO authorDAO) {

		List<Book> books = new ArrayList<Book>();

		books.add(new Book("To the lighthouse", 1927, 356));
		books.add(new Book("Orlando", 1928, 423));
		books.add(new Book("A room of my own", 1929, 122));

		Author virginia = new Author("Virgina", "Wolf", 59 , books);

		authorDAO.saveAuthor(virginia);
	}

	//CALL to printAll from AuthorDAO class thanks to object authorDAO
	public static void printAll(AuthorDAO authorDAO) {

		authorDAO.printAll();
	}

}
