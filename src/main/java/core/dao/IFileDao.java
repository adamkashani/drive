package core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import core.entity.FileEntity;

@Repository
public interface IFileDao extends JpaRepository<FileEntity, Long>{
	
	
	

}
