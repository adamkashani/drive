package core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.TokenEntity;

@Repository
public interface ITokenDao extends JpaRepository<TokenEntity, Long> {

	// get token from db
	TokenEntity findByToken(String token);

	void deleteByToken(String token);

}
