package repository;

import javax.persistence.EntityManager;

import model.Author;

import java.util.List;
import java.util.Optional;

public class AuthorRepository {

	private EntityManager entityManager;

	public AuthorRepository(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Optional<Author> save(Author author) {
		Optional<Author> resultSave;
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(author);
			entityManager.getTransaction().commit();
			return resultSave = Optional.of(author);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSave = Optional.empty();
	}

	public Optional<Author> findById(Integer id) {

		Author author = entityManager.find(Author.class, id);

		Optional<Author> resultFind;
		if (author != null)
			resultFind = Optional.of(author);
		else
			resultFind = Optional.empty();
		return resultFind;
	}

	public List<Author> findAll() {
		return entityManager.createQuery("from Author").getResultList();
	}

	public Optional<Author> findByName(String name) {
		Author author = entityManager.createNamedQuery("Author.findByName", Author.class).setParameter("name", name)
				.getSingleResult();

		Optional<Author> resultFind;
		if (author != null)
			resultFind = Optional.of(author);
		else
			resultFind = Optional.empty();
		return resultFind;
	}

	public Optional<Author> deleteByName(String name) {
		Author author = entityManager.createNamedQuery("Author.findByName", Author.class).setParameter("name", name)
				.getSingleResult();

		System.out.println(author);
		entityManager.getTransaction().begin();
		entityManager.remove(author);
		entityManager.getTransaction().commit();

		Optional<Author> resultDelete;
		if (author != null)
			resultDelete = Optional.of(author);
		else
			resultDelete = Optional.empty();
		return resultDelete;
	}

}