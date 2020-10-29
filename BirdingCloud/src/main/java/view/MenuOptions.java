package view;

import java.util.ArrayList;
import java.util.Collections;


public class MenuOptions {

	private ArrayList<String> options;

	public MenuOptions() {
		options = new ArrayList<String>();
		Collections.addAll(options, "Quit", "Add", "Observation", "Show", "Statistics");

	}

	public String getOption(String option) {
		return options.get(options.indexOf(option));
	}

	public void showMenu() {
		System.out.println("\r" + "Main Menu:");
		System.out.println("---------------------------------");
		System.out.println("Type one option, please:");
		System.out.println("\t" + options.get(0));
		System.out.println("\t" + options.get(1));
		System.out.println("\t" + options.get(2));
		System.out.println("\t" + options.get(3));
		System.out.println("\t" + options.get(4));
		
		
	}
}
