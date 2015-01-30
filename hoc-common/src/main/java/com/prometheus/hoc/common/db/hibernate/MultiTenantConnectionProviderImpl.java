package com.prometheus.hoc.common.db.hibernate;

import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;

public class MultiTenantConnectionProviderImpl implements
		MultiTenantConnectionProvider {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private final ConnectionProvider connectionProvider = ConnectionProviderUtils
			.buildConnectionProvider(ConnectionProviderUtils.MASTER_SCHEMA_NAME);

	public boolean isUnwrappableAs(Class unwrapType) {
		return ConnectionProvider.class.equals(unwrapType)
				|| MultiTenantConnectionProvider.class.equals(unwrapType)
				|| MultiTenantConnectionProviderImpl.class
						.isAssignableFrom(unwrapType);
	}

	public <T> T unwrap(Class<T> unwrapType) {
		if (isUnwrappableAs(unwrapType)) {
			return (T) this;
		} else {
			throw new UnknownUnwrapTypeException(unwrapType);
		}
	}

	public Connection getAnyConnection() throws SQLException {
		return connectionProvider.getConnection();
	}

	public Connection getConnection(String tenanantIdentifier)
			throws SQLException {
		final Connection connection = getAnyConnection();
		try {
			connection.createStatement().execute("USE " + tenanantIdentifier);
		} catch (SQLException e) {
			throw new HibernateException(
					"Could not alter JDBC connection to specified schema ["
							+ tenanantIdentifier + "]", e);
		}
		return connection;
	}

	public void releaseAnyConnection(Connection connection) throws SQLException {
		connectionProvider.closeConnection(connection);
	}

	public void releaseConnection(String tenantIdentifier, Connection connection) {
		try {
			connection.createStatement().execute(
					"USE " + ConnectionProviderUtils.MASTER_SCHEMA_NAME);
		} catch (SQLException e) {
			// on error, throw an exception to make sure the connection is not
			// returned to the pool.
			// your requirements may differ
			throw new HibernateException(
					"Could not alter JDBC connection to specified schema ["
							+ tenantIdentifier + "]", e);
		}
		try {
			connectionProvider.closeConnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean supportsAggressiveRelease() {
		return false;
	}

}
