package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Team;

public class TeamTableModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;
	private List<Team> teams;
	private static final String[] COL_NAMES = { "Holdnavn", "TeamTag" };
	
	public TeamTableModel() 
	{
		setTeams(null);
	}

	@Override
	public int getColumnCount() {
		return COL_NAMES.length;
	}
	
	@Override
	public String getColumnName(int column)
	{
		return COL_NAMES[column];
	}

	@Override
	public int getRowCount() {
		return teams.size();
	}

	@Override
	public Object getValueAt(int row, int column) 
	{
		Team t = teams.get(row);
		switch(column)
		{
		case 0:
			return t.getName();
		case 1:
			return t.getTeamTag();
		default:
			return "Intet hold fundet.";
		}
	}
	
	public void setTeams(List<Team> teams) {
		if (teams != null) {
			this.teams = teams;
		} else {
			this.teams = new ArrayList<>(0);
		}
		super.fireTableDataChanged();
	}
	
	public Team getSelectedTeam(int index)
	{
		if(index < 0 || index >= teams.size())
		{
			return null;
		}
		return teams.get(index);
	}
	
	
}
