package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Author;
import model.Book;
import repository.AuthorRepository;
import repository.BookRepository;
import java.util.List;
import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		
		// Create our entity manager..
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("library");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		

		// Create our repositories ...
		AuthorRepository authorRepository = new AuthorRepository(entityManager);
		BookRepository bookRepository = new BookRepository(entityManager);
		
		// Create four authors
		Author virginia = new Author("Virginia Wolf", "UK");
		Author leon = new Author("Leon Tolstoi", "Russia");
		Author victor = new Author("Victor Hugo", "French");
		Author dante = new Author("Dante Alighieri", "Italy");

		// and after authors,
		//create three books for the first author, Virginia
		virginia.addBook(new Book("To the lighthouse"));
		virginia.addBook(new Book("Orlando"));
		virginia.addBook(new Book("A room of my own"));
		
		// using optional is a very good idea, from JAVA 8
		Optional<Author> savedAuthor1 = authorRepository.save(virginia);
		Optional<Author> savedAuthor2 = authorRepository.save(leon);
		Optional<Author> savedAuthor3 = authorRepository.save(victor);
		Optional<Author> savedAuthor4 = authorRepository.save(dante);

		System.out.println("Saved author: " + savedAuthor1.get());
		
		// Find all authors
		List<Author> authors = authorRepository.findAll();
		System.out.println("Authors:");
		for (Author authorToPrint : authors) {
			System.out.println(authorToPrint);
		}
		// another way to write for in java
		// authors.forEach(author -> System.out.println(author));
		//besides we use the "::" operator to avoid
		// the creation and use of a tag object as a index at FOR
		authors.forEach(System.out::println);

		// Find author by name, that is Leon Tolstoi
		// using Optional JAVA 8 is nice cause be may us ifPresent
		Optional<Author> authorByName = authorRepository.findByName("Leon Tolstoi");
		System.out.println("Searching for an author by name: ");
		authorByName.ifPresent(System.out::println);

		// Find author by name and delete it, that is Leon Tolstoi
		authorByName = authorRepository.deleteByName("Leon Tolstoi");
		authorByName.ifPresent(System.out::println);

		// Search for a book by ID 1, that is "To the lighthouse"
		Optional<Book> foundBook = bookRepository.findById(1);
		foundBook.ifPresent(System.out::println);

		// Search for a book with an invalid ID
		Optional<Book> notFoundBook = bookRepository.findById(5);
		notFoundBook.ifPresent(System.out::println);

		// List all books
		List<Book> books = bookRepository.findAll();
		System.out.println("Books in database:");
		books.forEach(System.out::println);

		// Find a book by name, so "Orlando"
		Optional<Book> queryBook2 = bookRepository.findByTitle("Orlando");
		System.out.println("Query for book 2:");
		queryBook2.ifPresent(System.out::println);

		// Find a book by name using a named query, that is "A room of my own"
		Optional<Book> queryBook3 = bookRepository.findByTitleNamedQuery("A room of my own");
		System.out.println("Query for book 3:");
		queryBook3.ifPresent(System.out::println);

		// Add a book to author Virginia Wolf, the #4 so "Mrs Daloway"
		//Virginia Wolf is id #1
		// and JAVA 8 is ready to help us with ifPresent and lambda
		Optional<Author> bookToAuthor = authorRepository.findById(1);
		bookToAuthor.ifPresent(bookToAdd -> {
			bookToAdd.addBook(new Book("Mrs Daloway"));
			System.out.println("Saved author: " + authorRepository.save(bookToAdd));
		});

		// Close the entity manager and associated factory
		entityManager.close();
		entityManagerFactory.close();
	}
}
