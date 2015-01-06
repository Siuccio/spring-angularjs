package it.spring.web.service;

import it.spring.web.model.Role;
import it.spring.web.model.Users;

import java.util.Collection;

public interface UsersService {

	
	public Collection<Users> findAll();
	public Collection<Users> findByRole(Role role);
	public Users findByID(Integer id);
	public Users findByUsername(String username);
	
	public Users save (Users user);
	public void delete (Integer id);
}
