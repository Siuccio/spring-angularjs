package it.spring.web;


import java.util.Collection;

import it.spring.web.model.Person;
import it.spring.web.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

	   @Autowired
    private  PersonService service;

 
   
	
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) {
		model.addAttribute("msg", "JCG Hello World!");
		return "/jsp/hello";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index	() {
		return "/jsp/index";
	}

	@RequestMapping(value = "/persons")
	@ResponseBody
	public  Collection<Person> hibernate() {
		Collection<Person> persons=service.find();
		return persons;
				}
	
}
