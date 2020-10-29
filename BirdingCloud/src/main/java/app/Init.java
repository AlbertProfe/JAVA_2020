package app;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

import service.BirdDB;
import service.ConnectionDB;
import view.Menu;

public class Init {

	public static void start() {
		
		// Connect to db
		MongoCollection<Document> db = ConnectionDB.connectionBirdingDB();
		

		// Ask user from menu by scanner
		Scanner reader = new Scanner(System.in);

		
		//Menu menu = new Menu(reader, db);
		//menu.loop(reader);

		

	}
	
	public static void finish() {
		
		System.out.println("See you in the sky!");
		
	}

}
