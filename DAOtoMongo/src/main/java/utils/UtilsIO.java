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
	
	public static int askAge(Scanner reader) { 
		//Prompt
		System.out.println("Age?");
		
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
	
	public static String askForSurname(Scanner reader){
		System.out.println("Surname: ");
		return reader.nextLine();
	}
	
	
	
}

