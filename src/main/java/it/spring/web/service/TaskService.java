package it.spring.web.service;


import it.spring.web.model.Fase;
import it.spring.web.model.ResponseTask;
import it.spring.web.model.Task;
import it.spring.web.model.Users;

import java.util.Collection;

import org.springframework.data.domain.Page;

public interface TaskService {

	
	public Page<Task> findAllTask();
	public Page<Task> findNotAssingnetTask();
	public Page<Task> findConclusionTask();
	public Page<Task> findTakeTask();
	public Page<Task> findAssigmentTask();
	public Page<Task> findTaskUserFase(Users user,Fase fase,Integer page);
	
	public ResponseTask findTaskUser(Users id);

	public ResponseTask findCountTaskUser(Users id);
	
	public Task save(Task task);
	public void delete(Integer id);
}
