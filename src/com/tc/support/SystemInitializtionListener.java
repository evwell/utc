package com.tc.support;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.tc.support.cache.SystemDataCache;

public class SystemInitializtionListener implements ServletContextListener {

	Logger logger = Logger.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("-------------->   system shutdown!   <--------------");
	}

	public void contextInitialized(ServletContextEvent sce) {
		logger.info("-------------->   system starting...   <--------------");
		logger.info("-------------->   initializing   <--------------");
		ServletContext servletContext = sce.getServletContext();
		
		SystemDataCache.newInstance(servletContext).init();
		
		servletContext.setAttribute("SYSTEM_NAME", "TC");
		logger.info("-------------->   initialize completed!   <--------------");
	}

}
