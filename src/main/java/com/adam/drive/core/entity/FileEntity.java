package com.adam.drive.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.adam.drive.core.javaBean.FileBean;

@Entity
public class FileEntity {

	@Id
	private long id;
	@Column(nullable = false)
	private String fileName;
	@Column(nullable = false)
	private String pathToFile;

//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity userEntity;

	public FileEntity() {
		super();
	}

	public FileEntity(long id, String fileName, String pathToFile) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.pathToFile = pathToFile;
	}

	public FileEntity(FileBean fileBean) {
		super();
		this.id = fileBean.getId();
		this.fileName = fileBean.getFileName();
		this.pathToFile = fileBean.getPathToFile();
	}

	public FileBean toBean() {
		FileBean result = new FileBean();
		result.setId(id);
		result.setFileName(fileName);
		result.setPathToFile(pathToFile);
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPathToFile() {
		return pathToFile;
	}

	public void setPathToFile(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", fileName=" + fileName + ", pathToFile=" + pathToFile + ", userEntity="
				+ userEntity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileEntity other = (FileEntity) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
