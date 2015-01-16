package it.spring.web.repository;

import java.util.Collection;

import it.spring.web.model.Role;
import it.spring.web.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UsersRepository extends JpaRepository<Users,Integer> {
    
	
	//@Query("SELECT p FROM Users p WHERE LOWER(p.username) = LOWER(:username)")
    //public Users findUsername(@Param("username") String username);
	
	
	public Users findByUsername(String username);
	
	/*@Query("SELECT p FROM Users p WHERE p.authority = :role")
    public Collection<Users> findByRole(@Param("role") Role role);*/
    public Collection<Users> findByRole(Role role);
	
	
	//@Query("SELECT p FROM Users p WHERE p.authority = :role")
	//public Long countByRole()
}