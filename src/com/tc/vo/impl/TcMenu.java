package com.tc.vo.impl;
// Generated 2016-2-9 14:01:59 by Hibernate Tools 4.3.1.Final

import com.tc.core.frame.vo.AbstractVO;

/**
 * TcMenu generated by hbm2java
 */
public class TcMenu extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3935755788465467969L;
	private Long id;
	private String cid;
	private String name;
	private String description;
	private Integer type;
	private Integer pid;
	private Integer status;
	private Byte level;
	private String url;
	private Byte odr;

	public TcMenu() {
	}

	public TcMenu(Long id, String name, Integer type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public TcMenu(Long id, String cid, String name, String description, Integer type, Integer pid,
			Integer status, Byte level, String url, Byte odr) {
		this.id = id;
		this.cid = cid;
		this.name = name;
		this.description = description;
		this.type = type;
		this.pid = pid;
		this.status = status;
		this.level = level;
		this.url = url;
		this.odr = odr;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Byte getLevel() {
		return this.level;
	}

	public void setLevel(Byte level) {
		this.level = level;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte getOdr() {
		return odr;
	}

	public void setOdr(Byte odr) {
		this.odr = odr;
	}

}
