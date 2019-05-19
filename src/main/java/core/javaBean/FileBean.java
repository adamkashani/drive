package core.javaBean;

public class FileBean {

	private long id;
	private String fileName;
	private String pathToFile;

	public FileBean(long id, String fileName, String pathToFile) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.pathToFile = pathToFile;
	}

	public FileBean() {
		super();
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

	@Override
	public String toString() {
		return "FileBean [id=" + id + ", fileName=" + fileName + ", pathToFile=" + pathToFile + "]";
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
		FileBean other = (FileBean) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
