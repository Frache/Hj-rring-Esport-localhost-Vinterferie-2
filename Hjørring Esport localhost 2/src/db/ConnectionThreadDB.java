package db;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionThreadDB
{
	boolean connected;
	
	public ConnectionThreadDB() throws NoConnectionException
	{
		 isConnected();
	}
	
	public boolean isConnected() throws NoConnectionException
	{
		return DBConnection.getInstance().hasConnection();
	}
}
