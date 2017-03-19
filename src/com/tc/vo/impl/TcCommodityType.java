package com.tc.vo.impl;
// Generated 2016-2-9 14:01:59 by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.tc.core.frame.vo.AbstractVO;

/**
 * TcCommodityType generated by hbm2java
 */
public class TcCommodityType extends AbstractVO {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8479356674373101430L;
	private Long id;
	private String cid;
	private Integer traderId;
	private String tag;
	private Integer kindId;
	private String code;
	private String barCode;
	private String fullName;
	private String name;
	private String standard;
	private String type1;
	private String type2;
	private String type3;
	private String area;
	private Integer recPrice;
	private Integer sallPrice;
	private BigDecimal margins;
	private Integer qualityGuarantee;
	private Byte unit;
	private String pinyin;
	private String comment;
	private byte[] picture;
	private Long createBy;
	private Timestamp createTime;
	private Long modifyBy;
	private Timestamp modifyTime;
	private Integer status;
	private Integer total;
	private TcCommodityKind commodityKind;//many-to-one
	private TcTrader trader; //many-to-one

	public TcTrader getTrader() {
		return trader;
	}

	public void setTrader(TcTrader trader) {
		this.trader = trader;
	}

	public TcCommodityType() {
	}

	public TcCommodityType(Long id) {
		this.id = id;
	}

	public TcCommodityType(Long id, String cid, Integer traderId, String tag, Integer kindId, String code,
			String barCode, String fullName, String name, String standard, String type1, String type2, String type3,
			String area, Integer recPrice, Integer sallPrice, BigDecimal margins, Integer qualityGuarantee, Byte unit,
			String pinyin, String comment, byte[] picture, Long createBy, Timestamp createTime, Long modifyBy,
			Timestamp modifyTime, Integer status, Integer total, TcCommodityKind commodityKind, TcTrader trader) {
		this.id = id;
		this.cid = cid;
		this.traderId = traderId;
		this.tag = tag;
		this.kindId = kindId;
		this.code = code;
		this.barCode = barCode;
		this.fullName = fullName;
		this.name = name;
		this.standard = standard;
		this.type1 = type1;
		this.type2 = type2;
		this.type3 = type3;
		this.area = area;
		this.recPrice = recPrice;
		this.sallPrice = sallPrice;
		this.margins = margins;
		this.qualityGuarantee = qualityGuarantee;
		this.unit = unit;
		this.pinyin = pinyin;
		this.comment = comment;
		this.picture = picture;
		this.createBy = createBy;
		this.createTime = createTime;
		this.modifyBy = modifyBy;
		this.modifyTime = modifyTime;
		this.status = status;
		this.total = total;
		this.commodityKind = commodityKind;
		this.trader = trader;
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

	public Integer getTraderId() {
		return this.traderId;
	}

	public void setTraderId(Integer traderId) {
		this.traderId = traderId;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Integer getKindId() {
		return this.kindId;
	}

	public void setKindId(Integer kindId) {
		this.kindId = kindId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBarCode() {
		return this.barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getType1() {
		return this.type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return this.type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getType3() {
		return this.type3;
	}

	public void setType3(String type3) {
		this.type3 = type3;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getRecPrice() {
		return this.recPrice;
	}

	public void setRecPrice(Integer recPrice) {
		this.recPrice = recPrice;
	}

	public Integer getSallPrice() {
		return this.sallPrice;
	}

	public void setSallPrice(Integer sallPrice) {
		this.sallPrice = sallPrice;
	}

	public BigDecimal getMargins() {
		return this.margins;
	}

	public void setMargins(BigDecimal margins) {
		this.margins = margins;
	}

	public Integer getQualityGuarantee() {
		return this.qualityGuarantee;
	}

	public void setQualityGuarantee(Integer qualityGuarantee) {
		this.qualityGuarantee = qualityGuarantee;
	}

	public Byte getUnit() {
		return this.unit;
	}

	public void setUnit(Byte unit) {
		this.unit = unit;
	}

	public String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getModifyBy() {
		return this.modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Timestamp getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public TcCommodityKind getCommodityKind() {
		return commodityKind;
	}

	public void setCommodityKind(TcCommodityKind commodityKind) {
		this.commodityKind = commodityKind;
	}
}