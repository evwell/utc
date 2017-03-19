package com.tc.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.vo.impl.TcUser;

/**
 * The class JacksonUtil 使用泛型方法，把json字符串转换为相应的JavaBean对象。
 * (1)转换为普通JavaBean：readValue(json,Student.class) (2)转换为List,如List
 * <Student>,将第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
 * 
 * json字符与对像转换
 */
public final class JacksonUtil {

	public static ObjectMapper objectMapper;
	
	static{
		if(objectMapper == null){
			objectMapper = new ObjectMapper();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			objectMapper.setDateFormat(dateFormat);
			// 为了使JSON视觉上的可读性，增加一行如下代码，注意，在生产中不需要这样，因为这样会增大Json的内容
			//objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			// 配置忽略空属性
			objectMapper.setSerializationInclusion(Include.NON_EMPTY);
			
			//当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
			//因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
			objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		}
	}

	/**
	 * json对象转JAVA对象
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T readValue(String jsonStr, Class<T> valueType) {
		try {
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * json数组转List
	 * 
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) {
		try {
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJSon(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		TcUser tcUser = new TcUser();
		tcUser.setUserName("admin");
		tcUser.setPassword("123");
		tcUser.setCreateTime(new Timestamp(System.currentTimeMillis()));
		System.out.println(JacksonUtil.toJSon(tcUser));
		
		System.out.println(JacksonUtil.readValue("{\"password\":123}", TcUser.class).getPassword());
	}
}