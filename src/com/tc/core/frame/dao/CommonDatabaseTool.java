package com.tc.core.frame.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tc.core.exception.BusinessException;

enum DataBase{
	MySql("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/tc","root","1234"),
	oracle("","","",""),db2("","","",""),SQLserver("","","","");
	private String driverClass;
	private String url;
	private String user;
	private String password;
	private DataBase(String driverClass,String url,String user,String password){
		this.driverClass = driverClass;
		this.url = url;
		this.user = user;
		this.password = password;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
};

public class CommonDatabaseTool {

	private static CommonDatabaseTool commonDatabaseTool;
	
	private static DataBase database;
	
	private Connection connection;
	
	private CommonDatabaseTool(){}
	
	private CommonDatabaseTool(DataBase database){
		this.database = database;
	}
	
	public static CommonDatabaseTool newInstance(DataBase database) throws BusinessException{
		if(database==null) throw new BusinessException(" database is null ");
		if(commonDatabaseTool==null){
			commonDatabaseTool = new CommonDatabaseTool(database);
		}
		return commonDatabaseTool;
	}
	
	public Connection getConnection(){
		try {
			Class.forName(database.getDriverClass());
			connection = DriverManager.getConnection(database.getUrl(), database.getUser(), database.getPassword());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
