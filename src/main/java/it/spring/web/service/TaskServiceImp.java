package it.spring.web.service;

import it.spring.web.model.Fase;
import it.spring.web.model.Task;
import it.spring.web.model.Users;
import it.spring.web.repository.TaskRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskService")
@Transactional(readOnly = true) 
public class TaskServiceImp implements TaskService{
		@Autowired
	   private  TaskRepository repository;
	    
	   
	 
	
	@Override
	public Collection<Task> findAll() {
		return repository.findAll();
	
	}

	@Override
	public Collection<Task> findNotAssingnet() {
		return repository.findNotAssigment(Fase.NOT_ASSIGNMENT);
		
	}

	@Override
	public Collection<Task> findConclusion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Task> findUser(Users id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Task> findUserAssingnet(Users id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Task> findUserConclusion(Users id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Task save(Task task) {

		return repository.saveAndFlush(task);
	
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		repository.delete(id);
		
	}

}
