package com.tc.core.util;

/**
 * data type converter
 * @author Evwell
 *
 */

public interface ConverterTC {

	/**
	 * 基本数据类型转换
	 * @param clazz 转换的数据类型
	 * @param value 被转换的对象
	 * @return
	 */
	default Object convert(Class<?> clazz,Object value){
		String type = clazz.getName();
		if(value==null||type.equals(value.getClass().getName())) return value; //类型一样直接返回
		//logger.debug(log4jString(convert + "==>" + convert.getClass().getName() + " CONVERT TO " + type));
		//基本数据类型
		if(type.equals("btye")||type.equals("java.lang.Byte")) return Byte.valueOf(String.valueOf(value));
		if(type.equals("short")||type.equals("java.lang.Short")) return Short.valueOf(String.valueOf(value));
		if(type.equals("int")||type.equals("java.lang.Integer")) return Integer.valueOf(String.valueOf(value));
		if(type.equals("long")||type.equals("java.lang.Long")) return Long.valueOf(String.valueOf(value));
		if(type.equals("float")||type.equals("java.lang.Float")) return Float.valueOf(String.valueOf(value));
		if(type.equals("double")||type.equals("java.lang.Double")) return Double.valueOf(String.valueOf(value));
		if(type.equals("char")||type.equals("java.lang.Character")) return String.valueOf(value).toCharArray()[0];
		if(type.equals("boolean")||type.equals("java.lang.Boolean")) return Boolean.valueOf(String.valueOf(value));
		if(type.equals("java.lang.String")) return String.valueOf(value);
		//if(type.equals("java.util.Date")) return (java.util.Date)convert;
		//if(type.equals("java.sql.Date")) return (java.sql.Date)convert;
		return value;
	}
}
