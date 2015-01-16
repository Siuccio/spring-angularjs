package it.spring.web;

import java.util.Collection;

import it.spring.web.model.Role;
import it.spring.web.model.Users;
import it.spring.web.service.TaskService;
import it.spring.web.service.UsersService;

import javax.ws.rs.WebApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {
	
	
	@Autowired
	@Qualifier("userService")
	UsersService userService;
	
	@RequestMapping(value = "/users/{role}", method = RequestMethod.GET)
	@ResponseBody
	public Collection<Users> getUser(@PathVariable String role) {
		if("".equals(role))			
			return userService.findAll();
		else{
			Role ro=Role.valueOf(role);
			Collection<Users> col=userService.findByRole(ro);
			return col;
		}
	}
	
	
	@RequestMapping(value = "/users/username/{username}", method = RequestMethod.GET)
	@ResponseBody
	public Users getUserByUsername(@PathVariable String username) {
		
			
			Users col=userService.findByUsername(username);
			return col;
		
	}
	
}
