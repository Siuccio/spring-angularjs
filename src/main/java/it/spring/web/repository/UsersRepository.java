package it.spring.web.repository;

import java.util.List;

import it.spring.web.model.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users,Integer> {
    
	
	@Query("SELECT p FROM Users p WHERE LOWER(p.username) = LOWER(:username)")
    public List<Users> findUsername(@Param("username") String username);
}