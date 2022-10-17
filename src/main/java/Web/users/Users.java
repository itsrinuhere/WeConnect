package Web.users;

import javax.xml.bind.annotation.XmlRootElement;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@XmlRootElement
@Table(name="UserData")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userid",nullable=false,unique=true)
private	String id;
	@Column(name="username",length=90,unique=true,nullable=false)
private	String username;
	@Column(name="userbranch",length=10)
private	String userbranch;
	@Column(name="phonenumber",length=13)
	String phonenumber;
public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserbranch() {
		return userbranch;
	}
	public void setUserbranch(String userbranch) {
		this.userbranch = userbranch;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
