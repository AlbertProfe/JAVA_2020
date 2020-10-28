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

	public static MongoDatabase init() {

		MongoClientURI connectionString = new MongoClientURI(
				"mongodb+srv://home:1234@clustertest.0h8fd.mongodb.net/test");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("city");

		return database;

	}

	public static AuthorDAO setSource(MongoDatabase database) {

		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.setDataSource(database);
		return authorDAO;

	}

	public static void add(AuthorDAO authorDAO) {

		List<Book> books = new ArrayList<Book>();

		books.add(new Book("To the lighthouse", 1927, 356));
		books.add(new Book("Orlando", 1928, 423));
		books.add(new Book("A room of my own", 1929, 122));

		Author virginia = new Author("Virgina", "Wolf", 59 , books);

		authorDAO.saveAuthor(virginia);
	}

	public static void printAll(AuthorDAO authorDAO) {

		authorDAO.printAll();
	}

}
