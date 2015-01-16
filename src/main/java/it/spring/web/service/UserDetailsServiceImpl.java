package it.spring.web.service;

import java.util.HashSet;
import java.util.Set;

import it.spring.web.model.GrantedAuthorityImpl;
import it.spring.web.model.UserDetailsImpl;
import it.spring.web.model.Users;
import it.spring.web.repository.UsersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService{ 

    private  UsersRepository repository;
    
    @Autowired
    public UserDetailsServiceImpl(UsersRepository repository) {
        this.repository = repository;
    }

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		Users users=repository.findByUsername(username);
		if(users!=null)
		{
			
			Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
			authorities.add(new GrantedAuthorityImpl(users.getRole().toString()));
			return new UserDetailsImpl(users.getUsername(),users.getPassword(),authorities);
		}
		return null;
	}
}