package view;

public class PrintMenu {
	
	public PrintMenu () {	
	}
	
	public void showMenu() {
		
		
		//print all menu
		System.out.println("\r" + "Main Menu:");
		System.out.println("---------------------------------");
		System.out.println("Type one option, please:");
		System.out.println("\t" + MenuOptions.ADD.getOptionText());
		System.out.println("\t" + MenuOptions.ADDBOOK.getOptionText());
		System.out.println("\t" + MenuOptions.SHOW.getOptionText());
		System.out.println("\t" + MenuOptions.UPDATE.getOptionText());
		System.out.println("\t" + MenuOptions.FIND.getOptionText());
		System.out.println("\t" + MenuOptions.DELETE.getOptionText());
		System.out.println("\t" + MenuOptions.QUIT.getOptionText());
		
		
	}
}
