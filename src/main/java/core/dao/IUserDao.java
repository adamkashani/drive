package core.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.UserEntity;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Long> {

	boolean existsByEmail(String email);

	boolean existsByName(String name);

	UserEntity findByEmail(String email);

	UserEntity findByName(String name);

	@Transactional
	Long deleteByName(String name);

	// login method
	UserEntity findByNameAndPassword(String name, String password);

}
