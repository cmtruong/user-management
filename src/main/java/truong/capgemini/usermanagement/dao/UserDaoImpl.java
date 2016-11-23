package truong.capgemini.usermanagement.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import truong.capgemini.usermanagement.entity.UserEntity;
import truong.capgemini.usermanagement.model.User;

public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory factory;

	public User findUser(long id) {
		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("id", id));
		return (User) criteria.uniqueResult();
	}

	public List<User> getAllUsers() {
		String sql = "Select * from " + User.class.getName();
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(sql);
		return query.list();
	}

	public void saveUser(UserEntity entity) {
		// TODO Auto-generated method stub
		Long id = entity.getId();
		User user = null;
		if (id != null) {
			user = this.findUser(id);
		}

		boolean isNew = false;
		if (user == null) {
			isNew = true;
			user = new User();
			user.setId(getAllUsers().size() + 1);
		}
		user.setName(entity.getName());
		user.setEmail(entity.getEmail());
		user.setAge(entity.getAge());
		user.setDateOfBirth(entity.getDateOfBirth());
		user.setPhone(entity.getPhone());

		if (isNew) {
			Session session = this.factory.getCurrentSession();
			session.persist(user);
		}
	}

	public UserEntity findUserEntity(long id) {
		// TODO Auto-generated method stub
		User user = this.findUser(id);
		if (user != null) {
			return new UserEntity(user.getId(), user.getName(), user.getEmail(), user.getDateOfBirth(), user.getAge(),
					user.getPhone());
		}
		return null;
	}

	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		User user = this.findUser(id);
		if (user != null) {
			this.factory.getCurrentSession().delete(user);
		}
	}

}
