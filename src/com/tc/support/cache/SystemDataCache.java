package com.tc.support.cache;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.tc.core.constant.IdentificationDefined;
import com.tc.core.exception.BusinessException;
import com.tc.core.frame.dao.JdbcDao;
import com.tc.vo.impl.TcCommodityKind;
import com.tc.vo.impl.TcCommodityType;
import com.tc.vo.impl.TcMenu;
import com.tc.vo.impl.TcStock;
import com.tc.vo.impl.TcTrader;
import com.tc.vo.impl.TcUser;

public class SystemDataCache {
	
	private Logger logger = Logger.getLogger(getClass());
	
	private JdbcDao<?> jdbcDao;
	
	private ServletContext servletContext;
	
	private static SystemDataCache systemDataCache;
	
	private SystemDataCache(){}
	
	private SystemDataCache(ServletContext servletContext){
		this.servletContext = servletContext;
	}
	
	public static SystemDataCache newInstance(ServletContext servletContext){
		if(systemDataCache==null){
			systemDataCache = new SystemDataCache(servletContext);
		}
		return systemDataCache;
	}

	public void init() {
		try {
			trader();
			user();
			menu();
			commodityKind();
			commodityType();
			stock();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	public void refreshAttribute(String attr,Object obj) {
		if(attr!=null&&attr.length()>0&&obj!=null)servletContext.setAttribute(attr, obj);
	}

	public void setAttribute(String attr,Object obj) {
		if(attr!=null&&attr.length()>0)servletContext.setAttribute(attr, obj);
	}

	private void trader() throws BusinessException{
		logger.info("-------------->   供应商数据导入    <--------------");
		jdbcDao = JdbcDao.newInstance(TcTrader.class);
		String traderSql = "select * from tc_trader where status=1 and trade_type=?";
		Map<String,Object> supplierParams = new HashMap<String,Object>();
		supplierParams.put("tradeType", 1); //供应商
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_SUPPLIER.getStrValue(), jdbcDao.query(traderSql, supplierParams));
		logger.info("-------------->   供应商数据导入完成    <--------------");
		logger.info("-------------->   客户数据导入   <--------------");
		Map<String,Object> customerParams = new HashMap<String,Object>();
		customerParams.put("tradeType", 2); //客户
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_CUSTOMER.getStrValue(), jdbcDao.query(traderSql, customerParams));
		logger.info("-------------->   客户数据导入完成   <--------------");
	}
	
	private void user() throws BusinessException{
		logger.info("-------------->   用户数据导入    <--------------");
		jdbcDao = JdbcDao.newInstance(TcUser.class);
		String sql = "select * from tc_user where status=1";
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_USER.getStrValue(), jdbcDao.query(sql, null));
		logger.info("-------------->   用户数据导入完成    <--------------");
	}
	
	private void menu() throws BusinessException{
		logger.info("-------------->   菜单数据导入    <--------------");
		jdbcDao = JdbcDao.newInstance(TcMenu.class);
		String sql = "select * from tc_menu where status=1";
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_MENU.getStrValue(), jdbcDao.query(sql, null));
		logger.info("-------------->   菜单数据导入完成    <--------------");
	}
	
	private void commodityKind() throws BusinessException{
		logger.info("-------------->   商品分类数据导入    <--------------");
		jdbcDao = JdbcDao.newInstance(TcCommodityKind.class);
		String sql = "select * from tc_commodity_kind";
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_COMMODITYKIND.getStrValue(), jdbcDao.query(sql, null));
		logger.info("-------------->   商品分类数据导入完成    <--------------");
	}
	
	private void commodityType() throws BusinessException{
		logger.info("-------------->   商品类型数据导入    <--------------");
		jdbcDao = JdbcDao.newInstance(TcCommodityType.class);
		String sql = "select * from tc_commodity_type where status=1";
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_COMMODITYTYPE.getStrValue(), jdbcDao.query(sql, null));
		logger.info("-------------->   商品类型数据导入完成    <--------------");
	}
	
	private void stock() throws BusinessException{
		logger.info("-------------->   仓库数据导入    <--------------");
		jdbcDao = JdbcDao.newInstance(TcStock.class);
		String sql = "select * from tc_stock where status=1";
		servletContext.setAttribute(IdentificationDefined.DATA_CACHE_KEY_STOCK.getStrValue(), jdbcDao.query(sql, null));
		logger.info("-------------->   仓库数据导入完成    <--------------");
	}
}
