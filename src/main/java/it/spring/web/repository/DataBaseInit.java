package it.spring.web.repository;

import java.util.Calendar;
import java.util.Date;

import it.spring.web.model.Area;
import it.spring.web.model.Critical;
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
		Users user = new Users("admin","admin",Role.ROLE_ADMIN,"");
		this.usersService.save(user);
		user = new Users("test","test",Role.ROLE_USER,"images/test.jpg");
		user=this.usersService.save(user);
		

		Task task=new Task();
		task.setTitle("adad");
		task.setUsers(user);
		task.setAssignment(new Date());
		task.setFase(Fase.ASSIGNMENT);
		task.setArea(Area.SVILUPPO);
		task.setCriticality(Critical.HIGH);
		this.taskService.save(task);

	    task=new Task();
		task.setTitle("1");
		task.setUsers(user);
		task.setFase(Fase.ASSIGNMENT);
		task.setAssignment(new Date());
		task.setArea(Area.SVILUPPO);
		task.setCriticality(Critical.LOW);
		this.taskService.save(task);

		task=new Task();
		task.setTitle("2");
		task.setUsers(user);
		task.setFase(Fase.ASSIGNMENT);
		task.setAssignment(new Date());
		task.setArea(Area.SVILUPPO);
		task.setCriticality(Critical.EMERGERCY);
		this.taskService.save(task);
		
		task=new Task();
		task.setTitle("3");
		task.setUsers(user);
		task.setFase(Fase.ASSIGNMENT);
		task.setAssignment(new Date());
		task.setArea(Area.BUG_FIX);
		task.setCriticality(Critical.HIGH);
		this.taskService.save(task);
		
		task=new Task();
		task.setTitle("4");
		task.setUsers(user);
		task.setFase(Fase.ASSIGNMENT);
		task.setAssignment(new Date());
		task.setArea(Area.SVILUPPO);
		task.setCriticality(Critical.HIGH);
		this.taskService.save(task);
		
		task=new Task();
		task.setTitle("6");
		task.setUsers(user);
		task.setFase(Fase.ASSIGNMENT);
		task.setAssignment(new Date());
		task.setArea(Area.BUG_FIX);
		task.setCriticality(Critical.LOW);
		this.taskService.save(task);
		
		task=new Task();
		task.setTitle("7");
		task.setUsers(user);
		task.setFase(Fase.ASSIGNMENT);
	
		Calendar calendar = Calendar.getInstance(); // this would default to now
		calendar.add(Calendar.DAY_OF_MONTH, -5);
		
		task.setAssignment(calendar.getTime());
		task.setArea(Area.BUG_FIX);
		task.setCriticality(Critical.EMERGERCY);
		this.taskService.save(task);
		
		task=new Task();
		task.setTitle("8");
		task.setUsers(user);
		task.setFase(Fase.TAKE);
		task.setArea(Area.SVILUPPO);
		task.setAssignment(new Date());
		task.setCriticality(Critical.HIGH);
		this.taskService.save(task);
		
		
		task=new Task();
		task.setTitle("9");
		task.setUsers(user);
		task.setFase(Fase.CONCLUDE);
		task.setAssignment(calendar.getTime());
		task.setConclusion(new Date());
		task.setArea(Area.BUG_FIX);
		task.setCriticality(Critical.EMERGERCY);
		this.taskService.save(task);
		
		user = new Users("alessio","alessio",Role.ROLE_USER,"images/alessio.jpg");
		this.usersService.save(user);
		user = new Users("francesco","francesco",Role.ROLE_USER,"images/francesco.jpg");
		this.usersService.save(user);
		user = new Users("roberto","roberto",Role.ROLE_USER,"images/roberto.jpg");
		this.usersService.save(user);
		
	}

}