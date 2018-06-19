package services;

import java.util.List;

import model.Book;

public interface BookService {
	void addBook(Book book);
	void updateBook(int id, Book book);
	void deleteBook(int id);
	List<Book> listBook();
	Book getBookId(int id);
}
