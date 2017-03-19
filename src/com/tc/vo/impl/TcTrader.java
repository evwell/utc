package com.tc.vo.impl;
// Generated 2016-11-29 14:07:37 by Hibernate Tools 4.3.1.Final

import java.sql.Timestamp;

import com.tc.core.frame.vo.AbstractVO;

/**
 * TcTrader generated by hbm2java
 */
public class TcTrader extends AbstractVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -424226287841458121L;
	private Long id;
	private String cid;
	private Byte tradeType;
	private Byte nature;
	private String name;
	private String fullName;
	private String firmName;
	private String contacts;
	private String tel1;
	private String tel2;
	private String phone1;
	private String phone2;
	private String fax;
	private String postCode;
	private String address;
	private Byte status;
	private String compDsc;
	private Integer area;
	private String comment;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Long createBy;
	private Long modifyBy;

	public TcTrader() {
	}

	public TcTrader(Long id, Byte tradeType, Byte nature) {
		this.id = id;
		this.tradeType = tradeType;
		this.nature = nature;
	}

	public TcTrader(Long id, String cid, Byte tradeType, Byte nature, String name, String fullName, String firmName,
			String contacts, String tel1, String tel2, String phone1, String phone2, String fax, String postCode,
			String address, Byte status, String compDsc, Integer area, String comment, Timestamp createTime, Timestamp modifyTime,
			Long createBy, Long modifyBy) {
		this.id = id;
		this.cid = cid;
		this.tradeType = tradeType;
		this.nature = nature;
		this.name = name;
		this.fullName = fullName;
		this.firmName = firmName;
		this.contacts = contacts;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.fax = fax;
		this.postCode = postCode;
		this.address = address;
		this.status = status;
		this.compDsc = compDsc;
		this.area = area;
		this.comment = comment;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.createBy = createBy;
		this.modifyBy = modifyBy;
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

	public Byte getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(Byte tradeType) {
		this.tradeType = tradeType;
	}

	public Byte getNature() {
		return this.nature;
	}

	public void setNature(Byte nature) {
		this.nature = nature;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirmName() {
		return this.firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return this.tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getCompDsc() {
		return this.compDsc;
	}

	public void setCompDsc(String compDsc) {
		this.compDsc = compDsc;
	}

	public Integer getArea() {
		return this.area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

}
