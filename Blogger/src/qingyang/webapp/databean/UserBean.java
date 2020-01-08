package qingyang.webapp.databean;

import org.genericdao.PrimaryKey;

@PrimaryKey("email")
public class UserBean {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	public void setFirstName(String s) {
		firstName = s;
	}
	public void setLastName(String s) {
		lastName = s;
	}
	public void setEmail(String s) {
		email = s;
	}
	public void setPassword(String s) {
		password = s;
	}

}
