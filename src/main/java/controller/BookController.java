package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Components.HandleException;
import bookclub.ExceptionThrower;
import model.Book;
import model.User;
import services.BookService;
import services.UserService;

@RestController
@CrossOrigin
@RequestMapping(path = "/book")
public class BookController {

	/*******************************************************/

	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userSerivce;

	/**
	 * @throws HandleException
	 *****************************************************/

	@GetMapping(path = "/all")
	public ResponseEntity<?> listBook() throws HandleException {
		List<Book> bookList = bookService.listBook();
		if (bookList.isEmpty()) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found any books");
		}
		return new ResponseEntity<>(bookList, HttpStatus.FOUND);
	}

	/**
	 * @throws HandleException
	 *****************************************************/

	@PostMapping(path = "/add/byuser")
	public ResponseEntity<Book> addBook(@RequestParam int userid, @RequestBody Book book) throws HandleException {
		if (userSerivce.get(userid) != null) {
			User user = new User(userid);
			book.setUserId(user);
			if (book.getBookName() == null) {
				ExceptionThrower exceptionThrower = new ExceptionThrower();
				exceptionThrower.throwException(HttpStatus.BAD_REQUEST, "Book name is not null");
			} else {
				bookService.addBook(book);
			}
		} else {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Invalid username");
		}
		return new ResponseEntity<Book>(book, HttpStatus.CREATED);
	}

	/**
	 * @throws HandleException
	 *****************************************************/

	@RequestMapping(path = "/update/id/{id}")
	public ResponseEntity<HttpStatus> updateBook(@PathVariable("id") int id, @RequestBody Book book)
			throws HandleException {

		if (bookService.getBookId(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Invalid book with id " + id);
		} else {
			bookService.updateBook(id, book);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

	/**
	 * @throws HandleException
	 *****************************************************/

	@RequestMapping(path = "/delete/id/{id}")
	public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") int id, Book book) throws HandleException {
		if (bookService.getBookId(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Invalid book");
		} else {
			bookService.deleteBook(id);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	/**
	 * @throws HandleException
	 *****************************************************/

	@GetMapping(path = "/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) throws HandleException {
		if (bookService.getBookId(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Invalid book with id" + id);
		}
		return new ResponseEntity<Book>(bookService.getBookId(id), HttpStatus.FOUND);
	}
}
