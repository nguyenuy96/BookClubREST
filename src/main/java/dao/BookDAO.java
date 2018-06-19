package dao;

import java.util.List;

import model.Book;

public interface BookDAO {
	void addBook(Book book);
	List<Book> listBook();
	void deleteBook(int id);
	void updateBook(int id, Book book);
	Book getBookId(int id);
}
