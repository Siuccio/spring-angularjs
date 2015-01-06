package it.spring.web;

import java.util.Collection;

import it.spring.web.model.Task;
import it.spring.web.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TaskController {
	
	@Autowired
	@Qualifier("taskService")
	TaskService taskService;
	
	@RequestMapping(value = "/task/not", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Task> notAssigment(ModelMap model) {
		Collection<Task> col=taskService.findNotAssingnet();
		return col;
	}
}
