package core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.Length;

import core.Enum.UserStatus;
import core.javaBean.File;

@Entity
public class FileEntity {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, unique = true)
	private String fileName;

	@Column(nullable = false)
	private String owner;

	// where we save the file in server side
	@Column(nullable = false)
	private String pathToFile;

	@Column(nullable = false)
	private long lastModified;

//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity userEntity;

	@Enumerated(EnumType.STRING)
	@Length(max = 10)
	private UserStatus userStatus;

	public FileEntity() {
		super();
	}

	public FileEntity(long id, String fileName, String pathToFile) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.pathToFile = pathToFile;
	}

	public FileEntity(File fileBean) {
		super();
		this.id = fileBean.getId();
		this.fileName = fileBean.getFileName();
		this.pathToFile = fileBean.getPathToFile();
	}

	public File toBean() {
		File result = new File();
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", fileName=" + fileName + ", owner=" + owner + ", pathToFile=" + pathToFile
				+ ", lastModified=" + lastModified + ", userEntity=" + userEntity + ", userStatus=" + userStatus + "]";
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
