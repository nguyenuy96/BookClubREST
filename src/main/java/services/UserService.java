package services;

import java.util.List;

import model.User;
public interface UserService {
	User save(User user);
	User get(int id);
	List<User> list();
	void update(int id, User user);
	void delete(int id);
	User getName(String username);
	void updateInf(int id, User user);
}
