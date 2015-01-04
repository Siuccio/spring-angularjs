package it.spring.web.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;
 

@Entity
public class Users extends AbstractPersistable<Integer> {

    private static final long serialVersionUID = -8712872385957386182L;

    private String username = null;
    private String password = null;

    //@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    //@JoinColumn(name="USERNAME", nullable=false)
   // private Set<Authorities> authorities = null;
    
    private String authority;

   

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
	
	

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password
				+ ", authority=" + authority + "]";
	}

	



  

    

}

