package view;

import java.util.Scanner;

import controller.Controller;
import service.BirdDB;
import utils.UtilsIO;

import java.util.ArrayList;
import java.util.Collections;

public class Menu {		
	private Scanner reader;
	private BirdDB db;
	
	
	public Menu(Scanner reader,BirdDB db) {
		this.reader = reader;
		this.db = db;
	}
		
	public  void loop(Scanner reader) {	
		MenuOptions options = new MenuOptions();
		
	    while (true) {
	    	
	    	options.showMenu();
	        String command = UtilsIO.ask(this.reader, "Option?");
	        
		        try {
		        	options.getOption(command);	        	
		        } catch (Exception e) {
		        	System.out.println("Unknown command!");
		        }
	
	        if (command.equals("Quit")) {
	            break;
	        } else if (command.equals("Add")) {
	        	Controller.add(this.reader,db);
	        } else if (command.equals("Observation")) {
	        	Controller.observation(this.reader,db);
	        } else if (command.equals("Show")) {
	        	Controller.show(this.reader,db);
	        } else if (command.equals("Statistics")) {
	        	Controller.statistics(db);
	        } 
	    }
	}

}
