package com.prometheus.hoc.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.prometheus.hoc.common.db.hibernate.SchemaUpdateHelper;
import com.prometheus.hoc.common.db.util.ConnectionConstants;

public class GlobalDBSchemaContextLisenter implements ServletContextListener{
	
	private static final Logger log = Logger.getLogger(GlobalDBSchemaContextLisenter.class);
	
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("start to update db schema");
		SchemaUpdateHelper.updateDatabase(ConnectionConstants.DB_MYSQL_CONNECTION_MASTER_SCHEMA);
	}

}
