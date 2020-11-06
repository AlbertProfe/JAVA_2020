package app;

import controller.Controller;
import repository.AuthorRepository;
import repository.BookRepository;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("Loading ....");
		
		AuthorRepository authorRepository = Controller.initAuthor();
		BookRepository bookRepository = Controller.initBook();
		
		Controller.save(authorRepository); 
		
		Controller.getAll(authorRepository); 
		
		Controller.findBy(authorRepository,bookRepository );

		Controller.addBookToAuthor(authorRepository,bookRepository);
		
		Controller.close();

		System.out.println("Entity manager and factory closed.");


		
	}
}
