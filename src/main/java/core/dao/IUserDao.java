package core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.UserEntity;

@Repository
public interface IUserDao extends JpaRepository<UserEntity, Long> {

}
