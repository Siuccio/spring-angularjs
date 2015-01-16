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
    private Role role;
    private String image;
   
    public Users() {
		super();
		
	}
    public Users(String username,  Role role) {
  		super();
  		this.username = username;
 
  		this.role = role;
  	}
    
    public Users(String username, String password, Role role,String image) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.image=image;
	}
    public Users(Integer id,String username, String password, Role role,String image) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.image=image;
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
	
	

	

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Users [username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	

	



  

    

}

