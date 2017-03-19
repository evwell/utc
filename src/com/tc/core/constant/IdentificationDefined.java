package com.tc.core.constant;

public enum IdentificationDefined {

	DATA_CACHE_KEY_USERINFO("USERINFO"),
	DATA_CACHE_KEY_USER("LIST_USER"),
	DATA_CACHE_KEY_SUPPLIER("LIST_SUPPLIER"),
	DATA_CACHE_KEY_CUSTOMER("LIST_CUSTOMER"), 
	DATA_CACHE_KEY_MENU("LIST_MENU"),
	DATA_CACHE_KEY_COMMODITYKIND("LIST_COMMODITYKIND"),
	DATA_CACHE_KEY_COMMODITYTYPE("LIST_COMMODITYTYPE"),
	DATA_CACHE_KEY_STOCK("LIST_STOCK"),

	LOG_CMD_CREATE("创建"), LOG_CMD_UPDATE("更新"), LOG_CMD_RETRIVE("读取"), LOG_CMD_DELETE("删除"),

	MALE(1), FEMALE(0),

	LOG_CONTENT_LENGTH(1999),

	USER_STATUS_AUDITING((byte)0), USER_STATUS_VALID((byte)1), USER_STATUS_PENDING((byte)2), USER_STATUS_INVALID((byte)3),

	USER_LEVEL_SYSTEM((byte)1), USER_LEVEL_ADMIN((byte)2), USER_LEVEL_EMPLOYEE((byte)3), USER_LEVEL_MEMBER((byte)4);

	private String StrValue;
	private int intValue;
	private byte byteValue;
	private float floatValue;
	private double doubleValue;
	private boolean booleanValue;
	private long longValue;
	private char charValue;
	private Object value;

	private IdentificationDefined(int intValue) {
		this.value = intValue;
		this.intValue = intValue;
	}

	private IdentificationDefined(String strValue) {
		this.value = strValue;
		this.StrValue = strValue;
	}

	private IdentificationDefined(byte byteValue) {
		this.value = byteValue;
		this.byteValue = byteValue;
	}
	
	private IdentificationDefined(float floatValue) {
		this.value = floatValue;
		this.floatValue = floatValue;
	}

	private IdentificationDefined(double doubleValue) {
		this.value = doubleValue;
		this.doubleValue = doubleValue;
	}

	private IdentificationDefined(long longValue) {
		this.value = longValue;
		this.longValue = longValue;
	}

	private IdentificationDefined(char charValue) {
		this.value = charValue;
		this.charValue = charValue;
	}
	
	private IdentificationDefined(Object value) {
		this.value = value;
	}
	
	public int getIntValue() {
		return intValue;
	}

	public String getStrValue() {
		return StrValue;
	}

	public byte getByteValue() {
		return byteValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public long getLongValue() {
		return longValue;
	}

	public char getCharValue() {
		return charValue;
	}
	
	public Object getValue(){
		return value;
	}
}
