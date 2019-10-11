package ui;

import javax.swing.JOptionPane;

import controller.ConnectionController;
import db.NoConnectionException;

public class ConnectionUI extends Thread 
{
	ConnectionController connectionController;
	UI ui;
	
	public ConnectionUI(UI ui)
	{
		this.ui = ui;
		try 
		{
			connectionController = new ConnectionController();
		} 
		catch (NoConnectionException e) 
		{
			JOptionPane.showMessageDialog(ui, e.getErrorMessage(), "Info", JOptionPane.OK_OPTION);
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		while(true)
		{
			try 
			{
				ui.connected(connectionController.isConnected());
				Thread.sleep(2000);
			} 
			catch (NoConnectionException e) 
			{
				JOptionPane.showMessageDialog(ui, e.getErrorMessage(), "Info", JOptionPane.OK_OPTION);
				e.printStackTrace();
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
}	
