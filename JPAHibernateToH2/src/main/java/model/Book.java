package model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
//this is JPQL very similar to SQL
//it operates on entities, their fields, and their relationships
//NOT on database column names
//SELECT returnedEntity FROM entityName object WHERE whereClause
//FROM entityName object (object from class entityNAME)
//@namedQueries -> name + query
@NamedQueries({ @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
		@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b") })
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String title;
	// persistence will propagate (cascade) all EntityManager operations..
	// PERSIST, REMOVE, REFRESH, MERGE, DETACH to the relating entities.
	// The side which doesn't have the mappedBy attribute is the owner: books is the owner and authors is inverse side
	// so, as books is the owner. it makes the tough job, that is, create the Author_Book auxiliary table
	// auxiliary table: foreign keys from books and authors
	// owner side is book so joinColumns annotation has idBook as a column name (foreign key)
	// inverse side is auhtor so inverseJoinColumn has idAuthor as a column name (foreign key)
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "Author_Book",
            joinColumns = {@JoinColumn(name = "idBook")},
            inverseJoinColumns = {@JoinColumn(name = "idAuthor")}
    )
    private Set<Author> authors = new HashSet<>();
	

	public Book() {
	}

	public Book(Integer id, String name) {
		this.id = id;
		this.title = name;
	}

	public Book(String name) {
		this.title = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}


	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title +  "]";
	}

	
}