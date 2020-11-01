package view;

public enum MenuOptions {

	ADD(1, "Add an author, type ADD"),
	SHOW(2, "Show all authors, type SHOW"),
	UPDATE(3, "Update an author, type UPDATE"),
	DELETE(4, "Delete an author, type DELETE"),
	FIND(5, "Find one author, type FIND"),
	QUIT(6, "Quit, type QUIT"),
	ADDBOOK (7,"Add book to an author, type ADDBOOK"),
	UNKNOWN(8, "unknown");

	private int optionInteger;
	private String optionText;

	MenuOptions() {
	}

	MenuOptions(int optionInteger, String OptionText) {
		this.optionInteger = optionInteger;
		this.optionText = OptionText;
	}

	public int getOptionInteger() {
		return optionInteger;
	}

	public String getOptionText() {
		return optionText;
	}

	public static MenuOptions commandisValid(String command) {
		MenuOptions commandEnum;

		for (MenuOptions optionValue : MenuOptions.values()) {
			if (optionValue.name().equals(command.toUpperCase())) {
				commandEnum = MenuOptions.valueOf(command.toUpperCase());
				return commandEnum;
			}
		}
		return MenuOptions.UNKNOWN;

	}

}
