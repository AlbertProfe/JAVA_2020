package controller;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

import dao.AuthorDAO;
import model.Author;
import model.Book;
import utils.UtilsIO;
import view.MenuOptions;

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

		String authorName = UtilsIO.askForName(reader);
		String authorSurname = UtilsIO.askForSurname(reader);
		int authorAge = UtilsIO.askAge(reader);
		
		List<Book> books = new ArrayList<Book>();
		
		Author newAuthor = new Author(authorName, authorSurname, authorAge, addBook(reader, books));

		authorDAO.saveAuthor(newAuthor);
	}
	
	public static List<Book> addBook (Scanner reader, List<Book> books) {
			
		while (true) {

			String command = UtilsIO.ask(reader, "Add Book (type QUIT to exit, otherwise go ahead)?");
			MenuOptions commandEnum = MenuOptions.commandisValid(command);

			if (commandEnum.equals(MenuOptions.QUIT)) {
				break;
			}

			String bookTitle = UtilsIO.askForTitle(reader);
			int bookYear = UtilsIO.askForYear(reader);
			int bookPages = UtilsIO.askForPages(reader);
			
			books.add(new Book (bookTitle, bookYear, bookPages));
		}
		return books;
	}

	public static void addBookAuthor(Scanner reader) {
		
		String authorName = UtilsIO.askForName(reader);
		Document authorFound = authorDAO.findOneDocument(authorName);

		if (authorFound != null) {
			List<Book> books = new ArrayList<Book>();
			authorDAO.update(authorName, addBook(reader, books));
		}	
		else
			System.out.println("file not found");
		
	}
	
	public static void showAll() {

		authorDAO.showAll();
	}

	public static void delete(Scanner reader) {

		String authorName = UtilsIO.askForName(reader);
		authorDAO.delete(authorName);
	}

	public static void findone(Scanner reader) {

		String authorName = UtilsIO.askForName(reader);
		authorDAO.findOne(authorName);

	}

	public static void update(Scanner reader) {

		String authorNameToFind = UtilsIO.askForName(reader);
		String authorName = UtilsIO.askForName(reader);
		String authorSurname = UtilsIO.askForSurname(reader);
		int authorAge = UtilsIO.askAge(reader);

		// name & surame & age
		if (!authorName.isEmpty() && !authorSurname.isEmpty() && authorAge > 0) {
			authorDAO.update(authorNameToFind, authorName, authorSurname, authorAge);
			// name & surame
		} else if (!authorName.isEmpty() && !authorSurname.isEmpty() && authorAge < 0) {
			authorDAO.update(authorNameToFind, authorName, authorSurname);
			// name
		} else if (!authorName.isEmpty() && authorSurname.isEmpty() && authorAge < 0) {
			authorDAO.update(authorNameToFind, authorName);
			// surname & age
		} else if (authorName.isEmpty() && !authorSurname.isEmpty() && authorAge > 0) {
			authorDAO.update(authorNameToFind, authorSurname, authorAge);
			// name & age
		} else if (!authorName.isEmpty() && authorSurname.isEmpty() && authorAge > 0) {
			authorDAO.update(authorNameToFind, authorName);
			authorDAO.update(authorNameToFind, authorAge);
			// age
		} else if (authorName.isEmpty() && authorSurname.isEmpty() && authorAge > 0) {
			authorDAO.update(authorNameToFind, authorAge);
		}

	}

	public static void close(MongoDatabase database) {
		// to-do close all connections
	}


}
