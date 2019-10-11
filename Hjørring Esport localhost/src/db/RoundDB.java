package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Round;

public class RoundDB implements RoundDBIF 
{
	
	private Connection connection;
	private static final String ADD_ROUND_Q = "insert into round(roundNumber, tournament_id) values (?, ?)";
	private PreparedStatement addRoundPS;
	private static final String GET_ID_Q = "select isnull (max(id), 0) from round where roundNumber = ? and tournament_id = ?";
	private PreparedStatement getIdPS;		
	
	public RoundDB() throws NoConnectionException
	{
		connection = DBConnection.getInstance().getConnection();
		prepareStatements();
	}
	
	private void prepareStatements() throws NoConnectionException
	{
		try 
		{
			addRoundPS = connection.prepareStatement(ADD_ROUND_Q);
			getIdPS = connection.prepareStatement(GET_ID_Q);
		} 
		catch (SQLException e) 
		{
			throw new NoConnectionException(e, "No Connection to the database.");
		}
	}

	public int addRound(Round round) throws MisMatchException, WrongInputException 
	{
		int roundId = -1;
		TournamentDBIF tournamentDB = new TournamentDB();
		try 
		{
			DBConnection.getInstance().startTransaction();
			int tournamentId = tournamentDB.getTournamentId();
			
			getIdPS.setInt(1, round.getRoundNumber());
			getIdPS.setInt(2, tournamentId);
			ResultSet rs1 = getIdPS.executeQuery();
			ResultSet rs;
			if(rs1.next())
			{
				if(rs1.getInt(1) == 0)
				{
					//add round to Database
					addRoundPS.setInt(1, round.getRoundNumber());
					addRoundPS.setInt(2, tournamentId);
					addRoundPS.executeUpdate();
					//get round ID
					getIdPS.setInt(1, round.getRoundNumber());
					getIdPS.setInt(2, tournamentId);
					rs = getIdPS.executeQuery();
					if(rs.next())
					{
						roundId = rs.getInt(1);
					}
				}
				else
				{
					getIdPS.setInt(1, round.getRoundNumber());
					getIdPS.setInt(2, tournamentId);
					rs = getIdPS.executeQuery();
					if(rs.next())
					{
						roundId = rs.getInt(1);
					}
				}
				DBConnection.getInstance().commitTransaction();
			} 
		}
		catch (SQLException e) 
		{
			try 
			{
				DBConnection.getInstance().rollbackTransaction();
			} 
			catch (SQLException e1) 
			{
				throw new WrongInputException(e1, "Fejl ved oprettelse af runde. Intet er oprettet.");
			}
			throw new MisMatchException(e, "Kunne ikke oprette runde.");
		}	
		return roundId;	
	}
}