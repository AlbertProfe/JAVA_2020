package model;

public class Book {

	private String title;
	private int year;
	private int pages;

	public Book(String title, int year, int pages) {
		// super();
		this.title = title;
		this.year = year;
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", year=" + year + ", pages=" + pages + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

}
