package com.tc.core.util;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMappingCustomer extends ObjectMapper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6149933792133152744L;
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public ObjectMappingCustomer() {
		super();
		this.setDateFormat(dateFormat);
		// 配置忽略空属性
		this.setSerializationInclusion(Include.NON_EMPTY);
		//当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
		//因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
		this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

}
