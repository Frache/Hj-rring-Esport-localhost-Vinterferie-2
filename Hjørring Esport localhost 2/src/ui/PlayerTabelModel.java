package ui;

import java.util.ArrayList;
import java.util.List;

import model.*;
import javax.swing.table.AbstractTableModel;

public class PlayerTabelModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Player> players;
	private static final String[] COL_NAMES = { "Fornavn", "Efternavn" };
	
	public PlayerTabelModel()
	{
		setPlayers(null);
	}

	@Override
	public int getColumnCount() 
	{
		
		return COL_NAMES.length;
	}

	@Override
	public String getColumnName(int column)
	{
		return COL_NAMES[column];
	}

	@Override
	public int getRowCount() 
	{
			return players.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		Player p = players.get(row);
		switch(column)
		{
		case 0:
			return p.getFname();
		case 1:
			return p.getLname();
		default:
			return "Ingen spiller fundet.";
		}
		
	}
	
	public void setPlayers(List<Player> players)
	{
		if (players != null)
		{
			this.players = players;
		}
		else
		{
			this.players = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}
	
	public Player getSelectedPlayer(int index)
	{
		if(index < 0 || index >= players.size())
		{
			return null;
		}
		return players.get(index);
	}
}
