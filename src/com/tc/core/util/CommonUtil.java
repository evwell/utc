package com.tc.core.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.vo.AbstractVO;
import com.tc.core.frame.vo.VO;
import com.tc.vo.impl.TcCommodityKind;

import aj.org.objectweb.asm.Type;

public class CommonUtil {

	static{
		ConvertUtils.register(new Converter(){
			public <T> T convert(Class<T> type, Object value) {
				T obj = null;
				try {
					obj = type.newInstance();
					BeanUtils.copyProperties(obj, value);
				} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				return obj;
			}}, TcCommodityKind.class);
	}
	
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	private static ConverterTC converter = new ConverterTC(){};
	private static String entityName;
	private static VO entity;
	private static Map<String, Object> map;
	private static DetachedCriteria dc;

	private static boolean isExistsVoName(String voName) {
		return (CommonUtil.entityName != null && CommonUtil.entityName.length() != 0 && voName.equals(voName)) ? true : false;
	}

	private static boolean isExistsVo(VO entity) {
		return (CommonUtil.entity != null && CommonUtil.entity.equals(entity)) ? true : false;
	}

	public static String log4jString(Object str) {
		return " ... " + str + " ... ";
	}

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static Integer toInteger(String value) throws BusinessException {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new BusinessException(e.getClass() + " ERROR @ the type of value is:" + value.getClass());
		}
	}

	/**
	 * 判断字符串是否是浮点数
	 */
	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			if (value.contains("."))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是数字
	 */
	public static boolean isNumber(String value) {
		return isInteger(value) || isDouble(value);
	}
	
	/**
	 * 实例化一个值对象，所有属性为null
	 * 
	 * @param entityName
	 *            com.tc.vo.impl.TC+entityName 组成值对象的类名
	 * @return
	 * @throws BusinessException
	 */
	public static VO createEntity(String entityName) throws BusinessException {
		Objects.requireNonNull(entityName);
		String clazz = null;
		if(entityName.startsWith("tc")){
			clazz = "com.tc.vo.impl.Tc" + entityName.substring(2);
		}else if(entityName.startsWith("Tc")){
			clazz = "com.tc.vo.impl." + entityName;
		}else {
			clazz = "com.tc.vo.impl.Tc" + entityName.substring(0, 1).toUpperCase() + entityName.substring(1);
		}
		try {
			entity = (VO) Class.forName(clazz).newInstance();
			return entity;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			throw new BusinessException(e.getClass() + " ERROR @ new a instance of vo!");
		}
	}

	/**
	 * 实例化一个值对象，并将一个Map对象的数据赋值给此对象
	 * 
	 * @param entityName
	 *            com.tc.vo.impl.TC + entityName 组成值对象的类名
	 * @param map
	 *            赋值变量，key值必须与VO对象的属性相同
	 * @return
	 * @throws BusinessException
	 */
	public static VO createEntityByMap(String entityName, Map<String, Object> map) throws BusinessException {
		String key = null;
		VO entity = createEntity(entityName);
		fillingEntityByMap(entity,map);
		return entity;
	}
	
	/**
	 * 将一个Map对象的数据赋值给此对象
	 * 
	 * @param entity
	 *             对象
	 * @param map
	 *            赋值变量，key值必须与VO对象的属性相同
	 * @return
	 * @throws BusinessException
	 */
	@Deprecated
	public static VO fillingEntity(VO entity, Map<String, Object> map) throws BusinessException {
		String key = null;
		if (entity != null && map!=null && map.size()>0) {
			try {
				Method[] methods = entity.getClass().getDeclaredMethods();
				for (Method method : methods) {
					if (method.getName().startsWith("set")) {
						String tmp = method.getName().substring(3);
						key = tmp.substring(0, 1).toLowerCase() + tmp.substring(1);
						Object obj = map.get(key);
						if (obj != null) {
							Field field = entity.getClass().getDeclaredField(key);
							if(Set.class == field.getType()) continue; //跳过Set类型属性
							if (AbstractVO.class == field.getType().getSuperclass()) {
								obj = fillingEntity(createEntity(field.getName()), (Map<String, Object>) map.get(key));// 递归
							}
							obj = converter.convert(method.getParameterTypes()[0],obj);
							method.invoke(entity, obj);
						}
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
				throw new BusinessException(e.getClass() + " ERROR @ set something of vo!");
			} finally {
				//logger.debug(log4jString(entity.getClass() + ":" + entity));
			}
		}
		return entity;
	}

	/**
	 * map的key与entity的属性名的相同（区分大小写）匹配对应赋值关系，不支持Set类型属性，直接跳过。
	 * support by BeanUtils
	 * @param entity
	 * @param map
	 * @throws BusinessException
	 */
	public static VO fillingEntityByMap(VO entity, Map<String,Object> map) throws BusinessException{
		Objects.requireNonNull(entity);
		Objects.requireNonNull(map);
		String key = null;
		if (map.size()>0) {
			try {
				PropertyDescriptor[] pds = Introspector.getBeanInfo(entity.getClass()).getPropertyDescriptors();
				for(PropertyDescriptor pd : pds){
					Method method = pd.getWriteMethod();
					if(method!=null){
						key = pd.getName();
						Object obj = map.get(key);
						if (obj != null) {
							if(Set.class == pd.getPropertyType()) continue; //跳过Set类型属性
							if (AbstractVO.class == pd.getPropertyType().getSuperclass()) {
								obj = fillingEntityByMap(createEntity(key), (Map<String, Object>) map.get(key));
							}
							BeanUtils.setProperty(entity, key, obj);
						}
					}
				}
			} catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	/**
	 * 根据一个实例VO对象，创建一个hibernate查询条件对象DetachedCriteria（等值条件），本方法只支持2层VO对象引用
	 * @param entity 查询条件数据
	 * @return
	 * @throws BusinessException
	 */
	public static DetachedCriteria buildCommonDetachedCriteria(VO entity) throws BusinessException {
		// if(isExistsVo(vo)&&dc!=null){
		// logger.debug(log4jString("DetachedCriteria is exists!"));
		// return CommonUtil.dc;
		// }else{
		dc = DetachedCriteria.forClass(entity.getClass());
		try {
			Method[] methods = entity.getClass().getDeclaredMethods();
			String fieldName = null;
			String propertyName = null;
			Object obj = null;
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					obj = method.invoke(entity, null);
					if (obj != null) {
						propertyName = fieldName = getFieldNameFromGetOrSetMethodName(method.getName());
						Field field = entity.getClass().getDeclaredField(fieldName);
						if (AbstractVO.class == field.getType().getSuperclass()) {
							Method[] methods1 = obj.getClass().getDeclaredMethods();
							Object obj1 = null;
							String propertyName1 = null;
							for (Method method1 : methods1) {
								if (method1.getName().startsWith("get")) {
									obj1 = method1.invoke(obj, null);
									if (obj1 != null) {
										propertyName1 = propertyName + "." + getFieldNameFromGetOrSetMethodName(method1.getName());
										dc.createCriteria(fieldName).add(Restrictions.eq(getFieldNameFromGetOrSetMethodName(method1.getName()), obj1));
//										Criterion ci = Restrictions.eq(propertyName1, obj1);
//										dc.add(ci);
									}
								}

							}
						} else {
							Criterion ci = Restrictions.eq(propertyName, obj);
							dc.add(ci);
						}
					}
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e) {
			e.printStackTrace();
			throw new BusinessException(e.getClass() + " ERROR @ buildDetachedCriteria!");
		}	finally {
			logger.debug(log4jString(dc));
		}
		// }
		return dc;
	}
	
	/**
	 * 根据一个实例VO对象，创建一个hibernate查询条件对象DetachedCriteria（等值条件），递归方法，支持多层VO对象引用
	 * @param dc 用VO创建的DetachedCriteria
	 * @param entity 查询条件数据
	 * @return
	 * @throws BusinessException
	 */
	public static DetachedCriteria buildCommonDetachedCriteriaRecursion(DetachedCriteria dc, VO entity) throws BusinessException {
		if(dc == null){
			dc = DetachedCriteria.forClass(entity.getClass());
		}
		try {
			Method[] methods = entity.getClass().getDeclaredMethods();
			String fieldName = null;
			String propertyName = null;
			Object obj = null;
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					obj = method.invoke(entity, null);
					if (obj != null) {
						propertyName = fieldName = getFieldNameFromGetOrSetMethodName(method.getName());
						Field field = entity.getClass().getDeclaredField(fieldName);
						if (AbstractVO.class == field.getType().getSuperclass()) {
							buildCommonDetachedCriteriaRecursion(dc.createCriteria(fieldName), (VO) obj);
						} else {
							Criterion ci = Restrictions.eq(propertyName, obj);
							dc.add(ci);
						}
					}
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchFieldException
				| SecurityException e) {
			e.printStackTrace();
			throw new BusinessException(e.getClass() + " ERROR @ buildDetachedCriteria!");
		}	finally {
			logger.debug(log4jString(dc));
		}
		return dc;
	}
 
	
	/**
	 * 根据VO已赋值的属性，创建一个包含HQL和对应value的对象
	 * @param entity
	 * @param condition
	 * @return HqlAndValues
	 * @throws BusinessException
	 */
	public static HqlAndValues buildHql(VO entity, String condition) throws BusinessException {
		String hql = null;
		Object[] values = null;
		ArrayList<Object> array = new ArrayList<Object>();
		StringBuffer hqlBuffer = new StringBuffer("from ");
		Objects.requireNonNull(entity);
		hqlBuffer.append(entity.getClass().getSimpleName()).append(" where ");
		// if(map!=null && map.size()!=0){
		// logger.debug(log4jString("the size of map:"+map.size()));
		// Object[] keys = map.keySet().toArray();
		// Field [] fields = vo.getClass().getDeclaredFields();
		// fields[1].getName();
		// for(int i=0;i<map.size();i++){
		// hqlBuffer.append(keys[i]).append(" = ? and ");
		// }
		// hql = hqlBuffer.substring(0, hqlBuffer.lastIndexOf("and"));
		// }else{
		logger.debug(log4jString(entity.toString()));
		try {
			Method[] methods = entity.getClass().getDeclaredMethods();
			Object obj = null;
			for (Method method : methods) {
				if (method.getName().startsWith("get")) {
					obj = method.invoke(entity, null);
					if (obj != null) {
						hqlBuffer.append(method.getName().substring(3, 4).toLowerCase())
								.append(method.getName().substring(4)).append(" = ? and ");
						array.add(obj);
					}
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new BusinessException(e.getClass() + "ERROR @ get something of vo!");
		}
		values = array.toArray();
		hql = hqlBuffer.toString();
		// }
		if (hql.endsWith("and ")) {
			hql = hql.substring(0, hql.lastIndexOf("and"));
		} else if (hql.endsWith("where ")) {
			hql = hql.substring(0, hql.lastIndexOf("where"));
		}
		if (condition != null && condition.length() != 0) {
			hql = hql + " and " + condition;
		}
		return new HqlAndValues(hql, values);
	}
	
	/**
	 * 生成单表UPDATE的HQL
	 * @param map 值对象字段对应的值数据
	 * @param keys 第一个keys[0]参数为值对象类名，后面的参数为条件字段名
	 * @return
	 * @throws BusinessException
	 */
	public static HqlAndValues buildUpdateHql(Map<String,Object> map, String[] keys) throws BusinessException {
		Objects.requireNonNull(map);
		String hql = null;
		ArrayList<Object> valueArray = new ArrayList<Object>();
		StringBuffer hb = new StringBuffer("update ").append(keys[0]).append(" set ");
		map.forEach((k,v)->{
			boolean s = true;
			int i = keys.length-1;
			while(i!=0){
				if(k.equals(keys[i])){
					s = false;
				}
				i--;
			}
			if(s){
				hb.append(k).append("=?,");
				valueArray.add(v);
			}
		});
		hql = hb.substring(0, hb.length()-1);
		StringBuffer c = new StringBuffer(" where ");
		int i = keys.length-1;
		while(i!=0){
			c.append(keys[i]).append("=? and ");
			valueArray.add(map.get(keys[i]));
			i--;
		}
		hql += c.substring(0, c.lastIndexOf("and"));
		return new HqlAndValues(hql, valueArray.toArray());
	}

	/**
	 * 通过属性名获取getter方法或者setter方法
	 * @param fieldName
	 * @param suffix
	 * @return
	 */
	public static String getGetOrSetMethodNameFromFieldName(String fieldName, String suffix) {
		if("get".equals(suffix)||"set".equals(suffix)){
			return suffix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(0);
		} else {return null;}
	}
	
	/**
	 * 通过getter方法或者setter方法获取 属性名
	 * @param methodName
	 * @return
	 */
	public static String getFieldNameFromGetOrSetMethodName(String methodName) {
		return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
	}
	
	public static String getTableFieldNameFromClassFieldName(String classFieldName){
		String[] name = classFieldName.split("(?<!^)(?=[A-Z])"); //正则式，以大写字母为分隔
		if(name.length==1)return name[0].toLowerCase();
		String tableFieldName = name[0].toLowerCase();
		for(int i=1;i<name.length;i++){
			tableFieldName += "_" + name[i].toLowerCase();
		}
		return tableFieldName;
	}

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		System.out.println(CommonUtil.getTableFieldNameFromClassFieldName("TddDD"));
		
		Object obj;
		Long a = new Long(22);
		obj = converter.convert(Integer.class, a);
		System.out.println(a.getClass() +"   " + obj.getClass());
		
		Map<String, Object> map = new HashMap<String, Object>(){
			{
				put("id", 12);
				put("commodityKind", new HashMap<String, Object>(){
					{put("pid", 123);}
				});
			}
		};
		try {
			VO vo = createEntity("commodityType");
			BeanUtils.copyProperties(vo, map);
			System.out.println(vo);
			DetachedCriteria d = CommonUtil.buildCommonDetachedCriteriaRecursion(null,vo);
			System.out.println(d.toString());
			System.out.println(buildUpdateHql(map,new String[]{"commodityType","id"}).hql);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
