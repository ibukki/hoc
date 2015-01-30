package com.prometheus.hoc.common.db.hibernate;

import java.util.Properties;

import org.hibernate.cfg.Environment;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;

import com.prometheus.hoc.common.db.util.ConnectionConstants;

public class ConnectionProviderUtils {
	
	public static final String DRIVER = ConnectionConstants.DB_MYSQL_CONNECTION_DRIVER;
	public static final String URL = ConnectionConstants.DB_MYSQL_CONNECTION_BASE_URL;
	public static final String USER = ConnectionConstants.DB_MYSQL_CONNECTION_USER;
	public static final String PASS = ConnectionConstants.DB_MYSQL_CONNECTION_PASS;
	public static final String MASTER_SCHEMA_NAME=ConnectionConstants.DB_MYSQL_CONNECTION_MASTER_SCHEMA;

	public static Properties getConnectionProviderProperties(String schema) {
		Properties props = new Properties( null );
		props.put( Environment.DRIVER, DRIVER );
		props.put( Environment.URL, URL + schema );
		props.put( Environment.USER, USER );
		props.put( Environment.PASS, PASS );
		return props;
	}
	
	public static Properties getConnectionProviderProperties() {
		return getConnectionProviderProperties( MASTER_SCHEMA_NAME );
	}
	
	public static DriverManagerConnectionProviderImpl buildConnectionProvider() {
		return buildConnectionProvider( false );
	}

	public static DriverManagerConnectionProviderImpl buildConnectionProvider(String schema) {
		return buildConnectionProvider( getConnectionProviderProperties( schema ), false );
	}

	public static DriverManagerConnectionProviderImpl buildConnectionProvider(final boolean allowAggressiveRelease) {
		return buildConnectionProvider( getConnectionProviderProperties( MASTER_SCHEMA_NAME ), allowAggressiveRelease );
	}

	private static DriverManagerConnectionProviderImpl buildConnectionProvider(Properties props, final boolean allowAggressiveRelease) {
		DriverManagerConnectionProviderImpl connectionProvider = new DriverManagerConnectionProviderImpl() {
			public boolean supportsAggressiveRelease() {
				return allowAggressiveRelease;
			}
		};
		connectionProvider.configure( props );
		return connectionProvider;
	}
}
