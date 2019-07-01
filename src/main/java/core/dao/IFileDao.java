package core.dao;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import core.entity.FileEntity;

@Repository
public interface IFileDao extends JpaRepository<FileEntity, Long> {

	FileEntity findByFileName(String fileName);

	@Transactional
	void deleteByOwner(String owner);
	
	@Query("SELECT fileEntity FROM FileEntity fileEntity WHERE  fileEntity.userEntity.id = :#{#userId} AND fileEntity.fileName = :#{#fileName}")
	FileEntity findByUserIdAndFileName(@Param("userId") long userId ,@Param("fileName") String fileName);
}
