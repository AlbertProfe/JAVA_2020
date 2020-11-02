package utils;

import java.util.Scanner;

import org.bson.Document;

import dao.AuthorDAO;

public class UtilsIO {

	// static menu methods for IO
	public static String ask(Scanner reader, String option) {
		// Prompt
		System.out.println(option);

		// get the user answer
		option = reader.nextLine();

		return option;
	}

	public static int askAge(Scanner reader) {
		// Prompt
		System.out.println("Age?");

		while (true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sorry, you did not entered a number! Watch it! \nAge?");

			}
		}
	}

	public static String askForName(Scanner reader) {
		System.out.println("Name: ");
		return reader.nextLine();
	}

	public static String askForSurname(Scanner reader) {
		System.out.println("Surname: ");
		return reader.nextLine();
	}

	public static void printUpdateResult(Document updateResult, AuthorDAO authorDAO, String authorNameToFind) {

		if (updateResult != null) {
			System.out.println("Update succesful: " + updateResult);
			
			System.out.print("Updated to: ");
			Document authorFound = authorDAO.findOneDocument(authorNameToFind);

			if (authorFound != null)
				System.out.println(authorFound.toJson());
			else
				System.out.println("file not found: author found, saved and after that lost");

		} else
			System.out.println("file not found");
	}

	public static String askForTitle(Scanner reader) {
		System.out.println("Title: ");
		return reader.nextLine();
	}

	public static int askForYear(Scanner reader) {
		// Prompt
		System.out.println("Year?");

		while (true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sorry, you did not entered a number! Watch it! \nYear?");

			}
		}
	}

	public static int askForPages(Scanner reader) {
		// Prompt
		System.out.println("Pages?");

		while (true) {
			try {
				return Integer.parseInt(reader.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Sorry, you did not entered a number! Watch it! \nPages?");

			}
		}
	}

}
