package core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TokenEntity {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String token;

	@Column(nullable = false)
	private long userId;
	
//	@OneToOne
//	private UserEntity userEntity;

	public TokenEntity() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
