package utils;

import java.util.Scanner;

public class UtilsIO {
	
	//static menu methods for IO
	public static String ask(Scanner reader, String option) { 
		//Prompt
		System.out.println(option);
		
		//get the user answer
		option = reader.nextLine();
		
		return option;
	}
	
	public static int askObservation(Scanner reader) { 
		//Prompt
		System.out.println("How many observations do you want to add?");
		
		while(true) {
			try {
			    return Integer.parseInt(reader.nextLine());
			  } catch (NumberFormatException e) {  
				  System.out.println("Sorry, you did not entered a number! Watch it!");;
			  }
		}
	}
	
	public static String askForName(Scanner reader) {
		System.out.println("Name: ");
		return reader.nextLine();
	}
	
	public static String askForNameLatin(Scanner reader){
		System.out.println("Latin name: ");
		return reader.nextLine();
	}
	
	public static void noBirdMessage (String name) { 
		//Print the complete DB
		System.out.println(name + " does not exist in our DB!");
		
	}
	
}

