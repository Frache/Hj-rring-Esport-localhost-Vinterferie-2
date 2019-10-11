package db;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DBConnection 
{
	private Connection connection = null;
	private static DBConnection dbConnection;
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "HJEsport";
	private static final String serverAddress = "localhost";
	private static final int    serverPort = 1433;
	private static final String userName = "XXX";
	private static final String password = "XXXXX";
	
	private DBConnection() 
	{
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", 
													serverAddress, serverPort, dbName, userName, password);
		try 
		{
			Class.forName(driverClass);
			connection = DriverManager.getConnection(connectionString);
		} 
		catch (ClassNotFoundException e) 
		{
			System.err.println("Could not load JDBC driver");
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			System.err.println("Could not connect to database " + dbName + "@" + serverAddress + ":" + serverPort + " as user " + userName + " using password ****");
			System.out.println("Connection string was: " + connectionString.substring(0, connectionString.length() - password.length()) + "....");
			e.printStackTrace();
		}
	}
	
	public static DBConnection getInstance() 
	{
		if(dbConnection == null) 
		{
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}
	
	public void startTransaction() throws SQLException 
	{
		connection.setAutoCommit(false);
	}
	
	public void commitTransaction() throws SQLException 
	{
		connection.commit();
		connection.setAutoCommit(true);
	}
	
	public void rollbackTransaction() throws SQLException 
	{
		connection.rollback();
		connection.setAutoCommit(true);
	}
	
	
	public int executeUpdate(String sql) throws SQLException 
	{
		System.out.println("DBConnection, Updating: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement())
		{
			res = s.executeUpdate(sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw e;
		}
		return res;
	}
	
	
	public Connection getConnection() 
	{
		return connection;
	}
	
	public void disconnect() 
	{
		try
		{
			connection.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void connect()
	{
		try 
		{
			if(connection.isClosed())
			{
				String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", 
						serverAddress, serverPort, dbName, userName, password);
				connection = DriverManager.getConnection(connectionString);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean hasConnection()
	{
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s", 
				serverAddress, serverPort, dbName, userName, password);
		try 
		{
			connection = DriverManager.getConnection(connectionString);
			
		} catch (SQLException e) 
		{
			return false;
		}
		return true;
	}
}