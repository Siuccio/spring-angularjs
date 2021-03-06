package it.spring.web;



import javax.ws.rs.FormParam;
import javax.ws.rs.WebApplicationException;

import it.spring.web.model.Role;
import it.spring.web.model.TokenTransfer;
import it.spring.web.model.Users;
import it.spring.web.repository.UsersRepository;
import it.spring.web.security.TokenUtils;
import it.spring.web.service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class Login {

	@Autowired
	private UserDetailsServiceImpl userDetailService;

	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	

	
	@RequestMapping( value = "/login",method = RequestMethod.POST)
	@ResponseBody
	//public TokenTransfer loginUser(@RequestParam(required=false) String username, 
	 //       @RequestParam(required=false) String password) {
	public TokenTransfer loginUser(@RequestParam(value="username")String username,@RequestParam(value="password")String password) {
		

		
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
	//	SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}
	
	@RequestMapping(value = "/login/user", method = RequestMethod.GET)
	@ResponseBody
	public Users getUserLogged() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;
		Users users=new Users(userDetails.getUsername(),createRoleMap(userDetails));
		return users;
	}
	
	private Role createRoleMap(UserDetails userDetails)
	{
		
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			return Role.valueOf(authority.getAuthority());
		}

		return null;
	}
	
	private String extractString(String property,String body)
	{
		return "";
	}
}
