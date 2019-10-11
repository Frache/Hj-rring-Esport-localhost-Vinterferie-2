package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.sql.*;

import model.Game;
import model.Tournament;

public class TournamentDB implements TournamentDBIF 
{
	private Connection connection;
	private static final String ADD_TOURNAMENT_Q = "insert into tournament(name, date, rounds, currentRound, game_id) values (?, ?, 0, 0, 1);)";
	private PreparedStatement addTournamentPS;
	private static final String FIND_TOURNAMENT_Q = "select name, date, game_id from tournament where id = (select MAX(id) from tournament)";
	private PreparedStatement findTournamentPS;
	private static final String FIND_TOURNAMENT_ID_Q = "select MAX(id) from tournament";
	private PreparedStatement findTournamentIdPS;



	public TournamentDB()
	{
		connection = DBConnection.getInstance().getConnection();
		try 
		{
			addTournamentPS = connection.prepareStatement(ADD_TOURNAMENT_Q);
			findTournamentPS = connection.prepareStatement(FIND_TOURNAMENT_Q);
			findTournamentIdPS = connection.prepareStatement(FIND_TOURNAMENT_ID_Q);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}

	public void addTournament(Tournament tournament) throws WrongInputException
	{
		try
		{
			java.sql.Date date = java.sql.Date.valueOf(tournament.getDate());
			addTournamentPS.setString(1, tournament.getName());
			addTournamentPS.setDate(2, date);
			addTournamentPS.executeUpdate();
		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new WrongInputException(e, "Invalid input.");
		}
	}
	
	public Tournament findTournament() throws MisMatchException 
	{
		Tournament tournament = null;
		ResultSet rs = null;
		try 
		{
			rs = findTournamentPS.executeQuery();
			if(rs.next())
			{
				tournament = buildObject(rs);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tournament;
	}

	public Tournament buildObject(ResultSet rs) throws SQLException, MisMatchException
	{
		Tournament tournament = new Tournament(rs.getString("name"), rs.getDate("date").toLocalDate(), 0, new Game(rs.getInt("game_id")));
		tournament.setId(getTournamentId());
		return tournament;
	}

	public int getTournamentId() throws MisMatchException
	{
		int tournamentId = -1;
		try
		{
			ResultSet rs = findTournamentIdPS.executeQuery();
			if (rs.next())
			{
				tournamentId = rs.getInt(1);
			}
		}
		catch (SQLException e) 
		{
			throw new MisMatchException(e, "turneringen blev ikke fundet i databasen.");
		}
		return tournamentId;
	}
}
