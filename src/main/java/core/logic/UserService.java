package core.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.IUserDao;
import core.entity.UserEntity;
import core.javaBean.User;
import core.javaBean.UserLogin;

@Service
public class UserService {

	private IUserDao userDao;

	// בהמשך נעביר את המפה הזאת לדטה בייס כדי שנוכל להיות סקייל לכמה שרתים שנרצה
	// string = token , long = userId
	private TokenService tokenService;

	@Autowired
	public UserService(IUserDao userDao, TokenService tokenService) {
		super();
		this.userDao = userDao;
		this.tokenService = tokenService;
	}

	public long create(User user) {
		validateUser(user);
		UserEntity userEntity = new UserEntity(user);
		userDao.save(userEntity);

		return userEntity.getId();
	}

	public void remove(long id) {

		if (userDao.existsById(id)) {
			userDao.deleteById(id);
		} else {
			// TODO throw exception id not exists
		}

	}

	public String login(UserLogin userLogin) {
		UserEntity userEntity = userDao.findByNameAndPassword(userLogin.getName(), userLogin.getPassword());
		if (userEntity == null) {
			// TODO throw exception name or password not incorrect
		}
		String token = tokenService.create(userEntity.getId());

		return token;
	}

	private void validateUser(User user) {

		validateName(user.getName());
		validatePass(user.getPassword());
		validateEmile(user.getEmail());

	}

	private void validateEmile(String email) {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

//		Pattern pattern = Pattern.compile(regex);

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

		if (userDao.existsByEmail(name)) {
			// TODO throw exception name all ready use
		}

	}

}
