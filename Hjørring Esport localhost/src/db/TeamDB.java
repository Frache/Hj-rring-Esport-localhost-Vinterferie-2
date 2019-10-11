package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import model.Team;

public class TeamDB implements TeamDBIF 
{
	Connection connection;
	private final static String FIND_ALL_TEAMS_Q = "select name, teamTag, id from team where tournament_id = (?)";
	private PreparedStatement findAllTeamsPS;
	private final static String ADD_TEAM_Q = "insert into team(name, teamTag, tournament_id) values (?, ?, ?)";
	private PreparedStatement addTeamPS;
	private final static String DELETE_TEAM_Q = "delete from team where id = ?";
	private PreparedStatement deleteTeamPS;
	private final static String UPDATE_TEAM_Q = "update team set name = ?, teamTag = ? where id = ?";
	private PreparedStatement updateTeamPS;
	private final static String SET_GROUP_NUMBER_Q = "update team set groupNumber = ? where id = ?";
	private PreparedStatement setGroupNumberPS;
	private final static String UPDATE_TEAM_RESULT_Q = "update team set amountOfMatches = ?, wins = ?, losses = ?, roundsWon = ?, roundsLost = ?, groupNumber = ? where id = ?";
	private PreparedStatement updateTeamResultPS;
	
	
	public TeamDB()
	{
		connection = DBConnection.getInstance().getConnection();
		prepareStatements();
	}

	private void prepareStatements() 
	{
		try 
		{
			findAllTeamsPS = connection.prepareStatement(FIND_ALL_TEAMS_Q);
			addTeamPS = connection.prepareStatement(ADD_TEAM_Q);
			deleteTeamPS = connection.prepareStatement(DELETE_TEAM_Q);
			updateTeamPS = connection.prepareStatement(UPDATE_TEAM_Q);
			setGroupNumberPS = connection.prepareStatement(SET_GROUP_NUMBER_Q);
			updateTeamResultPS = connection.prepareStatement(UPDATE_TEAM_RESULT_Q);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	public ArrayList<Team> findAllTeams() throws NoConnectionException, MisMatchException 
	{
		TournamentDBIF tournamentDB = new TournamentDB();
		int tournamentId = -1;
		ResultSet rs = null;
		ArrayList<Team> teams = null;
		try 
		{
			tournamentId = tournamentDB.getTournamentId();
			findAllTeamsPS.setInt(1, tournamentId);
			rs = findAllTeamsPS.executeQuery();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			teams = buildObjects(rs);
		} 
		catch (SQLException e) 
		{
			throw new NoConnectionException(e, "Kunne ikke hente hold fra databasen.");
		}
		return teams;
	}

	private ArrayList<Team> buildObjects(ResultSet rs) throws SQLException 
	{
		ArrayList<Team> res = new ArrayList<>();
		while (rs.next())
		{
			res.add(buildObject(rs));
		}
		return res;
	}


	private Team buildObject(ResultSet rs) throws SQLException 
	{
		Team team = new Team(rs.getString("name"), rs.getString("teamTag"), rs.getInt("id"));
		return team;
	}
		
	public void createTeam(Team team) throws NoConnectionException, MisMatchException
	{
		TournamentDBIF tournamentDB = new TournamentDB();
		try 
		{
			int tournamentId = tournamentDB.getTournamentId();
			addTeamPS.setString(1, team.getName());
			addTeamPS.setString(2, team.getTeamTag());
			addTeamPS.setInt(3, tournamentId);
			addTeamPS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new NoConnectionException(e, "Ingen forbindelse til databasen.");
		}		
	}


	public void removeTeam(Team team) throws WrongInputException, NoConnectionException, MisMatchException 
	{
		MatchDB matchDB = new MatchDB();
		matchDB.setTeamTwoIdNull(team.getId());
		try {
			deleteTeamPS.setInt(1, team.getId());
			deleteTeamPS.executeUpdate();
		} catch (SQLException e) {
			throw new WrongInputException(e, "Intet hold med dette ID findes ikke i databasen.");
		}
	}	
	
	public void updateTeam(String name, String teamTag, int id) throws WrongInputException
	{
		try 
		{
			updateTeamPS.setString(1, name);
			updateTeamPS.setString(2, teamTag);
			updateTeamPS.setInt(3, id);
			updateTeamPS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new WrongInputException(e, "Intet hold med dette ID findes ikke i databasen.");
		}
	}
	
	public void updateGroupNumbers(ArrayList<Team> teams) throws MisMatchException
	{
		try 
		{
			DBConnection.getInstance().startTransaction();
			for(Team team: teams)
			{	
				setGroupNumberPS.setInt(1, team.getGroupNumber());
				setGroupNumberPS.setInt(2, team.getId());
				setGroupNumberPS.executeUpdate();
			}
			DBConnection.getInstance().commitTransaction();
		}
		catch (SQLException e) 
		{
			try 
			{
				DBConnection.getInstance().rollbackTransaction();
				throw new MisMatchException(e, "Kunne ikke opdatere grupper i databasen.");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}	
		}	
	}
	public void resetTeamVariables(ArrayList<Team> teams) throws MisMatchException
	{
		try
		{
			DBConnection.getInstance().startTransaction();
			for(Team team: teams)
			{
				updateTeamResultPS.setInt(1, 0);
				updateTeamResultPS.setInt(2, 0);
				updateTeamResultPS.setInt(3, 0);
				updateTeamResultPS.setInt(4, 0);
				updateTeamResultPS.setInt(5, 0);
				updateTeamResultPS.setInt(6, 0);
				updateTeamResultPS.setInt(7, team.getId());
				updateTeamResultPS.executeUpdate();	
			}
			DBConnection.getInstance().commitTransaction();
		}
		catch (SQLException e) 
		{
			try 
			{
				DBConnection.getInstance().rollbackTransaction();
				throw new MisMatchException(e, "Kunne ikke gemme data om hold i databasen.");
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void updateTeamVariables(ArrayList<Team> teams) throws MisMatchException 
	{
		try
		{
			DBConnection.getInstance().startTransaction();
			for(Team team: teams)
			{
				updateTeamResultPS.setInt(1, team.getAmountOfMatches());
				updateTeamResultPS.setInt(2, team.getWins());
				updateTeamResultPS.setInt(3, team.getLosses());
				updateTeamResultPS.setInt(4, team.getRoundsWon());
				updateTeamResultPS.setInt(5, team.getRoundsLost());
				updateTeamResultPS.setInt(6, team.getGroupNumber());
				updateTeamResultPS.setInt(7, team.getId());
				updateTeamResultPS.executeUpdate();
			}
		}
		catch(SQLException e)
		{
			try
			{
				DBConnection.getInstance().rollbackTransaction();
				throw new MisMatchException(e, "Holdet kunne ikke opdateres i databasen.");
			}
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
		}
	}
}