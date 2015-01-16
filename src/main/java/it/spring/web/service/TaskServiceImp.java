package it.spring.web.service;

import it.spring.web.model.Fase;
import it.spring.web.model.ResponseTask;
import it.spring.web.model.Task;
import it.spring.web.model.Users;
import it.spring.web.repository.TaskRepository;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("taskService")
@Transactional(readOnly = true) 
public class TaskServiceImp implements TaskService{
		@Autowired
	   private  TaskRepository repository;

	   

	
	@Override
	public Page<Task> findAllTask() {
		 PageRequest request =
	                new PageRequest(0, 5, Sort.Direction.DESC, "title");
			return repository.findAll(request);
	
	}

	@Override
	public Page<Task> findNotAssingnetTask() {
	    PageRequest request =
                new PageRequest(0, 5, Sort.Direction.DESC, "title");
		return repository.findByFase(Fase.NOT_ASSIGNMENT,request);
		
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
	public Page<Task> findConclusionTask() {
		 PageRequest request =
	                new PageRequest(0, 5, Sort.Direction.DESC, "title");
			return repository.findByFase(Fase.CONCLUDE,request);
	}

	@Override
	public ResponseTask findTaskUser(Users user) {
		ResponseTask response=new ResponseTask();
    
		Collection<Task> coll=repository.findTaskUserWithFase(Fase.CONCLUDE,user);
		response.setTaskConclude(coll);
		coll=repository.findTaskUserWithFase(Fase.ASSIGNMENT,user);
		response.setTaskAssignment(coll);
		coll=repository.findTaskUserWithFase(Fase.TAKE,user);
		response.setTaskTake(coll);
		return response;
	}

	@Override
	public ResponseTask findCountTaskUser(Users user) {
		ResponseTask response=new ResponseTask();
		response.setTaskAssignmentCount(repository.countByFase(Fase.ASSIGNMENT,user));
		response.setTaskConcludeCount(repository.countByFase(Fase.CONCLUDE,user));
		response.setTaskTakeCount(repository.countByFase(Fase.TAKE,user));
		return response;
	}

	@Override
	public Page<Task> findTaskUserFase(Users user, Fase fase,Integer page) {
		   PageRequest request =
	                new PageRequest(page, 5, Sort.Direction.DESC, "title");
			return repository.findTaskUserWithFasePage(fase, user, request);
	}

	
	@Override
	public Page<Task> findTakeTask() {
	    PageRequest request =
                new PageRequest(0, 5, Sort.Direction.DESC, "title");
		return repository.findByFase(Fase.TAKE,request);
	}

	@Override
	public Page<Task> findAssigmentTask() {
		   PageRequest request =
	                new PageRequest(0, 5, Sort.Direction.DESC, "title");
			return repository.findByFase(Fase.ASSIGNMENT,request);
	}

}
