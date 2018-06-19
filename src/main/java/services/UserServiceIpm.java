package services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDAO;
import model.User;

@Service
@Transactional(propagation = Propagation.SUPPORTS ,readOnly=true)
public class UserServiceIpm implements UserService {
	@Autowired
	private UserDAO userDAO;
	
	
	@Transactional
	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		User userSave = userDAO.save(user);
		return userSave;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return userDAO.get(id);
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		return userDAO.list();
	}

	@Transactional
	@Override
	public void update(int id, User user) {
		// TODO Auto-generated method stub
		userDAO.update(id, user);
	}

	@Transactional
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDAO.delete(id);
	}

	@Override
	public User getName(String username) {
		// TODO Auto-generated method stub
		return userDAO.getName(username);
	}

	@Transactional
	@Override
	public void updateInf(int id, User user) {
		// TODO Auto-generated method stub
		userDAO.updateInf(id, user);
	}

}
