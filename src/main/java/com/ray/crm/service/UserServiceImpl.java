package com.ray.crm.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ray.crm.dao.RoleDAO;
import com.ray.crm.dao.UserDAO;
import com.ray.crm.dto.RegisteredUser;
import com.ray.crm.entity.Role;
import com.ray.crm.entity.User;


@Service("myUserDetailsService")
public class UserServiceImpl implements UserService {
	private final UserDAO userDAO;
	private final RoleDAO roleDAO;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
		super();
		this.userDAO = userDAO;
		this.roleDAO = roleDAO;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		/// get user object base on username
		User user = userDAO.findByUserName(username);
		if (user == null) {
			throw new RuntimeException("User not found by username: " + username);
		}
		
		Set<Role> roleList = user.getRoles();
		
		List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
		for (Role role : roleList) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
			grantedAuthorityList.add(grantedAuthority);
		}
		
		/// to create UserDetails, need username, password, authorities
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				grantedAuthorityList);
		
		return userDetails;
	}


	@Override
//	@Transactional
	public Object save(RegisteredUser registeredUser) {
		User user = new User();
		
		user.setUsername(registeredUser.getUserName());
		user.setPassword(passwordEncoder.encode(registeredUser.getPassword()));
		user.setEmail(registeredUser.getEmail());
		user.setEnabled(true);
		
		Role role = roleDAO.findRoleByName("ROLE_EMPLOYEE");
		
		Set<Role> roles = new HashSet<Role>(Arrays.asList(role));
		user.setRoles(roles);
		
		return userDAO.save(user);
	}

}
