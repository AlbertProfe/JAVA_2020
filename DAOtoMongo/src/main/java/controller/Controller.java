package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import dao.AuthorDAO;
import model.Author;
import model.Book;
import utils.UtilsIO;

public class Controller {

	public static AuthorDAO authorDAO;

	public static MongoDatabase init() {

		MongoClientURI connectionString = new MongoClientURI(
				"mongodb+srv://home:1234@clustertest.0h8fd.mongodb.net/test");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("city");

		authorDAO = Controller.setSource(database);

		return database;

	}

	public static AuthorDAO setSource(MongoDatabase database) {

		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.setDataSource(database);
		return authorDAO;

	}

	public static void add(Scanner reader) {

		List<Book> books = new ArrayList<Book>();

		String authorName = UtilsIO.askForName(reader);
		String authorSurname = UtilsIO.askForSurname(reader);
		int authorAge = UtilsIO.askAge(reader);

		Author newAuthor = new Author(authorName, authorSurname, authorAge, books);

		authorDAO.saveAuthor(newAuthor);
	}

	public static void showAll() {

		authorDAO.showAll();
	}

	public static void delete(Scanner reader) {
		// TODO Auto-generated method stub

	}

	public static void findone(Scanner reader) {
		// TODO Auto-generated method stub

	}

	public static void update(Scanner reader) {
		// TODO Auto-generated method stub

	}

}
