package dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.query.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.User;

@Repository
public class UserDAOImp implements UserDAO {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public User save(User user) {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		session.save(user);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public void update(int id, User user) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		User userUpdate = session.byId(User.class).load(id);
		userUpdate.setPassword(user.getPassword());
		session.getTransaction().commit();
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).getCurrentSession();
		User expectedUser = session.load(User.class, id);
		return expectedUser;
	}

	@Override
	public List<User> list() {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criQuery = criBuilder.createQuery(User.class);
		Root<User> root = criQuery.from(User.class);
		criQuery.select(root);
		Query<User> query = session.createQuery(criQuery);
		List<User> listUser = query.getResultList();
		session.close();
		return listUser;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		User userDelete = session.byId(User.class).load(id);
		session.delete(userDelete);
		session.getTransaction().commit();
	}

	@Override
	public User getName(String username) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
		Root<User> root = criteriaQuery.from(User.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
		Query<User> query = session.createQuery(criteriaQuery);
		List<User> userFound = (List<User>) query.getResultList();
		User user = ((userFound == null) || (userFound.isEmpty())) ? null : userFound.get(0);
		return user;
	}

	@Override
	public void updateInf(int id, User user) {
		// TODO Auto-generated method stub
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		session.getTransaction().begin();
		User updateInf = session.byId(User.class).load(id);
		if (user.getAddress() == null || user.getFullname() == null) {
			if (user.getAddress() == null) {
				updateInf.setFullname(user.getFullname());
			} else {
				updateInf.setAddress(user.getAddress());
			}
		} else {
			updateInf.setAddress(user.getAddress());
			updateInf.setFullname(user.getFullname());
		}
		session.getTransaction().commit();
		session.close();
	}

}
