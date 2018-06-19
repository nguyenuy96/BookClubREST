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
import org.springframework.web.bind.annotation.RestController;

import Components.HandleException;
import bookclub.ExceptionThrower;
import model.User;
import services.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping(path = "/add")
	public ResponseEntity<User> addUser(@RequestBody User user) throws HandleException {
		if (user.getUsername() == null || user.getPassword() == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.BAD_REQUEST, "username or password is not null");
		} else {
			boolean isExistUsername = false;
			isExistUsername = (userService.getName(user.getUsername()) == null) ? false : true;
			if (isExistUsername) {
				ExceptionThrower exceptionThrower = new ExceptionThrower();
				exceptionThrower.throwException(HttpStatus.CONFLICT, "Existed username!");
			} else {
				userService.save(user);
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@GetMapping(path = "/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) throws HandleException {
		if (userService.get(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found user by id " + "'" + id + "'");
		}
		return new ResponseEntity<>(userService.get(id), HttpStatus.FOUND);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<User>> getAllUser() throws Exception {
		List<User> userList = userService.list();
		if (userList.isEmpty()) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found any users");
		}
		return new ResponseEntity<>(userList, HttpStatus.FOUND);
	}

	@RequestMapping(path = "/update/password/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) throws HandleException {
		if (userService.get(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found user " + user.getUsername());
		} else {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			if (user.getPassword() == null) {
				exceptionThrower.throwException(HttpStatus.BAD_REQUEST, "password is not null");
			} else {
				userService.update(id, user);
			}
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(path = "/delete/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id, @RequestBody User user) throws HandleException {
		if (userService.get(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found user " + user.getUsername());
		} else {
			userService.delete(id);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/username/{username}")
	public ResponseEntity<User> getUserByName(@PathVariable("username") String username) throws HandleException {
		if (userService.getName(username) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found user " + username);
		}
		return new ResponseEntity<User>(userService.getName(username), HttpStatus.FOUND);
	}

	@RequestMapping(path = "/update/information/{id}")
	public ResponseEntity<HttpStatus> updateInf(@PathVariable("id") int id, @RequestBody User user)
			throws HandleException {
		if (userService.get(id) == null) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.NOT_FOUND, "Not found user" + user.getUsername());
		} else {
			userService.updateInf(id, user);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(path = "/login")
	public ResponseEntity<HttpStatus> checkLogin(@RequestBody User user) throws HandleException {
		User user1 = userService.getName(user.getUsername());
		if (user1 == null || !user.getPassword().equals(user1.getPassword())) {
			ExceptionThrower exceptionThrower = new ExceptionThrower();
			exceptionThrower.throwException(HttpStatus.BAD_REQUEST, "Invalid username or password");
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.FOUND);
	}
}
