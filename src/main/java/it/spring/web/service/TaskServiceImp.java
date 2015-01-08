package it.spring.web.service;

import it.spring.web.model.Fase;
import it.spring.web.model.ResponseTask;
import it.spring.web.model.Task;
import it.spring.web.model.Users;
import it.spring.web.repository.TaskRepository;
import it.spring.web.repository.UsersRepository;

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
	public Collection<Task> findAllTask() {
		return repository.findAll();
	
	}

	@Override
	public Collection<Task> findNotAssingnetTask() {
		return repository.findNotAssigment(Fase.NOT_ASSIGNMENT);
		
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

	@Override
	public Collection<Task> findConclusionTask() {
		return repository.findNotAssigment(Fase.CONCLUDE);
	}

	@Override
	public ResponseTask findTaskUser(Users user) {
		ResponseTask response=new ResponseTask();
		Collection<Task> coll=repository.findTaskUserWithFase(Fase.CONCLUDE,user);
		response.setTaskConclude(coll);
		response.setTaskConcludeCount(coll.size());
		coll=repository.findTaskUserWithFase(Fase.ASSIGNMENT,user);
		response.setTaskAssignment(coll);
		response.setTaskAssignmentCount(coll.size());
		coll=repository.findTaskUserWithFase(Fase.TAKE,user);
		response.setTaskTake(coll);
		response.setTaskConcludeCount(coll.size());
		return response;
	}

}
