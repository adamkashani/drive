package core.logic;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.IUserDao;
import core.entity.UserEntity;
import core.javaBean.User;

@Service
public class UserService {

	private IUserDao userDao;

	@Autowired
	public UserService(IUserDao userDao) {
		super();
		this.userDao = userDao;
	}

	public long create(User user) {
		validateUser(user);
		UserEntity userEntity = new UserEntity(user);
		userDao.saveAndFlush(userEntity);

		return userEntity.getId();
	}

	public void remove(long id) {

		if (userDao.existsById(id)) {
			userDao.deleteById(id);
		} else {
			// TODO throw exception id not exists
		}

	}

	public UserEntity login(String name, String password) {
		UserEntity userEntity = userDao.findByNameAndPassword(name, password);
		if (userEntity == null) {
			// TODO throw exception name or password not incorrect
		}
		return userEntity;
	}

	private void validateUser(User user) {

		validateName(user.getName());
		validatePass(user.getPassword());
		validateEmile(user.getEmail());

	}

	private void validateEmile(String email) {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);

		if (!regex.matches(regex)) {
			// TODO throw exception
		}
		if (userDao.existsByEmail(email)) {
			// TODO throw exception mail all ready use
		}

	}

	private void validatePass(String password) {
		if (password == null) {
			// TODO throw exception
		}
		if (password.length() > 4) {
			// TODO throw exception
		}
	}

	private void validateName(String name) {
		if (name == null) {
			// TODO throw exception
		}
		if (name.length() > 4) {
			// TODO throw exception
		}

	}

}
