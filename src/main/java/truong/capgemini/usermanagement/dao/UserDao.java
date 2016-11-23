package truong.capgemini.usermanagement.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import truong.capgemini.usermanagement.model.User;

@Component
public class UserDao {
	
	private static List<User> users;
	{
		users = new ArrayList<User>();
		User user = new User(1, "Daniel", "d@gmail.com", 21, "07.77.77.77.77");
		User user1 = new User(2, "Vivian", "v@gmail.com", 25, "07.77.77.77.77");
		User user2 = new User(3, "Alain", "a@gmail.com", 29, "07.77.77.77.77");
		User user3 = new User(4, "Philippe", "p@gmail.com", 31, "07.77.77.77.77");
		User user4 = new User(5, "Jean-Loup", "jl@gmail.com", 19, "07.77.77.77.77");
		User user5 = new User(6, "Thomas", "t@gmail.com", 24, "07.77.77.77.77");
		
		users.add(user);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
	}
	
	/**
	 * List all users in dummy persistance
	 * @return
	 */
	public List list(){
		return users;
	}
	
	/**
	 * Get user from id
	 * @param id
	 * @return
	 */
	public User get(Long id){
		for(User u: users){
			if(u.getId() == id){
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Search by user's name
	 * @param name
	 * @return
	 */
	public User getName(String name){
		for(User u: users){
			if (u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	/**
	 * Create new user
	 * @param user
	 * @return
	 */
	public User create(User user){
		Long id = (long) (users.size() + 1);
		user.setId(id);
		users.add(user);
		return user;
	}
	
	public Long delete(Long id){
		for(User u: users){
			if (u.getId() == id) {
				users.remove(u);
				return id;
			}
		}
		return null;
	}
	
	public User update(Long id, User user){
		for(User u: users){
			if (u.getId() == id) {
				user.setId(id);
				users.remove(u);
				users.add(user);
				return user;
			}
		}
		return null;
	}
}
