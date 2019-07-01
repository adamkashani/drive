package core.logic;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.dao.ITokenDao;
import core.entity.TokenEntity;
import core.exception.DriveException;
import core.exception.ErrorType;

@Service
public class TokenService {

	private ITokenDao tokenDao;

	@Autowired
	public TokenService(ITokenDao tokenDao) {
		super();
		this.tokenDao = tokenDao;
	}

	/**
	 * this method create new token on db table tokenEntity
	 * 
	 * @param userId the id of user need to generate token
	 * @return the token string
	 */
	public String create(long userId) {
		TokenEntity tokenEntity = new TokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(generateToken());
		tokenDao.save(tokenEntity);
		return tokenEntity.getToken();
	}

	public void remove(TokenEntity tokenEntity) {
		tokenDao.delete(tokenEntity);
	}

	public void remove(String token) {
		tokenDao.deleteByToken(token);
	}

	public TokenEntity findByToken(String token) {
		TokenEntity tokenEntity = tokenDao.findByToken(token);
		if (tokenEntity == null) {
			// TODO throw new exception the token not exists
		}
		return tokenEntity;
	}

	private String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[64];
		random.nextBytes(bytes);
		String token = String.valueOf(bytes);
		return token;
	}

	public TokenEntity get(long id) throws DriveException {
		TokenEntity tokenEntity = null;
		tokenEntity = tokenDao.getOne(id);
		if (tokenEntity != null) {
			return tokenEntity;
		}
		throw new DriveException(ErrorType.TOKEN_NOT_EXISTS);
	}

}
