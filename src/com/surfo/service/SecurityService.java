package com.surfo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.surfo.dao.UserDao;
import com.surfo.entity.AcctUser;

public class SecurityService implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserDetails user = null;
		try {
			AcctUser acctUser = userDao.findByUserName(userName);
			if (null != acctUser) {
				user = new User(acctUser.getUserName(), acctUser.getPassword().toLowerCase(), true, true, true, true, getAuthorities());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	/**
	 * 获得访问角色权限
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//		for (AcctRole acctRole : roles) {
//			authList.add(new SimpleGrantedAuthority(acctRole.getName()));
//		}
		return authList;
	}
}
