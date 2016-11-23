package truong.capgemini.usermanagement.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import truong.capgemini.usermanagement.dao.UserDao;
import truong.capgemini.usermanagement.model.User;

@RestController
public class UserController {
	
	private static final Log log = LogFactory.getLog(UserController.class);
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/demo")
	public @ResponseBody String hello() {
		return "Hello";
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List getAllUsers(){
		return userDao.list();
	}
	
	@RequestMapping(value = "user/{name}", method= RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User findUserByName(@PathVariable("name") String name){
		User user = userDao.getName(name);
		return user;
	}
	
	@RequestMapping(value = "user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody User createNewUser(User user){
		User u = userDao.create(user);
		log.info(HttpStatus.OK);
		return u;
	}
	
	@RequestMapping(value = "user/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String deleteUser(@PathVariable("id") Long id){
		if (userDao.delete(id) == null) {
			return "Not exist this user in the list";
		}
		return "User " + id + " has been removed";
	}
	
	@RequestMapping(value = "user/update/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String updateUser(@PathVariable("id") Long id, User user){
		User u = userDao.update(id, user);
		if (u == null) {
			return "Not exist this user in the list";
		}
		return "User " + u.getName() + " has been updated";
	}
	
}
