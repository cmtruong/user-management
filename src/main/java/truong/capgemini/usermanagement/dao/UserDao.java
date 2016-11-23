package truong.capgemini.usermanagement.dao;

import java.util.List;

import truong.capgemini.usermanagement.entity.UserEntity;
import truong.capgemini.usermanagement.model.User;

public interface UserDao {
	public User findUser(long id);

	public List<User> getAllUsers();

	public void saveUser(UserEntity entity);

	public UserEntity findUserEntity(long id);

	public void deleteUser(long id);
}
