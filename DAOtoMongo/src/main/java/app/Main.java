package app;

import view.Menu;

public class Main {

	public static void main(String[] args) {

		System.out.println("Loading ....");
		
		//create Menu object and invoke loopIO
		Menu menu = new Menu();
		menu.loopIO();
		
		System.out.println("Connection finished.");
		
	}

}
