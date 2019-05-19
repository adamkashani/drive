package core.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.IUserDao;

@Service
public class UserService {

	private IUserDao userDao;

	@Autowired
	public UserService(IUserDao userDao) {
		super();
		this.userDao = userDao;
	}

}
