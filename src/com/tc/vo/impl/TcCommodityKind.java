package com.tc.vo.impl;
// Generated 2016-2-9 14:01:59 by Hibernate Tools 4.3.1.Final

import com.tc.core.frame.vo.AbstractVO;

/**
 * TcCommodityKind generated by hbm2java
 */
public class TcCommodityKind extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1325644497021112986L;
	private Long id;
	private String cid;
	private String name;
	private Integer pid;

	public TcCommodityKind() {
	}

	public TcCommodityKind(Long id, Integer pid) {
		this.id = id;
		this.pid = pid;
	}

	public TcCommodityKind(Long id, String cid, String name, Integer pid) {
		this.id = id;
		this.cid = cid;
		this.name = name;
		this.pid = pid;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}
}
