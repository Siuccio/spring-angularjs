package it.spring.web;

import it.spring.web.model.ResponseTask;
import it.spring.web.model.Users;
import it.spring.web.service.TaskService;
import it.spring.web.service.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		Users user=userService.findByUsername(username);
		return taskService.findTaskUser(user);
	
	}
}
