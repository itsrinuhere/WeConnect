package api.main.UserSystem;

import javax.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@XmlRootElement()
@Entity
@Table(name="UserModel")
public class User{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long userId;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasscode() {
		return passcode;
	}
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	@Column(name="username",length=100,nullable=false,unique=true)
	String username;
	@Column(name="passcode",length=100,nullable=false)
	String passcode;

}
