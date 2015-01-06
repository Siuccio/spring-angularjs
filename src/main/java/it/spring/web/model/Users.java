package it.spring.web.model;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 

@javax.persistence.Entity
public class Users {

    private static final long serialVersionUID = -8712872385957386182L;

  

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
	private Integer id=1;
    private String username = null;
    private String password = null;
    private Role authority;

   
    public Users() {
		super();
		
	}
    public Users(String username,  Role authority) {
  		super();
  		this.username = username;
 
  		this.authority = authority;
  	}
    
    public Users(String username, String password, Role authority) {
		super();
		this.username = username;
		this.password = password;
		this.authority = authority;
	}
    public Users(Integer id,String username, String password, Role authority) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.authority = authority;
	}

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
	
	

	public Role getAuthority() {
		return authority;
	}

	public void setAuthority(Role authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password
				+ ", authority=" + authority + "]";
	}

	

	



  

    

}

