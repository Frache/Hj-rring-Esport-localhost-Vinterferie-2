package controller;

import db.ConnectionThreadDB;
import db.NoConnectionException;

public class ConnectionController 
{
	ConnectionThreadDB connectionThreadDB;
	
	public ConnectionController() throws NoConnectionException
	{
		connectionThreadDB = new ConnectionThreadDB();
	}
	
	public boolean isConnected() throws NoConnectionException
	{
		return connectionThreadDB.isConnected();
	}
}
