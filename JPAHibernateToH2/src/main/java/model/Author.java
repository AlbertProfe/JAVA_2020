package model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
//this is JPQL very similar to SQL
//it operates on entities, their fields, and their relationships
//NOT on database column names
//SELECT returnedEntity FROM entityName object WHERE whereClause
//FROM entityName object (object from class entityNAME)
//@namedQueries -> name + query
@NamedQueries({ @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name = :name") })
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String country;
	// persistence will propagate (cascade) all EntityManager operations
	// PERSIST, REMOVE, REFRESH, MERGE, DETACH to the relating entities.
	@ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();
	
	

	public Author() {
	}

	public Author(String name, String country) {
		this.name = name;
		this.country = country;
	}

	public Author(Integer id, String name) {
		this.id = id;
		this.name = name;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	//GETTER from field books -STANDARD-
	public Set<Book> getBooks() {
		return books;
	}
	
	// SETTER from field books -STANDARD-
	public void setBooks(Set<Book> books) {
		this.books = books;
	}
	
	// SETTER from field books
	// BE CAREFUL: this setter is customized
	// array books add a new book come by as parameter
	// there is a double SETTER in BOTH classes: author and book
	// one in AUTHOR, that is, to THIS author add one book
	// second in BOOK to set an author to the book, the author in this case is THIS
	public void addBook(Book book) {
        books.add(book);
        book.getAuthors().add(this);
    }

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", country=" + country +  "]";
	}

	
}