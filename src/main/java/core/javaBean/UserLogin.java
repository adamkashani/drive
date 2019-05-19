package core.javaBean;

public class UserLogin {

	private String name;
	private String password;

	public UserLogin() {
		super();
	}

	public UserLogin(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserLogin [name=" + name + ", password=" + password + "]";
	}
	
	
}
