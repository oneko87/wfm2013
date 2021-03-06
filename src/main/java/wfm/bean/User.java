package wfm.bean;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@Stateful
@SessionScoped
public class User implements Serializable{

	private static final long serialVersionUID = -3854110981743884430L;
	
	private String username;
	private String password;
	private String membership; //bronze/silver/gold
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMembership() {
		return membership;
	}
	public void setMembership(String membership){
		this.membership = membership;
	}
}
