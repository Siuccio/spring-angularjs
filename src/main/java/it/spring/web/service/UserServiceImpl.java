package it.spring.web.service;

import it.spring.web.model.Role;
import it.spring.web.model.Users;
import it.spring.web.repository.TaskRepository;
import it.spring.web.repository.UsersRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional(readOnly = true) 
public class UserServiceImpl implements UsersService{

    @Autowired
    private  UsersRepository repository;
	    
	    
	    
	@Override
	public Collection<Users> findAll() {
		
		return repository.findAll();
	}

	@Override
	public Users findByID(Integer id) {
		return repository.findOne(id);
		
	}
	
	@Override
	public Users findByUsername(String username) {
		return repository.findUsername(username);
		
	}

	@Override
	@Transactional
	public Users save(Users user) {
		return repository.save(user);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Users> findByRole(Role role) {
		return repository.findByRole(role);
	}

}
