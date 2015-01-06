package it.spring.web.service;


import it.spring.web.model.Task;
import it.spring.web.model.Users;

import java.util.Collection;

public interface TaskService {

	
	public Collection<Task> findAll();
	
	public Collection<Task> findNotAssingnet();
	
	public Collection<Task> findConclusion();
	
	public Collection<Task> findUser(Users id);
	public Collection<Task> findUserAssingnet(Users id);
	public Collection<Task> findUserConclusion(Users id);
	
	public Task save(Task task);
	public void delete(Integer id);
}
