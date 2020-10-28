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

	public void printAll() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");

		for (Document cur : authorsCollection.find()) {
			System.out.println(cur.toJson());
		}
	}

	public MongoCollection<Document> getAuthorsCollection() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");

		return authorsCollection;

	}

}
