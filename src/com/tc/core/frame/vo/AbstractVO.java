package com.tc.core.frame.vo;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

public abstract class AbstractVO implements VO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3791420111453560153L;

	
	Logger logger = Logger.getLogger(this.getClass());

	public String toString() {
		StringBuffer str = new StringBuffer("{");
		Object obj = null;
		try {
			PropertyDescriptor[] pds = Introspector.getBeanInfo(this.getClass()).getPropertyDescriptors();
			for(PropertyDescriptor pd : pds){
				if("class".equals(pd.getName()))continue;
				if(pd.getReadMethod()==null){
					//logger.debug("pd.getName() = " + pd.getName());
					continue;
					}
				obj = pd.getReadMethod().invoke(this);
				if(obj != null){
					str.append("\"").append(pd.getName()).append("\":\"");
					str.append(pd.getReadMethod().invoke(this)).append("\",");
				}
			}
		} catch (IntrospectionException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} 
		if(str.length()==1){
			return str.toString() + "}";
		}else{
			return str.substring(0, str.lastIndexOf(",")) + "}";
		}
	}
	
	private Object objectValue(Object o,Field field) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String methodName = new StringBuffer("get").append(field.getName().substring(0, 1).toUpperCase()).append(field.getName().substring(1)).toString();
		Object ro = this.getClass().getMethod(methodName, null).invoke(o, null);
		return ro;
	}
	
	public boolean equals(Object obj) {
		if ((this == obj))return true;
		if ((obj == null))return false;
		if ((obj.getClass().getGenericSuperclass()==null))return false;
		if (!(obj.getClass().getGenericSuperclass()==AbstractVO.class))return false;
	    if(getClass()!=obj.getClass()){return false;}else{
	    	for (Field field : this.getClass().getDeclaredFields()) {
				if ("serialVersionUID".equals(field.getName())) {
					continue;
				}
				try {
					if(!objectValue(this,field).equals(objectValue(obj,field))){
						return false;
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					return false;
				}
			}
	    }
	    return true;
}
}
