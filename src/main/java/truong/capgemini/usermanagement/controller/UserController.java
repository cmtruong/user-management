package truong.capgemini.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import truong.capgemini.usermanagement.dao.UserDao;

@RestController
@Transactional
public class UserController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/demo")
	public @ResponseBody String hello() {
		return "Hello";
	}

}
