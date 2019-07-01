package core.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.IUserDao;
import core.entity.TokenEntity;
import core.entity.UserEntity;
import core.exception.DriveException;
import core.exception.ErrorType;
import core.javaBean.User;
import core.javaBean.UserLogin;

@Service
public class UserService {

	private IUserDao userDao;

	private TokenService tokenService;

	private FileService fileService;

	// Constructor injection
	@Autowired
	public UserService(IUserDao userDao, TokenService tokenService, FileService fileService) {
		super();
		this.userDao = userDao;
		this.tokenService = tokenService;
		this.fileService = fileService;
	}
	
	public long create(User user) throws DriveException {
		try {
			validateUser(user);
			UserEntity userEntity = new UserEntity(user);
			userDao.save(userEntity);
			// create directory for this user (insert we save all file this client upload)
			fileService.createDirectoryUser(userEntity.getName());
			return userEntity.getId();
		} catch (DriveException e) {
			userDao.deleteByName(user.getName());
			System.out.println(e.getMessage());
			throw new DriveException(e);
		}
	}

	// from token
	public void remove(long token) {
		TokenEntity tokenClient = tokenService.get(token);
		UserEntity userEntity = userDao.getOne(tokenClient.getUserId());
		if (userDao.existsById(tokenClient.getUserId())) {
			// remove all file this client was the owner
			fileService.removeByOwnerName(userEntity.getName());
			userDao.deleteById(tokenClient.getUserId());
		} else {
			// TODO throw exception id not exists
		}

	}

	public UserEntity get(long userId) {
		return this.userDao.getOne(userId);
	}

	public String login(UserLogin userLogin) {
		UserEntity userEntity = userDao.findByNameAndPassword(userLogin.getName(), userLogin.getPassword());
		if (userEntity == null) {
			// TODO throw exception name or password not incorrect
		}
		String token = tokenService.create(userEntity.getId());

		return token;
	}

	private void validateUser(User user) throws DriveException {

		validateName(user.getName());
		validatePass(user.getPassword());
		validateEmile(user.getEmail());

	}

	private void validateEmile(String email) throws DriveException {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

//		Pattern pattern = Pattern.compile(regex);

		if (!regex.matches(regex)) {
			throw new DriveException(ErrorType.UNVALIDATED_EMAIL);
		}
		if (userDao.existsByEmail(email)) {
			throw new DriveException(ErrorType.EMAIL_EXISTS);
		}

	}

	private void validatePass(String password) throws DriveException {
		if (password == null) {
			throw new DriveException(ErrorType.UNVALIDATED_PASSWORD);
		}
		if (password.length() > 4) {
			throw new DriveException(ErrorType.SHORT_PASSWORD);
		}
	}

	private void validateName(String name) throws DriveException {
		if (name == null) {
			throw new DriveException(ErrorType.UNVALIDATED_NAME);
		}
		if (name.length() > 4) {
			throw new DriveException(ErrorType.SHORT_NAME);
		}

		if (userDao.existsByEmail(name)) {
			throw new DriveException(ErrorType.USER_NAME_EXISTS);
		}
	}
}
