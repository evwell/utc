package com.tc.core.frame.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.tc.core.exception.BusinessException;
import com.tc.core.frame.vo.AbstractVO;
import com.tc.core.frame.vo.VO;
import static com.tc.core.util.CommonUtil.*;

import com.tc.vo.impl.TcCommodityKind;
import com.tc.vo.impl.TcCommodityType;
import com.tc.vo.impl.TcMenu;

public class JdbcDao<E extends VO>{

	private Logger logger = Logger.getLogger(this.getClass());
	
	private static JdbcDao<?> commonDao = null;
	
	private static CommonDatabaseTool commonDatabaseTool;
	
	private Connection connection = null;
	
	private PreparedStatement pstm = null;
	
	private Class<E> entityClass;
	
	private JdbcDao(){

	}
	
	private JdbcDao(Class<E> entityClass){
		logger.debug(log4jString("JdbcDao build"));
		this.entityClass = entityClass;
		logger.debug(log4jString("this.entityClass = " + this.entityClass));
	}
	
	public static JdbcDao newInstance(Class<?> entityClass) throws BusinessException{
		if(commonDao==null||commonDao.entityClass==null||!commonDao.entityClass.equals(entityClass)){
			commonDao = new JdbcDao(entityClass);
			commonDatabaseTool = CommonDatabaseTool.newInstance(DataBase.MySql);
		}
		return commonDao;
	}
	
	public List<VO> query(String sql, Map<String, Object> params) throws BusinessException {
		connection =  commonDatabaseTool.getConnection();
		List<VO> list = new ArrayList<VO>();
		Field[] fields = entityClass.getDeclaredFields();
		Object[] keys = null;
		Object[] entrys = null;
		if(params!=null&&params.size()>0){
			keys = params.keySet().toArray();
			entrys = params.entrySet().toArray();
		}
		VO vo = null;
		Map<String,Object> rsMap = null;
		try {
			logger.debug(log4jString("SQL :  " + sql));
			logger.debug(log4jString("params :  " + Arrays.toString(entrys)));
			pstm = connection.prepareStatement(sql);
			if(params!=null&&params.size()>0){
				for(int i=0;i<keys.length;i++){
					pstm.setObject(i+1, params.get(keys[i]));
				}
			}
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				vo = entityClass.newInstance();
				rsMap = new HashMap<String,Object>();
				Object rsObj=null;
				for(Field field : fields){
					if("serialVersionUID".equals(field.getName()))continue;
					if(Set.class.equals(field.getType())) continue; //跳过Set类型属性
					if(field.getType()!=null && AbstractVO.class.equals(field.getType().getSuperclass())) continue; //跳过AbstractVO子类型属性
					//logger.debug(log4jString(field.getName() + ":" + rs.getObject(field.getName())));
					try{
						rsObj =  rs.getObject(getTableFieldNameFromClassFieldName(field.getName()));
					}catch(SQLException e){
						e.printStackTrace();
						continue;
					}
					rsMap.put(field.getName(),rsObj);
				}
				vo = fillingEntity(vo, rsMap);
				list.add(vo);
			}
			 
		} catch (SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		} finally{
			try {
				connection.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		logger.info(log4jString("Rows:" + list.size()));
		return list;
	}


	public static void main(String[] args) {
		System.out.println(new TcCommodityKind());
//		try {
//			JdbcDao cd = JdbcDao.newInstance(TcCommodityType.class);
//			Map<String,Object> params = new HashMap<String,Object>();
//			params.put("status", 1);
//			List<VO> list = cd.query("select * from tc_commodity_type where status=?", params);
//			System.out.println(Arrays.toString(list.toArray()));
//		} catch (BusinessException e) {
//			e.printStackTrace();
//		}
//		
	}
}
