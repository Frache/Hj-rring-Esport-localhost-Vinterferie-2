package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

import model.Match;
import model.Round;

public class MatchDB implements MatchDBIF 
{
	private static final String CREATE_MATCH_Q = "insert into match(teamOne_id, teamTwo_id, round_id) values (?, ?, ?)";
	private PreparedStatement createMatchPS;
	private static final String UPDATE_MATCH_Q = "update match set teamOneScore = ?, teamTwoScore = ? where teamOne_id = ? and teamTwo_id = ? and round_id = ?";
	private PreparedStatement updateMatchPS;
	private static final String SET_TEAM_TWO_ID_NULL_Q = "update match set teamTwo_id = null where teamTwo_id = ?";
	private PreparedStatement setTeamTwoIdNullPS;
	private static final String DELETE_ALL_MATCHES_Q = "delete from match";
	private PreparedStatement deleteAllMatchesPS;
	
	private Connection connection;
	
	public MatchDB() throws NoConnectionException
	{
		connection = DBConnection.getInstance().getConnection();
		prepareStatements();
	}

	private void prepareStatements() throws NoConnectionException 
	{
		try 
		{
			createMatchPS = connection.prepareStatement(CREATE_MATCH_Q);
			updateMatchPS = connection.prepareStatement(UPDATE_MATCH_Q);
			setTeamTwoIdNullPS = connection.prepareStatement(SET_TEAM_TWO_ID_NULL_Q);
			deleteAllMatchesPS = connection.prepareStatement(DELETE_ALL_MATCHES_Q);
		} 
		catch (SQLException e) 
		{
			throw new NoConnectionException(e, "Der er ikke forbindelse til databasen.");
		}		
	}
	
	public void addMatchToDatabase(Match m, int roundId) throws WrongInputException
	{
		try 
		{
			createMatchPS.setInt(1, m.getTeamOne().getId());
			createMatchPS.setInt(2, m.getTeamTwo().getId());
			createMatchPS.setInt(3, roundId);
			createMatchPS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new WrongInputException(e, "Holdene blev ikke fundet i databasen.");
		}
	}
	
	public void addMatchesToDatabase(ArrayList<Match> matches, int roundId) throws WrongInputException, NoConnectionException
	{
		try 
		{
			try 
			{
				DBConnection.getInstance().startTransaction();
				for(Match m: matches)
				{
					addMatchToDatabase(m, roundId);
				}
				DBConnection.getInstance().commitTransaction();
			}
			catch(SQLException e)
			{
				DBConnection.getInstance().rollbackTransaction();
			}
		}
		catch(SQLException e)
		{
			throw new NoConnectionException(e, "Fejl i forbindelse til database.");
		}
	}

	public void updateResultInDatabase(Match match, Round round) throws MisMatchException 
	{
		try 
		{
			updateMatchPS.setInt(1, match.getTeamOneScore());
			updateMatchPS.setInt(2, match.getTeamTwoScore());
			updateMatchPS.setInt(3, match.getTeamOne().getId());
			updateMatchPS.setInt(4, match.getTeamTwo().getId());
			updateMatchPS.setInt(5, round.getId());
			updateMatchPS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new MisMatchException(e, "Kunne ikke opdatere kampen i databasen.");
		}
	}
	
	public void setTeamTwoIdNull(int id) throws MisMatchException
	{
		try 
		{
			setTeamTwoIdNullPS.setInt(1, id);
			setTeamTwoIdNullPS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new MisMatchException(e, "Intet hold tilsvarende det pågældende ID i databasen.");
		}
	}

	
	public void deleteAllMatches() 
	{
		try
		{
			deleteAllMatchesPS.executeUpdate();
		}
		catch(SQLException e)
		{
			try 
			{
				throw new NoConnectionException(e, "Ingen forbindelse til databasen.");
			} 
			catch (NoConnectionException e1) 
			{
				e1.printStackTrace();
			}
		}
		
	}
}