package it.spring.web;

import java.util.Collection;

import it.spring.web.model.Fase;
import it.spring.web.model.ResponseTask;
import it.spring.web.model.Task;
import it.spring.web.model.Users;
import it.spring.web.service.TaskService;
import it.spring.web.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {

	@Autowired
	@Qualifier("taskService")
	TaskService taskService;

	@Autowired
	@Qualifier("userService")
	UsersService userService;

	@RequestMapping(value = "/task/{username}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseTask taskUser(@PathVariable String username) {

		Users user = userService.findByUsername(username);
		return taskService.findTaskUser(user);

	}

	@RequestMapping(value = "/task/{username}/{fase}", method = RequestMethod.GET)
	@ResponseBody
	public Page<Task> taskUser(@PathVariable String username,
			@PathVariable Fase fase, @RequestParam Integer page) {

		Users user = userService.findByUsername(username);
		return taskService.findTaskUserFase(user, fase, page);

	}

	@RequestMapping(value = "/task/id/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Task taskID(@PathVariable Integer id) {
		//Task result = taskService.findId(id);
		Task col = taskService.findId(id);
		
		return col;

	}
	
	
	@RequestMapping(value = "/task/id/{id}", method = RequestMethod.PUT, consumes = {"application/json;charset=UTF-8"}, produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public Task postID(@PathVariable Integer id,@RequestBody Task task) {
		//Task result = taskService.findId(id);
		Task old=taskService.findId(task.getId());
		task.setUsers(old.getUsers());
		taskService.save(task);
		return task;

	}

	@RequestMapping(value = "/task/count/{username}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseTask taskCountUser(@PathVariable String username) {

		Users user = userService.findByUsername(username);
		return taskService.findCountTaskUser(user);

	}
}
