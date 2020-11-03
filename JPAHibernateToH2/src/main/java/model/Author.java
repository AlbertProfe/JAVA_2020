package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
@NamedQueries({ @NamedQuery(name = "Author.findByName", query = "SELECT a FROM Author a WHERE a.name = :name") })
public class Author {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String country;

	public Author() {
	}

	public Author(String name , String country) {
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

	@Override
	public String toString() {
		return "Author{" + "id=" + id + ", name='" + name + '\'' + ", country='" + country + '\'' + '}';
	}
}