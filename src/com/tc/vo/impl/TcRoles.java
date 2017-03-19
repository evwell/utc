package com.tc.vo.impl;
// Generated 2016-10-13 23:35:29 by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;

import com.tc.core.frame.vo.AbstractVO;

/**
 * TcRoles generated by hbm2java
 */
public class TcRoles extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8517336233407712575L;
	private Long id;
	private String cid;
	private String roleName;
	private Byte status;
	private String remark;
	private Set tcUserRoles = new HashSet(0);
	private Set tcRoleAuthorities = new HashSet(0);

	public TcRoles() {
	}

	public TcRoles(Long id) {
		this.id = id;
	}

	public TcRoles(Long id, String cid, String roleName, Byte status, String remark, Set tcUserRoles,
			Set tcRoleAuthorities) {
		this.id = id;
		this.cid = cid;
		this.roleName = roleName;
		this.status = status;
		this.remark = remark;
		this.tcUserRoles = tcUserRoles;
		this.tcRoleAuthorities = tcRoleAuthorities;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set getTcUserRoles() {
		return this.tcUserRoles;
	}

	public void setTcUserRoles(Set tcUserRoles) {
		this.tcUserRoles = tcUserRoles;
	}

	public Set getTcRoleAuthorities() {
		return this.tcRoleAuthorities;
	}

	public void setTcRoleAuthorities(Set tcRoleAuthorities) {
		this.tcRoleAuthorities = tcRoleAuthorities;
	}

}
