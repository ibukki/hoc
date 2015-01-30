package com.prometheus.hoc.common.db.hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.Entity;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import com.prometheus.hoc.common.db.util.ConnectionConstants;

@SuppressWarnings("deprecation")
public class SchemaUpdateHelper {

	private static final String SQL_CHECK_SCHEMA_EXIST = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = ? ";

	private static final String SQL_CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS ";

	public static void updateDatabase(String schema) {
		
		try {
			//create the database schema if not exist
			int rcode = createSchemaIfNotExistForMySQL(schema);
			
			if(rcode > 0){
				
				AnnotationConfiguration cfg = new AnnotationConfiguration();
				// cfg.configure("hibernate.cfg.xml");
				Properties props = new Properties();
				props.put(Environment.DIALECT,
						ConnectionConstants.DB_MYSEQL_CONNECTION_DIALECT);
				props.put(Environment.URL,
						ConnectionConstants.DB_MYSQL_CONNECTION_BASE_URL + composeSchemaName(schema));
				props.put(Environment.DRIVER,
						ConnectionConstants.DB_MYSQL_CONNECTION_DRIVER);
				props.put(Environment.USER,
						ConnectionConstants.DB_MYSQL_CONNECTION_USER);
				props.put(Environment.PASS,
						ConnectionConstants.DB_MYSQL_CONNECTION_PASS);
				ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
						false);
				scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
				for (BeanDefinition bd : scanner
						.findCandidateComponents("com.prometheus.hoc")) {
					String name = bd.getBeanClassName();
					System.out.println(name);
					try {
						cfg.addAnnotatedClass(Class.forName(name));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				cfg.addProperties(props);
				SchemaUpdate update = new SchemaUpdate(cfg);
				update.setDelimiter(";");
				update.setHaltOnError(true);
//				update.setOutputFile("src/main/resources/update.sql");
				update.setFormat(true);
				update.execute(true, true);
				
			}else{
				System.out.println("create schema failed");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
	}

	private static String composeSchemaName(String schemaName) {
		if (!schemaName
				.startsWith(ConnectionConstants.DB_MYSQL_CONNECTION_MASTER_SCHEMA)) {
			return ConnectionConstants.DB_MYSQL_CONNECTION_MASTER_SCHEMA + "_"
					+ schemaName;
		} else {
			return schemaName;
		}
	}

	private static boolean isSchemaNameValid(String schemaName) {
		return schemaName.matches("[a-zA-Z0-9|_]+");
	};

	private static int createSchemaIfNotExistForMySQL(String schemaName)
			throws ClassNotFoundException, SQLException {
		int rcode = -1;
		if (!isSchemaNameValid(schemaName)) {
			return rcode;
		}

		Class.forName(ConnectionConstants.DB_MYSQL_CONNECTION_DRIVER);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(
					ConnectionConstants.DB_MYSQL_CONNECTION_BASE_URL,
					ConnectionConstants.DB_MYSQL_CONNECTION_USER,
					ConnectionConstants.DB_MYSQL_CONNECTION_PASS);
			ps = conn.prepareStatement(SQL_CHECK_SCHEMA_EXIST);
			ps.setString(1, composeSchemaName(schemaName));

			rs = ps.executeQuery();
			String dbschema = "";
			while (rs.next()) {
				dbschema = rs.getString(1);
				rcode = 1;
			}
			if (StringUtils.isEmpty(dbschema)) {
				// create a new schema
				rcode = conn.createStatement().executeUpdate(
						SQL_CREATE_SCHEMA + composeSchemaName(schemaName));
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return rcode;
	}

	public static void main(String[] args) {
		try {
			SchemaUpdateHelper.updateDatabase("comp1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
