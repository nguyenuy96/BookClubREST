package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.BookDAO;
import model.Book;

@Service
@Transactional(propagation = Propagation.SUPPORTS ,readOnly=true)
public class BookServiceIpm implements BookService{
	
	@Autowired
	private BookDAO bookDAO;

	/*******************************************************/
	
	@Transactional
	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookDAO.addBook(book);
	}
	
	/*******************************************************/
	
	@Transactional
	@Override
	public void updateBook(int id, Book book) {
		// TODO Auto-generated method stub
		bookDAO.updateBook(id, book);
	}

	/*******************************************************/
	
	@Transactional
	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		bookDAO.deleteBook(id);
	}

	/*******************************************************/
	
	@Override
	public List<Book> listBook() {
		// TODO Auto-generated method stub
		List<Book> book = bookDAO.listBook();
		return book;
	}

	/*******************************************************/
	
	@Override
	public Book getBookId(int id) {
		// TODO Auto-generated method stub
		Book book = bookDAO.getBookId(id);
		return book;
	}

}
