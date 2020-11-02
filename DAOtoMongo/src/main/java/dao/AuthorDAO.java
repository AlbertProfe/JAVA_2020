package dao;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.MongoCollection;
import model.Author;
import model.Book;
import utils.UtilsIO;

import static com.mongodb.client.model.Filters.eq;

public class AuthorDAO {

	private MongoDatabase database;

	public void setDataSource(MongoDatabase database) {
		this.database = database;
	}

	public void saveAuthor(Author author) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		List<Document> booksMongo = new ArrayList<Document>();

		for (Book book : author.getBooks()) {

			Document bookMongo = new Document("_id", new ObjectId());
			bookMongo.append("title", book.getTitle()).append("year", book.getYear()).append("pages", book.getPages());
			booksMongo.add(bookMongo);
		}

		Document authorMongo = new Document("_id", new ObjectId());
		authorMongo.append("name", author.getName()).append("surname", author.getSurname())
				.append("age", author.getAge()).append("books", booksMongo);

		 authorsCollection.insertOne(authorMongo);
	}

	public Document findOneDocument(String nameToFind) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document authorFound = authorsCollection.find(eq("name", nameToFind)).first();

		return authorFound;
	}

	public DeleteResult delete(String authorName) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();
		DeleteResult deletedAuthor = authorsCollection.deleteOne(eq("name", authorName));
		
		return deletedAuthor;
	}

	public void update(String authorNameToFind, String authorName, String authorSurname, int authorAge) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorNameToFind), new Document("$set",
				new Document("name", authorName).append("surname", authorSurname).append("age", authorAge)));

		UtilsIO.printUpdateResult(updateResult, this, authorNameToFind);
	}

	public void update(String authorNameToFind, String authorName) {
		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorNameToFind),
				new Document("$set", new Document("name", authorName)));

		UtilsIO.printUpdateResult(updateResult, this, authorNameToFind);
	}

	public void update(String authorNameToFind, String authorName, String authorSurname) {
		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorNameToFind),
				new Document("$set", new Document("name", authorName).append("surname", authorSurname)));

		UtilsIO.printUpdateResult(updateResult, this, authorNameToFind);
	}

	public void update(String authorNameToFind, String authorSurname, int authorAge) {
		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorNameToFind),
				new Document("$set", new Document("surname", authorSurname).append("age", authorAge)));

		UtilsIO.printUpdateResult(updateResult, this, authorNameToFind);
	}

	public void update(String authorNameToFind, int authorAge) {
		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorNameToFind),
				new Document("$set", new Document("age", authorAge)));

		UtilsIO.printUpdateResult(updateResult, this, authorNameToFind);

	}

	public void update(String authorName, List<Book> booksToAdd) {
		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		for (Book book : booksToAdd) {
			
			Document bookMongo = new Document("_id", new ObjectId());
			
			bookMongo.append("title", book.getTitle())
			.append("year", book.getYear()).append("pages", book.getPages());
			
			Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorName),
					new Document("$push", new Document("books",bookMongo)));
			
			UtilsIO.printUpdateResult(updateResult, this, authorName);
		}
		
	}
	
	public MongoCollection<Document> showAll() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");
		
		return authorsCollection;
	}

	public MongoCollection<Document> getAuthorsCollection() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");
		return authorsCollection;

	}

}
