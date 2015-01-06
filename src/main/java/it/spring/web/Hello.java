package it.spring.web;

import it.spring.web.model.Users;

import javax.ws.rs.WebApplicationException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
/*
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String getUserLogged() {
		
		return "index";
	}*/

}
