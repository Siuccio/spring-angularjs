package it.spring.web.repository;

import it.spring.web.model.Fase;
import it.spring.web.model.Role;
import it.spring.web.model.Task;
import it.spring.web.model.Users;
import it.spring.web.service.TaskService;
import it.spring.web.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * Initialize the database with some test entries.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class DataBaseInit
{

	@Autowired
	@Qualifier("userService")
	private UsersService usersService;

	
	@Autowired
	@Qualifier("taskService")
	TaskService taskService;

	protected DataBaseInit()
	{
		/* Default constructor for reflection instantiation */
	}






	public void initDataBase()
	{
		Users user = new Users("admin","admin",Role.ROLE_ADMIN);
		this.usersService.save(user);
		user = new Users("test","test",Role.ROLE_USER);
		user=this.usersService.save(user);
		

		Task task=new Task();
		task.setTitle("adad");
		task.setUsers(user);
		task.setFase(Fase.NOT_ASSIGNMENT);
		this.taskService.save(task);
		
		user = new Users("alessio","baldini",Role.ROLE_USER);
		this.usersService.save(user);
		
	}

}