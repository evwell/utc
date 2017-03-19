package com.tc.core.util;

public class HqlAndValues {

	public String hql;
	public Object[] values;
	
	HqlAndValues(String hql, Object[] values){
		this.hql = hql;
		this.values = values;
	}
}
