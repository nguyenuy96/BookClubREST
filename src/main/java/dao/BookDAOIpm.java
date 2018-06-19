package dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Book;

@Repository
public class BookDAOIpm implements BookDAO {

	/*******************************************************/

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	/*******************************************************/
	@Transactional
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		session.save(book);
		session.getTransaction().commit();
		session.close();
	}

	/*******************************************************/

	@Override
	public List<Book> listBook() {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> root = criteriaQuery.from(Book.class);
		criteriaQuery.select(root);
		Query<Book> query = session.createQuery(criteriaQuery);
		List<Book> book = query.getResultList();
		return book;
	}

	/*******************************************************/

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		Book book = session.byId(Book.class).load(id);
		session.delete(book);
		session.getTransaction().commit();
		session.close();
	}

	/*******************************************************/

	@Override
	public void updateBook(int id, Book book) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		Book bookUpdate = session.byId(Book.class).load(id);
		if (book.getBookName() != null) {
			bookUpdate.setBookName(book.getBookName());
		}
		if (book.getBookAuthor() != null) {
			bookUpdate.setBookAuthor(book.getBookAuthor());
		}
		if (book.getBookDescription() != null) {
			bookUpdate.setBookDescription(book.getBookDescription());
		}
		session.getTransaction().commit();
	}

	/*******************************************************/

	@Override
	public Book getBookId(int id) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		Book book = session.get(Book.class, id);
		return book;
	}

	/*******************************************************/

}
