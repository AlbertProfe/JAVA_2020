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

	public void showAll() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");

		for (Document cur : authorsCollection.find()) {
			System.out.println(cur.toJson());
		}
	}

	public void findOne(String nameToFind) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document authorFound = authorsCollection.find(eq("name", nameToFind)).first();

		if (authorFound != null)
			System.out.println(authorFound.toJson());
		else
			System.out.println("file not found");
	}

	public void delete(String authorName) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();
		DeleteResult deletedAuthor = authorsCollection.deleteOne(eq("name", authorName));

		System.out.println(deletedAuthor);

	}

	public void update(String authorName, String authorSurname, int authorAge) {

		MongoCollection<Document> authorsCollection = this.getAuthorsCollection();

		Document updateResult = authorsCollection.findOneAndUpdate(eq("name", authorName),
				new Document("$set", new Document("surname", authorSurname)));
		//to-do update as well age ...
		System.out.println(updateResult);

	}
	
	public MongoCollection<Document> getAuthorsCollection() {

		MongoCollection<Document> authorsCollection = database.getCollection("authors");
		return authorsCollection;

	}


}
