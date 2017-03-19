package com.tc.core.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/*
 * 自定义org.springframework.security.core.userdetails.User
 */
public class TcUserDetail extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9053900620621434505L;
	private Long userId;
	private String cid;


	public TcUserDetail(Long userId, String cid, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.userId = userId;
		this.cid = cid;
	}
	
	public TcUserDetail(Long userId, String cid, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
		this.cid = cid;
	}

	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getCid() {
		return cid;
	}


	public void setCid(String cid) {
		this.cid = cid;
	}

	
}
