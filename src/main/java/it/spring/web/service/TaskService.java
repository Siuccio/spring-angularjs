package it.spring.web.service;


import it.spring.web.model.ResponseTask;
import it.spring.web.model.Task;
import it.spring.web.model.Users;

import java.util.Collection;

public interface TaskService {

	
	public Collection<Task> findAllTask();
	
	public Collection<Task> findNotAssingnetTask();
	
	public Collection<Task> findConclusionTask();
	
	public ResponseTask findTaskUser(Users id);

	
	
	public Task save(Task task);
	public void delete(Integer id);
}
