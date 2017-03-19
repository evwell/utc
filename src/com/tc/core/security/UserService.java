package com.tc.core.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.service.AbstractService;
import com.tc.core.frame.vo.VO;
import com.tc.vo.impl.TcUser;

public class UserService extends AbstractService<TcUser> implements UserDetailsService {
	private Logger logger = Logger.getLogger(this.getClass());
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<TcUser> tcUsers;
		TcUserDetail tcUserDetail = null;
		try {
			tcUsers = dao.findByNamedQuery("findByNamedQuery", username);
			TcUser tcUser = tcUsers.get(0);
			Set<VO> roles = tcUser.getRoles();
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			for (VO r : roles) {
				logger.debug("Grant role: " + r);
				authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			tcUserDetail = new TcUserDetail (tcUser.getId(),tcUser.getCid(),tcUser.getUserName(), tcUser.getPassword(), authorities);
		} catch (BusinessException e) {
			logger.debug(e);;
		}
		return tcUserDetail;
	}

	@Override
	public List<TcUser> find(TcUser e) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
