package controller;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import dao.AuthorDAO;
import model.Author;
import model.Book;
import utils.UtilsIO;
import view.MenuOptions;

public class Controller {

	public static AuthorDAO authorDAO;

	//just init and connect to mongoDB with creation of authorDAO object
	//needed to carry database
	public static MongoDatabase init() {

		MongoClientURI connectionString = new MongoClientURI(
				"mongodb+srv://cifo:1234@clustertest.0h8fd.mongodb.net/test");
		MongoClient mongoClient = new MongoClient(connectionString);

		MongoDatabase database = mongoClient.getDatabase("city");

		authorDAO = Controller.setSource(database);

		return database;

	}

	//authorDAO object set to carry database
	//set database source to authorDAO
	public static AuthorDAO setSource(MongoDatabase database) {

		AuthorDAO authorDAO = new AuthorDAO();
		authorDAO.setDataSource(database);
		
		return authorDAO;

	}

	//add new author to mongoDB
	public static void addAuthor(Scanner reader) {

		String authorName = UtilsIO.askForName(reader);
		String authorSurname = UtilsIO.askForSurname(reader);
		int authorAge = UtilsIO.askAge(reader);

		List<Book> books = new ArrayList<Book>();
		
		//create new author JAVA object with string and integer parameters and
		//list books from ADDBOOK -see addBook method-
		Author newAuthor = new Author(authorName, authorSurname, authorAge, addBook(reader, books));

		//author JAVA object send to DAO in order to upload to mongoDB as a DOCUMENT, mongo OBJECT
		authorDAO.saveAuthor(newAuthor);
	}

	//add new books with a list to a existing author at mongoDB
	public static void addBookToAuthor(Scanner reader) {

		String authorName = UtilsIO.askForName(reader);
		//check if author is in the mongoDB
		Document authorFound = authorDAO.findOneDocument(authorName);

		//if author is in DB, then, create a list with
		//the books created from addBook -see ADDBOOK method-
		if (authorFound != null) {
			List<Book> books = new ArrayList<Book>();
			authorDAO.update(authorName, addBook(reader, books));
		} else
			System.out.println("file not found");

	}

	//method used from ADDAUTHOR and ADDBOOKTOAUTHOR to get a list of new books
	//loop while the user needs to create new books in a list
	//and eventually return that list
	public static List<Book> addBook(Scanner reader, List<Book> books) {

		while (true) {

			String command = UtilsIO.ask(reader, "Add Book (type QUIT to exit, otherwise go ahead)?");
			MenuOptions commandEnum = MenuOptions.commandisValid(command);

			if (commandEnum.equals(MenuOptions.QUIT)) {
				break;
			}

			String bookTitle = UtilsIO.askForTitle(reader);
			int bookYear = UtilsIO.askForYear(reader);
			int bookPages = UtilsIO.askForPages(reader);

			books.add(new Book(bookTitle, bookYear, bookPages));
		}
		return books;
	}

	//delete a book, prior checking a existing author
	public static void delete(Scanner reader) {

		String authorName = UtilsIO.askForName(reader);
		DeleteResult deletedAuthor = authorDAO.delete(authorName);

		if (deletedAuthor != null)
			System.out.println("Delete succesful: " + deletedAuthor);
		else
			System.out.println("file not found");

	}

	//find an author and printing the data
	public static void findOne(Scanner reader) {

		String authorName = UtilsIO.askForName(reader);
		Document authorFound = authorDAO.findOneDocument(authorName);

		if (authorFound != null)
			System.out.println(authorFound.toJson());
		else
			System.out.println("file not found");
	}

	//update an author considering all the possible cases to update
	//if user leave a string out we do not update
	//if user puts negative number to age we do not update
	public static void update(Scanner reader) {

		String authorNameToFind = UtilsIO.askForName(reader);
		String authorName = UtilsIO.askForName(reader);
		String authorSurname = UtilsIO.askForSurname(reader);
		int authorAge = UtilsIO.askAge(reader);

		// name & surname & age
		if (!authorName.isEmpty() && !authorSurname.isEmpty() && authorAge > 0) {
			authorDAO.update(authorNameToFind, authorName, authorSurname, authorAge);
			// name & surname
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

	//getting all the authors and printing all them
	public static void showAll() {

		MongoCollection<Document> authorsCollection = authorDAO.showAll();

		for (Document authorIterator : authorsCollection.find()) {
			System.out.println(authorIterator.toJson());
		}
	}

	//close the database
	public static void close(MongoDatabase database) {
		// to-do close all connections
	}

}
