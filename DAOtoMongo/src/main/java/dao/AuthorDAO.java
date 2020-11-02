package dao;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import model.Author;
import model.Book;

public class AuthorDAO {

	private MongoDatabase database;

	public void setDataSource(MongoDatabase database) {
		this.database = database;
	}

	// get author, that is a JAVA OBJECT and then
	// transforms it into a DOCUMENT MONGO and finally
	// uploads it to MONGO collection at cloud
	public void saveAuthor(Author author) {

		// get collection with THIS, BE CAREFUL it is a MONGO COOLLECTION
		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		// JAVA list within mongo DOCUMENT
		List<Document> booksMongo = new ArrayList<Document>();

		// ITERATE all books from author
		for (Book book : author.getBooks()) {

			// create a JAVA object to be a MONGO DOCUMENT
			Document bookMongo = new Document("_id", new ObjectId());
			// fill the MONGO DOCUMENT with files from JAVA thanks to getters of book class
			bookMongo.append("title", book.getTitle()).append("year", book.getYear()).append("pages", book.getPages());
			booksMongo.add(bookMongo);
		}

		// create a JAVA object to be a MONGO DOCUMENT
		Document authorMongo = new Document("_id", new ObjectId());
		// fill the MONGO DOCUMENT with files from JAVA thanks to getters of author
		// class
		// BE CAREFUL: there is an array, books-booksMongo
		authorMongo.append("name", author.getName()).append("surname", author.getSurname())
				.append("age", author.getAge()).append("books", booksMongo);

		// finally, authorsCollecton calls insertOne and upload authorMongo, it is just
		// ONE MONGO DOCUMENT
		authorsCollection.insertOne(authorMongo);
		

	}
	

	// print all getting all documents from authors collection
	public void printAll() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");

		// iterate all authorsCollection, for each document, that is, authorDoc, prints
		// as a toString
		for (Document authorDoc : authorsCollection.find()) {
			System.out.println(authorDoc.toString());
		}
	}

	//getter collection authors from database
	public MongoCollection<Document> getAuthorsCollection() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");

		return authorsCollection;

	}

}
