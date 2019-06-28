package core.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.FileEntity;

@Repository
public interface IFileDao extends JpaRepository<FileEntity, Long> {

	FileEntity findByFileName(String fileName);

	@Transactional
	void deleteByOwner(String owner);
}
