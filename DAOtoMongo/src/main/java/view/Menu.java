package view;

import com.mongodb.client.MongoDatabase;
import controller.Controller;
import utils.UtilsIO;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Menu {

	public Menu() {
	}

	public void loopIO() {

		
		Scanner reader = new Scanner(System.in);
		//create connection and DATABASE
		MongoDatabase database = Controller.init();

		//just sleep for 2 seconds to print on console all connection log from MONGO
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//print Main Menu and options
		PrintMenu printMenu = new PrintMenu();

		//main loop to IO data  with user
		while (true) {

			printMenu.showMenu();
			String command = UtilsIO.ask(reader, "Option?");
			
			//work with ENUM to choose options, and STRING<>ENUM 
			MenuOptions commandEnum = MenuOptions.commandisValid(command);

			//close database in case of quit
			if (commandEnum.equals(MenuOptions.QUIT)) {
				Controller.close(database);
				break;

			} else if (commandEnum.equals(MenuOptions.UNKNOWN)) {
				System.out.println("Unknown command!");

			} else if (commandEnum.equals(MenuOptions.ADD)) {
				Controller.addAuthor(reader);
				
			} else if (commandEnum.equals(MenuOptions.ADDBOOK)) {
				Controller.addBookToAuthor(reader);
				
			} else if (commandEnum.equals(MenuOptions.SHOW)) {
				Controller.showAll();

			} else if (commandEnum.equals(MenuOptions.DELETE)) {
				Controller.delete(reader);

			} else if (commandEnum.equals(MenuOptions.FIND)) {
				Controller.findOne(reader);

			} else if (commandEnum.equals(MenuOptions.UPDATE)) {
				Controller.update(reader);

			}

		}

	}

}